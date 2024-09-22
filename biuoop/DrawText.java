package biuoop;

import java.awt.Font;
import java.awt.Graphics;

class DrawText implements Command {
   int x;
   int y;
   int fontSize;
   String text;

   public DrawText(int x, int y, String text, int fontSize) {
      this.x = x;
      this.y = y;
      this.text = text;
      this.fontSize = fontSize;
   }

   public void draw(Graphics g) {
      Font currentFont = g.getFont();
      g.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), this.fontSize));
      g.drawString(this.text, this.x, this.y);
   }
}
