package xdclass.concurrent.synopsis;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
         new Thread( () -> {
             synchronized ( lockA ){
                 try {
                     TimeUnit.MILLISECONDS.sleep(5);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 synchronized ( lockB ){
                     System.out.println("A成功获取B");
                 }
             }
         } ).start();

         new Thread( () -> {
             synchronized ( lockB ){
                 synchronized ( lockA ){
                     System.out.println("B成功获取A");
                 }
             }
         } ).start();
    }
}
