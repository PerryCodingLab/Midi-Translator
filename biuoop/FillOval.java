package biuoop;

import java.awt.Graphics;

class FillOval implements Command {
   int x;
   int y;
   int w;
   int h;

   public FillOval(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
   }

   public void draw(Graphics g) {
      g.fillOval(this.x, this.y, this.w, this.h);
   }
}
