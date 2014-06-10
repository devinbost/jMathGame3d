/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.GameTests;

import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
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
        System.out.println("Running SharedConstructors_IsConstructorWorking_ReturnsTrue()");
        BulletAppState appState = mock(BulletAppState.class);
        Node rootNode = mock(Node.class);
        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
        
        assertTrue(1 == 2);
        
    }
    @Test
    public void SharedConstructors_IsInitWallConstructingBricks_ReturnsTrue(){
//        Main mockedMainRootNode = mock(mygame.Main.class);
//        Node rootNode = mockedMainRootNode.getRootNode();
//        BulletAppState appState = mockedMainRootNode.getBulletAppState();
        System.out.println("Running SharedConstructors_IsConstructorWorking_ReturnsTrue()");
        BulletAppState appState = mock(BulletAppState.class);
        Node rootNode = mock(Node.class);
        SharedConstructors constructors = new SharedConstructors(rootNode, appState);
        
        assertTrue(1 == 2);
        
    }
}