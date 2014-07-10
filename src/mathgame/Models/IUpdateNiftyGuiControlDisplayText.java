/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.NiftyControl;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author devinbost
 * This interface is intended to contain the method used to update the ScreenController's TextField's display value.
 * It should be implemented by AbstractScreenControlDisplayTextMediator.
 * That abstract class should have a subtype for each specific type of NiftyControl.
 * (That way, it uses polymorphism to set the text property on the specific type of control.)
 * This interface may also be used to update other controls on the ScreenController.
 */
public interface IUpdateNiftyGuiControlDisplayText { // 
    //public void SetNiftyControl(NiftyControl control);
    public String GetControlDisplayText();
    public void SetControlDisplayText(String textToDisplayOnControl);
//    public void SetControlDisplayText(String displayTextStringFormat, String textToDisplayOnControl);
    public NiftyControl GetNiftyControlInstance();
    public void SetNiftyScreenController(String screenControllerName);
    public Screen GetNiftyScreenController();
    public NiftyControl FindNiftyControl(String controlName);
//    public <T extends NiftyControl> void UpdateNiftyControlDisplayText(
//            String controlElementName, 
//            Class<T> requestedClassControl,
//            String newTextToDisplay);
}