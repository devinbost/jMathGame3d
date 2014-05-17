/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MathGame.Tests;

import java.util.*;
import mathgame.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 *
 * @author devinbost
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({})
public class MathGameTest {

//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
    public MathGameTest(){
        
    }
    @Test
    public void QandABot_IsGetNextQuestionFunctionWorking_ReturnsTrue() {
        Gamer gamer = new Gamer(1, 3);
        QandABot qAndABot= new QandABot(gamer);
        Question question = qAndABot.GetNextQuestion();
        assertTrue(question instanceof Question);
    }
    @Test
    public void QandABot_IsConstructionValid_ReturnsInstance(){
        Gamer gamer = new Gamer(1, 3);
        QandABot qAndABot= new QandABot(gamer);
        assertTrue(qAndABot instanceof QandABot);
    }
    @Test
    public void QandABot_IsGetTotalQuestionsAskedReturningGreaterThan0_ReturnsTrue(){
        Gamer gamer = new Gamer(1, 3);
        QandABot qAndABot= new QandABot(gamer);
        //gamer.
        assertTrue(false);
    }
    @Test
    public void QandABot_IsGetTotalQuestionsAskedReturningAnything_ReturnsTrue(){
        Gamer gamer = new Gamer(1, 3);
        QandABot qAndABot = new QandABot(gamer);
        int returnValue = -1;
        returnValue = qAndABot.GetTotalQuestionsAsked();
        System.out.println("qAndABot.GetTotalQuestionsAsked() returns " + returnValue);
        assertThat(returnValue, is(not(-1)));
    }
    // We need some tests for the QuestionQueue;
    
//    @Test
//    public void Question_IsComputationCorrect_ReturnsValue() {
//        System.out.println("GallonsToLiters: Convert()");
//        assertEquals(37.854, GallonsToLiters.Convert(10), 0.0);
//       
//    }
//    
    @Test
    public void Question_IsGetMaximumDifficultyLevelReturningCorrectNumberOfValues_ReturnsTrue(){
        int enumTypes = Question.GetMaximumDifficultyLevel();
        assertEquals(4, enumTypes);
    }

}
