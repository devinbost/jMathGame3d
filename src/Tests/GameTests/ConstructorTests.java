/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.GameTests;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.JmeSystem;
import com.jme3.texture.Texture;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import java.awt.Desktop;
import java.util.Collection;
import mathgame.Models.CountdownTimer;
import mathgame.Models.ScreenControlDisplayMediationFactory;
import mygame.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.mockito.Mockito;
/**
 *
 * @author devinbost
 */
public class ConstructorTests {
//   public class SubMain extends Main{
//            
//        }
    public ConstructorTests() {
        
    }
    
//    @Test
//    public void ScreenControlDisplayMediationFactory_UpdatesGuiFromTimer_ReturnsTrue(){
//        //ScreenControlDisplayMediationFactory factory = new ScreenControlDisplayMediationFactory();
//        SubMain main = new SubMain();
//        main.start();
//        
////        AppStateManager appState = main.getAppStateManager();
//        Nifty myNifty = main.nifty;
//        Screen hudScreen = myNifty.getScreen("hudScreen");
//        ScreenControlDisplayMediationFactory.Make(EventTypeEnum.CountdownTick, "txtTimer", hudScreen);
//    }
//       @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void CountdownTimer_IsTimerTickWorkingProperly_ReturnsTrue(){
        CountdownTimer timer = CountdownTimer.getInstance();
        timer.StartCountdown();
        
    }
 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
//    public void SharedConstructors_IsConstructorWorking_ReturnsTrue(){
////        Main mockedMainRootNode = mock(mygame.Main.class);
////        Node rootNode = mockedMainRootNode.getRootNode();
////        BulletAppState appState = mockedMainRootNode.getBulletAppState();
//        System.out.println("Running SharedConstructors_IsConstructorWorking_ReturnsTrue()");
//        BulletAppState appState = mock(BulletAppState.class);
//        Node rootNode = mock(Node.class);
//        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
//        assertTrue(constructors instanceof SharedConstructors);
//        
//    }
//    @Test
//    public void TerribleMain_IsAssetManagerNotNull_ReturnsTrue(){
//        TerribleMain main = new TerribleMain();
//        AssetManager manager = main.getAssetManager();
//        assertThat(manager, is(not(null)));
//    }
//    @Test
//    public void TestMain_IsAssetManagerNotNull_ReturnsTrue(){
//         TestMain main = new TestMain();
//        AssetManager manager = main.getAssetManager();
//        System.out.println("WTF");
//        assertThat(manager, is(not(null)));
//    
//    }
//    @Test
//    public void TestBareBonesApp_IsAssetManagerNotNull_ReturnsTrue(){
//        TestBareBonesApp app = new TestBareBonesApp();
//        String[] args = null;
//        TestBareBonesApp.main(args);
//        
//        AssetManager mgr = app.getAssetManager();
//        System.out.println("WTF");
//        assertThat(mgr, is(not(null)));
//    }
//    @Test
//    public void SharedConstructors_IsMakeBrickConstructingBrick_ReturnsTrue(){
////        Main mockedMainRootNode = mock(mygame.Main.class);
////        Node rootNode = mockedMainRootNode.getRootNode();
////        BulletAppState appState = mockedMainRootNode.getBulletAppState();
//        System.out.println("Running SharedConstructors_IsMakeBrickConstructingBrick_ReturnsTrue()");
//        //Application app = mock(Application.class);
//        TestMain app = new TestMain();
//        Main myapp = new Main();
//        AssetManager assetManager = app.getAssetManager();
//       // AssetManager assetManager = JmeSystem.newAssetManager(); //Thread.currentThread().getContextClassLoader().getResource("com/jme3/asset/Desktop.cfg")
//         //AssetManager assetManager = new DesktopAssetManager(true);
////        assetManager.registerLocator("town.zip", ZipLocator.class.getName());
////        Spatial gameLevel = assetManager.loadModel("main.scene");
////        assetManager.loadModel("Scenes/main.scene");
//        
//        BulletAppState appState = mock(BulletAppState.class);
//        Node rootNode = mock(Node.class);
//        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
//        Vector3f loc = new Vector3f(0,0,0); // set vector to origin
//        Box box = new Box(); // setup box
//            box = new Box(4f, 4f, 4f); // set length, height, and width of box.
//            box.scaleTextureCoordinates(new Vector2f(1f, .5f));
//        //Material wall_mat = new Material(assetManager, "MatDefs/newMatDef.j3md");
////            Material mat = (Material) assetManager.loadAsset(
////                        new AssetKey("Common/Materials/RedColor.j3m"));
//             Material mat = (Material) assetManager.loadAsset(
//                        new AssetKey("jMonkeyProjects/BasicGame/assets/Materials/newMaterial.j3m"));
//          Material  wall_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); // throws NullReferenceException
//            TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
//            key.setGenerateMips(true);
//            Texture tex = assetManager.loadTexture(key);
//            wall_mat.setTexture("ColorMap", tex);
//        RigidBodyControl brick_phy = new RigidBodyControl();
//        constructors.makeBrick(loc,  box,  wall_mat,  brick_phy);
//        // Now, check the bulletAppState's physics space to determine if it contains the new brick.
//        constructors.getBulletAppState().getPhysicsSpace().add(brick_phy);
//        Collection<PhysicsRigidBody> physicsBodies = constructors.getBulletAppState().getPhysicsSpace().getRigidBodyList();
//        Boolean testSucceeded = false;
//        for (PhysicsRigidBody body : physicsBodies) {
//            if (body.equals(brick_phy)) {
//                testSucceeded = true; // only one PhysicsRigidBody instance needs to match brick_phy to pass the test.
//            }
//        }
//        assertTrue(testSucceeded);
//        
//    }
//    @Test
//    public void SharedConstructors_IsConstructBrickWallConstructingBricks_ReturnsTrue(){
////        Main mockedMainRootNode = mock(mygame.Main.class);
////        Node rootNode = mockedMainRootNode.getRootNode();
////        BulletAppState appState = mockedMainRootNode.getBulletAppState();
//        System.out.println("Running SharedConstructors_IsConstructBrickWallConstructingBricks_ReturnsTrue()");
//        BulletAppState appState = mock(BulletAppState.class);
//        Node rootNode = mock(Node.class);
//        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
//        
//        assertTrue(1 == 2);
//        
//    }
}
