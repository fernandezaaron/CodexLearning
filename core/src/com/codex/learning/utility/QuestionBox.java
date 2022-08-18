package com.codex.learning.utility;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class QuestionBox extends Table {

    private String text = "";
    private float animationTimer = 0f;
    private float animationTotalTime = 0f;
    private float TEXT_SPEED = 0.06f;
    private boolean isAnimating, isOpen;
    private Label textLabel;


    public QuestionBox(Skin skin, String name){
        //this.pack();
        this.setSkin(skin);
        this.setBackground(skin.getDrawable(name));
        textLabel = new Label("\n", skin);
        this.add(textLabel).align(Align.left).pad(50f);
        // this.setPosition(this.getX(), this.getY());
        isAnimating = false;
        isOpen = false;


    }

    public void textAnimation(String t){
//        System.out.println("yes");
        text = t;
        animationTotalTime = t.length()*TEXT_SPEED;
        setAnimating(true);
        setOpen(true);
        animationTimer = 0f;
        //System.out.println(isAnimating);

    }

    private void setText (String t){
        if(!t.contains("\n")){
            t += "\n";
        }
        this.textLabel.setText(t);
    }

//    @Override
    public void act(float delta){
        super.act(delta);
        System.out.println(isAnimating);
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
