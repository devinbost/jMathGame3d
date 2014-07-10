/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

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
        IUpdateNiftyGuiControlDisplayText screenControlMediator = new TextFieldDisplayTextMediator(controlName, screenController); // e.g. "txtTimer", hudScreen
        ScreenControlDisplayMediation mediation = new ScreenControlDisplayMediation(screenControlMediator, eventType);
        return mediation;
    }
}
