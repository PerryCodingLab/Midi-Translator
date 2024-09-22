package biuoop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class RecordingDrawSurface implements DrawSurface {
   private List<Command> commands = new ArrayList();
   private int width;
   private int height;
   private boolean rendered;

   public RecordingDrawSurface(int width, int height) {
      this.width = width;
      this.height = height;
      this.rendered = false;
   }

   boolean isRendered() {
      return this.rendered;
   }

   void setRendered(boolean rendered) {
      this.rendered = rendered;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void clear() {
      this.commands.clear();
   }

   private void validateRender() {
      if (this.isRendered()) {
         throw new DrawSurfaceAlreadyRenderedException("You can not use the same surface after show() has been called");
      }
   }

   public void setColor(Color color) {
      this.validateRender();
      if (color.getAlpha() != 255) {
         throw new AlphaChannelNotSupportedException("Alpha may not be used, the color provided had an alpha value of " + color.getAlpha());
      } else {
         this.commands.add(new SetColor(color));
      }
   }

   public void drawLine(int x1, int y1, int x2, int y2) {
      this.validateRender();
      this.commands.add(new DrawLine(x1, y1, x2, y2));
   }

   public void drawOval(int x, int y, int width, int height) {
      this.validateRender();
      this.commands.add(new DrawOval(x, y, width, height));
   }

   public void fillOval(int x, int y, int width, int height) {
      this.validateRender();
      this.commands.add(new FillOval(x, y, width, height));
   }

   public void drawRectangle(int x, int y, int width, int height) {
      this.validateRender();
      this.commands.add(new DrawRectangle(x, y, width, height));
   }

   public void fillRectangle(int x, int y, int width, int height) {
      this.validateRender();
      this.commands.add(new FillRectangle(x, y, width, height));
   }

   public void drawImage(int x, int y, Image image) {
      this.validateRender();
      this.commands.add(new DrawImage(x, y, image));
   }

   public void drawCircle(int x, int y, int r) {
      this.validateRender();
      this.commands.add(new DrawOval(x - r, y - r, r * 2, r * 2));
   }

   public void fillCircle(int x, int y, int r) {
      this.validateRender();
      this.commands.add(new FillOval(x - r, y - r, r * 2, r * 2));
   }

   public void drawText(int x, int y, String text, int fontSize) {
      this.validateRender();
      this.commands.add(new DrawText(x, y, text, fontSize));
   }

   public void drawPolygon(Polygon polygon) {
      this.validateRender();
      this.commands.add(new DrawPolygon(this.clone(polygon)));
   }

   public void fillPolygon(Polygon polygon) {
      this.validateRender();
      this.commands.add(new FillPolygon(this.clone(polygon)));
   }

   private Polygon clone(Polygon polygon) {
      int[] xPoints = Arrays.copyOf(polygon.xpoints, polygon.xpoints.length);
      int[] yPoints = Arrays.copyOf(polygon.ypoints, polygon.ypoints.length);
      return new Polygon(xPoints, yPoints, polygon.npoints);
   }

   public void paint(Graphics g) {
      Iterator i$ = this.commands.iterator();

      while(i$.hasNext()) {
         Command cmd = (Command)i$.next();
         cmd.draw(g);
      }

   }
}
