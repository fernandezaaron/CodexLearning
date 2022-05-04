package com.codex.learning.utility;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    //animation
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currFrameTime;
    private int frameCount;
    private int currFrame;
    private boolean isFlipped;


    public Animation(TextureRegion texture, int x, int y, int textureWidth,int textureHeight, int frameCount, float cycleTime){
        frames = new Array<>();
        int frameWidth = textureWidth / frameCount;
        int frameHeight = textureHeight;

        for(int i = 0; i < frameCount; i++) {
            int xOrigin = (i * frameWidth) + x;
            int yOrigin = y;

            frames.add(new TextureRegion(texture , xOrigin , yOrigin , frameWidth , frameHeight));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currFrame = 0;
        isFlipped = false;

    }

    public int getFrameCount() {
        return frameCount;
    }

    public int getCurrFrame() {
        return currFrame;
    }

    public void update(float delta){
        currFrameTime += delta;
        if(currFrameTime > maxFrameTime){
            currFrame++;
            currFrameTime = 0;
        }
        if(currFrame >= frameCount){
            currFrame = 0;
        }
    }

    public TextureRegion getFrame(){
        return  frames.get(currFrame);
    }

    public void dispose(){
        frames = null;
    }
    public void flip(){
        for(TextureRegion region : frames)
            region.flip(true, false);
        isFlipped = !isFlipped;
    }
    public boolean isFlipped(){
        return isFlipped;
    }
}
