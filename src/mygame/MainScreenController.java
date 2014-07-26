/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import mathgame.Gamer;
import mathgame.Models.EventTypeEnum;
import mathgame.Models.ScreenControlDisplayMediation;
import mathgame.Models.ScreenControlDisplayMediationFactory;
import mathgame.Models.UnknownControlException;
import mathgame.QandABot;
import mathgame.Questions.Question;
/**
 *
 * @author devinbost
 */
public class MainScreenController extends AbstractAppState implements ScreenController {
    private Nifty _nifty;
    private Screen _screen;
    private Main _gameApplication;
    private ViewPort viewPort;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private Node localRootNode = new Node("Start Screen RootNode");
    private Node localGuiNode = new Node("Start Screen GuiNode");
    private final ColorRGBA backgroundColor = ColorRGBA.Gray;  
    private AppStateManager _stateManager;
    private ScreenControlDisplayMediation _mediation;
    private String _gamerName;
    private Gamer _Gamer;
    private QandABot _QandABot = null;
    private int _lives;
    private Thread _mediatedTimerThread = null;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
        //this is called on the OpenGL thread after the AppState has been attached
        rootNode.attachChild(localRootNode);
        guiNode.attachChild(localGuiNode);
        viewPort.setBackgroundColor(backgroundColor);
        this._stateManager = stateManager;
 
    /** init the screen */    
        System.out.println("MainScreenController.initialize(AppStateManager stateManager, Application app) is being called here.");
    }
    public MainScreenController(Main gameApp){
        this._gameApplication = gameApp;
        System.out.println("MainScreenController(Main gameApp) is getting constructed here. gameApp is: " + gameApp.toString());
            this.rootNode     = gameApp.getRootNode();
            this.viewPort     = gameApp.getViewPort();
            this.guiNode      = gameApp.getGuiNode();
            this.assetManager = gameApp.getAssetManager();  
    }
    @Override
    public void update(float tpf) {
        //TODO: implement behavior during runtime
         /** any main loop action happens here */
//        if (screen.getScreenId().equals("hud")) {
//      Element niftyElement = nifty.getCurrentScreen().findElementByName("score");
//      // Display the time-per-frame -- this field could also display the score etc...
//      niftyElement.getRenderer(TextRenderer.class).setText((int)(tpf*100000) + ""); 
//    }
    }
 
    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);
        guiNode.detachChild(localGuiNode);
       
        super.cleanup();
        //TODO: clean up what you initialized in the initialize method,
        //e.g. remove all spatials from rootNode
        //this is called on the OpenGL thread after the AppState has been detached
    }
 
    public void bind(Nifty nifty, Screen screen) {
         System.out.println("The game has started (the MainScreenController's bind(Nifty nifty, Screen screen) method has been called.");
         System.out.println("In the MainScreenController's bind(Nifty nifty, Screen screen) method, this._nifty is: " + nifty.toString());
         this._nifty = nifty;
//         this._gameApplication._nifty = this._nifty;
         System.out.println("In the MainScreenController's bind(Nifty nifty, Screen screen) method, this._screen is: " + screen.toString());
         this._screen = screen;
       //  this._gameApplication._screen = this._screen;
         
        //throw new UnsupportedOperationException("Not supported yet.");
        //gameApplication._nifty = this._nifty;
        
        //gameApplication._screen = this._screen;
    }
 
    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("MainScreenController.onStartScreen() is being called here.");
    }
    /**
     * This method is invoked when a gamer presses a button on the nifty GUI that invokes the method.
     * It would also be useful to catch the IllegalArgumentException to display a warning screen rather than crashing the game.
     * @param nextScreen 
     */
    public void startGame(String nextScreen){
        // nifty.gotoScreen(nextScreen); // switch to another screen.
        System.out.println("MainScreenController.startGame() is being called here.");
        System.out.println("MainScreenController.startGame() is executing this._gameApplication.start().");
        // Get Gamer Name text field and set it to a property here. 
       // this.onEndScreen();
        // this._stateManager.detach(this);
        //this._nifty.gotoScreen(nextScreen);  // switch to another screen
        final Screen startScreen = this._nifty.getScreen("startGameScreen");
        String controlName = "txtGamerName";
        TextField txtScoreField = startScreen.findNiftyControl(controlName, TextField.class);
        if (txtScoreField == null) {
            throw new UnknownControlException("The unknown control was: " + controlName + " . It was called from MainScreenController.startGame(..).");
        }
        _gamerName = txtScoreField.getRealText();
        if ("".equals(_gamerName)) {
            throw new IllegalArgumentException("You must enter a name for the gamer!!");
        }
        System.out.println("Gamer's name is: " + _gamerName);
        int difficulty = 0;
        _Gamer = new Gamer(difficulty, 3, _gamerName);
        _QandABot = new QandABot(_Gamer);  // we could also queue up the QandABot elsewhere instead.
        this._nifty.gotoScreen(nextScreen);  // switch to another screen
        
       
    }
    /**
     * This method is used to update the HUD with the next math question.
     */
    public void getNextQuestion(){
        String questionText = "What is " + _QandABot.GetNextQuestion() + " ?";
        // Set a control to this text.
        final Screen hudScreen = this._nifty.getScreen("hudScreen");
        
        String control2Name = "lblLives";
        Label lblLives = hudScreen.findNiftyControl(control2Name, Label.class);
        if (lblLives == null) {
            throw new UnknownControlException("The unknown control was: " + control2Name + " . It was called from MainScreenController.startGame(..).");
        }
        if ("Lives".equals(lblLives.getText())) { // i.e. if this was the first question.
            lblLives.setText("5"); // Start with 5 lives.
        }
        
        
        String controlName = "lblNextQuestion";
        Label lblNextQuestion = hudScreen.findNiftyControl(controlName, Label.class);
        lblNextQuestion.setText(questionText);
        try{
            this.getScoreText();
        }
        catch (InterruptedException ex){
            System.out.println("The timer has been interrupted.");
            _mediation.StopTimer();
        }
    }
    public void checkAnswer(){
        final Screen hudScreen = this._nifty.getScreen("hudScreen");
        String controlName = "txtAnswer";
        TextField txtAnswer = hudScreen.findNiftyControl(controlName, TextField.class);
        String answerValue = txtAnswer.getRealText();
        if (answerValue.matches(".*[a-zA-Z]+.*")) {
            throw new IllegalArgumentException("Error: The answer cannot contain letters! It must be a number (e.g. 14 or 21.59)!");
        }
        double userAnswer = Double.parseDouble(answerValue);
        Question currentQuestion = _QandABot.GetCurrentQuestion();
        
        Label lblNextQuestion;
        if(currentQuestion.CheckAnswer(userAnswer) == true){
            _Gamer.ScoreUp();
            lblNextQuestion = hudScreen.findNiftyControl("lblNextQuestion", Label.class);
            lblNextQuestion.setText("Correct!!!");
            // Update score field.
            Label lblScore = hudScreen.findNiftyControl("txtScore", Label.class);
            lblScore.setText(Integer.toString(_Gamer.GetScore()));
            // we should compute the score from the remaining time here and update the score field.
            _mediation.StopTimer();
            
        }
        else {
            lblNextQuestion = hudScreen.findNiftyControl("lblNextQuestion", Label.class);
            lblNextQuestion.setText("Wrong!!!");
            
            String control2Name = "lblLives";
            Label lblLives = hudScreen.findNiftyControl(control2Name, Label.class);
            if (lblLives == null) {
                throw new UnknownControlException("The unknown control was: " + control2Name + " . It was called from MainScreenController.startGame(..).");
            }
            String livesString = lblLives.getText();
            int livesInt = Integer.parseInt(livesString);
            livesInt--;
            lblLives.setText(Integer.toString(livesInt));
            if (livesInt < 0) {
                // game over.
                this._Gamer.GamerLoses();
                this.quitGame();
            }
            // subtract from lives.
        }
    }
   public void quitGame() {
        this._gameApplication.stop();
        cleanup();
        _mediation.StopTimer();
        _nifty.exit();
         System.exit(0);
  }
    @Override
    public void onEndScreen() {
       // throw new UnsupportedOperationException("Not supported yet.");
        cleanup();
        System.out.println("MainScreenController.onEndScreen() is being called here.");
    }
    /**
     * This method is called from a button on the HUD. We really should move the contents of this method to something more appropriate,
     * such as when the gamer obtains the next question.
     * @throws InterruptedException 
     */
    public void getScoreText() throws InterruptedException{
        System.out.println("MainScreenController.getScoreText() is being called here.");
        // Construct an instance of ScreenControlDisplayMediator here.
        final Screen hudScreen = this._nifty.getScreen("hudScreen");
        Element myGuiElement = hudScreen.findElementByName("panel_top_right"); // pass the ID of the element.
        // We're assuming that "panel_top_right" has a text element.
        //Label txtScoreField = hudScreen.findNiftyControl("txtLives", Label.class);
        //String scoreText = txtScoreField.getDisplayedText();
        _mediatedTimerThread = new Thread(new Runnable() {
         public void run()
         {
              _mediation = ScreenControlDisplayMediationFactory.Make(EventTypeEnum.CountdownTick, "txtTimer", hudScreen);
         }
        });
        _mediatedTimerThread.start();
        System.out.println("MainScreenController.getScoreText() is at the end.");
        //Thread.sleep(10000);
    }
    /*
     * Add a setter method to this ScreenController that allows us to pass it an interface.
     * Use the interface to pass observers for the timer,
     * scoreUpdater, levelUpdater, .
     * 
     */
}