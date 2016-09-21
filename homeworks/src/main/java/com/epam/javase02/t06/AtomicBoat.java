package com.epam.javase02.t06;

import com.epam.javase02.t07.DevelopAnnotation;

/**
 * Created by Freemind on 2016-09-11.
 */

@DevelopAnnotation(lastChangeDate = "2016/09/20 23:01",lastChangeReason = "engine functionality adding")
public class AtomicBoat {
    private  String boatID;

    private BoatEngine engine=new BoatEngine();


    AtomicBoat(String boatID)
    {
        this.boatID=boatID;
    }

    public void startSailing(String course) {
        System.out.println("Start Moving to "+course);
        engine.startEngine();
        ;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBoat boat=new AtomicBoat("BlackShark");
        boat.startSailing("North");
        Thread.sleep(5000);
        boat.stopSailing();
        boat.runRockets();
        boat.startSailing("West");
        Thread.sleep(10000);
        boat.stopSailing();
        boat.runRockets();

    }

    public void runRockets()
    {
                int i=5;
                System.out.println("Rocket launching prepare.");
                while(i>0) {
                    System.out.println(i--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Launch!!!");



    }
    public void stopSailing(){
        this.engine.stopEngine();
    }

    class BoatEngine
    {
        private volatile boolean   engineIsStarted=false;
        public boolean startEngine()
        {
            if(engineIsStarted) return true;
            engineIsStarted=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(engineIsStarted&&!Thread.currentThread().isInterrupted())
                    {
                        System.out.print("->");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }).start();

            engineIsStarted=true;
            return true;
        }

        public void stopEngine()
        {
            System.out.println("Engine stop!");
            engineIsStarted=false;
        }
    }
}
