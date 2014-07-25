/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

/**
 *
 * @author devinbost
 */
public class UnknownMediationException extends IllegalArgumentException {

    public UnknownMediationException() {
    }

    public UnknownMediationException(String s) {
        super(s);
    }

    public UnknownMediationException(String message, Throwable cause) {
        super("Error: This mediation is unknown. " + message, cause);
    }

    public UnknownMediationException(Throwable cause) {
        super(cause);
    }
    
}
