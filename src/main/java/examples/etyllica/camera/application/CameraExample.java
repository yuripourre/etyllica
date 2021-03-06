package examples.etyllica.camera.application;

import java.awt.Color;

import br.com.etyllica.awt.camera.Camera;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class CameraExample extends Application {
	
	private Camera extendedCamera;
	
	private ImageLayer layer;
	
	public CameraExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		extendedCamera = new Camera(0, 0, w*2, h);
		
		layer = new ImageLayer("particle.png");
		layer.centralize(this); //Centralize based on Application Position
		
		//extendedCamera.setZoom(2);
		extendedCamera.setAngle(45);
	}

	@Override
	public void draw(Graphics g) {
		
		g.setCamera(extendedCamera);
		//Draw background
		g.setColor(Color.CYAN);
		g.fillRect(x, y, extendedCamera.getW(), extendedCamera.getH());
		//Draw layer
		layer.draw(g);
		
		g.resetCamera(extendedCamera);
		extendedCamera.draw(g);
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		extendedCamera.setAimX((int)(event.getX()*extendedCamera.getZoomX()-w/2));
		extendedCamera.setAimY((int)(event.getY()*extendedCamera.getZoomY()-h/2));
	}
	
}
