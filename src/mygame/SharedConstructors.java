/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author devinbost
 */
public class SharedConstructors {
    // TO DO: Set this up to use constructor injection. Use DI to pass in the rootNode and BulletAppState.
    private static final float brickLength = 0.48f;
    private static final float brickWidth  = 0.24f;
    private static final float brickHeight = 0.12f;
    private static final float brickMultiplier = 5f;
    
    private BulletAppState _bulletAppState;
    private Node _rootNode;
    public SharedConstructors(Node rootNode, BulletAppState bulletAppState){
        if (rootNode == null || bulletAppState == null) {
            throw new IllegalArgumentException("ERROR: parameter in SharedConstructors construction cannot be null!");
        }
        this._bulletAppState = bulletAppState;
        this._rootNode = rootNode;
    }
    public Node getRootNode(){
        return this._rootNode;
    }
    public BulletAppState getBulletAppState(){
        return this._bulletAppState;
    }
//    private static Node _rootNode = new Node();
//    private static BulletAppState _bulletAppState = new BulletAppState();
//    private static final SharedConstructors _sharedConstructors = new SharedConstructors();
//    private SharedConstructors(){}
//    public static SharedConstructors getInstance(){
//        return _sharedConstructors;
//    } // How in the world do I allow the singleton to require to have its root node set when constructed???
//    public static void setRootNode(){
//        
//    }
    /** This method creates one individual physical brick. */
  public void makeBrick(Vector3f loc, Box box, Material wall_mat, RigidBodyControl brick_phy) {
    /** Create a brick geometry and attach to scene graph. */
    Geometry brick_geo = new Geometry("brick", box);
    brick_geo.setMaterial(wall_mat);
    this.getRootNode().attachChild(brick_geo);
    /** Position the brick geometry  */
    brick_geo.setLocalTranslation(loc);
    /** Make brick physical with a mass > 0.0f. */
    brick_phy = new RigidBodyControl(4f);
    //brick_phy.getCollisionShape().setScale(new Vector3f(2,2,2)); // Won't work as this is now a CompoundCollisionShape containing a MeshCollisionShape
    /** Add physical brick to physics space. */
    brick_geo.addControl(brick_phy);
    // You must scale the geometry for the scale change to work.
    brick_geo.getControl(RigidBodyControl.class).getCollisionShape().setScale(new Vector3f(2,2,2)); // Now it should work.
    this.getBulletAppState().getPhysicsSpace().add(brick_phy);
    
//    this.getBulletAppState().getPhysicsSpace().setAccuracy(1f/60f); // Specifies physics accuracy. The higher the accuracy, the slower the game. Increase value if objects are passing through one another, or bounce oddly.
    brick_geo.getControl(RigidBodyControl.class).setCcdMotionThreshold(0.1f); // Do this if needed.
    //this.getBulletAppState().getPhysicsSpace().setMaxSubSteps(2);
   
  }
  // how do I check if the bulletAppState contains the RigidBodyControl?
  public void makeBrickWall(Box box, Material wall_mat, RigidBodyControl brick_phy) {
    float startpt = brickLength / 4;
    float height = 0;
    for (int j = 0; j < 15; j++) {
      for (int i = 0; i < 6; i++) {
        Vector3f vt =
         new Vector3f(i * brickLength * brickMultiplier * 2 + startpt, brickHeight * brickMultiplier + 40 + height, 0);
        makeBrick(vt, box, wall_mat, brick_phy);
      }
      startpt = -startpt;
      height += 2 * brickHeight * brickMultiplier;
    }
  }
}
