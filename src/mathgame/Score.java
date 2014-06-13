/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import java.io.Serializable;

/**
 *
 * @author devinbost
 */
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
    "The serialVersionUID field is not necessary, but strongly recommended as this maintains the binary compatibility between different 
        versions of the class and the serialized representations of its instances. 
        So when you add later a new field to the class, then you'd need to change the serialVersionUID field 
        (usually just incrementing it by 1 is sufficient) to prevent problems during deserialization of an instance 
        of an older version of the class." Source: http://stackoverflow.com/questions/2294551/java-io-writeabortedexception-writing-aborted-java-io-notserializableexception
    
    */
    double _score = 0;
    String _gamerName = "";
    public double getScore(){
        return _score;
    }
    public String getGamerName(){
        return _gamerName;
    }
    public Score(double score, String gamerName){
        this._score = score;
        this._gamerName = gamerName;
    }
}
