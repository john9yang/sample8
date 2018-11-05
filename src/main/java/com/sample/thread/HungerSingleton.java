package com.sample.thread;

/**
 * 饿汉式单例，线程安全
 * 在类加载的时候，就已经进行实例化，无论之后用不用到。
 * 如果该类比较占内存，之后又没用到，就白白浪费了资源。
 */
public class HungerSingleton {
	private static HungerSingleton ourInstance = new HungerSingleton();
	
    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return ourInstance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(HungerSingleton.getInstance());
                try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }).start();
        }
    }
}

