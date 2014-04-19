package br.com.etyllica.theme.mouse;

import br.com.etyllica.theme.mouse.arrow.DefaultArrow;
import br.com.etyllica.theme.mouse.arrow.DefaultTextArrow;
import br.com.etyllica.theme.mouse.arrow.MouseArrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultArrowTheme implements ArrowTheme {

	protected int arrowSize = 22;
	
	protected MouseArrow normalArrow;
	protected MouseArrow clickArrow;
	protected MouseArrow textArrow;
	protected MouseArrow loadingArrow;
	
	public DefaultArrowTheme(){
		
		normalArrow = new DefaultArrow(arrowSize);
		clickArrow = new DefaultArrow(arrowSize);
		textArrow = new DefaultTextArrow(arrowSize);
		
		loadingArrow = new DefaultArrow(arrowSize);
		
	}

	public int getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(int arrowSize) {
		this.arrowSize = arrowSize;
	}

	public MouseArrow getNormalArrow() {
		return normalArrow;
	}

	public void setNormalArrow(MouseArrow normalArrow) {
		this.normalArrow = normalArrow;
	}

	public MouseArrow getClickArrow() {
		return clickArrow;
	}

	public void setClickArrow(MouseArrow clickArrow) {
		this.clickArrow = clickArrow;
	}

	public MouseArrow getTextArrow() {
		return textArrow;
	}

	public void setTextArrow(MouseArrow textArrow) {
		this.textArrow = textArrow;
	}

	public MouseArrow getLoadingArrow() {
		return loadingArrow;
	}

	public void setLoadingArrow(MouseArrow loadingArrow) {
		this.loadingArrow = loadingArrow;
	}
	
	
}