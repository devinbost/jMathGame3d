/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import java.util.concurrent.TimeUnit;
import static mathgame.Models.TimePrecision.Hours;
import static mathgame.Models.TimePrecision.Milliseconds;
import static mathgame.Models.TimePrecision.Minutes;
import static mathgame.Models.TimePrecision.Seconds;

/**
 *
 * @author devinbost
 */
public class StringFormatters {
    public static String FormatTimeInMilliseconds(int milliseconds){
        long hour, minute, second;
        String time = "";
        time = String.format("%02d:%02d:%02d", 
            TimeUnit.MILLISECONDS.toHours(milliseconds),
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) -  
            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)), // The change is in this line
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) - 
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));   
        return time;
    }
            
}
