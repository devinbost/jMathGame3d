/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MathGame.Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import java.util.*;
import static org.junit.Assert.*;
import mathgame.*;
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
    public void QandABot_IsGetNextFunctionWorking_ReturnsTrue() {
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
    // We need some tests for the QuestionQueue;
    
//    @Test
//    public void Question_IsComputationCorrect_ReturnsValue() {
//        System.out.println("GallonsToLiters: Convert()");
//        assertEquals(37.854, GallonsToLiters.Convert(10), 0.0);
//       
//    }
//    
}
