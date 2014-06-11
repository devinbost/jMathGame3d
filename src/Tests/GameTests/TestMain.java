/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.GameTests;

import mygame.Main;
import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.shadow.BasicShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import com.jme3.system.JmeContext.Type;
import de.lessvoid.nifty.Nifty;
import mygame.MainScreenController;

/**
 *
 * @author devinbost, Kirill
 * Test Application functionality, such as create, restart, destroy, etc.
 */

public class TestMain extends Main {

    private static TestMain testMain;
    
    public static void main(String[] args) {
        testMain = new TestMain();
        testMain.start(JmeContext.Type.Headless);
//        try {
//            System.out.println("Creating application..");
//        Application app = new Application();
//        System.out.println("Starting application in LWJGL mode..");
//        app.start();
//        System.out.println("Waiting 5 seconds");
//        Thread.sleep(5000);
//        System.out.println("Closing application..");
//        app.stop();
//
//        Thread.sleep(2000);
//        System.out.println("Starting in fullscreen mode");
//        app = new Application();
//        AppSettings settings = new AppSettings(true);
//        settings.setFullscreen(true);
//        settings.setResolution(-1,-1); // current width/height
//        app.setSettings(settings);
//        app.start();
//        Thread.sleep(5000);
//        app.stop();
//
//        Thread.sleep(2000);
//        System.out.println("Creating offscreen buffer application");
//        app = new Application();
//        app.start(Type.OffscreenSurface);
//        Thread.sleep(3000);
//        System.out.println("Destroying offscreen buffer");
//        app.stop();
//        
//        }
//        catch (InterruptedException e){
//            System.err.println("InterruptedException: " + e.getMessage());
//        }
    }
    @Override
    public AssetManager getAssetManager(){
        return testMain.assetManager;
    }
//    @Override
//    public void simpleInitApp(){
//        System.out.println("Main.simpleInitApp() is being called here.");
//        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
//        /** Create a new NiftyGUI object */
//        Nifty nifty = niftyDisplay.getNifty();
//        // How do I set  the screen here?
//        /** Read your XML and initialize your custom ScreenController */
//        MainScreenController screenController = new MainScreenController(this);
//        stateManager.attach(screenController);
//        // [...] boilerplate init nifty omitted
//        nifty.fromXml("Interface/startGameScreen.xml", "startGameScreen", screenController); 
//        nifty.addXml("Interface/hudScreen.xml");
//        //nifty.fromXml("Interface/hudScreen.xml", "hudScreen", screenController);
//        // attach the Nifty display to the gui view port as a processor
//        guiViewPort.addProcessor(niftyDisplay);
//        this._nifty = nifty;
//        System.out.println("In the Main.simpleInitApp() method, nifty is: " + nifty.toString());
//        
//        // Q: How do I determine if "startGame" is the correct string to pass the above method?
//        // A: I think this (second parameter) is the ID of the screen in the given file.
//        // 
////        nifty.fromXml("Interface/startGameScreen.xml", "startGame"); // Once I have a screencontroller, we will use the commented version
//        // nifty.fromXml("Interface/helloworld.xml", "start", new MySettingsScreenController(data));
//        
//        flyCam.setDragToRotate(true); // This may cause complications without additional on/off logic.
//        // if we have additional screens to load, just call them like this:
//        // nifty.fromXml("Interface/startGameScreen.xml", "startGame");
//        
//        // We need to call LoadGameFromScreen() next!! If it won't be called from a screen,
//        // then it needs to be called here instead!
//        
//        // Need to disable mouse cursor when ready to start actual game: mouseInput.setCursorVisible(false);
//        LoadGameFromScreen();
//        if (settings.getRenderer().startsWith("LWJGL")) {
//            BasicShadowRenderer bsr = new BasicShadowRenderer(assetManager, 512);
//            bsr.setDirection(new Vector3f(-0.5f, -0.3f, -0.3f).normalizeLocal());
//            viewPort.addProcessor(bsr);
//        }
    
}
