/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MathGame.Tests;

<<<<<<< HEAD
<<<<<<< HEAD
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import java.io.File;
||||||| merged common ancestors
=======
||||||| merged common ancestors
import mathgame.Questions.Question;
=======
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
>>>>>>> Added jScience library to project.
import java.io.File;
>>>>>>> Built all of the infrastructure for the HallOfFame singleton. Added a bunch of unit tests and new classes. Also added the new code that will be used for replacing the Question class with an interface, abstract class, and polymorphism (plus a Factory Method design pattern). This will help our code move toward sustainable design and best practices.
import java.util.*;
import mathgame.*;
import mathgame.Questions.Question;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
<<<<<<< HEAD
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
<<<<<<< HEAD
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Real;
||||||| merged common ancestors
=======
import static org.hamcrest.CoreMatchers.not;
>>>>>>> Built all of the infrastructure for the HallOfFame singleton. Added a bunch of unit tests and new classes. Also added the new code that will be used for replacing the Question class with an interface, abstract class, and polymorphism (plus a Factory Method design pattern). This will help our code move toward sustainable design and best practices.
||||||| merged common ancestors
=======
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
>>>>>>> Added jScience library to project.
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
<<<<<<< HEAD
    @Test
    public void JScience_IsPossibleToSolveForUnknownValueOfX_ReturnsTrue(){
        // Defines two local variables (x, y).
        Variable<Real> varX = new Variable.Local<Real>("x");
        Variable<Real> varY = new Variable.Local<Real>("y");
        Variable<Real> varA = new Variable.Local<Real>("a");
        
        // f(x) = ix² + 2x + 1
        Polynomial<Real> x = Polynomial.valueOf(Real.ONE, varX);
        Polynomial<Real> fx = x.pow(2).times(Real.valueOf(2)).plus(x.times(Real.valueOf(4)).plus(Real.valueOf(0.5)));
        System.out.println(fx);
        System.out.println(fx.pow(2));
        System.out.println(fx.differentiate(varX));
        System.out.println(fx.integrate(varY));
        System.out.println(fx.compose(fx));

        // TO DO: Solve for x.
        // See: http://jscience.org/api/overview-summary.html#TUTORIAL
        
//        // Calculates expression.
//        varX.set(Complex.valueOf(2, 3));
//        System.out.println(fx.evaluate());
    }
||||||| merged common ancestors
=======
    
>>>>>>> Added jScience library to project.
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
<<<<<<< HEAD
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
    public void HallOfFame_DoesGetTopHighscoresStringDisplay_ReturnsTrue(){
        HallOfFame hallOfFame = HallOfFame.getInstance();
        hallOfFame.addScore(11, "Devin");
        hallOfFame.addScore(12, "Bill");
        hallOfFame.addScore(492, "Jack");
        hallOfFame.addScore(83, "Mary");
        String highScores = hallOfFame.getTopHighscoresString(10);
        System.out.print("HallOfFame_DoesGetTopHighscoresStringDisplay_ReturnsTrue() is getting called.\n");
        System.out.print("The top high scores are: \n" + highScores);
        System.out.print("HallOfFame_DoesGetTopHighscoresStringDisplay_ReturnsTrue() is done.\n");
        assertTrue(!"".equals(highScores));
    }
    @Test
    public void HallOfFame_DoesGetLowestScoresStringDisplay_ReturnsTrue(){
        HallOfFame hallOfFame = HallOfFame.getInstance();
        hallOfFame.addScore(11, "Devin");
        hallOfFame.addScore(12, "Bill");
        hallOfFame.addScore(492, "Jack");
        hallOfFame.addScore(83, "Mary");
        String highScores = hallOfFame.getLowestScores(10);
        System.out.print("HallOfFame_DoesGetLowestScoresStringDisplay_ReturnsTrue() is getting called.\n");
        System.out.print("The lowest scores are: \n" + highScores);
        System.out.print("HallOfFame_DoesGetLowestScoresStringDisplay_ReturnsTrue() is done.\n");
        assertTrue(!"".equals(highScores));
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
    
||||||| merged common ancestors
=======
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
    
>>>>>>> Built all of the infrastructure for the HallOfFame singleton. Added a bunch of unit tests and new classes. Also added the new code that will be used for replacing the Question class with an interface, abstract class, and polymorphism (plus a Factory Method design pattern). This will help our code move toward sustainable design and best practices.

}
