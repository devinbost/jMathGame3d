/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import java.beans.PropertyChangeListener;


/**
 *
 * @author devinbost
 */
public interface PropertyChangeTypedListener extends PropertyChangeListener {
    
    void propertyChange(PropertyChangeTypedEvent evt);
}
