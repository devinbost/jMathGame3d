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
    private int _sleepTime = 1000; // represents one second.
    private int _remainingTime; // Be sure this is set during construction and updated by the Tick method.

    public int getRemainingTime() {
        return _remainingTime;
    }

    public void setRemainingTime(int _remainingTime) {
        this._remainingTime = _remainingTime;
    }

    public CountdownTimer getTimer() {
        return _timer;
    }

    public String getThreadName() {
        return _threadName;
    }
    private String _threadName;
    /** The availableTime parameter is used to set the maximum time value used to determine how many seconds the timer will run.
     *  The sleepTime parameter is almost identical to the period from the Timer class; it is the interval (in milliseconds)
     *  of time between executions of the Tick() method. If set to 1000, it represents 1 second. For testing, use a smaller value.
     */
    public CountdownTimerTask(CountdownTimer timer, String threadName, int availableTime, int sleepTime) {
        System.out.println("CustomTimerTask is being constructed.");
        _remainingTime = availableTime;
        _timer = timer;
        _timer.Tick();
        _threadName = threadName;
        _sleepTime = sleepTime;
        System.out.println("CustomTimerTask.Tick() was called from the constructor.");
    }
    
    @Override
    public void run() {
        System.out.println("The run() method of the CustomTimerTask instance is starting.");
        try{
            while(_remainingTime > 1000){
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
