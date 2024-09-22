package biuoop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

class KeyboardSensorImpl implements KeyListener, KeyboardSensor {
   private static final Map<Integer, String> KEY_CODE_TO_BUTTON_MAP = Collections.unmodifiableMap(new HashMap<Integer, String>() {
      {
         this.put(37, "left");
         this.put(39, "right");
         this.put(38, "up");
         this.put(40, "down");
         this.put(32, "space");
         this.put(10, "enter");
         this.put(8, "return");
      }
   });
   private Set<String> pressedButtons = Collections.synchronizedSet(new HashSet());

   public KeyboardSensorImpl(JFrame frame) {
      frame.addKeyListener(this);
   }

   public void clearPressedButtons() {
      this.pressedButtons.clear();
   }

   public void keyTyped(KeyEvent keyEvent) {
   }

   public void keyPressed(KeyEvent keyEvent) {
      if (KEY_CODE_TO_BUTTON_MAP.keySet().contains(keyEvent.getKeyCode())) {
         this.pressedButtons.add(KEY_CODE_TO_BUTTON_MAP.get(keyEvent.getKeyCode()));
      } else {
         this.pressedButtons.add(String.valueOf(keyEvent.getKeyChar()));
      }

   }

   public void keyReleased(KeyEvent keyEvent) {
      if (KEY_CODE_TO_BUTTON_MAP.keySet().contains(keyEvent.getKeyCode())) {
         this.pressedButtons.remove(KEY_CODE_TO_BUTTON_MAP.get(keyEvent.getKeyCode()));
      } else {
         this.pressedButtons.remove(String.valueOf(keyEvent.getKeyChar()));
      }

   }

   public boolean isPressed(String c) {
      return this.pressedButtons.contains(c);
   }
}
