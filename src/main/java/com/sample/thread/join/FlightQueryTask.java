package com.sample.thread.join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FlightQueryTask extends Thread implements FightQuery{
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FlightQueryTask( String airLine,String origin,String destination ) {
        super("["+airLine+"]");
        this.origin = origin;
        this.destination = destination;
    }

    public void run(){
        System.out.printf("%s-query from %s to %s \n",getName(),origin,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try{
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName()+"-"+randomVal);
            System.out.printf("The Flight:%s list query successful\n",getName());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public List<String> get() {
        return this.flightList;
    }
}
