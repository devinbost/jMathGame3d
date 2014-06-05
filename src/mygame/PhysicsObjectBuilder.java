/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author devinbost
 */
public abstract class PhysicsObjectBuilder extends AbstractBuilder {
    BulletAppState _bulletAppState;
    public PhysicsObjectBuilder(BulletAppState bulletAppState, AssetManager assetManager, String modelPath){
        super(assetManager, modelPath);
        if (bulletAppState == null) {
            throw new IllegalArgumentException();
        }
        this._bulletAppState = bulletAppState;
    }
    @Override
    public void Build() {
        Spatial objectSpatial = super._assetManager.loadModel(super._modelPath);
        Node objectNode = (Node)objectSpatial;
        Node objectWrapperNode = new Node();
        objectWrapperNode.attachChild(objectNode);
        this._bulletAppState.getPhysicsSpace().addAll(objectWrapperNode);
        // Create the collision shape.
        this.ConstructPhysicsControl();
//        cannon.move(5f,-3.5f,8f);
//        cannon.setLocalScale(5f);
//        DirectionalLight dl = new DirectionalLight();
//        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
//        cannon.addLight(dl);
//        
//        rootNode.attachChild(cannon);
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public abstract void ConstructPhysicsControl();
}
