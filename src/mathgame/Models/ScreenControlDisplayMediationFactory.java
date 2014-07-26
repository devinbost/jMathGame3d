/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.label.LabelControl;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author devinbost
 */
public class ScreenControlDisplayMediationFactory {

    public ScreenControlDisplayMediationFactory() {
    }
    /** The EventTypeEnum eventType is used to specify which event type will be used to update the control in the specified
     *  screen controller.
     * 
     */
    public static ScreenControlDisplayMediation Make(EventTypeEnum eventType, String controlName, Screen screenController){
        //IUpdateNiftyGuiControlDisplayText screenControlMediator = new TextFieldDisplayTextMediator(controlName, screenController); // e.g. "txtTimer", hudScreen
        // Use the UpdateControlListener after passing it the label from the MainScreenController.
        IUpdateNiftyGuiControlDisplayText screenControlMediator;
        if (eventType == EventTypeEnum.CountdownTick) {
            screenControlMediator = new LabelDisplayTextMediator(controlName, screenController); // e.g. "txtTimer", hudScreen
            // Then construct the desired type of listener after we get the control.
            LabelControl labelControl;
            labelControl = screenController.findControl(controlName, LabelControl.class);
                if (labelControl == null) {
                    throw new UnknownControlException(" The control name provided was: " + controlName + " of the type: " 
                            + LabelControl.class.toString() + " . It failed at ScreenControlDisplayMediationFactory.Make(..)");
                }
            UpdateControlListener listener = new UpdateControlListener(labelControl);
            
            //System.out.println(" W");
            ScreenControlDisplayMediation mediation = new ScreenControlDisplayMediation(screenControlMediator, eventType, listener);
            
            mediation.Subscribe(listener);
            return mediation;
        }
        else{
            throw new UnknownMediationException("We don't know how to handle the eventType " + eventType.toString() + " called from ScreenControlDisplayMediationFactory.Make(..). ");
        }
    }
}
