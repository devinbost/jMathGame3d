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
public abstract class AbstractMenu implements DisplayInfo, EnterInfo {
      String _contents;
      public AbstractMenu(){
          
      }
      // set the contents of the menu inside the constructor.
      
      public String GetContents(){
          // This method must return the contents of this menu.
          return _contents;
      }
      public abstract void SetContents();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
