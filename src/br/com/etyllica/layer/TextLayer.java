package br.com.etyllica.layer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextLayer extends ImageLayer{

	private String text;

	private int style = Font.PLAIN;
	private float size = 26;
	
	private Font font = null;
	private String fontName = "";

	private Color color;
	
	private Color borderColor;

	private boolean border = false;
	
	private float borderWidth = 4f;

	private boolean antiAliased = true;

	private boolean fractionalMetrics = false;

	public TextLayer(String text){
		this(0,0,text);
	}

	public TextLayer(int x, int y, String text){
		super(x,y);

		this.color = new Color(0xff,0xff,0xff);
		this.borderColor = new Color(0,0,0);

		setText(text);

	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		
		this.font = FontLoader.getInstance().loadFont(fontName).deriveFont(style,size);		
	}

	@Override
	public void draw(Grafico g){
				
		if(this.visible){
			
			Font f;
			
			if(this.font!=null){
				
				f = font;
				
			}else{
				
				f = g.getFont().deriveFont(style, size);
			}
			
			g.setFont(f);
						
			if(!border){
				
				g.setColor(color);
				g.drawString(text,x,y);
								
			}else{
				
				FontRenderContext frc = new FontRenderContext(null, antiAliased, fractionalMetrics);
				TextLayout layout = new TextLayout(text, f, frc);

				//Rectangle2D bounds = layout.getBounds();
				
				//float height = (float) Math.ceil(bounds.getHeight()+size);
				
				Shape sha = layout.getOutline(AffineTransform.getTranslateInstance(x,y));

				g.setStroke(new BasicStroke(borderWidth));

				g.setColor(borderColor);
				g.draw(sha);

				g.setColor(color);
				g.fill(sha);
			
			}
			
		}
		
	}
	
	public void setColor(int r, int g, int b){
		color = new Color(r%256,g%256,b%256);
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getColor() {
		return color;
	}

	public void setText(String text) {
		if(!text.isEmpty())
			this.text = text;
		else
			this.text = " ";
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public boolean isBorder(){
		return border;
	}

	public String getText(){
		return text;
	}

	public boolean isAntiAliased() {
		return antiAliased;
	}

	public void setAntiAliased(boolean antiAliased) {
		this.antiAliased = antiAliased;
	}

	public boolean isFractionalMetrics() {
		return fractionalMetrics;
	}

	public void setFractionalMetrics(boolean fractionalMetrics) {
		this.fractionalMetrics = fractionalMetrics;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
	
}