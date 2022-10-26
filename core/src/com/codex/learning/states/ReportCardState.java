package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.codex.learning.utility.Manager;

public class ReportCardState extends State{

    private com.badlogic.gdx.scenes.scene2d.ui.Table backgroundTable, textButtonContainer, reportCardTable, containerTable;
    private ScrollPane scrollPane;
    private ImageTextButton[] textButtons;
//    private Label

    public ReportCardState(Manager manager){
        super(manager);

        backgroundTable = new Table(manager.getSkin());
        textButtonContainer = new Table(manager.getSkin());
        reportCardTable = new Table(manager.getSkin());
        containerTable = new Table(manager.getSkin());

        textButtons = new ImageTextButton[15];

        for(int i=0; i<15; i++){
            textButtons[i] = new ImageTextButton("Stage " + (i+1), manager.getSkin(), "Choices");
        }





    }

    @Override
    public void update(float delta) {
        manager.getStage().act();
    }

    @Override
    public void render(SpriteBatch sprite) {
        backgroundTable.defaults().size(manager.getStage().getWidth(),manager.getStage().getHeight());
        backgroundTable.setPosition(0,0);
        containerTable.setBackground("PCSCREEN");
        reportCardTable.setBackground("objectives");
        if(!backgroundTable.hasChildren()){
            for(int i=0; i<15; i++){
                textButtonContainer.add(textButtons[i]).padBottom(10f).padRight(10f).padLeft(10f).padTop(15f).row();
            }
            textButtonContainer.row().padBottom(50f);
            scrollPane = new ScrollPane(textButtonContainer, manager.getSkin(), "Reportcard");
            scrollPane.layout();
            scrollPane.setScrollbarsOnTop(true);
            scrollPane.setForceScroll(false,true);
            scrollPane.setSmoothScrolling(true);
        }

        containerTable.add(scrollPane).padTop(15f).padBottom(15f).padRight(20f);
        containerTable.add(reportCardTable).padTop(15f).padBottom(15f);
        backgroundTable.add(containerTable);
        backgroundTable.pack();

        manager.getStage().addActor(backgroundTable);
        manager.getStage().draw();




    }

    @Override
    public void dispose() {

    }
}
