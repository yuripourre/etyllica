package br.com.etyllica.core.input.joystick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.input.keyboard.KeyState;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Joystick{

	private int joysticks = 10;

	private List<KeyEvent> joyEvents = new ArrayList<KeyEvent>();

	private Map<Integer, FileInputStream> inputStreams = new HashMap<Integer, FileInputStream>();

	//Define constants
	private final int JS_EVENT_BUTTON = 0x01;
	private final int JS_EVENT_AXIS = 0x02;
	private final int JS_EVENT_INIT = 0x80;

	public static final int MAX_AXIS_MOVEMENT = 32767;
	public static final int MIN_AXIS_MOVEMENT = -32767;

	private final String JOYSTICK_DIRECTORY = "/dev/input/js";
	
	private ScheduledExecutorService executor;
	
	private final int UPDATE_DELAY = 20;

	public Joystick() {
		super();
	}
	
	public Joystick(int joysticks) {
		super();
		this.joysticks = joysticks;
	}
	
	public void start(){

		int j=0;

		for(;j<joysticks;j++){

			try {

				this.inputStreams.put(j, new FileInputStream(JOYSTICK_DIRECTORY+j));
				System.out.println("Joystick "+j+ " found.");

			} catch (FileNotFoundException e) {

				System.err.println("Joystick "+j+" not found.");
				break;
			}

		}
		
		this.joysticks = j;
		executor = Executors.newScheduledThreadPool(this.joysticks);
		
		for(int i=0;i<joysticks;i++){
			System.out.println("Create Handler");
			executor.scheduleAtFixedRate(new JoystickHandler(i), 0, UPDATE_DELAY, TimeUnit.MILLISECONDS);	
		}
		
	}
	
	private class JoystickHandler implements Runnable{
		
		private int id;
		
		public JoystickHandler(int id){
			super();
			this.id = id;
		}
		
		public void run() {
			listen(id,inputStreams.get(id));
		}
	}

	public List<KeyEvent> getJoyEvents() {
		return joyEvents;
	}

	public void poll(){
		
		for(Entry<Integer, FileInputStream> entry: inputStreams.entrySet()){
			listen(entry.getKey(), entry.getValue());
		}

	}

	private void listen(Integer id, FileInputStream is){

		byte[] buf = new byte[8];

		int n = 0;

		try {
			n = is.read(buf, 0, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (n == 8) {
			long time = buf[3] << 24 | (buf[2] & 0xff) << 16 | (buf[1] & 0xff) << 8 | (buf[0] & 0xff);
			int value = buf[5] << 8 | (buf[4] & 0xff);
			int type = buf[6] & 0xff;
			int channel = buf[7] & 0xff;

			if (type == JS_EVENT_AXIS) {

				switch (channel) {

				case 0:
				case 2:

					if(value>0){
						joyEvents.add(new KeyEvent(id, KeyEvent.TSK_JOYSTICK_RIGHT, value, KeyState.PRESSED));
					}else if(value<0){					
						joyEvents.add(new KeyEvent(id, KeyEvent.TSK_JOYSTICK_LEFT, value,  KeyState.PRESSED));
					}else{
						joyEvents.add(new KeyEvent(id, KeyEvent.TSK_JOYSTICK_CENTER_X, value,  KeyState.PRESSED));
					}

					break;

				case 1:
				case 3:

					if(value>0){
						joyEvents.add(new KeyEvent(id, KeyEvent.TSK_JOYSTICK_DOWN, value, KeyState.PRESSED));
					}else if(value<0){					
						joyEvents.add(new KeyEvent(id,KeyEvent.TSK_JOYSTICK_UP, value,  KeyState.PRESSED));
					}else{
						joyEvents.add(new KeyEvent(id, KeyEvent.TSK_JOYSTICK_CENTER_Y, value,  KeyState.PRESSED));
					}

					break;				

				default:
					break;
				}

			} else if (type == JS_EVENT_BUTTON) {

				int buttonCode = (KeyEvent.TSK_JOYSTICK_BUTTON_1)+channel;

				if(value==1){
					joyEvents.add(new KeyEvent(id, buttonCode, 0,  KeyState.PRESSED));
				}else{
					joyEvents.add(new KeyEvent(id, buttonCode, 0, KeyState.RELEASED));
				}

			}			

		} else {
			System.err.println("only " + n + " of 8 bytes read");
		}

	}
}