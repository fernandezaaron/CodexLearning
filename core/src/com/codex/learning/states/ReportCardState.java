package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;

public class ReportCardState extends State{

    private com.badlogic.gdx.scenes.scene2d.ui.Table backgroundTable, textButtonContainer, reportCardTable, containerTable;
    private ScrollPane scrollPane;
    private ImageTextButton[] textButtons;
    private ImageTextButton quit;
    private ArrayList<ArrayList<String>> results;
    private Label label;

    public ReportCardState(Manager manager){
        super(manager);

        backgroundTable = new Table(manager.getSkin());
        textButtonContainer = new Table(manager.getSkin());
        reportCardTable = new Table(manager.getSkin());
        containerTable = new Table(manager.getSkin());

        textButtons = new ImageTextButton[16];
        quit = new ImageTextButton("Exit",manager.getSkin(), "Choices");
        results = manager.getExpertSystem().readDataFirst(Constants.MINIGAME_RESULTS_FILE_PATH);
        label = new Label("", manager.getSkin());
        label.setWrap(true);
        label.getStyle().font.getData().setLineHeight(35);

        for(int i=0; i<16; i++){
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
            for(int i=0; i<16; i++){
                textButtonContainer.add(textButtons[i]).padBottom(10f).padRight(10f).padLeft(10f).padTop(15f).row();
                final int index = i+1;
                textButtons[i].addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                        System.out.println("stage " + index);
                        System.out.println(results.get(index).get(1));
                        label.setText("Topic: " + results.get(index).get(1) + "\n" +
                                "Number of Cookies: " + results.get(index).get(3) + "\n" +
                                "Time Consumed: " + results.get(index).get(4) + "\n" +
                                "Number of Errors: " + results.get(index).get(5) + "\n" +
                                "Number of Attempts: " + results.get(index).get(6) + "\n" +
                                "Correct Output: " + results.get(index).get(7));

                        return true;
                    }
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                    }

                });
            }
            textButtonContainer.row().padBottom(50f);
            scrollPane = new ScrollPane(textButtonContainer, manager.getSkin(), "Reportcard");
            scrollPane.layout();
            scrollPane.setScrollbarsOnTop(true);
            scrollPane.setForceScroll(false,true);
            scrollPane.setSmoothScrolling(true);

            containerTable.add(scrollPane).padTop(15f).padBottom(15f).padRight(20f);
            reportCardTable.add(label).padRight(250f);
            containerTable.add(reportCardTable).padTop(15f).padBottom(15f).row();
            containerTable.add(quit).padTop(2f).padBottom(2f).padLeft(150f).right();
            quit.addListener(new InputListener(){
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

            backgroundTable.add(containerTable);

            backgroundTable.pack();
        }




        manager.getStage().addActor(backgroundTable);
        manager.getStage().draw();
//        manager.getStage().setDebugAll(true);




    }

    @Override
    public void dispose() {

    }
}
