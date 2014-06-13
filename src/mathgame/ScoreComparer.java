/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import java.util.Comparator;

/**
 *
 * @author devinbost
 */
public class ScoreComparer implements Comparator<Score>{
//This class is used to tell Java how it needs to compare 2 objects of the type score. 
//  -1 means the first score is greater than the 2nd one, +1 (or you can just put 1) means it's smaller and 0 means it's equal.
    @Override
    public int compare(Score score1, Score score2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double sc1 =  score1.getScore();
        double sc2 = score2.getScore();
        
        if (sc1 > sc2) {
            return -1;
        }else if (sc1 < sc2) {
            return +1;
        }else{
            return 0;
        }
    
    }
    
}
