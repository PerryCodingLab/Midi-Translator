package biuoop;

import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;

public interface DrawSurface {
   int getWidth();

   int getHeight();

   void setColor(Color var1) throws AlphaChannelNotSupportedException;

   void drawLine(int var1, int var2, int var3, int var4);

   void drawOval(int var1, int var2, int var3, int var4);

   void fillOval(int var1, int var2, int var3, int var4);

   void drawRectangle(int var1, int var2, int var3, int var4);

   void fillRectangle(int var1, int var2, int var3, int var4);

   void drawImage(int var1, int var2, Image var3);

   void drawCircle(int var1, int var2, int var3);

   void fillCircle(int var1, int var2, int var3);

   void drawText(int var1, int var2, String var3, int var4);

   void drawPolygon(Polygon var1);

   void fillPolygon(Polygon var1);
}
