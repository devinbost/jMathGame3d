//package mygame;
//
//import com.jme3.app.SimpleApplication;
//import com.jme3.material.Material;
//import com.jme3.math.ColorRGBA;
//import com.jme3.math.Vector3f;
//import com.jme3.renderer.RenderManager;
//import com.jme3.scene.Geometry;
//import com.jme3.scene.shape.Box;

/**
 * test
 * @author Devin Bost
 */
//public class Main extends SimpleApplication { // This extends the Application class. The Application class represents a generic real-time 3D rendering jME3 application
//
//    public static void main(String[] args) {
//        Main app = new Main();
//        app.start();
//    }
//
//    @Override
//    public void simpleInitApp() {
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Red);
//        geom.setMaterial(mat);
//
//        rootNode.attachChild(geom);
//    }
//
//    @Override
//    public void simpleUpdate(float tpf) {
//        //TODO: add update code
//    }
//
//    @Override
//    public void simpleRender(RenderManager rm) {
//        //TODO: add render code
//    }
//}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author devinbost
 */
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.shape.Sphere;
import de.lessvoid.nifty.Nifty;
 
/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class Main extends SimpleApplication implements AnimEventListener
,        ActionListener 
{ // Make this class implement the ActionListener interface to customize the navigational inputs later.
    public Node player;
    public Spatial ninja;
    private AnimChannel channel;
    private AnimControl control;
    private AnimChannel animationChannel;
    private AnimChannel attackChannel;
    private AnimControl animationControl;
    private Node shootables;
    private Node inventory;
    Boolean isRunning=true;
    private Geometry mark;
    private BulletAppState bulletAppState;
    private RigidBodyControl landscape;
    private BetterCharacterControl playerControl;
    private Spatial gameLevel;
    // track directional input, so we can walk left-forward etc
    private boolean left = false, right = false, up = false, down = false;
    private Vector3f walkDirection = new Vector3f(0,0,0); // stop
    private float airTime = 0;
    private Vector3f oldPosition; // this may be deprecated.
    
    public static void main(String[] args){
        Main app = new Main();
        app.start(); // start the game
    }
    
    @Override
    public void simpleInitApp() {
         
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        /** Create a new NiftyGUI object */
        Nifty nifty = niftyDisplay.getNifty();
        /** Read your XML and initialize your custom ScreenController */
        nifty.fromXml("Interface/startGameScreen.xml", "startGame", new MainScreenController()); 
        // How do I determine if "startGame" is the correct string to pass the above method?
        // 
//        nifty.fromXml("Interface/startGameScreen.xml", "startGame"); // Once I have a screencontroller, we will use the commented version
        // nifty.fromXml("Interface/helloworld.xml", "start", new MySettingsScreenController(data));
        // attach the Nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);
        flyCam.setDragToRotate(true); // This may cause complications without additional on/off logic.
        // if we have additional screens to load, just call them like this:
        // nifty.fromXml("Interface/startGameScreen.xml", "startGame");
        
        bulletAppState = new BulletAppState(); // add the BulletAppState object to enable integration with jBullet's physical forces and collisions.
        stateManager.attach(bulletAppState); //  If you ever get confusing physics behaviour, remember to have a look at the collision shapes. Add the following line after the bulletAppState initialization to make the shapes visible:
        bulletAppState.getPhysicsSpace().enableDebug(assetManager);
        guiNode.detachAllChildren(); // be sure to reset guiNode BEFORE we load the crossHairs
         initCrossHairs(); // a "+" in the middle of the screen to help aiming
         initMark();       // a red sphere to mark the hit

        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
        inventory = new Node("Inventory");
        guiNode.attachChild(inventory);
        shootables.attachChild(makeCube("a Dragon", -2f, 0f, 1f));
        shootables.attachChild(makeCube("a tin can", 1f, -2f, 0f));
        shootables.attachChild(makeCube("the Sheriff", 0f, 1f, -12f));
        shootables.attachChild(makeCube("the Deputy", 10f, 0f, -4f));
        
        flyCam.setMoveSpeed(50); // make the camera move at a managable speed
        
       //  Create a blue box at coordinates (1, -1, 1)
        Geometry blueBox = ConstructBox(ColorRGBA.Blue);
        rootNode.attachChild(blueBox);              // make the cube appear in the scene
        Geometry redBox = ConstructBox(ColorRGBA.Red);
        // Create a pivot node at (0,0,0) and attach it to the root node.
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot);
        pivot.attachChild(blueBox); // Attach the two boxes to the pivot node. (And transitively to the root node.)
        pivot.attachChild(redBox);
        pivot.rotate(.4f, .4f, 0f);// rotate the pivot node: Note that both boxes have rotated.
        
        Spatial wall = ConstructWall();// Create a wall with a simple texture from test_data
        //rootNode.attachChild(wall);
        shootables.attachChild(wall);
        
        BitmapText helloText = ConstructGuiText();// Display a line of text with a default font
        guiNode.attachChild(helloText);
        
        // Load a model from test_data (OgreXML + material + texture)
        this.ConstructNinja();
        rootNode.attachChild(ninja);
        initKeys(); // load my custom keybinding
        // You must add light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, 1.0f));
        rootNode.addLight(sun);
        
        Spatial elephant = assetManager.loadModel("Models/Elephant/Elephant.mesh.xml"); // be sure to convert to .j3o file format for production. (see instructions here: http://hub.jmonkeyengine.org/wiki/doku.php/jme3:beginner:hello_asset)
        
        rootNode.attachChild(elephant);
    
        this.ConstructLevel();
        //rootNode.attachChild(gameLevel);
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);
        this.ConstructCharacter();
        
    }
//    private void setUpLight() {
//    // We add light so we see the scene
//    AmbientLight al = new AmbientLight();
//    al.setColor(ColorRGBA.White.mult(1.3f));
//    rootNode.addLight(al);
// 
//    DirectionalLight dl = new DirectionalLight();
//    dl.setColor(ColorRGBA.White);
//    dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
//    rootNode.addLight(dl);
//  }
//    private SkeletonDebugger ConstructSkeleton(){
//        SkeletonDebugger skeletonDebug = 
//         new SkeletonDebugger("skeleton", control.getSkeleton());
//     Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//     mat.setColor("Color", ColorRGBA.Green);
//     mat.getAdditionalRenderState().setDepthTest(false);
//     skeletonDebug.setMaterial(mat);
//     return skeletonDebug;
//    }
    private void ConstructCharacter(){
        Spatial playerSpatial = assetManager.loadModel("Models/Oto/Oto.mesh.xml");
        player =  (Node)playerSpatial;
        Node playerNode = new Node();
        playerNode.attachChild(player);
        player.move(0,3.5f,0);
        player.setLocalScale(0.5f);
        //rootNode.attachChild(player);
        /* Load the animation controls, listen to animation events,
     * create an animation channel, and bring the model in its default position.  */
////        control = player.getControl(AnimControl.class);
////        control.addListener(this);
////        channel = control.createChannel();
////        channel.setAnim("stand");
//        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
//        CharacterControl playerControl = new CharacterControl(capsuleShape, 0.01f);
        playerControl = new BetterCharacterControl(1.5f, 6f, 1f);
//        player.addControl(playerControl);
        playerNode.addControl(playerControl);
//        playerControl.setJumpSpeed(10f);
        playerControl.setJumpForce(new Vector3f(0,5f,0));
//        playerControl.setFallSpeed(20f);
//        playerControl.setGravity(1f);
        playerControl.setGravity(new Vector3f(0,1f,0));
        // Finally we put the player in its starting position and update its state – remember to use setPhysicsLocation() instead of setLocalTranslation() now, since you are dealing with a physical object.
        playerControl.warp(new Vector3f(0,10,10));
        // We need to register all solid objects to the PhysicsSpace!
        bulletAppState.getPhysicsSpace().add(playerControl);
        bulletAppState.getPhysicsSpace().addAll(playerNode);
        rootNode.attachChild(playerNode);
//        SkeletonDebugger skeletonDebug = ConstructSkeleton();
//        player.attachChild(skeletonDebug);
        
         animationControl = player.getControl(AnimControl.class);
        animationControl.addListener(this);
        animationChannel = animationControl.createChannel();
        attackChannel = animationControl.createChannel();
        attackChannel.addBone(animationControl.getSkeleton().getBone("uparm.right"));
        attackChannel.addBone(animationControl.getSkeleton().getBone("arm.right"));
        attackChannel.addBone(animationControl.getSkeleton().getBone("hand.right"));
    }
    private void ConstructLevel(){
        assetManager.registerLocator("town.zip", ZipLocator.class);
        gameLevel = assetManager.loadModel("main.scene"); // be sure to give new names to new scenes
        gameLevel.setLocalTranslation(0, -5.2f, 0); // move the level down by 5.2 units
        gameLevel.setLocalScale(2f);
        // Wrap the scene in a rigidbody collision object.
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape((Node) gameLevel);
        landscape = new RigidBodyControl(sceneShape, 0);
        gameLevel.addControl(landscape);
        bulletAppState.getPhysicsSpace().addAll(gameLevel);
        // Now attach it.
        shootables.attachChild(gameLevel); // should we add the shootables to the bulletAppState?
    }
    private void ConstructNinja(){
        ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
    }
    private BitmapText ConstructGuiText(){
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        return helloText;
    }
    private Geometry ConstructBox(ColorRGBA color){
        Box box1 = new Box(1, 1, 1); // create cube shape
        Geometry blueBox = new Geometry("Box", box1);  // create cube geometry from the shape
        blueBox.setLocalTranslation(new Vector3f(1, -1, 1));
        Material blueBoxMaterial = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        blueBoxMaterial.setColor("Color", color.Blue);   // set color of material to blue
        blueBox.setMaterial(blueBoxMaterial);                   // set the cube's material
        return blueBox;
    }
    private Spatial ConstructWall(){
        // Create a wall with a simple texture from test_data
        Box superBox = new Box(2.5f, 2.5f, 1.0f);
        Spatial wall = new Geometry("Box", superBox);
        Material mat_brick = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap", 
                assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        wall.setMaterial(mat_brick);
        wall.setLocalTranslation(2.0f, -2.5f, 0.0f);
        return wall;
    }
    @Override
    public void simpleUpdate(float tpf){ // "tpf" stands for "time per frame"
         Vector3f camDir = cam.getDirection().clone().multLocal(7.25f); // multLocal controls rate of movement multiplier.
        Vector3f camLeft = cam.getLeft().clone().multLocal(7.25f);
        camDir.y = 0;
        camLeft.y = 0;
        walkDirection.set(0, 0, 0);

        if (left)  walkDirection.addLocal(camLeft);
        if (right) walkDirection.addLocal(camLeft.negate());
        if (up) walkDirection.addLocal(camDir);
        if (down) walkDirection.addLocal(camDir.negate());

        if (!playerControl.isOnGround()) {
            airTime = airTime + tpf;
        } else {
            airTime = 0;
        }

        if (walkDirection.length() == 0) {
            if (!"stand".equals(animationChannel.getAnimationName())) {
              animationChannel.setAnim("stand", 1f);
            }
        } else {
            playerControl.setViewDirection(walkDirection);
            if (airTime > .3f) {
              if (!"stand".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("stand");
              }
            } else if (!"Walk".equals(animationChannel.getAnimationName())) {
              animationChannel.setAnim("Walk", 0.7f); // 0.7f controls rate of animation
            }
          }
        playerControl.setWalkDirection(walkDirection); // THIS IS WHERE THE WALKING HAPPENS
        
        // This method is where we update score, health, check for collisions, make enemies calculate next move, play sounds, roll dice for traps, etc.
        //ninja.rotate(0, 2*tpf, 0);
        /*
         * 
         * 
            Use the loop to poll the game state and then initiate actions.
            Use the loop to trigger reactions and update the game state.
            Use the loop wisely, because having too many calls in the loop also slows down the game.
            * 
            To prevent the size from becoming unmanagable:
*           Move code blocks from the simpleInitApp() method to AppStates.
            Move code blocks from the simpleUpdate() method to Custom Controls.
         * 
         */
        //Can you make a rolling cube? (rotate around the x axis, and translate along the z axis)
    }
    /** Custom Keybinding: Map named actions to inputs. */
  private void initKeys() {
      
    // configure mappings, e.g. the WASD keys
    inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_J));
    inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_L));
    inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_I));
    inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_K));
    inputManager.addMapping("CharJump", new KeyTrigger(KeyInput.KEY_RETURN));
    inputManager.addMapping("CharAttack", new KeyTrigger(KeyInput.KEY_O));
    // setup mapping that uses our override (via ActionListener interface):
    inputManager.addListener(this, "CharLeft", "CharRight");
    inputManager.addListener(this, "CharForward", "CharBackward");
    inputManager.addListener(this, "CharJump", "CharAttack");
    // You can map one or several inputs to one named action
    inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
//    inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_J));
//    inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_K));
//    inputManager.addMapping("RotateLeft", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//    inputManager.addMapping("RotateRight",  new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
    inputManager.addMapping("Run", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addMapping("Walk", new KeyTrigger(KeyInput.KEY_G));
    inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_B));
    inputManager.addListener(this, "Shoot");
//    inputManager.addListener(actionListener, "Walk", "Shoot", "Left", "Right", "Run","RotateLeft", "RotateRight");
//    // Add the names to the action listener.
//    inputManager.addListener(actionListener,"Pause"); // You register the pause action to the ActionListener, because it is an "on/off" action.
    //inputManager.addListener(analogListener, "RotateLeft", "RotateRight"); // You register the movement actions to the AnalogListener, because they are gradual actions.
 
    /* TO make character pickup an object, here are the steps:
     * 1. button is pressed
     * 2. We use ray casting to detect/target the object in front of the character.
     *      Aim ray in front of character
     *      detect collisions
     *      check for collisions less than particular distance
     * 3. If true, we use an actionListener to elevate the object and set isHoldingObject = true.
     * 4. If isHoldingObject == true, when character moves, the object also moves.
     * 5. We wire an actionListener to put down the object and set isHoldingObject = false.
     * 
     */
    
  }
//  public void onAction(String name, boolean isPressed, float tpf) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
  @Override
public void onAction(String binding, boolean value, float tpf) {
  if (binding.equals("CharLeft")) {
      if (value) left = true;
      else left = false;
  } else if (binding.equals("CharRight")) {
      if (value) right = true;
      else right = false;
  } else if (binding.equals("CharForward")) {
      if (value) up = true;
      else up = false;
  } else if (binding.equals("CharBackward")) {
      if (value) down = true;
      else down = false;
  } else if (binding.equals("CharJump"))
      playerControl.jump();
  if (binding.equals("CharAttack"))
      attack();
  
    if (binding.equals("Shoot")) { // Should we have something like [binding.equals("Shoot") && !keyPressed] here?
//              // 1. Reset results list
//              CollisionResults results = new CollisionResults();
//              // 2. Aim the ray from character location to character direction.
////                    Vector3f forward = new Vector3f(0,0,1.05f); // set the magnitude in front of char.
////                    Vector3f actualForward = new Vector3f();
////                    player.localToWorld(forward, actualForward);
////              Ray ray = new Ray(player.getLocalTranslation(), actualForward);
//              Ray ray = new Ray(cam.getLocation(), cam.getDirection());
//              // 3. Collect intersections between Ray and  grabbables in results list.
//              shootables.collideWith(ray, results);
//              System.out.println("----- Collisions? " + results.size() + "-----");
//                for (int i = 0; i < results.size(); i++) {
//                  // For each hit, we know distance, impact point, name of geometry.
//                  float dist = results.getCollision(i).getDistance();
//                  Vector3f pt = results.getCollision(i).getContactPoint();
//                  String hit = results.getCollision(i).getGeometry().getName();
//                  System.out.println("* Collision #" + i);
//                  System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
//                
//                // Next, we want to calculate and print the distance between our player's vector and
//                  // the colliding object's vector.
//                  Vector3f playerLocation = player.getLocalTranslation();
//                  float distanceBetweenPlayerAndCollision = playerLocation.distance(pt);
//                  System.out.println("The distance between your player and the collision is: " + distanceBetweenPlayerAndCollision +
//                          " world units (wu).");
//                    if (distanceBetweenPlayerAndCollision < 20 && !"level-geom-5".equals(hit)) { // (ensure we don't try to pick up the entire town)
//                        // i.e. If object is near player, then lift object (if object is not too heavy)
//                        Geometry collidingObject = results.getCollision(i).getGeometry();
//                        // i.e. Grab actual object from the collision.
//                        Vector3f objectLocation = collidingObject.getLocalTranslation();
//                        
//                        Vector3f moveUp = new Vector3f(0,1f,0);
//                        Vector3f actualMovement = new Vector3f();
//                        collidingObject.localToWorld(moveUp, actualMovement);
//                        collidingObject.setLocalTranslation(actualMovement);
//                        // we need to move the mark on the object
//                        // we need to check if the object is approximately in front of character.
//                        // we need to be able to put the object down
//                        // we need the picked-up object to move with the character
//                        
//                        /* Consider picking up the object into inventory:
//                         *  1. Create an inventory node to store the detached nodes temporarily.
//                            2. The inventory node is not attached to the rootNode.
//                            3. You can make the inventory visible by attaching the inventory node to the guiNode (which attaches it to the HUD). Note the following caveats:
//                                a. If your nodes use a lit Material (not "Unshaded.j3md"), also add a light to the guiNode.
//                                b. Size units are pixels in the HUD, therefor a 2-wu cube is displayed only 2 pixels wide in the HUD. – Scale it bigger!
//                                c. Position the nodes: The bottom left corner of the HUD is (0f,0f), and the top right corner is at (settings.getWidth(),settings.getHeight()).
//                         */
//                     }
//                }
                // THE IMPORTED CODE IS BELOW
                if (!inventory.getChildren().isEmpty())
              {
                  Spatial s1 = inventory.getChild(0);
                  // scale back
                  s1.scale(.02f);
                  s1.setLocalTranslation(oldPosition);
                  inventory.detachAllChildren();
                  shootables.attachChild(s1);
              }
              else
              {
                  CollisionResults results = new CollisionResults();
                  Ray ray = new Ray(cam.getLocation(), cam.getDirection());
                  shootables.collideWith(ray, results);

                  if (results.size() > 0)
                  {
                      CollisionResult closest = results.getClosestCollision();
                      Spatial s = closest.getGeometry();
                      // we cheat Model differently with simple Geometry
                      // s.parent is Oto-ogremesh when s is Oto_geom-1 and that is what we need
                      if (s.getName().equals("Oto-geom-1"))
                      {
                          s = s.getParent();
                      }
                      // It's important to get a clone or otherwise it will behave weird
                      oldPosition = s.getLocalTranslation().clone();
                      // We also need to ensure that the object can be actually picked up.
                      // To do this, we will need to check its mass and any other relevant properties.
                      // We also need a way to get the object to persist (in inventory) after the button is no longer being
                      // pressed. We also need a way to take the item out of inventory and put it in front of the 
                      // character.
                      
                      // We must check if the pickedUp boolean is true. If true, then remove the item from
                      // inventory and place it in front of the character (where cursor is placed).
                      
                      // Then, we need to add the animation in Blender.
                      shootables.detachChild(s);
                      inventory.attachChild(s);
                      // make it bigger to see on the HUD
                      s.scale(50f);
                      int width = settings.getWidth() - 100;
                      int height = settings.getHeight() - 100;
                      
                      // make it on the HUD center
                      s.setLocalTranslation(width, 100, 0);
//                      s.setLocalTranslation(settings.getWidth() / 2, settings.getHeight() / 2, 0);
                  }
              }
                    
    
      }
}
  private void attack() { // The player can attack and walk at the same time. attack() is a custom method that triggers an attack animation in the arms. Here you should also add custom code to play an effect and sound, and to determine whether the hit was successful.
    attackChannel.setAnim("Dodge", 0.1f); 
    attackChannel.setLoopMode(LoopMode.DontLoop); // this can be an attackChannel instead.
}
//  private ActionListener actionListener = new ActionListener(){
//      public void onAction(String name, boolean keyPressed, float tpf){
//          if (name.equals("Run")) {
//                   Vector3f forward = playerControl.getViewDirection();
//                   //Vector3f v = player.getLocalTranslation();
//                   //Vector3f forward = new Vector3f(0,0,.05f);
//                   Vector3f actualForward = new Vector3f();
//                   player.localToWorld(forward, actualForward);
//                    if (!channel.getAnimationName().equals("Walk")) {
//                        channel.setAnim("Walk", 0.50f);
//                        channel.setLoopMode(LoopMode.Loop);
//                    }
//                   //player.setLocalTranslation(actualForward);
//                   playerControl.setWalkDirection(keyPressed ? actualForward : Vector3f.ZERO);
//              }
//          if (name.equals("RotateLeft")) {
//              Vector3f forward = playerControl.getViewDirection();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f downVector = new Vector3f(0,-1,0);
//                   Vector3f leftVector = forward.cross(downVector).normalize();
//                   playerControl.setViewDirection(keyPressed ? leftVector : Vector3f.ZERO);
//          }
//          if (name.equals("RotateRight")) {
//              Vector3f forward = playerControl.getViewDirection();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f upVector = new Vector3f(0,1,0);
//                   Vector3f rightVector = forward.cross(upVector).normalize();
//                   playerControl.setViewDirection(keyPressed ? rightVector : Vector3f.ZERO);
//          }
//          if (name.equals("Left")) {
//                   Vector3f forward = player.getLocalTranslation();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f downVector = new Vector3f(0,-1,0);
//                   Vector3f leftVector = forward.cross(downVector).normalize();
//                   playerControl.setWalkDirection(keyPressed ? leftVector : Vector3f.ZERO);
//                   //playerControl.setWalkDirection(leftVector); 
//                   //Vector3f v = player.getLocalTranslation();
//                   //player.setLocalTranslation(v.x - value*speed, v.y, v.z);
//              }
//          if (name.equals("Right")) {
//                   Vector3f forward = player.getLocalTranslation();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f upVector = new Vector3f(0,1,0);
//                   Vector3f rightVector = forward.cross(upVector).normalize();
//                   playerControl.setWalkDirection(keyPressed ? rightVector : Vector3f.ZERO);
//                   //playerControl.setWalkDirection(rightVector);
//                   //player.setLocalTranslation(v.x + value*speed, v.y, v.z);
//              }
//          if (name.equals("Pause") && !keyPressed) {
//              isRunning = !isRunning;
//          }
//          if (name.equals("Walk") && !keyPressed) {
//              if (!channel.getAnimationName().equals("Walk")) {
//                  channel.setAnim("Walk", 0.50f);
//                  // How do we activate the analogListener for walk when this occurs?
//                  channel.setLoopMode(LoopMode.Loop);
//              }
//          }
//          if (name.equals("Shoot") && !keyPressed) {
//              // 1. Reset results list
//              CollisionResults results = new CollisionResults();
//              // 2. Aim the ray from character location to character direction.
////                    Vector3f forward = new Vector3f(0,0,1.05f); // set the magnitude in front of char.
////                    Vector3f actualForward = new Vector3f();
////                    player.localToWorld(forward, actualForward);
////              Ray ray = new Ray(player.getLocalTranslation(), actualForward);
//              Ray ray = new Ray(cam.getLocation(), cam.getDirection());
//              // 3. Collect intersections between Ray and  grabbables in results list.
//              shootables.collideWith(ray, results);
//              System.out.println("----- Collisions? " + results.size() + "-----");
//                for (int i = 0; i < results.size(); i++) {
//                  // For each hit, we know distance, impact point, name of geometry.
//                  float dist = results.getCollision(i).getDistance();
//                  Vector3f pt = results.getCollision(i).getContactPoint();
//                  String hit = results.getCollision(i).getGeometry().getName();
//                  System.out.println("* Collision #" + i);
//                  System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
//                
//                // Next, we want to calculate and print the distance between our player's vector and
//                  // the colliding object's vector.
//                  Vector3f playerLocation = player.getLocalTranslation();
//                  float distanceBetweenPlayerAndCollision = playerLocation.distance(pt);
//                  System.out.println("The distance between your player and the collision is: " + distanceBetweenPlayerAndCollision +
//                          " world units (wu).");
//                    if (distanceBetweenPlayerAndCollision < 20 && !"level-geom-5".equals(hit)) { // (ensure we don't try to pick up the entire town)
//                        // i.e. If object is near player, then lift object (if object is not too heavy)
//                        Geometry collidingObject = results.getCollision(i).getGeometry();
//                        // i.e. Grab actual object from the collision.
//                        Vector3f objectLocation = collidingObject.getLocalTranslation();
//                        
//                        Vector3f moveUp = new Vector3f(0,1f,0);
//                        Vector3f actualMovement = new Vector3f();
//                        collidingObject.localToWorld(moveUp, actualMovement);
//                        collidingObject.setLocalTranslation(actualMovement);
//                        // we need to move the mark on the object
//                        // we need to check if the object is approximately in front of character.
//                        // we need to be able to put the object down
//                        // we need the picked-up object to move with the character
//                        
//                        /* Consider picking up the object into inventory:
//                         *  1. Create an inventory node to store the detached nodes temporarily.
//                            2. The inventory node is not attached to the rootNode.
//                            3. You can make the inventory visible by attaching the inventory node to the guiNode (which attaches it to the HUD). Note the following caveats:
//                                a. If your nodes use a lit Material (not "Unshaded.j3md"), also add a light to the guiNode.
//                                b. Size units are pixels in the HUD, therefor a 2-wu cube is displayed only 2 pixels wide in the HUD. – Scale it bigger!
//                                c. Position the nodes: The bottom left corner of the HUD is (0f,0f), and the top right corner is at (settings.getWidth(),settings.getHeight()).
//                         */
//                        
//                        
//                    }
//                }
//                // 5. Use the results (we mark the hit object)
//                if (results.size() > 0) {
//                  // The closest collision ponit is what was truly hit:
//                    CollisionResult closest = results.getClosestCollision();
//                    // Let's interact - we mark the hit object with a red dot
//                    mark.setLocalTranslation(closest.getContactPoint());
//                    rootNode.attachChild(mark); // we need to attach to a container instead of the object
//                    // then, we can move the container when the object is picked up by the character
//                    
//                    
//                    
//                    
//              }else{
//                    // No hits? Then remove the red mark.
//                    rootNode.detachChild(mark);
//                }
//          }
//      }
//  };
  private AnalogListener analogListener = new AnalogListener(){
      public void onAnalog(String name, float value, float tpf){
          if (isRunning) {
//               if (name.equals("Right")) {
//                   Vector3f forward = player.getLocalTranslation();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f upVector = new Vector3f(0,1,0);
//                   Vector3f rightVector = forward.cross(upVector).normalize();
//                   playerControl.setWalkDirection(isPressed ? rightVector : Vector3f.zero);
//                   //playerControl.setWalkDirection(rightVector);
//                   //player.setLocalTranslation(v.x + value*speed, v.y, v.z);
//              }
//              if (name.equals("Left")) {
//                   Vector3f forward = player.getLocalTranslation();
//                   // right direction = cross product between forward and up (forward cross up)
//                   Vector3f downVector = new Vector3f(0,-1,0);
//                   Vector3f leftVector = forward.cross(downVector).normalize();
//                   playerControl.setWalkDirection(isPressed ? leftVector : Vector3f.zero);
//                   //playerControl.setWalkDirection(leftVector); 
//                   //Vector3f v = player.getLocalTranslation();
//                   //player.setLocalTranslation(v.x - value*speed, v.y, v.z);
//              }
              if (name.equals("RotateLeft")) {
                  
                  player.rotate(0, value*speed, 0);
              }
              if (name.equals("RotateRight")) {
                  
                  player.rotate(0, -value*speed, 0);
              }
             
              if (name.equals("Run")) {
                   Vector3f v = player.getLocalTranslation();
                   
                   // I can set the local translation to a vector instead.
                   // I would need to calculate the walk vector as a function of their direction?
                   // i.e. It's a function of x and z where x and z can sometimes be negative,
                   // depending on their direction. Thus, it has something to do with radial numbers.
                   Vector3f forward = new Vector3f(0,0,.05f);
                   Vector3f actualForward = new Vector3f();
                   player.localToWorld(forward, actualForward);
                   // actualForward corresponds to the actual forward direction, but how do we scale this up?
                   
                   // use lookAt to create a quaternion from an up vector and a look direction.
                   // use this resulting quaternion to rotate my vector.
                   Vector3f upVector = new Vector3f(0,1,0);
                  
                   Quaternion quat = new Quaternion();
                   //quat.lookAt(direction, upVector);
                   quat.lookAt(v, upVector);
                   // the resulting quaternion should rotate a vector that is perpendicular (around x) to the upVector.
                   // we take the vector of our applied values and use the quaternion to rotate it.
                   // The lookAt method available on SPATIALS generates a the direction vector
                   //   based on the world location of the spatial and the supplied
                   //   location vector.
                   // This allows us to quickly rotate the spatial towards a certain location.
                   if (!channel.getAnimationName().equals("Walk")) {
                        channel.setAnim("Walk", 0.50f);
                        // How do we activate the analogListener for walk when this occurs?
                        channel.setLoopMode(LoopMode.Loop);
                    }
                   //player.setLocalTranslation(actualForward);
                   playerControl.setWalkDirection(actualForward);
              }
              else {
                  System.out.println("Press P to unpause.");
              }
          
          }
      }
  };

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // reset the character to a standing position after a Walk cycle is done.
        if (channel == attackChannel) channel.setAnim("stand");
        
        if (animName.equals("Walk") || animName.equals("Run") || animName.equals("Right") || animName.equals("Left")) {
            channel.setAnim("stand", 0.50f);
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1f);
            
        }
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // unused
    }

    /** A cube object for target practice */
  protected Geometry makeCube(String name, float x, float y, float z) {
    Box box = new Box(1, 1, 1);
    Geometry cube = new Geometry(name, box);
    cube.setLocalTranslation(x, y, z);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", ColorRGBA.randomColor());
    cube.setMaterial(mat1);
    return cube;
  }
  /** A red ball that marks the last spot that was "hit" by the "shot". */
  protected void initMark() {
    Sphere sphere = new Sphere(30, 30, 0.2f);
    mark = new Geometry("BOOM!", sphere);
    Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mark_mat.setColor("Color", ColorRGBA.Red);
    mark.setMaterial(mark_mat);
  }
  /** A centred plus sign to help the player aim. */
  protected void initCrossHairs() {
    setDisplayStatView(false);
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+"); // crosshairs
    ch.setLocalTranslation( // center
      settings.getWidth() / 2 - ch.getLineWidth()/2, settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
    guiNode.attachChild(ch);
  }

    
}
