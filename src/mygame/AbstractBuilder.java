/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;

/**
 *
 * @author devinbost
 */
public abstract class AbstractBuilder implements IBuilder {
    protected AssetManager _assetManager;
    protected String _modelPath;
    public AbstractBuilder(AssetManager assetManager, String modelPath){
        if (assetManager == null || "".equals(modelPath)) {
            throw new IllegalArgumentException();
        }
        this._assetManager = assetManager;
        this._modelPath = modelPath;
    }
    public abstract void Build();
}
