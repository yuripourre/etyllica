package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.linear.Rectangle;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class ImageLayer extends StaticLayer{

	protected int xImage = 0;
	protected int yImage = 0;

	protected double angle = 0;

	protected ColisionArea areaColisao = null;

	public ImageLayer(){
		super();
	}

	public ImageLayer(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}

	public ImageLayer(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	public ImageLayer(int x, int y, int w, int h, String caminho){
		super(x,y,w,h,caminho);
	}

	public ImageLayer(int x, int y, int w, int h, int xImagem, int yImagem, String caminho){
		super(x,y,w,h,caminho);
		this.xImage = xImagem;
		this.yImage = yImagem;
	}

	public ImageLayer(int x, int y, String caminho){
		super(caminho);
		setCoordenadas(x, y);
	}

	public ImageLayer(String caminho){
		this(0,0,caminho);
	}

	public int getXImage() {
		return xImage;
	}

	public void setXImage(int imagem) {
		xImage = imagem;
	}

	public int getYImage() {
		return yImage;
	}

	public void setYImage(int imagem) {
		yImage = imagem;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setOffsetAngle(double offset) {
		setAngle(angle+offset);
	}

	public ColisionArea getAreaColisao() {
		return areaColisao;
	}

	public void setAreaColisao(ColisionArea areaColisao) {
		this.areaColisao = areaColisao;
	}

	public void setCoordenadas(int x , int y) {
		this.x = x;
		this.y = y;
	}

	public void setCoordImagem(int xImagem , int yImagem) {
		this.xImage = xImagem;
		this.yImage = yImagem;
	}	


	public void centraliza(int x, int y, int xLimite, int yLimite){
		centralizaX(x,x+xLimite);
		centralizaY(y,y+yLimite);
	}

	public void centraliza(ImageLayer cam){
		centralizaX(cam);
		centralizaY(cam);
	}
	public void centralizaX(ImageLayer b){
		centralizaX(b.getX(),b.getX()+b.getW());
	}
	public int centralizaX(int xInicial, int xFinal)
	{
		int x;
		x = (((xInicial+xFinal)/2)-(getW()/2));
		setX(x);
		return x;
	}
	public void centralizaY(ImageLayer b){
		centralizaY(b.getY(),b.getY()+b.getH());
	}
	public int centralizaY(int yInicial, int yFinal)
	{
		int y;
		y = (((yInicial+yFinal)/2)-(getH()/2));
		setY(y);
		return y;
	}

	public boolean colideRetangular(int bx, int by, int bw, int bh){

		return colideRect(bx, by, bw, bh);

	}

	public boolean colideRetangular(ImageLayer b)
	{
		return colideRetangular(b.getX(), b.getY(), b.getW(), b.getH());
	}

	public boolean colideCircular(ImageLayer b)
	{
		return colideCircular(b.getX(), b.getY(), b.getW(), b.getH());
	}

	public boolean colideCircularPonto(int px, int py){

		int centroX = x+w/2;
		int centroY = y+h/2;

		return (px-centroX)*(px-centroX) + (py - centroY)*(py - centroY) < (w/2)*(w/2);
	}

	public boolean colideCircular(int bx, int by, int bw, int bh)
	{
		int xdiff = bx - x;
		int ydiff = by - y;

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = bw/2 + w/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	private boolean colideRetangulo(Rectangle rect, Rectangle rect2, int rect2x, int rect2y){

		if(rect2x+rect2.getX() + rect2.getW() < x+rect.getX())	return false;
		if(rect2x+rect2.getX() > x+rect.getX() + rect.getW())		return false;

		if(rect2y+rect2.getY() + rect2.getH() < y+rect.getY())	return false;
		if(rect2y+rect2.getY() > y+rect.getY() + rect.getH())		return false;

		return true;	
	}

	private boolean colideAreaRetangulo(Rectangle rect2, int rect2x, int rect2y){

		for(Rectangle rect: areaColisao.getArea()){

			if(colideRetangulo(rect, rect2, rect2x, rect2y)){
				return true;
			}

		}

		return false;
	}

	public boolean colideAreaCamada(AnimatedImageLayer b){
		Rectangle rect2 = new Rectangle(0, 0, b.getXTile(), b.getYTile());
		return colideAreaRetangulo(rect2,b.getX(), b.getY());
	}

	public boolean colideAreaCamada(ImageLayer b){
		Rectangle rect2 = new Rectangle(0, 0,b.getW(),b.getH());
		return colideAreaRetangulo(rect2,b.getX(),b.getY());
	}

	public boolean colideAreas(ImageLayer b){

		for(Rectangle rect: areaColisao.getArea()){

			for(Rectangle rect2: b.getAreaColisao().getArea()){

				if(colideRetangulo(rect, rect2, b.getX(), b.getY())){
					return true;
				}

			}

		}

		return false;
	}

	//Based on code http://developer.coronalabs.com/code/checking-if-point-inside-rotated-rectangle
	public boolean colisionRotated(int mx, int my){

		//Pivot Point of rotation		
		int px = x+w/2;
		int py = y+h/2;
		
		double c = Math.cos(angle);

		double s = Math.sin(angle);

		// UNrotate the point depending on the rotation of the rectangle
		double rotatedX = px + c * (mx - px) - s * (my - py);

		double rotatedY = py + s * (mx - px) + c * (my - py);

		// perform a normal check if the new point is inside the 
		// bounds of the UNrotated rectangle
		int leftX = px - w / 2;
		int rightX = px + w / 2;
		int topY = py - h / 2;
		int bottomY = py + h / 2;
		
		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	public void clone(ImageLayer b){
		this.w = b.w;
		this.h = b.h;

		this.xImage = b.xImage;
		this.yImage = b.yImage;

		this.path = b.path;
	}

	public void draw(Grafico g){
		if(visible){

			if(angle==0){
				g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w,y+h,
						xImage,yImage,xImage+w,yImage+h, null );
			}else{

				AffineTransform reset = g.getTransform();

				AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(angle),x+w/2, y+h/2);
				
				g.setTransform(transform);
				g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w,y+h,
						xImage,yImage,xImage+w,yImage+h, null );
				g.setTransform(reset);
			}
		}
	}

	public boolean onMouse(Mouse mouse){

		boolean colision = false;

		if(angle==0){
			colision = colideRetangular(mouse.getX(), mouse.getY(), 1, 1);
		}else{
			colision = colisionRotated(mouse.getX(), mouse.getY());
		}

		return colision;
	}

}
