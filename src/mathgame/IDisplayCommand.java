/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

import mathgame.Menus.AbstractMenu;

/**
 *
 * @author devinbost
 */
public interface IDisplayCommand { // This is required for the Command design pattern.
    void Execute();
    AbstractMenu abstractMenu = null;
    String GetContents();
}
