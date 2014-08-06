package br.com.etyllica.animation.scripts;


public abstract class AnimationScript {

	protected long startedAt = 0;
	protected long time = 0;
	protected long delay = 0;
	protected int repeat = 0;

	private boolean started = false;
	private boolean stopped = false;	

	private AnimationScript next;
	
	public static final int REPEAT_FOREVER = -1;

	public AnimationScript(long time) {
		super();

		this.time = time;
	}

	public AnimationScript(long delay, long time) {
		super();

		this.delay = delay;
		this.time = time;
	}

	public void restart() {
		started = false;
		stopped = false;
	}

	public void start(long now) {
		this.startedAt = now;
	}

	public void preAnimate(long now) {

		if(!started) {
			started = true;
			stopped = false;
			startedAt = now;
		}

		if(started&&!stopped) {

			if(now-startedAt-delay>=time) {
				//Last Update
				calculate(1);
				stopped = true;
			} else {
				
				if(now-startedAt>=delay) {
					this.animate(now);
				}
			}

		}

	}

	public void animate(long now) {

		long timeElapsed = now-startedAt-delay;

		float factor = (float)timeElapsed/time;

		calculate(factor);
		
	}
	
	protected abstract void calculate(double factor);

	public boolean isStopped() {
		return stopped;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public AnimationScript getNext() {
		return next;
	}

	public void setNext(AnimationScript next) {
		this.next = next;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}	

}