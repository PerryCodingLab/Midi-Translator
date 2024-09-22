package biuoop;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class DialogManagerImpl implements DialogManager {
   private JFrame frame;
   private int width;
   private int height;
   private KeyboardSensorImpl keyboardSensor;

   DialogManagerImpl(JFrame frame, KeyboardSensorImpl keyboardSensor, int width, int height) {
      this.frame = frame;
      this.width = width;
      this.height = height;
      this.keyboardSensor = keyboardSensor;
   }

   public String showQuestionDialog(String title, String question, String defaultValue) {
      this.keyboardSensor.clearPressedButtons();
      final JOptionPane optionPane = new JOptionPane(question, 3, 0, (Icon)null, new String[]{"OK"}, defaultValue);
      optionPane.setWantsInput(true);
      optionPane.setInitialSelectionValue(defaultValue);
      final JDialog dialog = new JDialog(this.frame, title, true);
      dialog.setContentPane(optionPane);
      dialog.setDefaultCloseOperation(0);
      dialog.setResizable(false);
      dialog.setModal(true);
      optionPane.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent e) {
            String prop = e.getPropertyName();
            if (dialog.isVisible() && e.getSource() == optionPane && prop.equals("value")) {
               dialog.setVisible(false);
            }

         }
      });
      dialog.pack();
      dialog.setLocationRelativeTo(this.frame);
      dialog.setLocation(this.width / 2 - dialog.getWidth() / 2, this.height / 2 - dialog.getHeight() / 2);
      dialog.setVisible(true);
      return ((String)optionPane.getInputValue()).trim();
   }

   public void showInformationDialog(String title, String message) {
      this.keyboardSensor.clearPressedButtons();
      JOptionPane.showMessageDialog(this.frame, message, title, 1);
   }

   public void showWarningDialog(String title, String message) {
      this.keyboardSensor.clearPressedButtons();
      JOptionPane.showMessageDialog(this.frame, message, title, 2);
   }

   public void showErrorDialog(String title, String message) {
      this.keyboardSensor.clearPressedButtons();
      JOptionPane.showMessageDialog(this.frame, message, title, 0);
   }

   public boolean showConfirmationDialog(String title, String message) {
      this.keyboardSensor.clearPressedButtons();
      int answer = JOptionPane.showConfirmDialog(this.frame, message, title, 2, 2);
      return answer == 0;
   }

   public boolean showYesNoDialog(String title, String message) {
      this.keyboardSensor.clearPressedButtons();
      int answer = JOptionPane.showConfirmDialog(this.frame, message, title, 0, 3);
      return answer == 0;
   }
}
