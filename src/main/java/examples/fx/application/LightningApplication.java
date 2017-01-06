package examples.fx.application;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.effect.Effect;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;

public class LightningApplication extends Application{

	private Effect lightning;
	
	public LightningApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		loading = 10;
		
		lightning = new Effect(300, 110, 120, 370, "lightning.png");
		lightning.setFrames(10);
		lightning.setSpeed(100);
				
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawStringX(100, "Press Space to see the magic");
		
		lightning.draw(g);
	}
	
	@Override
	public void update(long now) {
		
	}
		
	@Override
	public void updateKeyboard(KeyEvent event) {
		if(event.isKeyUp(KeyEvent.VK_SPACE)) {
			lightning.startEffect();
		}
	}
}
