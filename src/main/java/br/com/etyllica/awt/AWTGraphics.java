package br.com.etyllica.awt;

import br.com.etyllica.awt.camera.Camera;
import br.com.etyllica.awt.helper.ColorHelper;
import br.com.etyllica.awt.helper.TransformHelper;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.commons.layer.GeometricLayer;
import br.com.etyllica.commons.layer.Layer;
import br.com.etyllica.linear.Line2D;
import br.com.etyllica.linear.Point2D;
import br.com.etyllica.commons.math.Vector2i;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

/**
 *
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AWTGraphics implements Graphics {

	private VolatileImage vimg;

	protected Graphics2D screen;

	private int width;
	private int height;

	private Color shadowColor = Color.BLACK;

	private static final Rectangle CLIP = new Rectangle();

	//Identity matrix
	private static final AffineTransform RESET_TRANSFORM = AffineTransform.getScaleInstance(1, 1);

	public AWTGraphics(int width, int height) {
		super();

		this.width = width;
		this.height = height;
	}

	public AWTGraphics(BufferedImage image) {
		super();
		setImage(image);
	}

	public void setFastImage(BufferedImage image) {
		this.screen = (Graphics2D) image.getGraphics();

		if(screen == null)
			screen = image.createGraphics();

		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public void setImage(BufferedImage image) {
		setFastImage(image);

		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	public void setVolatileImage(VolatileImage vimg) {

		this.vimg = vimg;
		this.width = vimg.getWidth();
		this.height = vimg.getHeight();

		this.screen = vimg.createGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	public void resetImage() {
		if(vimg == null)
			return;

		this.screen = vimg.createGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(shadowColor);
	}

	/*public void setBufferedImage(BufferedImage bimg) {
		this.width = bimg.getWidth();
		this.height = bimg.getHeight();

		this.bimg = bimg;
		this.screen = (Graphics2D)bimg.getGraphics();
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.screen.setColor(Color.BLACK);

	}*/

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawString(String text, int x, int y, int w, int h) {

		int dx = centralizeTextX(text, x, w);
		int dy = centralizeTextY(text, y, h);

		screen.drawString(text, dx, dy);
	}

	public void drawString(String text, GeometricLayer layer) {
		drawString(text, layer.getX(), layer.getY(), layer.getW(), layer.getH());
	}

	public void drawString(String text, GeometricLayer layer, int x, int y) {
		drawString(text, layer.getX()+x, layer.getY()+y, layer.getW(), layer.getH());
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawString(String text, float x, float y, float w, float h) {
		this.drawString(text, (int) x, (int) y, (int) w, (int) h);
	}

	/**
	 *
	 * @param text
	 * @param exponent
	 * @param x
	 * @param y
	 */
	public void drawStringExponent(String text, String exponent, int x, int y) {
		this.drawString(text, x, y);

		FontMetrics fm = screen.getFontMetrics();

		float lastSize = fm.getFont().getSize2D();

		float h = lastSize*0.7f;

		int w = fm.stringWidth(text);

		this.setFontSize(h);
		this.drawString(exponent, x+w, (int)(y-h*0.5f));

		this.setFontSize(lastSize);
	}

	public void drawStringExponentShadow(String text, String exponent, int x, int y) {
		this.drawStringShadow(text, x, y);

		FontMetrics fm = screen.getFontMetrics();

		float lastSize = fm.getFont().getSize2D();

		float h = lastSize*0.7f;

		int w = fm.stringWidth(text);

		this.setFontSize(h);
		this.drawStringShadow(exponent, x+w, (int)(y-h*0.5f));

		this.setFontSize(lastSize);
	}

	public void drawStringShadow(String text, int x, int y, int w, int h) {
		drawStringShadow(text, x, y, w, h, shadowColor);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param shadowColor
	 */
	public void drawStringShadow(String text, int x, int y, int w, int h, Color shadowColor) {
		int dx = centralizeTextX(text, x, w);
		int dy = centralizeTextY(text, y, h);

		drawStringShadow(text, dx, dy,shadowColor);
	}

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param w
     * @param h
     * @param shadowColor
     */
    public void drawStringShadow(String text, int x, int y, int w, int h, br.com.etyllica.commons.graphics.Color shadowColor) {
        drawStringShadow(text, x, y, w, h, ColorHelper.convert(shadowColor));
    }


	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param shadowColor
	 */
	public void drawStringShadow(String text, float x, float y, float w, float h, Color shadowColor) {
		this.drawStringShadow(text, (int)x, (int)y, (int)w, (int)h, shadowColor);
	}

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param w
     * @param h
     * @param shadowColor
     */
    public void drawStringShadow(String text, float x, float y, float w, float h, br.com.etyllica.commons.graphics.Color shadowColor) {
        drawStringShadow(text, x, y, w, h, ColorHelper.convert(shadowColor));
    }

	/**
	 *
	 * @param text
	 * @param offsetX
	 * @param y
	 * @param border
	 */
	public void drawStringX(String text, float offsetX, float y, boolean border) {

		/*if((text==null)||(text.isEmpty())) {
			return;
		}*/

		FontMetrics fm = screen.getFontMetrics();

		float x = centralizeTextX(text)+offsetX;
		float fy = y+fm.getHeight();

		if(!border) {
			screen.drawString(text,x,fy);
		} else {
			drawStringBorder(text, x, fy);
		}
	}

	public void drawStringBorder(String text, int x, int y, int w, int h) {
		int dx = centralizeTextX(text, x, w);
		int dy = centralizeTextY(text, y, h);

		drawStringBorder(text, dx, dy);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawStringBorder(String text, float x, float y) {

		/*if((text==null)||(text.isEmpty())) {
			return;
		}*/

		Font f = getFont();

		FontRenderContext frc = screen.getFontRenderContext();

		TextLayout tl = new TextLayout(text, f, frc);

		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x,y));

		Color standardColor = screen.getColor();

		screen.setColor(shadowColor);
		screen.draw(sha);

		screen.setColor(standardColor);
		screen.fill(sha);
	}

	/**
	 *
	 * @param text
	 * @param y
	 */
	public void drawStringBorderX(String text, float y) {

		int x = centralizeTextX(text);

		drawStringBorder(text, x, y);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawStringShadow(String text, int x, int y) {
		drawStringShadow(text, x, y, shadowColor);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawStringShadow(String text, float x, float y) {
		this.drawStringShadow(text, x, y, shadowColor);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param shadowColor
	 */
	public void drawStringShadow(String text, int x, int y, Color shadowColor) {
		Color lastColor = screen.getColor();

		screen.setColor(shadowColor);
		screen.drawString(text,x+1,y+1);
		screen.setColor(lastColor);
		screen.drawString(text,x,y);
	}

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param shadowColor
     */
	public void drawStringShadow(String text, int x, int y, br.com.etyllica.commons.graphics.Color shadowColor) {
		drawStringShadow(text,x,y,ColorHelper.convert(shadowColor));
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 * @param shadowColor
	 */
	public void drawStringShadow(String text, float x, float y, Color shadowColor) {
		this.drawStringShadow(text, (int)x, (int)y, shadowColor);
	}

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param shadowColor
     */
    public void drawStringShadow(String text, float x, float y, br.com.etyllica.commons.graphics.Color shadowColor) {
        drawStringShadow(text,x,y,ColorHelper.convert(shadowColor));
    }

	/**
	 *
	 * @param text
	 * @param y
	 */
	public void drawStringShadowX(String text, int y) {
		FontMetrics fm = screen.getFontMetrics();

		int x = centralizeTextX(text);
		int fy = y+fm.getHeight();

		drawStringShadow(text, x, fy);
	}

	private int centralizeTextX(String text) {
		return centralizeTextX(text, 0, width);
	}



	private int centralizeTextX(String text, int x, int w) {
		int textWidth = textWidth(text);

		int dx = x + w/2 - textWidth/2;

		return dx;
	}

	public int textWidth(String text) {
		FontMetrics fm = screen.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		return textWidth;
	}

	private int centralizeTextY(String text) {
		return centralizeTextY(text, 0, height);
	}

	private int centralizeTextY(String text, int y, int h) {
		FontMetrics fm = screen.getFontMetrics();

		int ascent = fm.getMaxAscent();
		int descent= fm.getMaxDescent();

		int dy = y+h/2 - descent/2 + ascent/2;

		return dy;
	}


	/**
	 *
	 * @param text
	 * @param y
	 */
	public void drawStringShadowX(String text, float y) {
		this.drawStringShadowX(text, (int)y);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawString(String text, float x, float y) {
		//if((text!=null)&&(!text.isEmpty())) {
		screen.drawString(text, x, y);
		//}
	}

	public void drawStringShadow(String text, GeometricLayer layer) {
		//if((text!=null)&&(!text.isEmpty())) {
		drawStringShadow(text, layer.getX(), layer.getY(), layer.getW(), layer.getH());
		//}
	}

	/**
	 *
	 * @param text
	 * @param y
	 */
	public void drawStringX(String text, int y) {
		drawStringX(text, 0, y, false);
	}

	/**
	 *
	 * @param text
	 * @param offsetX
	 * @param y
	 */
	public void drawStringX(String text, float offsetX, float y) {
		drawStringX(text, offsetX, y, false);
	}

	/**
	 *
	 * @param text
	 * @param offsetX
	 * @param y
	 */
	public void drawStringX(String text, int offsetX, int y) {
		drawStringX(text, offsetX, y, false);
	}

	public void drawStringX(String text, float y) {
		drawStringX(text, 0, y, false);
	}

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
	public void drawImage (Image img, int dx1, int dy1,int dx2, int dy2, int sx1 , int sy1, int sx2, int sy2, ImageObserver observer ) {
		screen.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void drawImage (Image img, float dx1, float dy1, float dx2, float dy2, float sx1 , float sy1, float sx2, float sy2, ImageObserver observer ) {
		drawImage(img, (int)dx1, (int)dy1, (int)dx2, (int)dy2, (int)sx1, (int)sy1, (int)sx2, (int)sy2, observer);
	}


	public Graphics2D getGraphics() {
		return screen;
	}

	/**
	 *
	 * @param layer
	 */
	public void drawRect(GeometricLayer layer) {
		drawRect(layer.getX(), layer.getY(), layer.getW(), layer.getH());
	}

	/**
	 *
	 * @param layer
	 */
	public void drawRect(Layer layer) {

		AffineTransform transform = TransformHelper.getTransform(layer);

		if (transform == null) {
			drawRect(layer.getX(), layer.getY(), layer.getW(), layer.getH());
		} else {
			setTransform(transform);
			drawRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
			resetTransform();
		}
	}

	/**
	 *
	 * @param layer
	 */
	public void fillOval(GeometricLayer layer) {
		screen.fillOval((int)layer.getX(), (int)layer.getY(), (int)layer.getW(), (int)layer.getH());
	}

	/**
	 *
	 * @param layer
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(GeometricLayer layer, int startAngle, int arcAngle) {
		screen.fillArc((int)layer.getX(), (int)layer.getY(), (int)layer.getW(), (int)layer.getH(), startAngle, arcAngle);
	}

	/** Delegated Methods */
	/**
	 *
	 * @param width
	 */
	public void setLineWidth(float width) {
		screen.setStroke(new BasicStroke(width));
	}

	public AffineTransform getTransform() {
		return screen.getTransform();
	}

	/**
	 *
	 * @param tx
	 */
	public void setTransform(AffineTransform tx) {
		//Avoid Java Bug
		if(screen != null) {
			screen.setTransform(tx);
		}
	}

	/**
	 *
	 * @param tx
	 */
	public void transform(AffineTransform tx) {
		//Avoid Java Bug
		if(screen!=null) {
			screen.transform(tx);
		}
	}

	public void resetTransform() {
		setTransform(RESET_TRANSFORM);
	}

	/**
	 * Set basic stroke with width 1f
	 */
	public void resetStroke() {
		screen.setStroke(new BasicStroke(1f));
	}

	/**
	 *
	 * @param stroke
	 */
	public void setStroke(Stroke stroke) {
		screen.setStroke(stroke);
	}

	/**
	 *
	 * @param font
	 */
	public void setFont(br.com.etyllica.core.graphics.Font font) {
		screen.setFont(font.getFont());
	}

	/**
	 *
	 * @param font
	 */
	public void setFont(Font font) {
		screen.setFont(font);
	}

	public Font getFont() {
		return screen.getFont();
	}

	public FontRenderContext getFontRenderContext() {
		return screen.getFontRenderContext();
	}

	/**
	 *
	 * @param color
	 */
	public void setColor(int color) {
		screen.setColor(new Color(color));
	}

	/**
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		screen.setColor(color);
	}

	/**
	 *
	 * @param color
	 */
	public void setColor(br.com.etyllica.commons.graphics.Color color) {
		screen.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
	}

	public void setFontSize(float size) {
		screen.setFont(screen.getFont().deriveFont(size));
	}

	/**
	 *
	 * @param percent
	 */
	public void setAlpha(int percent) {
		float alpha = (float)percent/100;
		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	public void setComposite(AlphaComposite composite) {
		screen.setComposite(composite);
	}

	public void setClearAlpha(int percent) {
		float alpha = (float)percent/100;
		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, alpha));
	}

	/**
	 *
	 * @param opacity
	 */
	public void setOpacity(int opacity) {

		float a = (float)opacity/255;

		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));

	}

	public void resetAlpha() {
		resetOpacity();
	}

	public void resetOpacity() {
		float a = 1f;
		screen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
	}

	/**
	 *
	 * @param img
	 * @param x
	 * @param y
	 */
	public void drawImage(Image img, int x, int y) {
		screen.drawImage(img, x, y, null);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
		screen.drawArc(x, y, w, h, startAngle, arcAngle);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(float x, float y, float w, float h, int startAngle, int arcAngle) {
		this.drawArc((int)x, (int)y, (int)w, (int)h, startAngle, arcAngle);
	}

	/**
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1,int y1,int x2,int y2) {
		screen.drawLine(x1, y1, x2, y2);
	}

	/**
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(float x1,float y1,float x2,float y2) {
		this.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	/**
	 *
	 * @param p
	 * @param q
	 */
	public void drawLine(Point2D p, Point2D q) {
		screen.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
	}

	/**
	 *
	 * @param line
	 */
	@Override
	public void drawLine(Line2D line) {
		this.drawLine(line.getP1(), line.getP2());
	}

	/**
	 *
	 * @param polygon
	 */
	public void drawPolygon(Polygon polygon) {
		screen.drawPolygon(polygon);
	}

	/**
	 *
	 * @param polygon
	 */
	public void fillPolygon(Polygon polygon) {
		screen.fillPolygon(polygon);
	}

	/**
	 *
	 * @param layer
	 */
	public void fillRect(GeometricLayer layer) {
		fillRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
	}

	public void fillRect(Layer layer) {

		AffineTransform transform = TransformHelper.getTransform(layer);

		if (transform == null) {
			fillRect(layer.getX(), layer.getY(), layer.getW(), layer.getH());
		} else {
			setTransform(transform);
			fillRect(layer.getX(),layer.getY(),layer.getW(),layer.getH());
			resetTransform();
		}
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(int x, int y, int w, int h) {
		screen.fillRect(x,y,w,h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillRect(float x, float y, float w, float h) {
		screen.fillRect((int)x,(int)y,(int)w,(int)h);
	}


	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(int x, int y, int w, int h, boolean raised) {
		screen.fill3DRect(x, y, w, h, raised);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param raised
	 */
	public void fill3DRect(float x, float y, float w, float h, boolean raised) {
		this.fill3DRect((int)x, (int)y, (int)w, (int)h, raised);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
		screen.fillArc(x, y, w, h, startAngle, arcAngle);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param startAngle
	 * @param arcAngle
	 */
	public void fillArc(float x, float y, float w, float h, int startAngle, int arcAngle) {
		this.fillArc((int)x, (int)y, (int)w, (int)h, startAngle, arcAngle);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(int x, int y, int w, int h) {
		screen.drawRect(x,y,w,h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawRect(double x, double y, double w, double h) {
		screen.drawRect((int)x,(int)y,(int)w,(int)h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		screen.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		screen.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(int x, int y, int w, int h ) {
		screen.drawOval(x,y,w,h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawOval(float x, float y, float w, float h ) {
		this.drawOval((int)x,(int)y,(int)w,(int)h);
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(int cx, int cy, int radius ) {
		screen.drawOval(cx-radius, cy-radius, radius*2, radius*2);
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(float cx, float cy, float radius ) {
		this.drawCircle((int)cx, (int)cy, (int)radius);
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void drawCircle(double cx, double cy, double radius ) {
		this.drawCircle((int)cx, (int)cy, (int)radius);
	}

	/**
	 *
	 * @param point
	 * @param radius
	 */
	public void drawCircle(Point2D point, int radius ) {
		screen.drawOval((int)point.getX()-radius, (int)point.getY()-radius, radius*2, radius*2);
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(int cx, int cy, int radius ) {
		screen.fillOval(cx-radius, cy-radius, radius*2, radius*2);
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(float cx, float cy, float radius ) {
		screen.fillOval((int)(cx-radius), (int)(cy-radius), (int)(radius*2), (int)(radius*2));
	}

	/**
	 *
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public void fillCircle(double cx, double cy, double radius ) {
		screen.fillOval((int)(cx-radius), (int)(cy-radius), (int)(radius*2), (int)(radius*2));
	}

	/**
	 *
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Point2D point, int radius ) {
		screen.fillOval((int)point.getX()-radius, (int)point.getY()-radius, radius*2, radius*2);
	}

	/**
	 *
	 * @param point
	 * @param radius
	 */
	public void fillCircle(Vector2i point, int radius) {
		screen.fillOval(point.getX()-radius, point.getY()-radius, radius*2, radius*2);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(int x, int y, int w, int h ) {
		screen.fillOval(x, y, w, h);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void fillOval(float x, float y, float w, float h ) {
		this.fillOval((int)x, (int)y, (int)w, (int)h);
	}

	/**
	 *
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawString(String text, int x, int y) {
		screen.drawString(text, x, y);
	}

	/**
	 *
	 * @param shape
	 */
	public void draw(Shape shape) {
		screen.draw(shape);
	}

	/**
	 *
	 * @param shape
	 */
	public void fill(Shape shape) {
		screen.fill(shape);
	}

	public FontMetrics getFontMetrics() {
		return screen.getFontMetrics();
	}

	/*public void setGraphics(GLGraphics2D graphics) {		
		this.screen = graphics;
		this.screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}*/

	public BufferedImage getBimg() {
		return vimg.getSnapshot();
	}

	public VolatileImage getVimg() {
		return vimg;
	}

	/*
	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		vimg.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}
	*/

	public void drawImage(BufferedImage image, int x, int y) {
		screen.drawImage(image, x, y, null);
	}

	public void drawImage(BufferedImage image, float x, float y) {
		screen.drawImage(image, (int)x, (int)y, null);
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	public void translate(int x, int y) {
		screen.translate(x, y);
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	public void translate(double x, double y) {
		screen.translate(x, y);
	}

	public void rotate(double angle) {
		screen.rotate(angle);
	}

	public void setBackground(Color color) {
		screen.setBackground(color);
	}

	public void clearRect(int x, int y, int width, int height) {
		screen.clearRect(x, y, width, height);
	}

	public void setPaint(Paint paint) {
		screen.setPaint(paint);
	}

	public void resetPaint() {
		screen.setPaint(screen.getColor());
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}

	public void dispose() {
		screen.dispose();
	}

	public void setCamera(Camera camera) {
		camera.resetImage();
		setImage(camera.getBuffer());
	}

	public void resetCamera(Camera camera) {
		resetImage();
	}

	public void drawArrow(Point2D p, Point2D q, int arrowSize) {
		double pq = p.distance(q);

		int arrowAngle = 30;

		Point2D p1 = p.distantPoint(q, pq+arrowSize);
		Point2D p2 = new Point2D(p1.getX(), p1.getY());

		p1.rotate(q, 180-arrowAngle);
		p2.rotate(q, 180+arrowAngle);

		drawLine(p, q);
		drawLine(q, p1);
		drawLine(q, p2);
	}

	public void drawArrow(Point2D p, Point2D q) {
		drawArrow(p, q, 25);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void setClip(int x, int y, int w, int h) {
		CLIP.x = x;
		CLIP.y = y;
		CLIP.width = w;
		CLIP.height = h;

		screen.setClip(CLIP);
	}

	@Override
	public void resetClip() {
		screen.setClip(null);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		screen.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		screen.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void beginImageBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endImageBatch() {
		// TODO Auto-generated method stub

	}

}