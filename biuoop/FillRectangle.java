package biuoop;

import java.awt.Graphics;

class FillRectangle implements Command {
   int x;
   int y;
   int w;
   int h;

   public FillRectangle(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
   }

   public void draw(Graphics g) {
      g.fillRect(this.x, this.y, this.w, this.h);
   }
}
