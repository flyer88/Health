package com.holyboom.flyer.health.uitil;

/**
 * Created by shyboooy on 15/3/22.
 */
import android.os.Handler;
public class Delay {
    int timeInterval;
    DelayDelegate delegate;
    public Delay(int timeInterval,DelayDelegate delegate){
        this.timeInterval = timeInterval;
        this.delegate = delegate;
    }

    public void delay() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                delegate.didDelay();
            }
        }, timeInterval);
    }
}
