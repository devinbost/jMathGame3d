/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.io.Serializable;

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
    private class PhysicalBrick implements Serializable{
        private Geometry _brick_geo = null;
        private RigidBodyControl _brick_phy = null;
        
        public PhysicalBrick(Vector3f loc, Box box, Material wall_mat){
            _brick_geo = new Geometry("brick", box);
            _brick_geo.scale(4f);
            _brick_geo.setMaterial(wall_mat);
            /** Position the brick geometry  */
            _brick_geo.setLocalTranslation(loc);
            /** Make brick physical with a mass > 0.0f. */
            CollisionShape brick_collision = CollisionShapeFactory.createBoxShape(_brick_geo);
            _brick_phy = new RigidBodyControl(4f);
            //brick_phy.getCollisionShape().setScale(new Vector3f(2,2,2));
            _brick_phy.setCollisionShape(brick_collision);
            _brick_phy.setFriction(2f);
            _brick_phy.setDamping(.2f, .2f);
            //brick_phy.getCollisionShape().setScale(new Vector3f(2,2,2)); // Won’t work as this is now a CompoundCollisionShape containing a MeshCollisionShape
            /** Add physical brick to physics space. */
            _brick_geo.addControl(_brick_phy);
            // You must scale the geometry for the scale change to work.
            
            _brick_geo.getControl(RigidBodyControl.class).setCcdMotionThreshold(0.01f); // Do this if needed.
            //this.getBulletAppState().getPhysicsSpace().setMaxSubSteps(2);
        }
        public RigidBodyControl getBrick_phy(){
            return this._brick_phy;
        }
        public Geometry getBrick_geo(){
            return this._brick_geo;
        }
        public void setBrick_phy(RigidBodyControl brick_phy){
            this._brick_phy = brick_phy;
        }
        public void setBrick_geo(Geometry brick_geo){
            this._brick_geo = brick_geo;
        }
        @Override
        public String toString(){
            return "This is a physical brick.";
        }
        public boolean equals(PhysicalBrick brick){
            if (this._brick_geo == brick._brick_geo && this._brick_phy == brick._brick_phy) {
                return true;
            }
            else{
                return false;
            }
        }
        
    }
    public void makePhysicalBrick(Vector3f loc, Box box, Material wall_mat){
        PhysicalBrick phyBrick = new PhysicalBrick(loc, box, wall_mat);
        this.getRootNode().attachChild(phyBrick.getBrick_geo());
        this.getBulletAppState().getPhysicsSpace().add(phyBrick.getBrick_phy());
        this.getBulletAppState().getPhysicsSpace().setAccuracy(1f/80f);// Specifies physics accuracy. The higher the accuracy, the slower the game. Increase value if objects are passing through one another, or bounce oddly.
    }
//    /** This method creates one individual physical brick. */
//public void makeBrick(Vector3f loc, Box box, Material wall_mat, RigidBodyControl brick_phy) {
//    /** Create a brick geometry and attach to scene graph. */
//    Geometry brick_geo = new Geometry("brick", box);
//    brick_geo.scale(4f);
//    brick_geo.setMaterial(wall_mat);
//    this.getRootNode().attachChild(brick_geo);
//    /** Position the brick geometry  */
//    brick_geo.setLocalTranslation(loc);
//    /** Make brick physical with a mass > 0.0f. */
//    CollisionShape brick_collision = CollisionShapeFactory.createBoxShape(brick_geo);
//    brick_phy = new RigidBodyControl(4f);
//    //brick_phy.getCollisionShape().setScale(new Vector3f(2,2,2));
//    brick_phy.setCollisionShape(brick_collision);
//    brick_phy.setFriction(2f);
//    brick_phy.setDamping(.2f, .2f);
//    //brick_phy.getCollisionShape().setScale(new Vector3f(2,2,2)); // Won’t work as this is now a CompoundCollisionShape containing a MeshCollisionShape
//    /** Add physical brick to physics space. */
//    brick_geo.addControl(brick_phy);
//    // You must scale the geometry for the scale change to work.
//    //brick_geo.getControl(RigidBodyControl.class).getCollisionShape().setScale(new Vector3f(2,2,2)); // Now it should work.
//    this.getBulletAppState().getPhysicsSpace().add(brick_phy);
//    //this.getBulletAppState().getPhysicsSpace().add(brick_collision); // can’t add this object to the physics space
//    this.getBulletAppState().getPhysicsSpace().setAccuracy(1f/80f); // Specifies physics accuracy. The higher the accuracy, the slower the game. Increase value if objects are passing through one another, or bounce oddly.
//    brick_geo.getControl(RigidBodyControl.class).setCcdMotionThreshold(0.01f); // Do this if needed.
//    //this.getBulletAppState().getPhysicsSpace().setMaxSubSteps(2);
// 
//  }
  // how do I check if the bulletAppState contains the RigidBodyControl?
  public void makeBrickWall(Box box, Material wall_mat, RigidBodyControl brick_phy) {
    float startpt = brickLength / 4;
    float height = 0;
    for (int j = 0; j < 15; j++) {
      for (int i = 0; i < 6; i++) {
        Vector3f vt =
         new Vector3f(i * brickLength * brickMultiplier * 2 + startpt, brickHeight * brickMultiplier + 40 + height, 0);
        //makeBrick(vt, box, wall_mat, brick_phy);
        makePhysicalBrick(vt, box, wall_mat);
      }
      startpt = -startpt;
      height += 2 * brickHeight * brickMultiplier;
    }
  }
}
