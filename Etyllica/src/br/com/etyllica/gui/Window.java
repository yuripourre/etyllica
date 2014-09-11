package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.context.Context;
import br.com.etyllica.context.ContextContainer;
import br.com.etyllica.context.SessionMap;
import br.com.etyllica.context.load.ApplicationLoader;
import br.com.etyllica.context.load.DefaultLoadApplication;
import br.com.etyllica.context.load.GenericLoadApplication;
import br.com.etyllica.layer.GeometricLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Window extends GeometricLayer implements ContextContainer {
	
	protected Context application;

	//TODO Change to Application backApplication
	protected List<Context> oldApplications = new ArrayList<Context>();

	protected Camera camera;
	
	protected SessionMap sessionMap = new SessionMap();

	//protected DefaultLoadApplication load;

	protected ApplicationLoader applicationLoader;

	protected boolean close = false;

	private boolean loaded = true;
	//private boolean locked = false;
	
	private List<Window> windows = new ArrayList<Window>();
	
	/**
	 * Load Application
	 */
	protected DefaultLoadApplication loadApplication = null;

	public Window(int x, int y, int w, int h){
		super(x,y,w,h);
		
		camera = new Camera(0, 0, w, h);

		//load = new LoadApplication(x,y,w,h);
		//load = new GenericLoadApplication(x,y,w,h);
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB));
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB));

		applicationLoader = new ApplicationLoader();
		
		loadApplication = new GenericLoadApplication(x,y,w,h);
		loadApplication.load();
	}

	public void restartWindow(){
		
	}

	public void setApplication(Context application) {
		this.application = application;
	}

	public void setLoadApplication(DefaultLoadApplication loadApplication) {
		this.loadApplication = loadApplication;
		this.application = loadApplication;		
	}

	public void reload(Context context){
		
		if(loaded){
			
			loaded = false;

			/*application.load();
			this.application = context;
			this.loaded = true;*/
			
			
			loadApplication.load();
			setLoadApplication(loadApplication);

			applicationLoader.setWindow(this);
			applicationLoader.setApplication(context);
			applicationLoader.setLoadApplication(loadApplication);

			applicationLoader.loadApplication();						
		}

	}

	public void closeWindow(){
		setClose(true);
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public List<Window> getWindows() {
		return windows;
	}

	public void setWindows(List<Window> windows) {
		this.windows = windows;
	}
	
	public SessionMap getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public Context getContext() {
		return application;
	}	
		
}
