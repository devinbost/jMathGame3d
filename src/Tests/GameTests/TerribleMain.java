/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.GameTests;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
/**
 *
 * @author devinbost
 */
public class TerribleMain extends SimpleApplication {

    public static void main(String[] args){
        TerribleMain app = new TerribleMain(); // I hate Java. That's why this is a Terrible Main.
        app.start(); // start the game
    }
    @Override
    public void simpleInitApp() {
         Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube appear in the scene
    }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
