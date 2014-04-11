package br.com.etyllica.effects;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class EffectSound extends Effect{
	
	private String sound = "";
	private boolean playing = false;
	
	public EffectSound(int x, int y, int xTile, int yTile){
		super(x,y,xTile,yTile);
		setVisible(false);
	}
	
	public EffectSound(int x, int y, int xTile, int yTile, String caminho){
		super(x,y,xTile,yTile,caminho);
		setVisible(false);
		
	}
	
	@Override
	public void animate(int frame) {
		
		currentFrame = 0;
		
		stopped = false;
		once = true;
		playing = true;
		
		setVisible(true);
		animate();
	}
	
	@Override
	public void stopAnimation(){
		stopped = true;
		playing = false;
		setVisible(false);
		//desanim();
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void setPlaying(boolean tocando){
		this.playing = tocando;
	}
	
	public void setSom(String caminho){
		this.sound = caminho;
	}
	
	public String getSom(){
		return sound;
	}

}