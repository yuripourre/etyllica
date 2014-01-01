package examples.etyllica.tutorial17;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.particle.BasicEmitter;

public class TimedApplication extends Application{
	
	private ImageLayer layer;
	
	public TimedApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		layer = new ImageLayer(50,450, "particle.png");
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		layer.draw(g);
	}
	
	@Override
	public void update(long now){
		
		if(press){
			
			/*if(now-pressTime>=200){
				layer.setOffsetX(10);
			}else{
				press = false;
			}*/
			
			layer.setOffsetX(10);
			
		}
		
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private long pressTime = 0;
	
	private boolean press = false;
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_SETA_DIREITA)){
			pressTime = event.getTimestamp();
			press = true;
		}
		
		if(event.isKeyUp(KeyEvent.TSK_SETA_DIREITA)){
			press = false;
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}