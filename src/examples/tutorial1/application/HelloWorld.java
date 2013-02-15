package examples.tutorial1.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.util.SVGColor;

public class HelloWorld extends Application{

	private int yText = 100;
	private int xText = 0;
	
	private int mx = 0;
	private int my = 0;
	
	@Override
	public void load() {
		carregando = 100;
	}

	@Override
	public void draw(Grafico g) {

		//Set Color to SVG Crimson
		g.setColor(SVGColor.CRIMSON);
		//Draw Background
		g.getGraphics().fillRect(0, 0, w, h);
		
		//Set Color to Black
		g.setColor(Color.BLACK);
		//Write HelloWorld!
		g.escreveX(xText, yText, "Hello World!");
		
		g.setColor(Color.BLACK);
		g.escreveX(300, "Press <SPACE> to change the application!");
		
		g.setColor(Color.BLACK);
		g.escreveX(350, "Press <ALT+ENTER> to change to Fullscreen Mode");
		
		//Fill Circle around the Mouse Point
		g.setColor(Color.WHITE);
		g.fillCircle(mx, my, 10);
		
		//Draw Circle around the Mouse Point
		g.setColor(Color.BLACK);
		g.drawCircle(mx, my, 10);
		
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
				
		//If Up Arrow is Pressed
		if(event.getPressed(Tecla.TSK_SETA_CIMA)){
			yText-=10;
		}
		//Else If Down Arrow is Pressed
		else if(event.getPressed(Tecla.TSK_SETA_BAIXO)){
			yText+=10;
		}
		
		//If RIGHT Arrow is Pressed
		if(event.getPressed(Tecla.TSK_SETA_DIREITA)){
			xText+=10;
		}
		//If LEFT Arrow is Pressed
		else if(event.getPressed(Tecla.TSK_SETA_ESQUERDA)){
			xText-=10;
		}
		
		if(event.getPressed(Tecla.TSK_ENTER)){
			xText = 0;
			yText = 100;
		}
		
		if(event.getPressed(Tecla.TSK_ESPACO)){
			returnApplication = new ByeWorld();
		}

		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		mx = event.getX();
		my = event.getY();
		
		return GUIEvent	.NONE;
	}
	
}
