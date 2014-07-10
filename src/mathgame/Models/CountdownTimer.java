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
public class CountdownTimer {
    private int _totalSeconds;
    public static int _remainingSeconds;
    private Timer _timer;
    private int _delay = 1000;  // milliseconds
    private int _period = 1000; // milliseconds
    private boolean _isTimeRemaining;
    private List<PropertyChangeTypedListener> listener = new ArrayList<PropertyChangeTypedListener>();
    
    public CountdownTimer(int totalSeconds){
        _totalSeconds = totalSeconds * 1000;
        _isTimeRemaining = true;
    }
    public void setCountdownTimeLimit(int seconds){
        _totalSeconds = seconds;
        _remainingSeconds = seconds;
    }
    public int getCountdownTimeLimit(){
        return _totalSeconds;
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
        _timer = new Timer(false);
        System.out.println("Timer starting countdown. ");
        _timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Test");
                Tick();
            }
        }, 1000);
        System.out.println("Testing");
        
    }
    
    public void StopTimer(){
        this._timer.cancel();
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
}
