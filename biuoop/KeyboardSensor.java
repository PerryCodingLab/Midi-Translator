package biuoop;

public interface KeyboardSensor {
   String LEFT_KEY = "left";
   String RIGHT_KEY = "right";
   String UP_KEY = "up";
   String DOWN_KEY = "down";
   String SPACE_KEY = "space";
   String ENTER_KEY = "enter";
   String RETURN_KEY = "return";

   boolean isPressed(String var1);
}
