/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import mathgame.Menus.AbstractMenu;
import java.lang.IllegalArgumentException;
import javax.swing.JOptionPane;

/**
 *
 * @author devinbost
 */
public abstract class AbstractDisplayCommand implements IDisplayCommand {
    public AbstractMenu _abstractMenu;
    public String _contents;
    public AbstractDisplayCommand(AbstractMenu abstractMenu){ // dependency injection technique.
        if (abstractMenu == null) {
            throw new IllegalArgumentException("ERROR: abstractMenu in AbstractDisplayCommand cannot be null!");
        }
        _abstractMenu = abstractMenu;
        _contents = _abstractMenu.GetContents();
    }
    @Override
    public String GetContents(){
        return _contents;
    }
    @Override
    public void Execute(){
        // calls GetContents() and uses the results to display a messagebox
        JOptionPane.showMessageDialog(null, 
            this.GetContents(), "Display Message: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
