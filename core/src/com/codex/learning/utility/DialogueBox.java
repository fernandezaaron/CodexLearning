package com.codex.learning.utility;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class DialogueBox extends Table {

    private String text = "";
    private float animationTimer = 0f;
    private float animationTotalTime = 0f;
    private float TEXT_SPEED = 0.05f;
    private boolean isAnimating;
    private Label textLabel;


    public DialogueBox(Skin skin, String name){
        this.setSkin(skin);
        this.setBackground(skin.getDrawable(name));
        textLabel = new Label("\n", skin);
        this.add(textLabel).expand().align(Align.left).pad(5f);
        isAnimating = false;

    }

    public void textAnimation(String t){
        text = t;
        animationTotalTime = t.length()*TEXT_SPEED;
        setAnimating(true);
        animationTimer = 0f;
    }

    private void setText (String t){
        if(!t.contains("\n")){
            t += "\n";
        }
        this.textLabel.setText(t);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if(isAnimating()){
            animationTimer += delta;
            if(animationTimer > animationTotalTime){
                setAnimating(false);
                animationTimer = animationTotalTime;
            }
            String displayedText = "";
            int stringSize = (int) ((animationTimer/animationTotalTime)*text.length());
            for(int i=0; i<stringSize; i++){
                displayedText += text.charAt(i);
            }
            if(!displayedText.equals(textLabel.getText().toString())){
                setText(displayedText);
            }
        }
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }


}
