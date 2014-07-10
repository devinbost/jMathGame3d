/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author devinbost
 * This event class is used to extend the PropertyChangeEvent to include the EventTypeEnum (EventTypeEnum).
 * This extension allows us to encapsulate the event type with the method raising the event. 
 * This event is intended for consumption by a mediator used to update the GUI display elements.
 * This enum makes it much easier to maintain the events because they the mediators can then switch on the event type 
 * instead of needing to know the exact property name that will be returned by the event. 
 */
public class PropertyChangeTypedEvent extends PropertyChangeEvent {
    private EventTypeEnum _eventType;
    public PropertyChangeTypedEvent(Object source, String propertyName,
                                     Object oldValue, Object newValue, EventTypeEnum eventType) {
        super(source, propertyName, oldValue, newValue);
        this._eventType = eventType;
    }
    public EventTypeEnum getEventType(){
        return _eventType;
    }
}
