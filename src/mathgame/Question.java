/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import de.congrace.exp4j.*;
/**
 *
 * @author devinbost
 */

public class Question {
    private double _leftValue = 0; // we put the underscore at the front of these variables as a naming convention to indicate that
    private double _rightValue = 0;  //     they are private fields.
    private double _answer = 0;
    private String _operator = "";
    private int _maxValues = 0;
    // This is the class exchanged between the QandABot and the Gamer.
    // The Gamer must answer the question to complete the task.
    // We need to override the toString() method to concatenate and display the question parameters.
    public Question(int gamerLevel, int digits){
        if(gamerLevel < 0){
            throw new IllegalArgumentException("Error: The Question construction method cannot have a negative valued gamerLevel!");
        } // This is a Dependency Injection (DI) technique called a "Guard Clause". Guard Clauses ensure that we have legitimate values for our logic.
        // The DI technique is best used with non-primitive value types that can actually be set to null (unlike an integer).
        if(digits < 1){
            throw new IllegalArgumentException("Error: The Question construction method cannot have a value for digits that is less than 1!");
        } //  Guard Clauses make debugging much easier by causing exceptions (i.e. errors) to appear much closer to the source of the error.
        
        SetOperator(SetLevel(gamerLevel));
        SetQuestionValues(digits);
        
    }

    private int SetMaxValue(int digits){
        return (int)Math.pow(10, digits);
    }
    private QuestionTypeEnum SetLevel(int gamerLevel){
        QuestionTypeEnum typeEnum;
         switch (gamerLevel){
            case 1: 
                typeEnum = QuestionTypeEnum.Addition;
                break;
            case 2:
                typeEnum = QuestionTypeEnum.Subtraction;
                break;
            case 3:
                typeEnum = QuestionTypeEnum.Multiplication;
                break;
            case 4:
                typeEnum = QuestionTypeEnum.Division;
                break;
            default: 
                typeEnum = QuestionTypeEnum.Addition;
                break;
        }
         return typeEnum;
    }
    private void SetOperator(QuestionTypeEnum questionType){
        switch (questionType){
            case Addition: 
                _operator = "+";
                break;
            case Subtraction:
                _operator = "-";
                break;
            case Multiplication:
                _operator = "*";
                break;
            case Division:
                _operator = "/";
                break;
            default: 
                _operator = "+";
                break;
        }
    }
    private void SetQuestionValues(int digits){ // This is where a delegate in C# would be very useful. In C#, we would be able to pass the SetMaxValue method as a parameter instead of forcing it to become a dependency that could break.
        // This method generates random values for the leftValue and rightValue;
        double min = 0;
        double max = SetMaxValue(digits);
        _leftValue = Math.round(Math.sqrt(Math.random() * (max - min))); // We take the square root to ensure that the value does not exceed our maximum when multiplied.
        _rightValue = Math.round(Math.sqrt(Math.random() * (max - min)));
        // The _leftValue and _rightValue could easily be converted to integers instead of doubles if we don't care about decimal values.
        
    } // Code is easier to read when functional dependencies occur in the order of their usage.
    @Override public String toString() {
     // this generates the string by concatenating the leftValue, operator, and rightValue;
        String questionString = _leftValue + _operator + _rightValue;
        return questionString;
    }
    private double ComputeAnswer(){
        // This method calculates the correct answer for the question involved.
        double result = 0;
        try{
          Calculable calc = new ExpressionBuilder(this.toString())
               .build();
          result = Math.round(calc.calculate()* 1000.0)/1000.0; // The multiplication and division by 100.0 is to round to two decimal places. Yes, it's a hack; but this is how Java does things.
      }
      catch (UnknownFunctionException ex){
          System.err.println("Caught UnknownFunctionException in Question.ComputeAnswer(): "
                  + " for the values: _leftValue = " + _leftValue 
                  + ", _operator = " + _operator 
                  + ", and _rightValue = " + _rightValue 
                  + ex.getMessage());
      }
      catch (UnparsableExpressionException ex){
         System.err.println("Caught UnparsableExpressionException in Question.ComputeAnswer(): "
                  + " for the values: _leftValue = " + _leftValue 
                  + ", _operator = " + _operator 
                  + ", and _rightValue = " + _rightValue 
                  + ex.getMessage());
      }  
        
//        ScriptEngineManager mgr = new ScriptEngineManager(); // We are using the JavaScript engine to process the dynamic string. It's a somewhat ugly hack, but it is much cleaner than the other possible options that Java offers. 
//        ScriptEngine engine = mgr.getEngineByName("JavaScript"); // (C# is much more powerful for clean solutions to problems like this.)
//        String questionString = this.toString(); // We get the string by executing toString on this instance.
//        _answer = (double)engine.eval(questionString); // This line is not really necessary unless we return void on this method.
        return result;
        // Then, we just want to set the answer to our private field.
        //System.out.println(engine.eval(foo));
    }

    public boolean CheckAnswer(double answer){
        // This method allows to check if their answer is correct.
        // This method will be used by the QandABot to handle the user's score.
        if(answer == ComputeAnswer()){
            return true;
        }
        else{
            return false;
        }
    }
    public double GetAnswer(){
        return ComputeAnswer();
    }
    public static int GetMaximumDifficultyLevel(){
        QuestionTypeEnum[] questionTypeEnumValues = QuestionTypeEnum.values();
        int numberOfEnumValues = questionTypeEnumValues.length;
        return numberOfEnumValues;
    }
}
