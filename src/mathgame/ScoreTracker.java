/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame;

/**
 *
 * @author devinbost
 */
public class ScoreTracker {
    private static volatile ScoreTracker instance = null;
    private static volatile Object mutex = new Object();
    private static volatile int _sharedScore = 0;

    public synchronized static int getSharedScore() {
        return _sharedScore;
    }

    public synchronized static void setSharedScore(int _sharedScore) {
        ScoreTracker._sharedScore = _sharedScore;
    }
    private ScoreTracker(){
        
    }
    public synchronized static ScoreTracker getInstance(){
        if(instance==null){
            synchronized (mutex){
                if(instance==null) instance= new ScoreTracker();
            }
        }
        return instance;
    }
    
}
