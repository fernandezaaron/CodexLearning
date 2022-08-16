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
    private boolean isAnimating, isOpen;
    private Label textLabel;


    public DialogueBox(Skin skin, String name){
        this.pack();
        this.setSkin(skin);
        this.setBackground(skin.getDrawable("dialogbox2"));
        textLabel = new Label("\n", skin);
//        textLabel.setHeight(1000);
//        textLabel.setWidth(1000);
        this.add(textLabel).expand().align(Align.left).pad(5f);
//        this.setWidth(400);
//        this.setHeight(200);
        this.setPosition(this.getX(), this.getY());

        System.out.println(getName() + getActions() + getBackground() + getDebug());
        isAnimating = false;
        isOpen = false;

    }

    public void textAnimation(String t){
        text = t;
        animationTotalTime = t.length()*TEXT_SPEED;
        setAnimating(true);
        setOpen(true);
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
//            System.out.println(delta);
            int stringSize = (int) ((animationTimer/animationTotalTime)*text.length());
            for(int i=0; i<stringSize; i++){
                displayedText += text.charAt(i);
            }
            if(!displayedText.equals(textLabel.getText().toString())){
                setText(displayedText);
//                System.out.println(displayedText);
            }
        }
    }

    public void close(){
        this.reset();

    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public float getPrefWidth(){
        return 200f;
    }
}
