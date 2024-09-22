package biuoop;

import java.awt.Graphics;

class DrawOval implements Command {
   int x;
   int y;
   int w;
   int h;

   public DrawOval(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
   }

   public void draw(Graphics g) {
      g.drawOval(this.x, this.y, this.w, this.h);
   }
}
