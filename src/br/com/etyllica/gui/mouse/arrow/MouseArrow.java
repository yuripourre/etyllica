package br.com.etyllica.gui.mouse.arrow;

import br.com.etyllica.core.DrawableComponent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface MouseArrow extends DrawableComponent{
	
	public void move(int mx, int my);
	
}