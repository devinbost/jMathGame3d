/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.NiftyControl;

/**
 *
 * @author devinbost
 */
public interface IUpdateNiftyGuiControlDisplayText { // contains the method used to update the ScreenController's TextField's display value.
    public <T extends NiftyControl> void UpdateNiftyControlDisplayText(
            String controlElementName, 
            Class<T> requestedClassControl,
            String newTextToDisplay);
}