package biuoop;

import java.awt.Graphics;
import java.awt.Polygon;

class DrawPolygon implements Command {
   Polygon polygon;

   DrawPolygon(Polygon polygon) {
      this.polygon = polygon;
   }

   public void draw(Graphics g) {
      g.drawPolygon(this.polygon);
   }
}
