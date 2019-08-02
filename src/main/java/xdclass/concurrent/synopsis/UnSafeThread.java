package xdclass.concurrent.synopsis;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class UnSafeThread {
    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void inCreate(){
        num++;
    }

    public static void main(String[] args) {

        IntStream.range(0,10).forEach( i -> {
            new Thread( () -> {
                IntStream.range(0,100).forEach( j -> {
                    inCreate();
                } );

                countDownLatch.countDown();
            } ).start();
        } );


        while(true){
            if ( countDownLatch.getCount() == 0 ){
                System.out.println(num);
                break;
            }
        }
    }
}
