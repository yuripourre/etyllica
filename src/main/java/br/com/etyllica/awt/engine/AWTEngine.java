package br.com.etyllica.awt.engine;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.awt.core.AWTCore;
import br.com.etyllica.core.Engine;
import br.com.etyllica.core.context.Session;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.loader.FontLoader;
import br.com.etyllica.loader.Loader;
import br.com.etyllica.loader.image.ImageLoader;
/**
 * AWTEngine is an Engine based on AWT
 * @author yuripourre
 *
 */
public class AWTEngine implements Engine {

	private Component component;

	private AWTCore core;

	private Set<Loader> loaders = new HashSet<Loader>();
	
	protected Session session = new Session();

	public AWTEngine(Component component, int w, int h) {
		super();

		this.component = component;

		core = new AWTCore(component, w, h);		
		core.setSession(session);
	}

	public void updateSuperEvent(GUIEvent event) {

		if (event == GUIEvent.ENABLE_FULL_SCREEN) {

			core.enableFullScreen();

		} else if (event == GUIEvent.DISABLE_FULL_SCREEN) {

			core.disableFullScreen();
			
		}
		else if (event == GUIEvent.REQUEST_FOCUS) {

			if ( !component.hasFocus() ) {
				component.requestFocus();
			}
		}
	}
	
	public AWTCore getCore() {
		return core;
	}

	public void init() {
		
		initLoaders();

		core.initDefault();
	}
	
	public void initLoaders() {

		addLoader(ImageLoader.getInstance());
		addLoader(FontLoader.getInstance());

		core.setLoaders(loaders);		
	}

	public Set<Loader> getLoaders() {
		return loaders;
	}

	public void addLoader(Loader loader) {
		loaders.add(loader);
	}
	
	public void hideCursor() {
		core.hideCursor();
	}

}
