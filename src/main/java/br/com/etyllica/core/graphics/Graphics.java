package br.com.etyllica.core.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.core.linear.PointInt2D;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 *
 */

public interface Graphics {

	public void setFastImage(BufferedImage image);

	public void setImage(BufferedImage image);

	public void resetImage();

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(int x, int y, int w, int h, String text);
	
	public void drawString(GeometricLayer layer, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(float x, float y, float w, float h, String text);

	/**
	 * @param x
	 * @param y
	 * @param text
	 * @param exponent
	 */
	public void drawStringExponent(String text, String exponent, int x, int y);

	public void drawStringExponentShadow(String text, String exponent, int x, int y);

	public void drawStringShadow(int x, int y, int w, int h, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(int x, int y, int w, int h, String text, Color shadowColor);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(float x, float y, float w, float h, String text, Color shadowColor);

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void writeX(float offsetX, float y, String text, boolean border);

	public void drawStringBorder(String text, int x, int y, int w, int h);
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorder(String text, float x, float y);
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorderX(String text, float y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawShadow(int x, int y, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawShadow(float x, float y, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawShadow(int x, int y, String text, Color shadowColor);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawShadow(float x, float y, String text, Color shadowColor);

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(int y, String text);

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(float y, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void write(float x, float y, String text);

	public void write(String text, GeometricLayer layer);

	public void writeShadow(String text, GeometricLayer layer);

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void escreveX(int y, String text);

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 */
	public void escreveX(int offsetX, int y, String text);

	/**
	 * 
	 * @param y
	 * @param text
	 * @param borda
	 */
	public void writeX(float y, String text, boolean borda);

	public void writeX(float y, String text);

	/*
	 * Write Methods
	 */

	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 */
	public void escreveXCustom(int offsetX, int y, String text);

	/**
	 * 
	 * @param img
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param sx1
	 * @param sy1
	 * @param sx2
	 * @param sy2
	 * @param observer
	 */
	public void drawImage( Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer);

	public void drawImage( Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer);

	public Graphics2D getGraphics();

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 */
	public void drawRect(Layer layer);

	/**
	 * 
	 * @param layer
	 */
	public void fillOval(GeometricLayer layer);

	/**
	 * 
	 * @param layer
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(GeometricLayer layer, int startAngle, int arcAngle);

	/**
	 * 
	 * @param width
	 */
	public void setLineWidth(float width);

	public AffineTransform getTransform();

	/**
	 * 
	 * @param tx
	 */
	public void setTransform(AffineTransform tx);

	/**
	 * 
	 * @param tx
	 */
	public void transform(AffineTransform tx);

	public void resetTransform();

	/**
	 * Set basic stroke with width 1f 
	 */
	public void resetStroke();

	/**
	 * 
	 * @param stroke
	 */
	public void setStroke(Stroke stroke);

	/**
	 * 
	 * @param font
	 */
	public void setFont(Font font);

	public Font getFont();

	public FontRenderContext getFontRenderContext();

	/**
	 * 
	 * @param color
	 */
	public void setColor(int color) ;

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color);

	public void setFontSize(float size);

	/**
	 * 
	 * @param percent
	 */
	public void setAlpha(int percent);

	public void setComposite(AlphaComposite composite);

	public void setClearAlpha(int percent);

	/**
	 * 
	 * @param opacity
	 */
	public void setOpacity(int opacity);

	public void resetOpacity();

	/**
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	public void drawImage(Image img, int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1,int y1,int x2,int y2);

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(float x1,float y1,float x2,float y2);

	/**
	 * 
	 * @param p
	 * @param q
	 */
	public void drawLine(Point2D p, Point2D q);

	/**
	 * 
	 * @param polygon
	 */
	public void drawPolygon(Polygon polygon);

	/**
	 * 
	 * @param polygon
	 */
	public void fillPolygon(Polygon polygon);

	/**
	 * 
	 * @param layer
	 */
	public void fillRect(GeometricLayer layer);

	public void fillRect(Layer layer);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(float x, float y, float w, float h);


	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(int x, int y, int w, int h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(float x, float y, float w, float h, boolean raised);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(float x, float y, float w, float h, int startAngle, int arcAngle);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(double x, double y, double w, double h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(float x, float y, float w, float h);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void drawCircle(Point2D point, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(int cx, int cy, int radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(float cx, float cy, float radius);

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(double cx, double cy, double radius);

	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Point2D point, int radius);
	
	/**
	 * 
	 * @param point
	 * @param radius
	 */
	public void fillCircle(PointInt2D point, int radius);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(int x, int y, int w, int h);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(float x, float y, float w, float h);

	/**
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawString(String text, int x, int y);

	/**
	 * 
	 * @param shape
	 */
	public void draw(Shape shape);

	/**
	 * 
	 * @param shape
	 */
	public void fill(Shape shape);

	public FontMetrics getFontMetrics();

	/*public void setGraphics(GLGraphics2D graphics) {		
		this.screen = graphics;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}*/

	public BufferedImage getBimg();

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param w
	 * @param h
	 * @param rgbArray
	 * @param offset
	 * @param scansize
	 */
	/*public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {

		vimg..setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}*/

	public void drawImage(BufferedImage image, int x, int y);

	public void drawImage(BufferedImage image, float x, float y);

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(double x, double y);

	public void rotate(double angle);

	public void setBackground(Color color);

	public void clearRect(int x, int y, int width, int height);

	public void setPaint(Paint paint);

	public void resetPaint();
	
	public void setShadowColor(Color shadowColor);
	
	public void dispose();

	public void setCamera(Camera camera);

	public void resetCamera(Camera camera);

	public void drawArrow(Point2D p, Point2D q, int arrowSize);
	
	public void drawArrow(Point2D p, Point2D q);

	public void setClip(int x, int y, int w, int h);
	
	public void resetClip();
	
}