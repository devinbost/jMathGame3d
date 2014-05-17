/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;
import java.lang.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author devinbost
 */
public class Gamer {
    int _level = 0;
    int _score = 0;
    int _correctAnswerStreak = 0;
    int _totalCorrect = 0;
    int _totalWrong = 0;
    int _digits = 0;
    private String _username = "";
    private String _password;
    private QandABot _qAndABot;
    
    // When the game starts, the user will be prompted for the level. That level will be used to construct the Gamer object.
    public Gamer(int level, int digits){
        // determine if the gamer's level is set to a legitimate level.
        _level = this.ValidateLevel(level);
        _digits = digits;
    }
    public void LevelUp(){
        _level++;
        if (_level >= Question.GetMaximumDifficultyLevel() - 1) {
            this.GamerWins();
        }
        // This is where we need to check if the next level actually exists. If not, then the 
        // gamer has won the game.
    }
    public void ScoreUp(){
        _score++;
    }
    public int GetScore(){
        return _score;
    }
    public String GetName(){
        return _username;
    }
    public int GetLevel(){
        return _level;
    }
<<<<<<< HEAD
    private void GamerWins(){
        JOptionPane.showMessageDialog(null, 
                "Congratulations! You have won the game!", 
                "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
        // Do something about score here. (Check with singleton if high score.)
    }
    private int ValidateLevel(int level){
        if (level % 1 == 0) { // if level somehow becomes a float instead.
            level = (int)level; // cast back to int
        }
        int adjustedLevel = 1;
        if (level < 0 || level > Question.GetMaximumDifficultyLevel() - 1) {
             JOptionPane.showMessageDialog(null, 
                "Error: \t " + "You have entered: " + level
                        + "Please enter a level between 0 and 3. "
                        + "\n You will need to start the game again.", 
                "Error: ", JOptionPane.INFORMATION_MESSAGE);
             // This is a very ugly hack designed to comply with the class assignment requirements.
             // The best practice is to throw an exception here.
            if (level > Question.GetMaximumDifficultyLevel() - 1) {
                adjustedLevel =  Question.GetMaximumDifficultyLevel() - 1;
            }
            if (level < 0) {
                adjustedLevel = 0;
            }
        }
        else{
            adjustedLevel = level;
            
        }
        return adjustedLevel;
                
        
    }
||||||| parent of 7bb142c... Made the high score function in Gamer.java
    
=======
    public double HighScore(){
        double totalasked = this._qAndABot.GetTotalQuestionsAsked();
        double percentilescore = _score/totalasked;
        return percentilescore;
>>>>>>> 7bb142c... Made the high score function in Gamer.java
    // public function that returns high score.
    // high score should be returned as a double. The function should divide total
    // correct answers by the total questions answered.
    // // Gamer tracks the _score, which is the total number of correct answers.
    // // We need to be able to get the total questions asked. (This is tracked by the 
    // // this._qAndABot.GetTotalQuestionsAsked().)
    }
    // User input will be passed through the UI to the gamer class. 
    
// The Login and Logout functions have been removed to reduce complexity of application.

//public void Login(String username, String password){
//    // login with QandABot
//    if(username == null){
//        throw new IllegalArgumentException("Error: The Gamer.Login(..) method cannot have a null valued username!");
//    }
//    if(password == null){
//        throw new IllegalArgumentException("Error: The Gamer.Login(..) method cannot have a null valued password!");
//    }
//    // We need to validate the password here with the AuthenticationBot singleton class.
//    _username = username;
//    _password = password;
//    
//    }
//public void Logout(){
//    // perform object disposal here
//    }
//}
// QandABot needs to be a singleton
}
