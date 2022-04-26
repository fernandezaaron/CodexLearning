package com.codex.learning.utility;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currFrameTime;
    private int frameCount;
    private int currFrame;
    private boolean isFlipped;

    public Animation(TextureRegion texture, int frameCount, float cycleTime, int jediStandWidth, int jediStandHeight, float v, boolean b){
        frames = new Array<TextureRegion>();
        int frameWidth = texture.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++)
            frames.add(new TextureRegion(
                    texture /* texture reference */,
                    i * frameWidth /* x-origin */,
                    0 /* y-origin */,
                    frameWidth /* width */,
                    texture.getRegionHeight() /* height */
            ));
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currFrame = 0;
        isFlipped = false;
    }
    public Animation(TextureRegion texture, int x, int y, int textureWidth,int textureHeight, int frameCount, float cycleTime, boolean isGoingDown){
        frames = new Array<TextureRegion>();
        int frameWidth = textureWidth / frameCount;
        int frameHeight = textureHeight;
        if(isGoingDown){
            frameWidth = textureWidth;
            frameHeight = textureHeight / frameCount;
        }

        for(int i = 0; i < frameCount; i++) {
            int xOrigin = (i * frameWidth) + x;
            int yOrigin = y;
            if (isGoingDown) {
                xOrigin = x;
                yOrigin = (i * frameHeight) + y;
            }
            frames.add(new TextureRegion(
                    texture /* texture reference */,
                    xOrigin /* x-origin */,
                    yOrigin /* y-origin */,
                    frameWidth /* width */,
                    frameHeight /* height */
            ));
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
