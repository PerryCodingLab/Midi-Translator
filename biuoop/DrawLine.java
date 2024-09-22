package biuoop;

import java.awt.Graphics;

class DrawLine implements Command {
   int x1;
   int x2;
   int y1;
   int y2;

   public DrawLine(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
   }

   public void draw(Graphics g) {
      g.drawLine(this.x1, this.y1, this.x2, this.y2);
   }
}
