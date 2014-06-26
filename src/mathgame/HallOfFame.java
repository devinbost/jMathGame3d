/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import java.util.*;
import java.io.*;
/**
 *
 * @author devinbost
 */
// This is a singleton.
public class HallOfFame {
    private static HallOfFame _hallOfFame = new HallOfFame(); // can we use the "final" keyword here?
    // a private constructor prevents any other class from instantiating.
    private static ArrayList<Score> _scores = new ArrayList<Score>();
    private HallOfFame(){}
     // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "C:\\Users\\devinbost.BOSTINFORMATION\\Documents\\NetBeansProjects\\MathGame\\src\\mathgame\\scores.dat";
    //Initialising an in and _outputStream for working with the file
    private static ObjectOutputStream _outputStream = null;
    private static ObjectInputStream _inputStream = null;
    // static "instance" method.
    public static HallOfFame getInstance(){
        return _hallOfFame;
    }
    // This method is used by the singleton. It checks the gamer's score. 
    // We also need a sorted dictionary/list of the top ten _scores (and the gamers' names).
//    protected static void EvaluateHighScore(Score score){
//        // This method will later check the user's score to determine if it's worth including as a top score.
//         _scores.add(score);
//    }
    public ArrayList<Score> GetHighScores(){
        // This method gets the list of top _scores.
        loadScoreFile();
        sort();
        return _scores;
    }
    public ArrayList<Score> getScores(){
        return _scores;
    }
//    // We can deal with the top ten _scores later. 
//    private static double GetLowestScoreFromTopTen(){
//        // This method gets the lowest score in the list.
//        ArrayList<Score> _scores = GetHighScores();
//        double lowestScoreInTopTen = _scores.get(9).getScore();
//        return lowestScoreInTopTen;
//    }
    private static void sort(){
        ScoreComparer comparator = new ScoreComparer();
        Collections.sort(_scores, comparator); // This sort method uses our ScoreComparer to sort the objects. This technique works because
                                                // our ScoreComparer class implements the Comparator interface.
    }
    public void addScore(double score, String gamerName){
        loadScoreFile();
        _scores.add(new Score(score, gamerName));
        updateScoreFile();
        // It's easier to track every score and just display only the top ten _scores.
    }
    public void loadScoreFile(){
        try{
            _inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            _scores = (ArrayList<Score>) _inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Error:\n FileNotFound when trying to load scores: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error:\n IO Problem when trying to load scores: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error:\n Class Not Found when trying to load scores: " + e.getMessage());
        }finally {
            try {
                if (_outputStream != null) {
                    _outputStream.flush();
                    _outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error:\n IO Problem when trying to load scores: " + e.getMessage());
            }
        }
    }
    public void updateScoreFile() {
        try {
            _outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            _outputStream.writeObject(_scores);
        } catch (FileNotFoundException e) {
            System.out.println("Error:\n FileNotFound when trying to update scores:  " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("Error:\n IO Problem when trying to update scores: " + e.getMessage());
        } finally {
            try {
                if (_outputStream != null) {
                    _outputStream.flush();
                    _outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error:\n IO Problem when trying to update scores: " + e.getMessage());
            }
        }
    }
    public String getHighscoreString() {
        String highscoreString = "";
	int max = 10;

        ArrayList<Score> scores;
        scores = GetHighScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) { // Using a stringbuilder here would be faster and use less memory. Please do fix this in a later revision.
            highscoreString += (i + 1) + ".\t" + scores.get(i).getGamerName()+ "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
    public String getTopHighscoresString(int numberOfHighScoresToReturn) {
        String highscoreString = "";
	int max = numberOfHighScoresToReturn;

        ArrayList<Score> scores;
        scores = GetHighScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < numberOfHighScoresToReturn) { // Using a stringbuilder here would be faster and use less memory. Please do fix this in a later revision.
            highscoreString += (i + 1) + ".\t" + scores.get(i).getGamerName()+ "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
    public String getLowestScores(int numberOfLowScoresToReturn){
        String lowScoreString = "";
	int max = numberOfLowScoresToReturn;

        ArrayList<Score> scores;
        ArrayList<Score> sortedScores;
        scores = GetHighScores();
        sortedScores = this.ScoreInsertionSort(scores);
        
         int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < numberOfLowScoresToReturn) { // Using a stringbuilder here would be faster and use less memory. Please do fix this in a later revision.
            lowScoreString += (i + 1) + ".\t" + sortedScores.get(i).getGamerName()+ "\t\t" + sortedScores.get(i).getScore() + "\n";
            i++;
        }
        
       return lowScoreString;
    }
    public ArrayList<Score> ScoreInsertionSort(ArrayList<Score> scores){
        int j;                     // the number of items sorted so far
        Score key;                // the item to be inserted
        int i;
        
        for (j = 1; j < scores.size(); j++)    // Start with 1 (not 0)
         {
               key = scores.get(j);
               for(i = j - 1; (i >= 0) && (scores.get(i)._score > key._score); i--)   // Smaller values are moving down
               {
                   scores.set(i+1, scores.get(i));
               }
               scores.set(i+1, key); // Put the key in its proper location
         }
        return scores;
    }
//    public static void InsertionSort( int [ ] num)
//    {
//         int j;                     // the number of items sorted so far
//         int key;                // the item to be inserted
//         int i;  
//
//         for (j = 1; j < num.length; j++)    // Start with 1 (not 0)
//         {
//               key = num[ j ];
//               for(i = j - 1; (i >= 0) && (num[ i ] < key); i--)   // Smaller values are moving up
//              {
//                     num[ i+1 ] = num[ i ];
//              }
//             num[ i+1 ] = key;    // Put the key in its proper location
//         }
//    }
    
}
