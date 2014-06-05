/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MathGame.Tests;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import java.io.File;
import java.util.*;
import mathgame.*;
import mathgame.Questions.Question;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
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
        Gamer gamer = new Gamer(1, 3, "Devin");
        QandABot qAndABot= new QandABot(gamer);
        Question question = qAndABot.GetNextQuestion();
        assertTrue(question instanceof Question);
    }
    @Test
    public void QandABot_IsConstructionValid_ReturnsInstance(){
        Gamer gamer = new Gamer(1, 3, "Devin");
        QandABot qAndABot= new QandABot(gamer);
        assertTrue(qAndABot instanceof QandABot);
    }
    @Test
    public void QandABot_IsGetTotalQuestionsAskedReturningGreaterThan0_ReturnsTrue(){
        Gamer gamer = new Gamer(1, 3, "Devin");
        QandABot qAndABot= new QandABot(gamer);
        //gamer.
        assertTrue(false);
    }
    @Test
    public void QandABot_IsGetTotalQuestionsAskedReturningAnything_ReturnsTrue(){
        Gamer gamer = new Gamer(1, 3, "Devin");
        QandABot qAndABot = new QandABot(gamer);
        int returnValue = -1;
        returnValue = qAndABot.GetTotalQuestionsAsked();
        System.out.println("qAndABot.GetTotalQuestionsAsked() returns " + returnValue);
        assertThat(returnValue, is(not(-1)));
    }
    @Test
    public void MenuController_IsgetDisplayMenuCommandReturningHelpCommand_ReturnsCommand(){
        MenuController _controller = MenuController.getInstance();
        IDisplayCommand command = _controller.getDisplayMenuCommand("jMenuItemHelp");
        assertTrue(command instanceof IDisplayCommand);
    }
    @Test
    public void MenuController_IsgetDisplayMenuCommandReturningAboutCommand_ReturnsCommand(){
        MenuController _controller = MenuController.getInstance();
        IDisplayCommand command = _controller.getDisplayMenuCommand("jMenuItemAbout");
        assertTrue(command instanceof IDisplayCommand);
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
    @Test
    public void Score_IsConstructorWorking_ReturnsInstance(){
        Score score = new Score(10, "Devin");
        assertTrue(score instanceof Score);
    }
    @Test
    public void HallOfFame_UpdateScoreFileCreatesFile_ReturnsTrue(){
        HallOfFame hallOfFame = HallOfFame.getInstance();
        hallOfFame.updateScoreFile();
        File f = new File("C:\\Users\\devinbost.BOSTINFORMATION\\Documents\\NetBeansProjects\\MathGame\\src\\mathgame\\scores.dat");
        assertTrue(f.exists());
        // the best practice would be to use an interface to create a seam to make it easier to unit test the HallOfFame class.
    }
    @Test
    public void HallOfFame_GetHighScoresActuallyReturnsScores_ReturnsTrue(){
        HallOfFame hallOfFame = HallOfFame.getInstance();
        double score = 10;
        hallOfFame.addScore(score, "Devin");
        ArrayList<Score> scores = hallOfFame.GetHighScores();
        System.out.println("scores should not be empty. scores has a size of: " + scores.size());
        assertTrue(scores.isEmpty() == false);
        // the best practice would be to use an interface to create a seam to make it easier to unit test the HallOfFame class.
    }
    @Test
    public void HallOfFame_IsGetInstanceWorking_ReturnsInstance(){
        HallOfFame hallOfFame = HallOfFame.getInstance();
        assertTrue(hallOfFame instanceof HallOfFame);
        // the best practice would be to use an interface to create a seam to make it easier to unit test the HallOfFame class.
    }
    @Test
    public void Gamer_IsScoreRecordedWhenGamerWinsGame_ReturnsNotEmptyString(){
        Gamer gamer = new Gamer(1, 3, "Devin");
        gamer.SetScore(10);
        gamer.LevelUp();
        HallOfFame hallOfFame = HallOfFame.getInstance();
        String scores = "";
        scores = hallOfFame.getHighscoreString();
        System.out.println("hallOfFame.getHighscoreString() returns: " + scores);
        assertThat(scores, is(not("")));
        // the best practice would be to use an interface to create a seam to make it easier to unit test the HallOfFame class.
    }
    

}
