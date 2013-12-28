package br.com.etyllica.core.input.keyboard;

import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.input.InputListener;

/**
 *
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Keyboard implements KeyListener, Updatable {

	private List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	private Map<Integer,KeyState> keyStates = new HashMap<Integer,KeyState>();
	private Map<Integer,Boolean> keys = new HashMap<Integer,Boolean>();

	private Set<Integer> changed = new HashSet<Integer>();

	private Set<Integer> changedCopy = new HashSet<Integer>(5);

	private InputListener listener;

	public Keyboard(InputListener listener){
		super();
		this.listener = listener;
	}

	public void update(long now){

		changedCopy.clear();
		changedCopy.addAll(changed);

		for(Integer key: changedCopy){

			KeyState keyState = keyStates.get(key);

			if( keys.get(key) ) {

				if( keyState == KeyState.RELEASED ){

					keyStates.put(key,KeyState.ONCE);

					keyEvents.add(new KeyEvent(key, KeyState.PRESSED));

				}else if(keyState != KeyState.PRESSED){

					keyStates.put(key,KeyState.PRESSED);

				}

			}else{

				if(( keyState == KeyState.ONCE )||( keyState == KeyState.PRESSED )){

					keyStates.put(key,KeyState.FIRST_RELEASED);

				}else if(keyState==KeyState.FIRST_RELEASED){

					keyStates.put(key,KeyState.RELEASED);

					keyEvents.add(new KeyEvent(key, KeyState.RELEASED));

					changed.remove(key);

				}else if(keyState!=KeyState.RELEASED){

					keyStates.put(key,KeyState.RELEASED);

					changed.remove(key);

				}
			}
		}

		notifyListener();

	}

	private void notifyListener(){

		for(KeyEvent event: keyEvents){
			listener.updateKeyEvent(event);
		}

		keyEvents.clear();

	}

	public synchronized void keyPressed( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, true);

		changed.add(code);

		ke.consume();
	}

	public synchronized void keyReleased( java.awt.event.KeyEvent ke ) {

		int code = getKeyFromEvent(ke);

		keys.put(code, false);

		changed.add(code);

		ke.consume();

	}

	@Override
	public void keyTyped( java.awt.event.KeyEvent ke) {

		int code = getKeyFromEvent(ke);

		char c = ke.getKeyChar();

		//TODO Ajeitar o typed
		if ( c != java.awt.event.KeyEvent.CHAR_UNDEFINED ) {
			keyEvents.add(new KeyEvent(code, c, KeyState.TYPED));
		}

		ke.consume();

	}

	private int getKeyFromEvent(java.awt.event.KeyEvent ke){
		int code = ke.getKeyCode();

		if(ke.getKeyLocation()!=java.awt.event.KeyEvent.KEY_LOCATION_STANDARD){
			code+=ke.getKeyLocation()*100;
		}

		return code;

	}

	/*public List<KeyEvent> getEvents(){
		return keyEvents;
	}
	 */

	public void reset(){

		Field[] fields = KeyEvent.class.getDeclaredFields();

		for (Field f : fields) {
			if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()) && f.getName().startsWith("TSK_")) {
				try {
					keys.put(f.getInt(null),false);
					keyStates.put(f.getInt(null),KeyState.RELEASED);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                                
			}
		}
	}

}