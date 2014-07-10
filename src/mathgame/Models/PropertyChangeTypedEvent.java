/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author devinbost
 */
public class PropertyChangeTypedEvent extends PropertyChangeEvent {
    private HudEventTypeEnum _eventType;
    public PropertyChangeTypedEvent(Object source, String propertyName,
                                     Object oldValue, Object newValue, HudEventTypeEnum eventType) {
        super(source, propertyName, oldValue, newValue);
        this._eventType = eventType;
    }
    public HudEventTypeEnum getEventType(){
        return _eventType;
    }
}
