package com.codex.learning.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import org.apache.poi.ss.formula.functions.T;

public class HowToPlayState extends State{
    private Table backgroundTable, photoTable, textButtonContainer, containerTable;
    private ImageTextButton[] imageTextButtons;
    private Image[] image;
    private TextureRegion fillInTheBlocks, mysteryCode, codeOrder, codeIT, startArea;
    private Image placeholder;
    private ImageButton backButton;

    public HowToPlayState(Manager manager){
        super(manager);

        photoTable = new Table(manager.getSkin());
        textButtonContainer = new Table(manager.getSkin());
        backgroundTable = new Table(manager.getSkin());
        containerTable = new Table(manager.getSkin());


        fillInTheBlocks = new TextureRegion(new Texture(Constants.FILL_IN_THE_BLOCKS), 0,0, 1600, 900);
        mysteryCode = new TextureRegion(new Texture(Constants.MYSTERY_CODE), 0, 0, 1600, 900);
        codeOrder = new TextureRegion(new Texture(Constants.CODE_ORDER), 0, 0, 1600, 900);
        codeIT = new TextureRegion(new Texture(Constants.CODE_IT), 0, 0, 1600, 900);
        startArea = new TextureRegion(new Texture(Constants.START_AREA), 0, 0, 1600,900);

        placeholder = new Image(new TextureRegion(startArea));
        backButton = new ImageButton(manager.getSkin(), "PauseButton");

        image = new Image[5];
        image[0] = new Image(new TextureRegion(startArea));
        image[1] = new Image(new TextureRegion(fillInTheBlocks));
        image[2] = new Image(new TextureRegion(mysteryCode));
        image[3] = new Image(new TextureRegion(codeOrder));
        image[4] = new Image(new TextureRegion(codeIT));

        imageTextButtons = new ImageTextButton[5];
        imageTextButtons[0] = new ImageTextButton("BASICS", manager.getSkin(), "Choices");
        imageTextButtons[1] = new ImageTextButton("FILL IN THE BLOCKS", manager.getSkin(), "Choices");
        imageTextButtons[2] = new ImageTextButton("MYSTERY CODE", manager.getSkin(), "Choices");
        imageTextButtons[3] = new ImageTextButton("CODE ORDER", manager.getSkin(), "Choices");
        imageTextButtons[4] = new ImageTextButton("CODE IT", manager.getSkin(), "Choices");

    }

    @Override
    public void update(float delta) {
        manager.getStage().act(delta);

    }

    @Override
    public void render(SpriteBatch sprite) {

        backgroundTable.defaults().size(manager.getStage().getWidth(),manager.getStage().getHeight());
        backgroundTable.setPosition(0,0);
        containerTable.setBackground("dialogbox1");
        if(!backgroundTable.hasChildren()){
            photoTable.add(placeholder);
            for(int i=0; i<5; i++){
                textButtonContainer.add(imageTextButtons[i]).padBottom(20f).padRight(10f).padLeft(10f).row();
                final int index = i;
                imageTextButtons[i].addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                        placeholder.setDrawable(image[index].getDrawable());
                        return true;
                    }
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                    }

                });
            }

            backButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    manager.set(new MenuState(manager));
                    manager.getStage().clear();
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                }
            });

            containerTable.add(backButton).padTop(25f).row();
            containerTable.add(textButtonContainer);
            containerTable.add(photoTable);

            backgroundTable.add(containerTable);
            backgroundTable.pack();

        }

        manager.getStage().addActor(backgroundTable);
        manager.getStage().draw();

    }

    @Override
    public void dispose() {

    }
}
