/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MathGame.Tests;
import mathgame.Questions.Question;
import org.junit.*;
import org.junit.runner.*;
import java.util.*;
import javax.script.*;
import static org.junit.Assert.*;
import mathgame.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 *
 * @author devinbost
 */
@RunWith(Parameterized.class)
public class QuestionTest {
    private int _gamerLevel;
    private int _digits;
    public QuestionTest(int gamerLevel, int digits){
        this._digits = digits;
        this._gamerLevel = gamerLevel;
    }
    @Parameters // The values of the parameters must correspond to the items required by the test's constructor.
    // (It must be a collection of arrays where the parameters of the array match the parameters of the constructor, in the correct order.)
    public static Collection<Object[]> addedNumbers() {
        return Arrays.asList(new Object[][] 
        { 
            { 1 , 1},
            { 2 , 1},
            { 3, 1},
            { 4, 1},
            { 1 , 2},
            { 2 , 2},
            { 3, 2},
            { 4, 2},
            { 1 , 3},
            { 2 , 3},
            { 3, 3},
            { 4, 3},
            { 1 , 4},
            { 2 , 4},
            { 3, 4},
            { 4, 4},
            { 1 , 5},
            { 2 , 5},
            { 3, 5},
            { 4, 5},
            { 1 , 6},
            { 2 , 6},
            { 3, 6},
            { 4, 6}
            
        });
    }
    // Test construction
    @Test
    public void Question_IsConstructionValid_ReturnsInstance(){
        Question myQuestion = new Question(this._gamerLevel, this._digits);
        System.out.println("Question myQuestion = new Question(this._gamerLevel, this._digits) for " + this._gamerLevel + " and " + this._digits);
        assertTrue(myQuestion instanceof Question);
    }
   
//    @Test
//    public void Question_IsToStringNotEmpty_ReturnsNotEmpty(){
//        Question myQuestion = new Question(this._gamerLevel, this._digits);
//        String myQuestionString = myQuestion.toString();
//        System.out.println("myQuestionString for " + this._gamerLevel + " and " + this._digits + " is: " + myQuestionString);
//        assertNotSame(myQuestionString, "");
//    }
//    @Test
//    public void Question_IsCheckAnswerValid_ReturnsTrue() throws ScriptException{
//        Question myQuestion = new Question(this._gamerLevel, this._digits);
//        String myQuestionString = myQuestion.toString();
//        double testedAnswer = myQuestion.GetAnswer();
//        
//        ScriptEngineManager mgr = new ScriptEngineManager(); // We are using the JavaScript engine to process the dynamic string. It's a somewhat ugly hack, but it is much cleaner than the other possible options that Java offers. 
//        ScriptEngine engine = mgr.getEngineByName("JavaScript"); // (C# is much more powerful for clean solutions to problems like this.)
//        double answer = Math.round((double)engine.eval(myQuestionString) * 1000.0)/1000.0; // The multiplication and division by 100.0 is to round to two decimal places. Yes, it's a hack; but this is how Java does things.
//        //return (double)engine.eval(questionString);
//        
//        
//        System.out.println("myQuestionString for " + this._gamerLevel + " and " + this._digits + " is: " + myQuestionString + 
//                ". The Question class computed " + myQuestionString + "=" + testedAnswer + " whereas the test runner" + 
//                " expected " + answer);
//        assertEquals(answer, testedAnswer, 0);
//    }
    //// Test SetOperator
    
    // Test SetMaxValue
    
    // Test toString
    
    // Test ComputeAnswer
    
    // Test CheckAnswer
}
