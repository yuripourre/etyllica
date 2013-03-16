package examples.etyllica.tutorial1.application;

import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.util.SVGColor;

public class HelloWorld extends Application{

	private ScheduledExecutorService loadSimulator = Executors.newSingleThreadScheduledExecutor();
		
	public HelloWorld(int w, int h) {
		super(w, h);
	}

	//Text Offset
	private int yText = 100;
	private int xText = 0;
	
	//Hold Mouse position
	private int mx = 0;
	private int my = 0;
	
	@Override
	public void load() {
			
		//Simulating Loads
		loadSimulator.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				loading+=3;
				
				if(loading<30){
					
					loadingPhrase = "Loading Nothing...";
					
				}else if(loading<50){
					
					loadingPhrase = "Loading Something...";
					
				}else if(loading<90){
					
					loadingPhrase = "Almost Loaded...";
					
				}else if(loading==100){
					loadSimulator.shutdown();
				}
				
			}
			
		}, 25, 25, TimeUnit.MILLISECONDS);
		
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

		g.escreveX(250, "Click with Mouse to Gain Keyboard Focus.");
		
		g.escreveX(300, "Press <SPACE> to change the application!");
		
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
			returnApplication = new ByeWorld(w,h);
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