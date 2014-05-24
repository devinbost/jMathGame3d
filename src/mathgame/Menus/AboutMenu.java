<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame.Menus;

/**
 *
 * @author devinbost
 */
public class AboutMenu extends AbstractMenu {

    @Override
    public void SetContents() {
        super._contents = "jMathGame: Copyright 2014 Devin Bost and Jesse White.";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // This class needs to include code with the contents of the About menu.
    
}
||||||| merged common ancestors
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame.Menus;

/**
 *
 * @author devinbost
 */
public class AboutMenu extends AbstractMenu {

    @Override
    void SetContents() {
        super._contents = "jMathGame: Copyright 2014 Devin Bost and Jesse White.";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // This class needs to include code with the contents of the About menu.
    
}
>>>>>>> Added infrastructure for view and controller for menu system. Also added code for Command pattern.
