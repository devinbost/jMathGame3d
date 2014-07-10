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

//    @Override
//    public void SetContents() {
//        super._contents = "jMathGame: Copyright 2014 Devin Bost and Jesse White.";
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    public void SetContents() {
        this.SetControlDisplayText("The value inside the AboutMenu's command text value is: ");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetControlDisplayText(String textToDisplayOnControl) {
        super._contents = textToDisplayOnControl;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String GetControlDisplayText() {
        return super._contents;
    }
    // This class needs to include code with the contents of the About menu.
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
