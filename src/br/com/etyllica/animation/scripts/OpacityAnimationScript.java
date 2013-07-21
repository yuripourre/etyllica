package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class OpacityAnimationScript extends AnimationScript{
		
	public OpacityAnimationScript(long time) {
		super(time);
	}
	
	public OpacityAnimationScript(long delay, long time) {
		super(delay, time);
	}
	
	public OpacityAnimationScript(Layer target, long time) {
		super(target, time);
	}
		
	@Override
	public void animate(long now) {
		
		long timeElapsed = now-startedAt-delay;
		
		float factor = (float)timeElapsed/time;

		float value = startValue+(endValue-startValue)*factor;
		
		target.setOpacity((int)value);
		
	}	

}
