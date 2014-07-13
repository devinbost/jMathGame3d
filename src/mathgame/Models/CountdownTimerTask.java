/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import java.util.TimerTask;

/**
 *
 * @author devinbost
 */
public class CountdownTimerTask extends TimerTask {
    private CountdownTimer _timer;
    private final int _sleepTime; // represents one second.
    private final int _oneSecond = 1000;
    private int _remainingTime; // Be sure this is set during construction and updated by the Tick method.
    private final String _threadName;
    private final int _availableTime;

    /**
     * This method is used to get the number of milliseconds that represents one tick. This is generally
     * 1000, but for testing purposes, it can be adjusted. It is not recommended to change this property 
     * (by setting a different value in this class's constructor), so do not change it unless you are sure you 
     * know what you are doing.
     * @return 
     */
    public int getSleepTime() {
        return _sleepTime;
    }
/**
 * This property is used to get the total (maximum) number of milliseconds that the timer will count down from.
 * @return 
 */
    public int getAvailableTime() {
        return _availableTime;
    }
    /** The availableTime parameter is used to set the maximum time value used to determine how many seconds the timer will run.
     *  The sleepTime parameter is almost identical to the period from the Timer class; it is the interval (in milliseconds)
     *  of time between executions of the Tick() method. If set to 1000, it represents 1 second. For testing, use a smaller value.
     */
    public CountdownTimerTask(CountdownTimer timer, String threadName, int availableTime, int sleepTime) {
        System.out.println("CustomTimerTask is being constructed.");
        _availableTime = availableTime;
        _remainingTime = _availableTime;
        _timer = timer;
        _timer.Tick();
        _threadName = threadName;
        _sleepTime = sleepTime;
        System.out.println("CustomTimerTask.Tick() was called from the constructor.");
    }
    /**
     * This property represents the current remaining time of the countdown timer (in milliseconds).
     * @return 
     */
    public int getRemainingTime() {
        return _remainingTime;
    }
//
//    public void setRemainingTime(int _remainingTime) { // Don't expose this.
//        this._remainingTime = _remainingTime;
//    }
/**
 * This property is used to get the CountdownTimer instance that is passed into this thread during construction. 
 * The CountdownTimer instance is used to control much of the event handling, thread management, and it contains
 * the important Tick() method. That method is used to increment the counter's state and push notification to all
 * objects that have subscribed to the CountdownTimer for notifications. (It's using the observer pattern.)
 * @return 
 */
    public CountdownTimer getTimer() {
        return _timer;
    }

    public String getThreadName() {
        return _threadName;
    }
    /**
     * This method is exposed by the Runnable interface that this class inherits from the abstract TimerTask class.
     */
    @Override
    public void run() {
        System.out.println("The run() method of the CustomTimerTask instance is starting.");
        try{ 
            while(_remainingTime > _oneSecond){ // i.e. when more than one second is remaining.
                Thread.sleep(_sleepTime);
                System.out.println("In " + _threadName + ", the CountdownTimerTask instance, _remainingTime was: " + _remainingTime);
                int seconds = _timer.Tick();
                System.out.println("When we call _timer.Tick() from the run() method of the CountdownTimerTask instance, this is the time value it gives us:" + seconds);
                _remainingTime -= _sleepTime;
                System.out.println("In " + _threadName + ", the CountdownTimerTask instance, _remainingTime is now: " + _remainingTime);
            }
             System.out.println("The value of _remainingTime is now: " + _remainingTime + ". "
                     + "\n The CountdownTimerTask's run() method is now coming to an end.");
        }
        catch(InterruptedException ex){
            System.out.println(_threadName + " was interrupted.");
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
