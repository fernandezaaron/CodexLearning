package com.codex.learning.entity.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.*;

import java.util.Random;

public class NPC extends Entity {

    private TextureRegion jediGrandpaDown, jediProfDown, jediOfficeDown;
    private TextureRegion jediGrandpaLeft, jediProfLeft, jediOfficeLeft;
    private TextureRegion jediGrandpaRight, jediProfRight, jediOfficeRight;
    private TextureRegion jediGrandpaTop, jediProfTop, jediOfficeTop;
    private String direction;
    private boolean inContact;
    private boolean talking;
    private Table table, image, choiceTable, choiceTableContainer, dialogBoxContainer;
    private ImageTextButton[] textButtons;
    private DialogueBox db;
    private Label.LabelStyle labelStyle;
    private Dialogue dialogue;
    private String dialogSet;
    private int index, hintIndex, behaviorIndex;
    private int nextStatement;
    private boolean inPlayroom, readiness, introDialogFlag, doneChecker, tableTouched, hintFlag, autoDialogDone, inPlayroomCarpet, computerReady;
    private boolean newPlayerDialogueDone, behaviorFlag, choicesOpen;
    private Random rand;

    public NPC(Manager manager, String dialogSet, int index, boolean inPlayroom) {
        super(manager);
        this.dialogSet = dialogSet;
        this.index = index;
        this.inPlayroom = inPlayroom;
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
//        Create a body without collision yet.
        this.position = position;
        this.size = size;
        rand = new Random();


        //initialize a table(just like how JPANEL works)
        table = new Table();
        //provides an image of the NPC on the left hand side of the table

        image = new Table(manager.getSkin());
        choiceTable = new Table(manager.getSkin());
        choiceTable.setBackground("dialogbox3");

        choiceTableContainer = new Table(manager.getSkin());
        dialogBoxContainer = new Table(manager.getSkin());

        textButtons = new ImageTextButton[2];
        textButtons[0] = new ImageTextButton("Yes", manager.getSkin(), "Choices");
        textButtons[1] = new ImageTextButton("No", manager.getSkin(), "Choices");
        textButtons[0].getLabel().setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.022f);
        textButtons[1].getLabel().setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.022f);


        if(manager.getStageSelector().map().equals("1")){
            image.setBackground("jediGrandpaAvatar");
        }else if(manager.getStageSelector().map().equals("2")){
            image.setBackground("jediProfAvatar");
        }else{
            image.setBackground("jediOfficeAvatar");
        }

        //style of the label (text) that will be added in the table
        labelStyle = new Label.LabelStyle();
        labelStyle.font = manager.getFont();
        labelStyle.font.setColor(Color.BLACK);
        manager.getSkin().add("default", labelStyle);

        manager.getFont().setColor(Color.BLACK);
        manager.getSkin().add("pokemon", manager.getFont());

        //animates the text
        db = new DialogueBox(manager.getSkin(), "dialogbox3", (manager.getStage().getWidth()/Constants.PPM)*0.024f);
        //dialogue of the NPC
        manager.getDialogue().setStage(manager.getStageSelector().getStageMap());

        db.setOpen(false);
        talking = false;
        readiness = false;
        doneChecker = false;
        tableTouched =false;
        hintFlag = false;
        autoDialogDone = false;
        inPlayroomCarpet = false;
        computerReady = false;
        newPlayerDialogueDone = false;
        inContact = false;
        behaviorFlag = false;
        choicesOpen = false;


        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(this.position);
        def.fixedRotation = true;

//        Create a rectangle for the character to have collision detection.
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, (float) (this.size.y / 1.5),
                new Vector2(0, -(this.size.y - this.size.y /2)), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();

        nextStatement = 0;
        index = 0;
        hintIndex = 0;
        behaviorIndex = 0;

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;


        jediGrandpaDown = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_X, Constants.JEDI_GRANDPA_Y,
                Constants.JEDI_GRANDPA_WIDTH,Constants.JEDI_GRANDPA_HEIGHT);
        jediProfDown = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_X, Constants.JEDI_PROF_Y, Constants.JEDI_PROF_WIDTH, Constants.JEDI_PROF_HEIGHT);
        jediOfficeDown = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_MANAGER_X, Constants.JEDI_MANAGER_Y, Constants.JEDI_MANAGER_WIDTH, Constants.JEDI_MANAGER_HEIGHT);

        jediGrandpaLeft = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_LEFT_X, Constants.JEDI_GRANDPA_SIDE_Y, Constants.JEDI_GRANDPA_SIDE_WIDTH, Constants.JEDI_GRANDPA_SIDE_HEIGHT);
        jediProfLeft = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_LEFT_X, Constants.JEDI_PROF_SIDE_Y, Constants.JEDI_PROF_SIDE_WIDTH, Constants.JEDI_PROF_SIDE_HEIGHT);
        jediOfficeLeft = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_MANAGER_LEFT_X, Constants.JEDI_MANAGER_SIDE_Y, Constants.JEDI_MANAGER_SIDE_WIDTH, Constants.JEDI_MANAGER_SIDE_HEIGHT);


        jediGrandpaRight = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_RIGHT_X, Constants.JEDI_GRANDPA_SIDE_Y, Constants.JEDI_GRANDPA_SIDE_WIDTH, Constants.JEDI_GRANDPA_SIDE_HEIGHT);
        jediProfRight = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_RIGHT_X, Constants.JEDI_PROF_SIDE_Y, Constants.JEDI_PROF_SIDE_WIDTH, Constants.JEDI_PROF_SIDE_HEIGHT);
        jediOfficeRight = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_MANAGER_RIGHT_X, Constants.JEDI_MANAGER_SIDE_Y, Constants.JEDI_MANAGER_SIDE_WIDTH, Constants.JEDI_MANAGER_SIDE_HEIGHT);


        jediGrandpaTop = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_TOP_X, Constants.JEDI_GRANDPA_TOP_Y, Constants.JEDI_GRANDPA_TOP_WIDTH, Constants.JEDI_GRANDPA_TOP_HEIGHT);
        jediProfTop = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_TOP_X, Constants.JEDI_PROF_TOP_Y, Constants.JEDI_PROF_TOP_WIDTH, Constants.JEDI_PROF_TOP_HEIGHT);
        jediOfficeTop = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_MANAGER_TOP_X, Constants.JEDI_MANAGER_TOP_Y, Constants.JEDI_MANAGER_TOP_WIDTH, Constants.JEDI_MANAGER_TOP_HEIGHT);


        direction = "south";
    }

    @Override
    public void update(float delta) {
        dialogBoxContainer.setFillParent(true);
        dialogBoxContainer.defaults().width(0.8f*manager.getStage().getViewport().getScreenWidth());
//        dialogBoxContainer.setSize(0.2f*manager.getStage().getWidth(), 0.2f*manager.getStage().getWidth() * dialogBoxContainer.getHeight()/dialogBoxContainer.getWidth());
        if(manager.getStageSelector().getStageMap() == 1){
            newPlayerDialogue();
        }
        hintsDialog();
        autoDialog();
        npcInteraction(delta);
        db.act(delta);
        dialogBoxContainer.setPosition(manager.getCamera().position.x - manager.getStage().getWidth()/Constants.PPM/2 , manager.getCamera().position.y - manager.getStage().getHeight()/2.6f );
        System.out.println(manager.getStage().getViewport().getScreenWidth() + " " + manager.getStage().getViewport().getScreenHeight());
        System.out.println(manager.getStage().getWidth() + " " + manager.getStage().getHeight());
        System.out.println(manager.getStage().getCamera().viewportWidth + " " + manager.getStage().getCamera().viewportHeight);
        System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());


        manager.getStage().addActor(dialogBoxContainer);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        if(manager.getStageSelector().map().equals("1")){
            if(isTalking()){
                switch (direction){
                    case "north":
                        sprite.draw(jediGrandpaTop, body.getPosition().x * Constants.PPM - jediGrandpaTop.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediGrandpaTop.getRegionHeight() / 2);
                        break;
                    case "south":
                        sprite.draw(jediGrandpaDown, body.getPosition().x * Constants.PPM - jediGrandpaDown.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediGrandpaDown.getRegionHeight() / 2);
                        break;
                    case "east":
                        sprite.draw(jediGrandpaRight, body.getPosition().x * Constants.PPM - jediGrandpaRight.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediGrandpaRight.getRegionHeight() / 2);
                        break;
                    case "west":
                        sprite.draw(jediGrandpaLeft, body.getPosition().x * Constants.PPM - jediGrandpaLeft.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediGrandpaLeft.getRegionHeight() / 2);
                        break;
                }
            }
            else{
                sprite.draw(jediGrandpaDown, body.getPosition().x * Constants.PPM - jediGrandpaDown.getRegionWidth() / 2,
                        body.getPosition().y * Constants.PPM - jediGrandpaDown.getRegionHeight() / 2);
            }

        }else if(manager.getStageSelector().map().equals("2")) {
            if(isTalking()){
                switch (direction){
                    case "north":
                        sprite.draw(jediProfTop, body.getPosition().x * Constants.PPM - jediProfTop.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediProfTop.getRegionHeight() / 2);
                        break;
                    case "south":
                        sprite.draw(jediProfDown, body.getPosition().x * Constants.PPM - jediProfDown.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediProfDown.getRegionHeight() / 2);
                        break;
                    case "east":
                        sprite.draw(jediProfRight, body.getPosition().x * Constants.PPM - jediProfRight.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediProfRight.getRegionHeight() / 2);
                        break;
                    case "west":
                        sprite.draw(jediProfLeft, body.getPosition().x * Constants.PPM - jediProfLeft.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediProfLeft.getRegionHeight() / 2);
                        break;
                }
            }
            else {
                sprite.draw(jediProfDown, body.getPosition().x * Constants.PPM - jediProfDown.getRegionWidth() / 2,
                        body.getPosition().y * Constants.PPM - jediProfDown.getRegionHeight() / 2);
            }

        }
        else {
            if(isTalking()){
                switch (direction){
                    case "north":
                        sprite.draw(jediOfficeTop, body.getPosition().x * Constants.PPM - jediOfficeTop.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediOfficeTop.getRegionHeight() / 2);
                        break;
                    case "south":
                        sprite.draw(jediOfficeDown, body.getPosition().x * Constants.PPM - jediOfficeDown.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediOfficeDown.getRegionHeight() / 2);
                        break;
                    case "east":
                        sprite.draw(jediOfficeRight, body.getPosition().x * Constants.PPM - jediOfficeRight.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediOfficeRight.getRegionHeight() / 2);
                        break;
                    case "west":
                        sprite.draw(jediOfficeLeft, body.getPosition().x * Constants.PPM - jediOfficeLeft.getRegionWidth() / 2,
                                body.getPosition().y * Constants.PPM - jediOfficeLeft.getRegionHeight() / 2);
                        break;
                }
            }
            else {
                sprite.draw(jediOfficeDown, body.getPosition().x * Constants.PPM - jediOfficeDown.getRegionWidth() / 2,
                        body.getPosition().y * Constants.PPM - jediOfficeDown.getRegionHeight() / 2);
            }
        }

        sprite.end();
    }

    public void tableRender(SpriteBatch sprite){
        sprite.begin();
        manager.getStage().draw();
        sprite.end();
    }

    public void newPlayerDialogue(){
        if(!inPlayroom && manager.isNewPlayer()){
            dialogBoxContainer.setVisible(true);
            setTalking(true);
            if(!db.isOpen()) {
                System.out.println(nextStatement + " " + index);
                db.textAnimation(manager.getDialogue().reader(nextStatement, "newPlayer", 0));
                if(!dialogBoxContainer.hasChildren()){
                    System.out.println("creating table");
                    table.add(image).align(Align.left).height(0.2f*manager.getStage().getHeight()).padRight(5f);
                    table.add(db).align(Align.left);
                    dialogBoxContainer.add(table);
                }
            }
        }

        if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && manager.isNewPlayer()){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            db.textAnimation((manager.getDialogue().reader(nextStatement, "newPlayer", 0)));
            setTableTouched(false);
        }


        if((manager.getDialogue().isStatementEnd() && db.isOpen() && manager.isNewPlayer())){
            //if at the end resets the table and the statement to the first index
            dialogBoxContainer.setVisible(false);
            setTalking(false);
            db.setOpen(false);
            nextStatement = 0;
            setTableTouched(false);
            manager.setNewPlayer(false);
            setNewPlayerDialogueDone(true);
        }
    }

    public void npcInteraction(float delta){

        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            dialogBoxContainer.setVisible(true);
            setTalking(true);
            if(!db.isOpen()){
                //if the dialogue box is not yet open then animate the text and add it to the table to draw it
                if(isInPlayroom()){
                    doneChecker = true;
                    choiceTableContainer.setVisible(true);
                    setChoicesOpen(true);

                    for(int i=0; i<textButtons.length; i++){
                        final int finalTempindex = i;
                        textButtons[i].addListener(new InputListener(){
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                if(finalTempindex == 0){
                                    choiceTableContainer.setVisible(false);
                                    setReadiness(true);
                                    setChoicesOpen(false);
                                }
                                else{
                                    setHintFlag(true);
                                    setReadiness(false);
                                    choiceTableContainer.setVisible(false);
                                    setChoicesOpen(false);

                                }
                                return true;
                            }

                            @Override
                            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                            }

                        });
                    }

                if(!choiceTableContainer.hasChildren()){
                    choiceTable.center();
                    choiceTable.add(textButtons[0]).height(75).width(150);
                    choiceTable.row();
                    choiceTable.add(textButtons[1]).height(75).width(150);
                    choiceTableContainer.setPosition(manager.getStage().getWidth()/1.3f,  manager.getStage().getHeight()/3.2f);
                    choiceTableContainer.add(choiceTable).size(150,150);
                }

                manager.getStage().addActor(choiceTableContainer);
                }
                db.textAnimation(manager.getDialogue().reader(nextStatement, dialogSet, index));
                if(!dialogBoxContainer.hasChildren()){
                    table.add(image).align(Align.left).height(0.2f*manager.getStage().getHeight()).padRight(5f);
                    table.add(db).align(Align.left).height(0.2f*manager.getStage().getHeight());
                    dialogBoxContainer.add(table);
                }
                setComputerReady(true);
            }
        }

        dialogBoxContainer.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                setTableTouched(true);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });


        /** CHECKS EACH BLOCKHOLDER THEN CHECK IF CORRECT OUTPUT **/
        if(isInPlayroom() && isReady()) {
            manager.getMinigameChecker().minigameCheck();
            if(manager.getMinigameChecker().correctOutputCheck()) {
                setTalking(false);
                dialogBoxContainer.setVisible(false);
                db.setOpen(false);
                nextStatement = 0;
                manager.getMinigameChecker().setDone(true);
            }
            else {
                db.textAnimation(manager.getDialogue().reader(nextStatement, "finishCheck", 2));
                manager.getDialogue().setStatementEnd(true);
                setTalking(false);

            }
            setReadiness(false);
        }

        if(isInPlayroom() && isHintFlag()){
            System.out.println(hintIndex);
            db.textAnimation(manager.getDialogue().reader(hintIndex, "hints", 0));
            setHintFlag(false);
            setTalking(false);
        }

        if((!isReady() || !isHintFlag()) && isTableTouched() && !isChoicesOpen()){
            doneChecker = false;
        }


        if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && !isIntroDialogFlag() && !doneChecker && !isInPlayroomCarpet()){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            db.textAnimation((manager.getDialogue().reader(nextStatement, dialogSet, index)));
            setTableTouched(false);
        }

        if((manager.getDialogue().isStatementEnd() && db.isOpen() && !isIntroDialogFlag() && !doneChecker && !isInPlayroomCarpet())){
            setTalking(false);
            //if at the end resets the table and the statement to the first index
            dialogBoxContainer.setVisible(false);
            db.setOpen(false);
            nextStatement = 0;
            setTableTouched(false);
        }
    }

    public void autoDialog(){
        if(isInPlayroom() && isIntroDialogFlag()){
            setTalking(true);
            dialogBoxContainer.setVisible(true);
            if(!db.isOpen()) {
                System.out.println(manager.getQuestionnaire().getMinigameTopic() + "asd");
                db.textAnimation(manager.getDialogue().reader(nextStatement, "minigameintrodialogue", manager.getDialogue().getTopic(manager.getQuestionnaire().getMinigameTopic())));
                if(!dialogBoxContainer.hasChildren()){
                    table.add(image).align(Align.left).height(0.2f*manager.getStage().getHeight()).padRight(5f);
                    table.add(db).align(Align.left).height(0.2f*manager.getStage().getHeight());
                    dialogBoxContainer.add(table);
                }
            }
        }

        if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && isIntroDialogFlag()){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            db.textAnimation(manager.getDialogue().reader(nextStatement, "minigameintrodialogue", manager.getDialogue().getTopic(manager.getQuestionnaire().getMinigameTopic())));
            setTableTouched(false);
        }

        if((manager.getDialogue().isStatementEnd() && db.isOpen() && isIntroDialogFlag())){
            setTalking(false);
            setIntroDialogFlag(false);
            //if at the end resets the table and the statement to the first index
            db.setOpen(false);
            setTableTouched(false);
            dialogBoxContainer.setVisible(false);
            nextStatement = 0;
            setAutoDialogDone(true);
        }
    }

    public void hintsDialog(){
        if(isInPlayroom() && manager.getMinigame().isEngaged()){
            doneChecker = true;
            dialogBoxContainer.setVisible(true);
            if(!db.isOpen()){
                behaviorIndex = rand.nextInt(10-1)+1;
                db.textAnimation(manager.getDialogue().reader(behaviorIndex, "behavior", 0));
                if(!dialogBoxContainer.hasChildren()){
                    table.add(image).align(Align.left).height(0.2f*manager.getStage().getHeight()).padRight(5f);
                    table.add(db).align(Align.left).height(0.2f*manager.getStage().getHeight());
                    dialogBoxContainer.add(table);
                }
            }
            behaviorFlag = true;
        }

        if(isInPlayroom() && manager.getMinigame().isNotEngaged()){
            doneChecker = true;
            dialogBoxContainer.setVisible(true);
            if(!db.isOpen()){
                System.out.println("true ditooo");
                db.textAnimation(manager.getDialogue().reader(hintIndex, "hints", manager.getDialogue().getTopic(manager.getQuestionnaire().getMinigameTopic())));
                if(hintIndex < manager.getQuestionnaire().getHints()){
                    hintIndex++;
                }
                else {
                    hintIndex = 0;
                }
            }
            setHintFlag(false);
        }


        if((!isHintFlag() || behaviorFlag) && isTableTouched() && (manager.getMinigame().isNotEngaged() || manager.getMinigame().isEngaged())){
            doneChecker = false;
            manager.getDialogue().setStatementEnd(true);
        }

        if((manager.getDialogue().isStatementEnd() && !doneChecker && db.isOpen()  && (manager.getMinigame().isNotEngaged() || manager.getMinigame().isEngaged()))){
            db.setOpen(false);
            setTableTouched(false);
            dialogBoxContainer.setVisible(false);
            manager.getMinigame().setNotEngaged(false);
            manager.getMinigame().setEngaged(false);
        }
    }

    public void noToPlayroom(Character character){
        if(!isInPlayroom() && isInPlayroomCarpet()){
            setTalking(true);
            dialogBoxContainer.setVisible(true);
            if(!db.isOpen()) {
                db.textAnimation(manager.getDialogue().reader(nextStatement, "noplayroom", Integer.parseInt(manager.getStageSelector().map())));
                if(!dialogBoxContainer.hasChildren()){
                    table.add(image).align(Align.left).height(0.2f*manager.getStage().getHeight()).padRight(5f);
                    table.add(db).align(Align.left).height(0.2f*manager.getStage().getHeight());
                    dialogBoxContainer.add(table);
                }
            }

            if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && isInPlayroomCarpet()){
                //proceeds to the next statement if it is not the end
                nextStatement++;
                db.textAnimation((manager.getDialogue().reader(nextStatement, "noplayroom", Integer.parseInt(manager.getStageSelector().map()))));
                setTableTouched(false);
            }


            if((manager.getDialogue().isStatementEnd() && db.isOpen() && isInPlayroomCarpet())){
                //if at the end resets the table and the statement to the first index
                dialogBoxContainer.setVisible(false);
                setTalking(false);
                db.setOpen(false);
                nextStatement = 0;
                setTableTouched(false);
                character.getBody().setTransform(10, character.getBody().getPosition().y, 0);
                character.getBody().getPosition().set(10, character.getBody().getPosition().y);

            }
        }

    }



    public boolean isReady() {
        return readiness;
    }

    public void setReadiness(boolean readiness) {
        this.readiness = readiness;
    }

    public boolean isInPlayroom() {
        return inPlayroom;
    }

    public void setInPlayroom(boolean inPlayroom) {
        this.inPlayroom = inPlayroom;
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isTalking() {
        return talking;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public boolean isIntroDialogFlag() {
        return introDialogFlag;
    }

    public void setIntroDialogFlag(boolean introDialogFlag) {
        this.introDialogFlag = introDialogFlag;
    }

    public boolean isTableTouched() {
        return tableTouched;
    }

    public void setTableTouched(boolean tableTouched) {
        this.tableTouched = tableTouched;
    }

    public boolean isHintFlag() {
        return hintFlag;
    }

    public void setHintFlag(boolean hintFlag) {
        this.hintFlag = hintFlag;
    }

    public boolean isAutoDialogDone() {
        return autoDialogDone;
    }

    public void setAutoDialogDone(boolean autoDialogDone) {
        this.autoDialogDone = autoDialogDone;
    }

    public boolean isInPlayroomCarpet() {
        return inPlayroomCarpet;
    }

    public void setInPlayroomCarpet(boolean inPlayroomCarpet) {
        this.inPlayroomCarpet = inPlayroomCarpet;
    }

    public boolean isComputerReady() {
        return computerReady;
    }

    public void setComputerReady(boolean computerReady) {
        this.computerReady = computerReady;
    }


    public boolean isNewPlayerDialogueDone() {
        return newPlayerDialogueDone;
    }

    public void setNewPlayerDialogueDone(boolean newPlayerDialogueDone) {
        this.newPlayerDialogueDone = newPlayerDialogueDone;
    }

    public boolean isChoicesOpen() {
        return choicesOpen;
    }

    public void setChoicesOpen(boolean choicesOpen) {
        this.choicesOpen = choicesOpen;
    }
}
