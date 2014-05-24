<<<<<<< HEAD
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
public class MainMenu extends AbstractMenu {
    String command = "q";
//    public void displayHelp() {
//        //Get input from the user and place it in command.
//        //use the variable in command to display a pop up 
//        //box with the help menu inside it.
//        System.out.println("The value inside command is " + command);
//    }

    @Override
    public void SetContents() {
        super._contents = "The value inside command is " + command;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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
 * @author jessewhite
 */
public class MainMenu extends AbstractMenu {
    String command = "q";
//    public void displayHelp() {
//        //Get input from the user and place it in command.
//        //use the variable in command to display a pop up 
//        //box with the help menu inside it.
//        System.out.println("The value inside command is " + command);
//    }

    @Override
    void SetContents() {
        super._contents = "The value inside command is " + command;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
>>>>>>> Added infrastructure for view and controller for menu system. Also added code for Command pattern.
