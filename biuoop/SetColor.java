package biuoop;

import java.awt.Color;
import java.awt.Graphics;

class SetColor implements Command {
   Color color;

   public SetColor(Color color) {
      this.color = color;
   }

   public void draw(Graphics g) {
      g.setColor(this.color);
   }
}
