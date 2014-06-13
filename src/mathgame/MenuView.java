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
public class MenuView { // This is a singleton.
    // It has a method that is intended to be called by the GUI event handlers.
    // It passes the control's name to the MenuController and expects an IDisplayCommand in response.
    // It then executes the Execute() method on the IDisplayCommand.
    private static MenuView instance = null;
   protected MenuView() {
      // Protected constructor exists only to defeat outside instantiation.
   }
   public static MenuView getInstance() {
      if(instance == null) {
         instance = new MenuView();
      }
      return instance;
   }
   public void HandleMenuItemClick(String menuItemName){
       MenuController controller = MenuController.getInstance();
       IDisplayCommand command = controller.getDisplayMenuCommand(menuItemName);
       command.Execute(); // Executing the command should display the messagebox.
               
   }
    
}
