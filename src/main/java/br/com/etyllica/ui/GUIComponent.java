package br.com.etyllica.ui;

import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.event.PointerEvent;

/**
 * 
 * @author yuripourre
 *
 */

public interface GUIComponent {
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public GUIEvent updateMouse(PointerEvent event);
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public GUIEvent updateKeyboard(KeyEvent event);
	
	/**
	 * 
	 * @param event
	 */
	public void updateEvent(GUIEvent event);
		
	/**
	 * 
	 * @param mx - mouse coordinate x
	 * @param my - mouse coordinate y
	 * @return
	 */
	public boolean onMouse(int mx, int my);
	
	/**
	 * 
	 * @param width - new window width 
	 * @param height - new window height
	 */
	public void resize(int width, int height);

}
