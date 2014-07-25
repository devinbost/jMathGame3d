/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author devinbost
 */
public class CountdownTimer implements Runnable{
    private int _totalMilliseconds;
    public  int _remainingSeconds;
//    private  TimerTask _timerTask = null;
//    private  Timer _controlTimer = null;
    private  CountdownTimerTask _runnableTimerTask = null;
//    private int _delay = 2000;  // milliseconds
    private int _period = 1000; // milliseconds
    private boolean _isTimeRemaining;
    private List<PropertyChangeTypedListener> listener = new ArrayList<PropertyChangeTypedListener>();
    
    /**
     * The totalSeconds parameter is used to set the maximum number of seconds that the timer will count down from. 
     * It converts the seconds into milliseconds, which are used by the CountdownTimerTask to control the timing
     * of the clock. If you want to set this value to a number less than a second, then it will need to be refactored.
     * @param totalSeconds 
     */
    public CountdownTimer(int totalSeconds){
        _totalMilliseconds = totalSeconds * 1000;
        _isTimeRemaining = true;
        System.out.println("CountdownTimer is getting constructed.");
    }
    /**
     *  This method gets a Hours:Minutes:Seconds:Milliseconds representation of the remaining time.
     *  It is given an enum value that represents the granularity of the resulting string. 
     *  Milliseconds, for example, displays a string with hours:minutes:seconds:milliseconds, whereas
     *  an enum value of minutes returns a string that only contains hours:minutes.
     *  This method is useful for displaying the properly formatted value of remaining time (for the
     *  CountdownTimer's countdown) to the user.
     * @return time
     */
    public String getFormattedTimeRemaining(TimePrecision precision){
        int milliseconds = this.getRemainingSeconds();
        long hour, minute, second;
        String time = "";
        switch(precision){
            case Hours:
                hour = (milliseconds / (1000 * 60 * 60)) % 24;
                time = String.format("%02d", hour);
                break;
            case Minutes:
                hour = (milliseconds / (1000 * 60 * 60)) % 24;
                minute = (milliseconds / (1000 * 60)) % 60;
                time = String.format("%02d:%02d", hour, minute);
                break;
            case Seconds:
                hour = (milliseconds / (1000 * 60 * 60)) % 24;
                minute = (milliseconds / (1000 * 60)) % 60;
                second = (milliseconds / 1000) % 60;
                time = String.format("%02d:%02d:%02d", hour, minute, second);
                break;
            case Milliseconds:
                hour = (milliseconds / (1000 * 60 * 60)) % 24;
                minute = (milliseconds / (1000 * 60)) % 60;
                second = (milliseconds / 1000) % 60;
                time = String.format("%02d:%02d:%02d:%d", hour, minute, second, milliseconds);
        }
        return time;
    }
    /**
     * This method is used to set the initial value 
     * @param seconds 
     */
    public void setCountdownTimeLimit(int seconds){
        _totalMilliseconds = seconds;
        _remainingSeconds = _totalMilliseconds;
    }
    public int getCountdownTimeLimit(){
        return _totalMilliseconds;
    }
    public int getRemainingSeconds(){
        return _remainingSeconds;
    }
    public void ResetCountdown(){
        _remainingSeconds = this.getCountdownTimeLimit();
        _isTimeRemaining = true;
    }
    public boolean getIsTimeRemaining(){
        return _isTimeRemaining;
    }
    public void StartCountdown(){
        this.ResetCountdown();
        // fire event that starts countdown process.
        Timer timer = new Timer("MyTimer", false); // Should timer be static or not?
        int count = 10;
        //_timer = new Timer(false);
        System.out.println("CountdownTimer class's StartCountdown() method is starting countdown.");
        _runnableTimerTask = new CountdownTimerTask(this, "countdownTimerTask1", this._totalMilliseconds, _period);
        Thread newThread = new Thread(_runnableTimerTask); // Should this thread be static?
        newThread.start(); // The start method executes the run() method on the runnable interface object.
        // The Run method (in the CountdownTimerTask) must be used to set the frequency of the event if the Timer class will not work.
        for (int i = 0; i < _totalMilliseconds; i++) {
            System.out.print(".");
            try{
                Thread.sleep(_period/5);
            }
            catch(InterruptedException exc){
                System.out.println("Main thread in CountdownTimer class was interrupted.");
                
            }
        }
        // Let's first determine if the above code actually runs. If so, then we can try and pass it into the timer.
       // timer.scheduleAtFixedRate(_runnableTimerTask, _delay, _period);
//        System.out.println("Main thread in CountdownTimer class's StartCountdown() method is ending.");
//        
//        _timerTask = new CountdownTimerTask(count, new Runnable(){
//            public void run() {
//                System.out.println("Test");
//                Tick();
//            }
//        }))
//        
//        _timer.schedule(new TimerTask(){
//            public void run(){
//                System.out.println("Test");
//                Tick();
//            }
//        }, 1000, _period);
//        System.out.println("Testing");
        
    }
    
    public void StopTimer(){
        this._runnableTimerTask.cancel(); // What should this reference?
        System.out.println("Timer ending countdown. ");
    }
    public void OutOfTime(){
        // do something like fire an event so we can update the gamer's score.
        this.StopTimer();
        boolean isTimeRemaining = this.getIsTimeRemaining();
        this._isTimeRemaining = false;
        // Notify listeners that time ran out.
        this.notifyIsTimeRemainingListeners(this, "_isTimeRemaining", isTimeRemaining, this.getIsTimeRemaining());
        System.out.println("Timer is out of time. ");
    }
    public int Tick() {
//        if (this.getRemainingSeconds() == 1)
//            this.OutOfTime();
        // We need to raise an event to indicate that the value of this.getRemainingSeconds() has changed.
        int priorRemainingSeconds = this.getRemainingSeconds();
        System.out.println("We are executing the Tick() method of the CountdownTimer instance.");
        _remainingSeconds--;
        this.notifyCountdownListeners(this, "_remainingSeconds", priorRemainingSeconds, this.getRemainingSeconds());
        return this.getRemainingSeconds();
    }
    // need to make this observable.
    private void notifyCountdownListeners(Object object, String property, int oldValue, int newValue) {
        for (PropertyChangeTypedListener name : listener) 
        {
          name.propertyChange(new PropertyChangeTypedEvent(this, property, oldValue, newValue, EventTypeEnum.CountdownTick));
        }
    }
    private void notifyIsTimeRemainingListeners(Object object, String property, boolean oldValue, boolean newValue) {
        for (PropertyChangeTypedListener name : listener) 
        {
          name.propertyChange(new PropertyChangeTypedEvent(this, property, oldValue, newValue, EventTypeEnum.CountdownOutOfTime));
        }
    }
    public void addChangeListener(PropertyChangeTypedListener newListener) {
        listener.add(newListener);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
