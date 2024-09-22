package biuoop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

class DrawImage implements Command {
   int x;
   int y;
   Image image;

   public DrawImage(int x, int y, Image image) {
      this.x = x;
      this.y = y;
      this.image = image;
   }

   public void draw(Graphics g) {
      g.drawImage(this.image, this.x, this.y, (ImageObserver)null);
   }
}
