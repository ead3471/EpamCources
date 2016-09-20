package com.epam.javase02.t06;

/**
 * Created by Freemind on 2016-09-11.
 */
public class AtomicBoat {
    private  String boatID;

    private BoatEngine engine=new BoatEngine();


    AtomicBoat(String boatID)
    {
        this.boatID=boatID;
    }

    public void startSailing()
    {
        engine.startEngine();
    }

    class BoatEngine
    {
        private boolean engineIsStarted=false;
        public boolean startEngine()
        {
            engineIsStarted=true;
            return true;
        }
    }
}
