package biuoop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI {
   private KeyboardSensorImpl keyboardSensor;
   private DialogManagerImpl dialogManager;
   private RecordingDrawSurface surface;
   private JFrame frame;
   private JPanel mainPanel;
   private JPanel drawingPanel;
   private int width;
   private int height;

   public GUI(String title, int width, int height) {
      this.width = width;
      this.height = height;
      this.frame = new JFrame(title);
      this.mainPanel = new JPanel();
      this.drawingPanel = new GUI.GUIPanel();
      this.frame.setDefaultCloseOperation(3);
      this.frame.setSize(width, height);
      this.frame.setResizable(false);
      this.frame.setVisible(true);
      this.frame.setIgnoreRepaint(true);
      this.frame.setContentPane(this.mainPanel);
      this.frame.setLayout(new BorderLayout());
      this.mainPanel.add(this.drawingPanel, "Center");
      this.mainPanel.setPreferredSize(new Dimension(width, height));
      this.frame.pack();
      this.keyboardSensor = new KeyboardSensorImpl(this.frame);
      this.dialogManager = new DialogManagerImpl(this.frame, this.keyboardSensor, width, height);
      this.surface = new RecordingDrawSurface(this.width, this.height);
   }

   public DialogManager getDialogManager() {
      return this.dialogManager;
   }

   public DrawSurface getDrawSurface() {
      return new RecordingDrawSurface(this.width, this.height);
   }

   public void show(DrawSurface surface) {
      final RecordingDrawSurface recordingDrawSurface = (RecordingDrawSurface)surface;
      if (recordingDrawSurface.isRendered()) {
         throw new DrawSurfaceAlreadyRenderedException("You can not show the same draw surface twice, you must request a new one each time using getDrawSurface()");
      } else {
         recordingDrawSurface.setRendered(true);
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               GUI.this.frame.createBufferStrategy(2);
               BufferStrategy bf = GUI.this.frame.getBufferStrategy();
               Graphics graphics = bf.getDrawGraphics();
               GUI.this.surface = recordingDrawSurface;
               GUI.this.frame.paint(graphics);
               bf.show();
            }
         });
      }
   }

   public KeyboardSensor getKeyboardSensor() {
      return this.keyboardSensor;
   }

   public void close() {
      this.frame.dispatchEvent(new WindowEvent(this.frame, 201));
   }

   private class GUIPanel extends JPanel {
      private GUIPanel() {
      }

      public void paint(Graphics g) {
         if (GUI.this.surface != null) {
            GUI.this.surface.paint(g);
         }

      }

      // $FF: synthetic method
      GUIPanel(Object x1) {
         this();
      }
   }
}
