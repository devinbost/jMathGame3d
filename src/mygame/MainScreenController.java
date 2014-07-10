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
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
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
//        
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
    public void startGame(String nextScreen){
        // nifty.gotoScreen(nextScreen); // switch to another screen.
        System.out.println("MainScreenController.startGame() is being called here.");
        System.out.println("MainScreenController.startGame() is executing this._gameApplication.start().");
       // this.onEndScreen();
        // this._stateManager.detach(this);
        //this._nifty.gotoScreen(nextScreen);  // switch to another screen
        this._nifty.gotoScreen(nextScreen);  // switch to another screen
        
    }
   public void quitGame() {
    this._gameApplication.stop();
    cleanup();
  }
    public void onEndScreen() {
       // throw new UnsupportedOperationException("Not supported yet.");
        // app.stop();
        //this._gameApplication.stop();
         cleanup();
        System.out.println("MainScreenController.onEndScreen() is being called here.");
    }
    public void getScoreText(){
        System.out.println("MainScreenController.getScoreText() is being called here.");
        Screen hudScreen = this._nifty.getScreen("hudScreen");
        Element myGuiElement = hudScreen.findElementByName("panel_top_right"); // pass the ID of the element.
        // We're assuming that "panel_top_right" has a text element.
        TextField txtScoreField = hudScreen.findNiftyControl("txtLives", TextField.class);
        String scoreText = txtScoreField.getDisplayedText();
    }
    /*
     * Add a setter method to this ScreenController that allows us to pass it an interface.
     * Use the interface to pass observers for the timer,
     * scoreUpdater, levelUpdater, .
     * 
     */
}