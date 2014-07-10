/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame.Menus;

/**
 *
 * @author jessewhite
 */
public class HelpMenu extends AbstractMenu {
    String command = "q";
//    public void displayHelp() {
//        //Get input from the user and place it in command.
//        //use the variable in command to display a pop up 
//        //box with the help menu inside it.
//        System.out.println("The value inside command is " + command);
//    }
    
    public void SetContents() {
        this.SetControlDisplayText("The value inside the HelpMenu's command text value is: " + command);
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
}