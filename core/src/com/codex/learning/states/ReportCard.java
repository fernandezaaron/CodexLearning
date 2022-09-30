package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;

public class ReportCard extends State{

    private Table reportCardTable, reportCardContainerTable, cookieTable;
    private ImageButton saveAndQuit;
    private ArrayList<Image> cookies;
    private Label timeConsumed, numberOfErrors, numberOfAttempts, correctOutput;
    private boolean inReportCard;
    private boolean resetFlag;

    public ReportCard(Manager manager){
        super(manager);
        reportCardTable = new Table(manager.getSkin());
        reportCardTable.setBackground("REPORTCARD");

        resetFlag = false;

        reportCardContainerTable = new Table(manager.getSkin());

        saveAndQuit = new ImageButton(manager.getSkin());
        saveAndQuit.setBackground("saveandquit");

        cookies = new ArrayList<>();


        cookieTable = new Table(manager.getSkin());

        timeConsumed = new Label("", manager.getSkin());
        timeConsumed.setAlignment(Align.right);
        timeConsumed.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);

        numberOfErrors = new Label("", manager.getSkin());
        numberOfErrors.setAlignment(Align.right);
        numberOfErrors.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);


        numberOfAttempts = new Label("", manager.getSkin());
        numberOfAttempts.setAlignment(Align.right);
        numberOfAttempts.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);


        correctOutput = new Label("", manager.getSkin());
        correctOutput.setAlignment(Align.right);
        correctOutput.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);


        inReportCard = true;



    }

    @Override
    public void update(float delta) {
        createTable();
        manager.getStage().act();
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.enableBlending();
        manager.getStage().draw();
        sprite.end();

    }

    public void createTable(){
        /**
         * use the text builders here to output the following scores ^^
         */
        timeConsumed.setText(String.valueOf(manager.getMinigame().getTimeConsumption()));
        numberOfErrors.setText(String.valueOf(manager.getMinigame().getNumberofError()));
        numberOfAttempts.setText(String.valueOf(manager.getMinigame().getNumberOfAttempts()));
        correctOutput.setText(String.valueOf(manager.getMinigame().getCorrectOutput()));

        /**
         * replace the length to the number of cookies length lang
         */
        for(int i=0; i<3; i++){
            cookies.add(new Image(manager.getSkin(),"nocookie"));
        }
        for(int i=0; i<manager.getMinigame().getCookies(); i++){
            cookies.set(i, new Image(manager.getSkin(), "cookie"));
        }

        if(!reportCardContainerTable.hasChildren()){
            reportCardTable.add(timeConsumed).grow().colspan(3).center();
            reportCardTable.row().grow().colspan(2);
            reportCardTable.add(numberOfErrors).grow().colspan(3);
            reportCardTable.row().grow().colspan(3);
            reportCardTable.add(numberOfAttempts).grow().colspan(3);
            reportCardTable.row().grow().colspan(3);
            reportCardTable.add(correctOutput).grow().colspan(3);
            reportCardTable.row().grow();
            for(Image i: cookies){
                cookieTable.add(i).size(manager.getStage().getWidth()*.047f,manager.getStage().getHeight()*0.1f).padRight(15f);
            }
            reportCardTable.add(cookieTable).grow().padTop(20f);
            reportCardTable.row().grow();
            reportCardTable.add(saveAndQuit).size(manager.getStage().getHeight()*.22f);

            reportCardTable.padRight(25f);
            reportCardTable.padLeft(25f);
            reportCardTable.padTop(manager.getStage().getHeight()*0.2f);
            reportCardTable.layout();


            reportCardContainerTable.add(reportCardTable).size(manager.getStage().getWidth()*.5f,manager.getStage().getHeight()*.95f);
            reportCardContainerTable.setPosition(manager.getStage().getWidth()*0.5f, manager.getStage().getHeight()*.5f);

        }

        saveAndQuit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                manager.getMusic().stop();
                manager.getQuestionnaire().dispose();
                if (manager.getQuestionnaire().getMinigameHolder() != null){
                    manager.getQuestionnaire().clearMinigames();
                    manager.getMinigame().dispose();
                }
                manager.getPlayroomMap().dispose();
                if(manager.getStageSelector().map().equals("1")){
                    manager.getHouseMap().dispose();
                }
                else if(manager.getStageSelector().map().equals("2")){
                    manager.getSchoolMap().dispose();
                }
                else {
                    manager.getOfficeMap().dispose();
                }

                if(!resetFlag){
                    resetFlag = true;
//                    System.out.println("CODE RIDDLE - " + manager.getCodeRiddle().getCodeRiddleData());
//                    System.out.println("MINIGAME - " + manager.getMinigame().getMinigameData());

                    //codeRiddleData.txt
                    manager.getExpertSystem().writeGameDataGathered(0, Constants.CODE_RIDDLE_DATA_FILE_PATH, manager.getStageSelector().getStageMap(),
                            manager.getQuestionnaire().getMinigameTopic(), manager.getCodeRiddle().getCodeRiddleData());

                    //minigameData.txt
                    manager.getExpertSystem().writeGameDataGathered(1, Constants.MINIGAME_DATA_FILE_PATH, manager.getStageSelector().getStageMap(),
                            manager.getQuestionnaire().getMinigameTopic(), manager.getMinigame().getMinigameData());

                    //data.txt
                    manager.getExpertSystem().writeDataGathering(manager.getStageSelector().getStageMap(),
                            manager.getQuestionnaire().getMinigameTopic(), manager.getExpertSystem().getExpertiseLevel(),
                            manager.getMinigame().getCookies(), manager.getCodeRiddle().getCodeRiddleData(),
                            manager.getMinigame().getMinigameData());
                    manager.getMinigameChecker().setNumberOfAttempts(0);
                    manager.getCodeRiddle().setCodeRiddleData(null);
                    manager.getMinigameChecker().setDone(false);
                    manager.getMinigame().reset();
                    manager.set(new StageSelectState(manager));
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });

        manager.getStage().addActor(reportCardContainerTable);
    }

    @Override
    public void dispose() {
        reportCardContainerTable.remove();


    }

    public boolean isInReportCard() {
        return inReportCard;
    }

    public void setInReportCard(boolean inReportCard) {
        this.inReportCard = inReportCard;
    }
}
