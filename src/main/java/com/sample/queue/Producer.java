package com.sample.queue;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Producer implements Runnable{
    private final TransferQueue<String> queue;
	
	public Producer(TransferQueue<String> queue) {
		this.queue = queue;
	}

	private String produce() {
		return " your lucky number " + (new Random().nextInt(100));
	}

	@Override
	public void run() {
		try {
		while (true) {
			if ( queue.hasWaitingConsumer() ) {
				queue.transfer(produce());
			}
			
			TimeUnit.SECONDS.sleep(1);
		}
		}
		catch( InterruptedException e ) {
			
		}
	}

}