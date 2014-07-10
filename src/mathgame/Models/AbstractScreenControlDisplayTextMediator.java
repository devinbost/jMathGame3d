/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.NiftyControl;
import de.lessvoid.nifty.screen.Screen;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author devinbost
 * Each subtype of this class should be used to specifically set the text property of a particular subtype of NiftyControl.
 * 
 */
public abstract class AbstractScreenControlDisplayTextMediator implements IUpdateNiftyGuiControlDisplayText {
    public AbstractScreenControlDisplayTextMediator(String _controlName, Screen _screenControllerInstance) {
    //public AbstractScreenControlDisplayTextMediator(String _controlName, String _screenControllerName) {
//        this.setControlName(_controlName);
//        this._screenControllerName = _screenControllerName;
        this._screenControllerInstance = _screenControllerInstance;
        if (this._screenControllerInstance == null) {
                throw new IllegalArgumentException("ERROR: The controlName, " + _screenControllerInstance + ", resulted in a null object when we tried to construct the AbstractScreenControlDisplayTextMediator object!");
            }
//        this._controlInstance = this.FindNiftyControl(_controlName);
    }
        private String _controlName;
        private NiftyControl _controlInstance;
        private String _controlDisplayText;
        private Screen _screenControllerInstance;
        private String _screenControllerName;

        public NiftyControl getControlInstance() {
            return _controlInstance;
        }

        public void setControlInstance(NiftyControl _controlInstance) {
            this._controlInstance = _controlInstance;
        }

        public String getControlDisplayText() {
            return _controlDisplayText;
        }

        public void setControlDisplayText(String _controlDisplayText) {
            this._controlDisplayText = _controlDisplayText;
        }

        public Screen getScreenControllerInstance() {
            return _screenControllerInstance;
        }

        public void setScreenControllerInstance(Screen _screenControllerInstance) {
            this._screenControllerInstance = _screenControllerInstance;
        }

        public String getScreenControllerName() {
            return _screenControllerName;
        }

        public void setScreenControllerName(String _screenControllerName) {
            this._screenControllerName = _screenControllerName;
        }
        
        public String getControlName() {
            return _controlName;
        }
//        private void setControlName(String controlName) {
//            this._controlName = controlName;
//            this._controlInstance = this.FindNiftyControl(controlName);
//            if (this._controlInstance == null) {
//                throw new IllegalArgumentException("ERROR: The controlName, " + controlName + ", resulted in a null object when we tried to locate it in the AbstractScreenControlDisplayTextMediator.FindNiftyControl(controlName) method!");
//            }
//        }
        
        @Override
        public abstract NiftyControl FindNiftyControl(String controlName);
//        @Override
//        public String GetControlDisplayText(){
//            return this.getControlDisplayText();
//        }
//        @Override
//        public void SetControlDisplayText(String textToDisplayOnControl){
//            this.setControlDisplayText(textToDisplayOnControl);
//        }
        @Override
        public abstract String GetControlDisplayText();
        @Override
        public abstract void SetControlDisplayText(String textToDisplayOnControl);
//        @Override
//        public abstract void SetControlDisplayText(String displayTextStringFormat, String textToDisplayOnControl);
        @Override
        public void SetNiftyScreenController(String screenControllerName){
            this.setScreenControllerName(screenControllerName);
        }
        @Override
        public Screen GetNiftyScreenController(){
            return this._screenControllerInstance;
        }     
        @Override
        public NiftyControl GetNiftyControlInstance() {
            return this.FindNiftyControl(this.getControlName());
        }
   }

