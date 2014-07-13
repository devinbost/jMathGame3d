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

    public CountdownTimer getTimer() {
        return _timer;
    }

    public String getThreadName() {
        return _threadName;
    }
    private String _threadName;
    public CountdownTimerTask(CountdownTimer timer, String threadName) {
        System.out.println("CustomTimerTask is being constructed.");
        _timer = timer;
        _timer.Tick();
        _threadName = threadName;
        System.out.println("CustomTimerTask.Tick() was called from the constructor.");
    }
    
    @Override
    public void run() {
        System.out.println("The run() method of the CustomTimerTask instance is starting.");
        try{
            for (int count = 0; count < 10; count++) {
                Thread.sleep(1000);
                System.out.println("In " + _threadName + ", count is: " + count);
                _timer.Tick();
            }
        }
        catch(InterruptedException ex){
            System.out.println(_threadName + " was interrupted.");
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
