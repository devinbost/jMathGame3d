/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.GameTests;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import java.util.Collection;
import mygame.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author devinbost
 */
public class ConstructorTests {
   
    public ConstructorTests() {
    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void SharedConstructors_IsConstructorWorking_ReturnsTrue(){
//        Main mockedMainRootNode = mock(mygame.Main.class);
//        Node rootNode = mockedMainRootNode.getRootNode();
//        BulletAppState appState = mockedMainRootNode.getBulletAppState();
        System.out.println("Running SharedConstructors_IsConstructorWorking_ReturnsTrue()");
        BulletAppState appState = mock(BulletAppState.class);
        Node rootNode = mock(Node.class);
        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
        assertTrue(constructors instanceof SharedConstructors);
        
    }
    @Test
    public void SharedConstructors_IsMakeBrickConstructingBrick_ReturnsTrue(){
//        Main mockedMainRootNode = mock(mygame.Main.class);
//        Node rootNode = mockedMainRootNode.getRootNode();
//        BulletAppState appState = mockedMainRootNode.getBulletAppState();
        System.out.println("Running SharedConstructors_IsMakeBrickConstructingBrick_ReturnsTrue()");
        //Application app = mock(Application.class);
        TestMain app = new TestMain();
        AssetManager assetManager = app.getAssetManager();
        
        BulletAppState appState = mock(BulletAppState.class);
        Node rootNode = mock(Node.class);
        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
        Vector3f loc = new Vector3f(0,0,0); // set vector to origin
        Box box = new Box(); // setup box
            box = new Box(4f, 4f, 4f); // set length, height, and width of box.
            box.scaleTextureCoordinates(new Vector2f(1f, .5f));
        Material wall_mat = new Material(); // setup material
            wall_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
            key.setGenerateMips(true);
            Texture tex = assetManager.loadTexture(key);
            wall_mat.setTexture("ColorMap", tex);
        RigidBodyControl brick_phy = new RigidBodyControl();
        constructors.makeBrick(loc,  box,  wall_mat,  brick_phy);
        // Now, check the bulletAppState's physics space to determine if it contains the new brick.
        constructors.getBulletAppState().getPhysicsSpace().add(brick_phy);
        Collection<PhysicsRigidBody> physicsBodies = constructors.getBulletAppState().getPhysicsSpace().getRigidBodyList();
        Boolean testSucceeded = false;
        for (PhysicsRigidBody body : physicsBodies) {
            if (body.equals(brick_phy)) {
                testSucceeded = true; // only one PhysicsRigidBody instance needs to match brick_phy to pass the test.
            }
        }
        assertTrue(testSucceeded);
        
    }
    @Test
    public void SharedConstructors_IsConstructBrickWallConstructingBricks_ReturnsTrue(){
//        Main mockedMainRootNode = mock(mygame.Main.class);
//        Node rootNode = mockedMainRootNode.getRootNode();
//        BulletAppState appState = mockedMainRootNode.getBulletAppState();
        System.out.println("Running SharedConstructors_IsConstructBrickWallConstructingBricks_ReturnsTrue()");
        BulletAppState appState = mock(BulletAppState.class);
        Node rootNode = mock(Node.class);
        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
        
        assertTrue(1 == 2);
        
    }
}
