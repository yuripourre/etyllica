package br.com.tide.platform.player;

import br.com.etyllica.core.Updatable;
import br.com.tide.ActivePlayer;
import br.com.tide.PlayerState;
import br.com.tide.input.ControllerListener;

public class PlatformPlayer extends ActivePlayer implements Updatable, ControllerListener {

	protected PlatformPlayerListener listener;
	
	public PlatformPlayer() {
		super();
	}
	
	public PlatformPlayer(PlatformPlayerListener listener) {
		super();
		
		this.listener = listener;
	}

	public void walkLeft() {
		x -= walkSpeed;
		
		listener.onWalkLeft();		
		states.add(PlayerState.WALK_LEFT);
	}

	public void stopWalkLeft() {
		states.remove(PlayerState.WALK_LEFT);
		listener.onStopWalkLeft();
	}

	public void walkRight() {
		x += walkSpeed;
		
		listener.onWalkRight();
		states.add(PlayerState.WALK_RIGHT);
	}

	public void stopWalkRight() {
		states.remove(PlayerState.WALK_RIGHT);
		listener.onStopWalkRight();
	}

	public void lookUp() {
		listener.onLookUp();
		states.add(PlayerState.LOOK_UP);
	}

	public void stopWalkUp() {
		states.remove(PlayerState.WALK_UP);
		listener.onStopLookUp();
	}

	public void standDown() {
		listener.onStandDown();
		states.add(PlayerState.STAND_DOWN);
	}

	public void stopStandDown() {
		states.remove(PlayerState.STAND_DOWN);
		listener.onStopStandDown();
	}
	
	public void stopWalk() {
		states.remove(PlayerState.WALK_LEFT);
		states.remove(PlayerState.WALK_RIGHT);		
	}
	
	public boolean isWalking() {
		return states.contains(PlayerState.WALK_RIGHT)||states.contains(PlayerState.WALK_LEFT)||states.contains(PlayerState.WALK_UP)||states.contains(PlayerState.WALK_DOWN);
	}
	
	public void onUpButtonPressed() {
		lookUp();
	}

	@Override
	public void onUpButtonReleased() {
		stopWalkUp();
	}

	@Override
	public void onDownButtonPressed() {
		standDown();
	}

	@Override
	public void onDownButtonReleased() {
		stopStandDown();
	}

	@Override
	public void onRightButtonPressed() {
		walkRight();
	}

	@Override
	public void onRightButtonReleased() {
		stopWalkRight();
	}

	@Override
	public void onLeftButtonPressed() {
		walkLeft();
	}

	@Override
	public void onLeftButtonReleased() {
		stopWalkLeft();
	}

	@Override
	public void onAButtonPressed() {
		attack();
	}

	public void onAButtonReleased() {
		stopAttack();
		stand();	
	}

	public void onBButtonPressed() {
		specialAttack();
	}

	public void onBButtonReleased() {
		onStopSpecialAttack();
		stand();	
	}

	public void onCButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	public void onCButtonReleased() {
		// TODO Auto-generated method stub
		
	}

}
