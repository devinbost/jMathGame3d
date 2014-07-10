/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.NiftyControl;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author Administrator
 */
public class LabelDisplayTextMediator extends AbstractScreenControlDisplayTextMediator{
    private String _displayText;
    private Label _lblText;
//    private String _displayTextStringFormat;
    private String _formattedDisplayText;
    
//    public TextFieldDisplayTextMediator(String _controlName, Screen _screenControllerInstance, String displayTextStringFormat) {
//        super(_controlName, _screenControllerInstance);
//        this.setControlInstance(this.FindNiftyControl(_controlName));
//        this._displayTextStringFormat = displayTextStringFormat;
//    }
    public LabelDisplayTextMediator(String _controlName, Screen _screenControllerInstance) {
//        super(_controlName, _screenControllerName);
        super(_controlName, _screenControllerInstance);
        this.setControlInstance(this.FindNiftyControl(_controlName));
    }
    /**
     * The entire reason for this subclass is to allow us to specify the type of control class
     * when calling the findNiftyControl method. (This is because we cannot access the generic type's class during runtime with Java.)
     * This method is invoked by the Abstract class's constructor.
     * @param controlName
     * @return 
     */
    @Override
    public final NiftyControl FindNiftyControl(String controlName) { 
        Label lblText = super.GetNiftyScreenController().findNiftyControl(controlName, Label.class);
        if (lblText == null) {
            throw new IllegalArgumentException("ERROR: The controlName, " + controlName + ", resulted in a null object when we tried to locate it in the TextFieldDisplayTextMediator.FindNiftyControl(..) method!");
        }
        this.SetSpecificControl(lblText);
        return lblText;
    }

    @Override
    public String GetControlDisplayText() {
        return this.GetSpecificControl().getText();
    }
    public Label GetSpecificControl(){
        return this._lblText;
    }
    public void SetSpecificControl(Label lblText){
        this._lblText = lblText;
    }
//    @Override
//    public void SetControlDisplayText(String displayTextStringFormat, String textToDisplayOnControl) {
//        this._displayText = textToDisplayOnControl;
//        // I need to be able to specify how to format the displayText somehow.
//        this._displayTextStringFormat = displayTextStringFormat;
//        this._formattedDisplayText = String.format(this._displayTextStringFormat, this._displayText);
//        this.GetSpecificControl().setText(this._formattedDisplayText);
//    }
    @Override
    public void SetControlDisplayText(String textToDisplayOnControl) {
        this._displayText = textToDisplayOnControl;
        // I need to be able to specify how to format the displayText somehow.
        this.GetSpecificControl().setText(this._displayText);
    }
    // set Nifty control by calling the find method.
    
    

    
}

