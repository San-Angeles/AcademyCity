package com.sanangeles.academycity.kit;

import android.os.*;

public class Ticker extends Thread
{
	private int tick = 100;
	private Handler handler = new Handler();
	private Runnable run = null;
	private boolean started = false;
	
	public Ticker(Runnable run) {
		this.run = run;
	}

	public Ticker(Runnable run, int time) {
		this.run = run;
		this.tick = time;
	}

	public boolean isStarted() {
		return started;
	}

	@Override
	public synchronized void start() {
		super.start();
		started = true;
	}

	@Override
	public void run() {
		if (run == null)
			return;
		run.run();
		handler.postDelayed(this, tick);
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getTick() {
		return tick;
	}

	public void setRun(Runnable run) {
		this.run = run;
	}

	public Runnable getRun() {
		return run;
	}
}

