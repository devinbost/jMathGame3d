<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import javax.swing.JOptionPane;
import mathgame.Menus.AboutMenu;
import mathgame.Menus.AbstractMenu;
import mathgame.Menus.HelpMenu;

/**
 *
 * @author devinbost
 */
public class MenuController { // This needs to be a singleton.
    // This class needs a method that takes a MenuDisplayCommand (of type IDisplayCommand).
    // (It is assumed that this will be called by the MenuView.)
    // It nees to construct a MenuDisplayCommand (of type IDisplayCommand) to the MenuView
    // The MenuDisplayCommand must be constructed with a type of AbstractMenu.
    // The of menu injected into the IDisplayCommand must depend upon the input from the MenuView.
    // The IDisplayCommand is then returned to the View, where it is executed.
    // (The IDisplayCommand needs to expect to be given a type of AbstractMenu.
    // (When the IDisplayCommand's execute() method is called, it must display
    // a dialog containing the contents of the AbstractMenu's contents property.)
    
    private static MenuController instance = null;
   protected MenuController() {
      // Protected constructor exists only to defeat outside instantiation.
   }
   public static MenuController getInstance() {
      if(instance == null) {
         instance = new MenuController();
      }
      return instance;
   }
   public IDisplayCommand getDisplayMenuCommand(String jMenuItemName){
       IDisplayCommand command;
       AbstractMenu _menu;
       switch (jMenuItemName){
            case "jMenuItemHelp": 
                HelpMenu helpMenu = new HelpMenu();
                _menu = helpMenu;
                _menu.SetContents();
                command = new DisplayMenuCommand(_menu);
                break;
            case "jMenuItemAbout":
                 AboutMenu aboutMenu = new AboutMenu();
                 _menu = aboutMenu;
                 _menu.SetContents();
                command = new DisplayMenuCommand(_menu);
                break;
            default: 
                 AboutMenu mYaboutMenu = new AboutMenu();
                 _menu = mYaboutMenu;
                 _menu.SetContents();
                command = new DisplayMenuCommand(_menu);
                break;
        }
       if ("".equals(_menu.GetContents())) {
           JOptionPane.showMessageDialog(null, 
            "Warning: _menu.GetContents() is an empty string.", "Display Message: ", JOptionPane.INFORMATION_MESSAGE);
       }
       _menu.SetContents();
         return command;
       // This method determines the correct type of AbstractMenu to construct,
       // according to the name of the jMenuItem.
       
       // This is where we use a switch statement to determine the correct
       // type of Menu to construct.
       
       // Once we have constructed the Menu, we stuff it into a type of IDisplayCommand.
       // This is done with dependeny injection. (We just pass the menu object into the constructor of the DisplayCommand type.)
       // We then return the IDisplayCommand.
       // Here are the items that must be included in the switch statement's cases:
       // 1. jMenuItemHelp
       // 2. jMenuItemAbout
       //
       
       // For example, in the case that jMenuItemName == jMenuItemAbout,
       // create a new instance of AboutMenu, and cast it to a private field of type AbstractMenu.
       // 
       // needs to return IDisplayCommand at the end.
   }
}
||||||| merged common ancestors
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

/**
 *
 * @author devinbost
 */
public class MenuController { // This needs to be a singleton.
    // This class needs a method that takes a MenuDisplayCommand (of type IDisplayCommand).
    // (It is assumed that this will be called by the MenuView.)
    // It nees to construct a MenuDisplayCommand (of type IDisplayCommand) to the MenuView
    // The MenuDisplayCommand must be constructed with a type of AbstractMenu.
    // The of menu injected into the IDisplayCommand must depend upon the input from the MenuView.
    // The IDisplayCommand is then returned to the View, where it is executed.
    // (The IDisplayCommand needs to expect to be given a type of AbstractMenu.
    // (When the IDisplayCommand's execute() method is called, it must display
    // a dialog containing the contents of the AbstractMenu's contents property.)
    
    private static MenuController instance = null;
   protected MenuController() {
      // Protected constructor exists only to defeat outside instantiation.
   }
   public static MenuController getInstance() {
      if(instance == null) {
         instance = new MenuController();
      }
      return instance;
   }
   public IDisplayCommand getDisplayMenuCommand(String jMenuItemName){
       // This method determines the correct type of AbstractMenu to construct,
       // according to the name of the jMenuItem.
       
       // This is where we use a switch statement to determine the correct
       // type of Menu to construct.
       
       // Once we have constructed the Menu, we stuff it into a type of IDisplayCommand.
       // We then return the IDisplayCommand.
       // Here are the items that must be included in the switch statement's cases:
       // 1. jMenuItemHelp
       // 2. jMenuItemAbout
       //
       
       // For example, in the case that jMenuItemName == jMenuItemAbout,
       // create a new instance of AboutMenu, and cast it to a private field of type AbstractMenu.
       // 
       // needs to return IDisplayCommand at the end.
   }
}
>>>>>>> Added infrastructure for view and controller for menu system. Also added code for Command pattern.
