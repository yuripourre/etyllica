package br.com.etyllica.ui;

import java.awt.Color;

import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.BufferedLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class ColorPicker extends View{

	private BufferedLayer colors;
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(Graphics g){
		int offsetX = 26;
		int offsetY = 90;
		
		int wBar = 256/6;
		//int hBar = 20;
		int hBar = 200;
		
		for(int j=0;j<hBar;j++){
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(255, i*(255/wBar), 0));
				g.fillRect(offsetX+i+wBar*0, offsetY+j, 1, 1);
			}
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(255-i*(255/wBar),255,0));
				g.fillRect(offsetX+i+wBar*1, offsetY+j, 1, 1);
			}
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(0,255,i*(255/wBar)));
				g.fillRect(offsetX+i+wBar*2, offsetY+j, 1, 1);
			}
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(0,255-i*(255/wBar),255));
				g.fillRect(offsetX+i+wBar*3, offsetY+j, 1, 1);
			}
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(i*(255/wBar),0,255));
				g.fillRect(offsetX+i+wBar*4, offsetY+j, 1, 1);
			}
			for(int i=0;i<wBar;i++){
				g.setColor(new Color(255,0,255-i*(255/wBar)));
				g.fillRect(offsetX+i+wBar*5, offsetY+j, 1, 1);
			}
			
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(offsetX, offsetY, wBar*6, hBar);
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
	
}
