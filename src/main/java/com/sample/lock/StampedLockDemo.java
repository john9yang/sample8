package com.sample.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

	public static void main(String[] args) {

	}

}

class Point{
	private double x,y;
	private final StampedLock sl = new StampedLock();
	
	void move(double deltaX,double deltaY) {
		long stamp = sl.writeLock();
		try {
			x += deltaX;
			y += deltaY;
		}
		finally {
			sl.unlockWrite(stamp);
		}
	}
	
	//乐观锁读锁
	double distanceFromOrigin() {
		//只读
		long stamp = sl.tryOptimisticRead();
		double currentX = x,currentY = y;
		
		if ( !sl.validate(stamp) ) {
			stamp = sl.readLock();
			try {
				currentX = x;
				currentY = y;
			}
			finally {
				sl.unlockRead(stamp);
			}
		}
		
		return Math.sqrt(currentX*currentX+currentY*currentY);
	}
	
	//悲观读锁
	void moveIfAtOrigin(double newX,double newY) {
		long stamp = sl.readLock();
		try {
			while(x==0.0 && y==0.0) {
				long ws = sl.tryConvertToWriteLock(stamp);
				
				if( ws != 0l ) {
					stamp = ws;
					x = newX;
					y = newY;
					break;
				}
				else {
					sl.unlockRead(stamp);
					stamp = sl.writeLock();
				}
			}
		}
		finally {
			sl.unlock(stamp);
		}
	}
}