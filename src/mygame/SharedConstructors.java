/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
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
      float scaleFactor = 4f;
    /** Create a brick geometry and attach to scene graph. */
    Geometry brick_geo = new Geometry("brick", box);
    brick_geo.setMaterial(wall_mat);
    brick_geo.scale(scaleFactor); // increase brick size
    this.getRootNode().attachChild(brick_geo);
    /** Position the brick geometry  */
    brick_geo.setLocalTranslation(loc);
    /** Make brick physical with a mass > 0.0f. */
    brick_phy = new RigidBodyControl(2f);
    /** Add physical brick to physics space. */
    brick_geo.addControl(brick_phy);
    
    // Get collision shape to scale the object's size;
    CollisionShape brick_shape = brick_phy.getCollisionShape();
    brick_shape.setScale(brick_shape.getScale().mult(2f));
    this.getBulletAppState().getPhysicsSpace().add(brick_phy);
  }
  // how do I check if the bulletAppState contains the RigidBodyControl?
  public void makeBrickWall(Box box, Material wall_mat, RigidBodyControl brick_phy) {
    float startpt = brickLength / 4;
    float height = 0;
    for (int j = 0; j < 15; j++) {
      for (int i = 0; i < 6; i++) {
        Vector3f vt =
         new Vector3f(i * brickLength * 2 + startpt, brickHeight + height, 0);
        makeBrick(vt, box, wall_mat, brick_phy);
      }
      startpt = -startpt;
      height += 2 * brickHeight;
    }
  }
}
