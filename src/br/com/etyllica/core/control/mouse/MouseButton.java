package br.com.etyllica.core.control.mouse;

import java.awt.event.MouseEvent;

public enum MouseButton {

	MOUSE_BUTTON_LEFT(MouseEvent.BUTTON1),
	MOUSE_BUTTON_MIDDLE(MouseEvent.BUTTON2),
	MOUSE_BUTTON_RIGHT(MouseEvent.BUTTON3),
	MOUSE_WHEEL_UP(MouseEvent.MOUSE_WHEEL),
	MOUSE_WHEEL_DOWN(MouseEvent.MOUSE_WHEEL+1),
	
	MOUSE_NONE(0);	
	
	private final int code;

	MouseButton(int code){
		this.code = code;
	}
	
	public final int getCode(){ 
		return code; 
	}
	
}