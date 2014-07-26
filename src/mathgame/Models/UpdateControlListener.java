/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author devinbost
 */
public class UpdateControlListener implements PropertyChangeTypedListener {
    Label _niftyGuiLabelControl;
    public UpdateControlListener(Label niftyGuiLabelControl) {
        _niftyGuiLabelControl = niftyGuiLabelControl;
        _niftyGuiLabelControl.setText("Updated");
    }

    
    @Override
    public void propertyChange(PropertyChangeTypedEvent evt) {
        if (evt.getEventType() == EventTypeEnum.CountdownTick) {
            // Update text property on the element somehow (from evt.newValue).
            _niftyGuiLabelControl.setText(StringFormatters.FormatTimeInMilliseconds(Integer.parseInt(evt.getNewValue().toString())));
            
        }
        // I need something to handle when the timer runs out.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
