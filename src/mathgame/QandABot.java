/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import mathgame.Questions.Question;

/**
 *
 * @author devinbost
 */
public class QandABot {
    // The QandABot constructs a QuestionQueue of Question objects.
    private Gamer _gamer = null;
    private QuestionQueue _questionQueue = null;
    private Question _currentQuestion = null;
    int questionsPerLevel = 10;
    int _totalQuestionsAsked = 0;
    
    public QandABot(Gamer gamer){
        if(gamer == null){
            throw new IllegalArgumentException("Error: The QandABot construction method cannot have a null valued Gamer!");
        } // This is a Dependency Injection (DI) technique. We are forcing QandABot to use a valid gamer class.
        _gamer = gamer;
        _questionQueue = GenerateQuestions();
        
    }
   private QuestionQueue GenerateQuestions(){
       // Here, we will use the Gamer's level and digits to generate the questions.
       // First, we must construct our queue.
       
       QuestionQueue questionQueue = new QuestionQueue();
       for(int i=0; i < questionsPerLevel; i++){
           Question myQuestion = new Question(_gamer._level, 3);
           // use the gamer's level for the QuestionQueue constructor.
           questionQueue.add(myQuestion);
       }
       _currentQuestion = questionQueue.remove();
       // If level == 0, we create an addition question.
       // If level == 1, we create a subtraction question.
       // If level == 2, we create a multiplication question.
       // If level == 3, we create a division question.
       // After populating the list of questions, we then return the questions.
       
       // foreach number from 1 to questionsPerLevel, generate a new question and add to the queue.
       // The Question class should take the required parameters during construction.
       
       
       return questionQueue;
   }
   public Question GetNextQuestion(){
       if(_questionQueue.size() > 0){
           _totalQuestionsAsked++;
           _currentQuestion = _questionQueue.poll();
           return _currentQuestion;
       }
       else{
           // if _questionQueue is empty
            _totalQuestionsAsked++;
            _gamer.LevelUp(); // level up.
            _questionQueue = GenerateQuestions();
            _currentQuestion = _questionQueue.remove();
            return _currentQuestion;
       }
       // We need to deal with end of game situations here.
       
       // This method is intended to be invoked by a gamer.
       // This method simply returns the next question in the queue.
   }
   public Question GetCurrentQuestion(){
       return _currentQuestion;
   }
   public int GetTotalQuestionsAsked(){
       return _totalQuestionsAsked;
   }
}