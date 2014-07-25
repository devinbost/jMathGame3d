/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.Models;

/**
 *
 * @author devinbost
 */
public class UnknownControlException extends IllegalArgumentException {

    public UnknownControlException() {
    }

    public UnknownControlException(String s) {
        super(s);
    }

    public UnknownControlException(String message, Throwable cause) {
        super("Error: The specified control name was not found! " + message, cause);
    }

    public UnknownControlException(Throwable cause) {
        super(cause);
    }
    
}
