package br.com.etyllica.animation.pivot;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.loader.image.ImageLoader;
import br.com.etyllica.layer.PivotImageLayer;

public class Part extends PivotImageLayer {

	protected List<PivotPoint> points = new ArrayList<PivotPoint>();

	private Color borderColor = Color.BLUE;

	public Part(int x, int y) {
		super(x,y);
	}

	public Part(int x, int y, String caminho) {
		super(x,y,caminho);
	}
	
	public Part(String caminho) {
		super(0,0,caminho);
	}

	public void addPoint(PivotPoint p) {
		this.addPoint((int)p.getX(),(int)p.getY());
	}

	public void addPoint(int px, int py) {
		points.add(new PivotPoint(x+px,y+py));
	}
	
	public void rotate(double angle) {
				
		double diffAngle = angle-this.angle;
		
		for(PivotPoint point: points) {
						
			double cx = x+xPivot;
			double cy = y+yPivot;
			
			point.rotate(cx, cy, diffAngle);
		}

		setAngle(angle);
	}
	
	public void rotateByParent(double angle) {
				
		for(PivotPoint point: points) {
			
			double cx = x+xPivot;
			double cy = y+yPivot;
			
			point.rotate(cx, cy, angle);
			
		}
		
		setOffsetAngle(angle);		
	}

	public AffineTransform getTransform() {
		return AffineTransform.getRotateInstance(Math.toRadians(angle),x+xPivot, y+yPivot);
	}
	
	@Override
	public void draw(Graphic g) {
		if(visible) {

			AffineTransform reset = g.getTransform();

			g.setTransform(getTransform());
			g.drawImage( ImageLoader.getInstance().getImage(path), (int)x, (int)y, (int)(x+w), (int)(y+h),
					xImage,yImage,xImage+(int)w,yImage+(int)h, null );

			g.setTransform(reset);
			
		}
	}	
	
	@Override
	public void setOffsetX(int x) {
		
		this.x += x;

		for(PivotPoint point: points) {
			point.setX(point.getX()+x);
		}
		
	}
	
	@Override
	public void setOffsetY(int y) {
		
		this.y += y;

		for(PivotPoint point: points) {
			point.setY(point.getY()+y);
		}
		
	}
	
	/*@Override
	public void setOffset(int x, int y) {
		
		
		this.x += x;
		this.y += y;

		for(PivotPoint point: points) {
			point.setX(point.getX()+x);
			point.setY(point.getY()+y);
		}
		
	}*/
	
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public List<PivotPoint> getPoints() {
		return points;
	}

	public void setPontos(List<PivotPoint> pontos) {
		this.points = pontos;
	}
		
}