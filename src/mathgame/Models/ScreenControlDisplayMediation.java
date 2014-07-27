/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.NiftyControl;
import de.lessvoid.nifty.controls.*;
import de.lessvoid.nifty.screen.Screen;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 *
 * @author devinbost
 * This class uses a set of objects that implement the IUpdateNiftyGuiControlDisplayText interface to update a set of controls.
 * Each Mediation specifically maps an observable object to a Nifty GUI control. 
 * Each Mediator is scoped to a specific ScreenController.
 */
//public class ScreenControlDisplayMediation {
//    private Nifty _niftyGui;
//    private String _screenControllerName;
//    private Class<? extends NiftyControl> _niftyControl;
//
//    public void setNiftyControl(Class<? extends NiftyControl> _niftyControl) {
//        this._niftyControl = _niftyControl;
//    }
//
//   private final Screen _hudScreen;
//
//    public Class<? extends NiftyControl> getNiftyControl() {
//        return _niftyControl;
//    }
//
//    public Nifty getNiftyGui() {
//        return _niftyGui;
//    }
//
//    public void setNiftyGui(Nifty _niftyGui) {
//        this._niftyGui = _niftyGui;
//    }
//
//    public String getScreenControllerName() {
//        return _screenControllerName;
//    }
//
//    public void setScreenControllerName(String _screenControllerName) {
//        this._screenControllerName = _screenControllerName;
//    }
//    public ScreenControlDisplayMediation(Nifty niftyGui, String screenControllerName) {
//        Screen hudScreen = niftyGui.getScreen(screenControllerName); // e.g. "hudScreen"
//        if (hudScreen == null) {
//            throw new IllegalArgumentException("Error: The screenControllerName provided for the ScreenControlDisplayMediator constructor is null!");
//        }
//        this._hudScreen = hudScreen;
//        this._niftyGui = niftyGui;
//        this._screenControllerName = screenControllerName;
//    }
////    public <T extends NiftyControl> void setNiftyControl(Class<T> niftyGuiControl){
////        this.setNiftyControl(this._hudScreen.findNiftyControl(this.getScreenControllerName(), niftyGuiControl));
////        this._niftyControl.
////    }
//    
    /**
    *
    * @author devinbost
    * This class is used to map a specific observable Model to a specific Nifty GUI control (via IUpdateNiftyGuiControlDisplayText's 
    * UpdateNiftyControlDisplayText(..) method).
    * Each Mediation is scoped to a specific control on the ScreenController.
    * The Mediation is used to push updates from the observable object to that specific control.
    * This Mediation allows an observable object, for example, to automatically update the display text of a GUI TextField control or Label.
    * 
    */
   public class ScreenControlDisplayMediation implements PropertyChangeTypedListener {
        private IUpdateNiftyGuiControlDisplayText _screenControlMediator;
        private EventTypeEnum _eventType;
        private CountdownTimer _timer;
        private CountdownTimerTask _timerTask;

        public CountdownTimer getTimer() {
            return _timer;
        }
        public IUpdateNiftyGuiControlDisplayText getScreenControlMediator() {
            return _screenControlMediator;
        }

//        public void setScreenControlMediator(IUpdateNiftyGuiControlDisplayText _screenControlMediator) {
//            this._screenControlMediator = _screenControlMediator;
//        }
       /**
        * The 
        * @param screenControlMediator
        * @param eventType
        * @param updateControlListener 
        * This parameter is used to provide the specific type of listener used to update a control.
        * The factory must determine the specific type of PropertyChangeTypedListener to use.
        */
        public ScreenControlDisplayMediation(
                IUpdateNiftyGuiControlDisplayText screenControlMediator, 
                EventTypeEnum eventType,
                PropertyChangeTypedListener updaterListener) { // we need to replace this with an observable interface or something.
            this._screenControlMediator = screenControlMediator;
            this._eventType = eventType;
            this._timer = CountdownTimer.getInstance(); // Represents number of seconds to countdown from.
            this.Subscribe(updaterListener);
            //this._timer.addChangeListener(updaterListener);
            this.StartTimer();
            int availableTime = 1000;
            _timerTask = new CountdownTimerTask(_timer, "timerThread1", availableTime, 1000);
            System.out.println("ScreenControlDisplayMediation is getting constructed.");
            //this._timer.addChangeListener(this);
            
            
            //this._timer.StartCountdown();
        }
        /** We want to be able to set an enum during construction of this object to ensure that it only pushes
         * updates to the control for ONE specific eventType. (Otherwise, unrelated events will replace the display text for our control.)
         * 
         */
        
        @Override
        public void propertyChange(PropertyChangeTypedEvent evt) {
           IUpdateNiftyGuiControlDisplayText mediator = this.getScreenControlMediator();
           EventTypeEnum actualEventType = evt.getEventType();
           String displayText = evt.getNewValue().toString();
           String formattedDisplayText = displayText;
            if (actualEventType == this._eventType) {
                switch (actualEventType){
                    case CountdownTick: formattedDisplayText = "Time Remaining: " + new SimpleDateFormat("HH/mm/ss.SSS").format(displayText);
                        break;
                    case ScoreUpdate: formattedDisplayText = "Score: " + displayText;
                        break;
                    case RemainingLivesUpdate: formattedDisplayText = "Lives: " + displayText;
                        break;
                    case NpcDialogueUpdate: formattedDisplayText = "NPC says: " + displayText;
                        break;
                    case NextQuestion: formattedDisplayText = "Question is: " + displayText;
                        break;
//                    CountdownOutOfTime,
//                    GameOver,
//                    GamerAnswerSelected,
//                    CannonFired
                }
                // formattedDisplayText = "Time Remaining: " + new SimpleDateFormat("HH/mm/ss.SSS").format(displayText);
                
                mediator.SetControlDisplayText(formattedDisplayText);
            }
           
        }
        
        final public void StartTimer(){
            _timer.StartCountdown();
        }
        final public void StopTimer(){
            _timer.StopTimer();
        }
        final public void Subscribe(PropertyChangeTypedListener newListener ){
            _timer.addChangeListener(newListener);
            
        }
//        private String _controlElementName;
//        private NiftyControl _niftyControl;
//        public String getControlElementName() {
//            return _controlElementName;
//        }
//        
        // Take the text from the propertyChange event and pass it to the SetControlDisplayText method of a type of abstract mediator.
//        public void setControlElementName(String _controlElementName) {
//            this._controlElementName = _controlElementName;
//        }
//        public Class<T> getRequestedClassControl() {
//            return _niftyControl;
//        }
//        public void setRequestedClassControl(Class<T> niftyControl) {
//            this._niftyControl = niftyControl;
//        }
//        public ScreenControlDisplayMediation(String controlElementName, Class<T> niftyControl){
//           this._controlElementName = controlElementName;
//           this._niftyControl = requestedClassControl;
//        }
//        
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.propertyChange((PropertyChangeTypedEvent) evt);
    }

   

    

        
   }

