package com.nicaiya.glview.texture;

import com.nicaiya.glview.glrender.GLCanvas;
import com.nicaiya.glview.glrender.GLId;
import com.nicaiya.glview.texture.BasicTexture;

/**
 * ExtTexture is a texture whose content comes from a external texture.
 * Before drawing, setSize() should be called.
 */
public class ExtTexture extends BasicTexture {

    private int mTarget;

    public ExtTexture(GLCanvas canvas, int target) {
        GLId glId = canvas.getGLId();
        mId = glId.generateTexture();
        mTarget = target;
    }

    private void uploadToCanvas(GLCanvas canvas) {
        canvas.setTextureParameters(this);
        setAssociatedCanvas(canvas);
        mState = STATE_LOADED;
    }

    @Override
    public boolean onBind(GLCanvas canvas) {
        if (!isLoaded()) {
            uploadToCanvas(canvas);
        }

        return true;
    }

    @Override
    public int getTarget() {
        return mTarget;
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public void yield() {
        // we cannot free the texture because we have no backup.
    }
}
