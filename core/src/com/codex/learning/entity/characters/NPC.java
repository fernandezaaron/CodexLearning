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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.*;

public class NPC extends Entity {

    private TextureRegion jediGrandpaDown, jediProfDown, jediOfficeDown;
    private TextureRegion jediGrandpaLeft, jediProfLeft, jediOfficeLeft;
    private TextureRegion jediGrandpaRight, jediProfRight, jediOfficeRight;
    private TextureRegion jediGrandpaTop, jediProfTop, jediOfficeTop;
    private String direction;
    private boolean inContact;
    private boolean talking;
    private Table table, image, choiceTable, choiceTableContainer, dialogBoxContainer;
    private TextButton[] textButtons;
    private DialogueBox db;
    private Label.LabelStyle labelStyle;
    private Dialogue dialogue;
    private String dialogSet;
    private int index, hintIndex;
    private int nextStatement;
    private boolean inPlayroom, readiness, introDialogFlag, doneChecker, tableTouched, hintFlag;

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

        //initialize a table(just like how JPANEL works)
        table = new Table();
        //provides an image of the NPC on the left hand side of the table

        image = new Table(manager.getSkin());
        choiceTable = new Table(manager.getSkin());
        choiceTable.setBackground("dialogbox2");

        choiceTableContainer = new Table(manager.getSkin());
        dialogBoxContainer = new Table(manager.getSkin());

        textButtons = new TextButton[2];
        textButtons[0] = new TextButton("Yes", manager.getSkin());
        textButtons[1] = new TextButton("No", manager.getSkin());

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
        db = new DialogueBox(manager.getSkin(), "dialogbox2", 0.6f);
        //dialogue of the NPC
        manager.getDialogue().setStage(manager.getStageSelector().getStageMap());

        talking = false;
        readiness = false;
        doneChecker = false;
        tableTouched =false;
        hintFlag = false;

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

        inContact = false;
        nextStatement = 0;
        hintIndex = 0;

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
        autoDialog();
        npcInteraction(delta);
        db.act(delta);
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

    public void npcInteraction(float delta){

        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            dialogBoxContainer.setVisible(true);

            setTalking(true);
            if(!db.isOpen()){
                //if the dialogue box is not yet open then animate the text and add it to the table to draw it
                if(isInPlayroom()){
                    doneChecker = true;
                    choiceTableContainer.setVisible(true);


                    for(int i=0; i<textButtons.length; i++){
                        final int finalTempindex = i;
                        textButtons[i].addListener(new InputListener(){
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                if(finalTempindex == 0){
                                    choiceTableContainer.setVisible(false);
                                    setReadiness(true);
                                }
                                else{
                                    setHintFlag(true);
                                    setReadiness(false);
                                    choiceTableContainer.setVisible(false);
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
                    choiceTable.add(textButtons[0]);
                    choiceTable.row().pad(15f);
                    choiceTable.add(textButtons[1]);
                    choiceTableContainer.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2 + 600, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2 + 150);
                    choiceTableContainer.add(choiceTable).size(80,80);
                }


                manager.getStage().addActor(choiceTableContainer);
                }
                db.textAnimation(manager.getDialogue().reader(nextStatement, dialogSet, index));
                if(!dialogBoxContainer.hasChildren()){
                    dialogBoxContainer.defaults().size(700,100);
                    table.add(image).align(Align.left).height(100).padRight(15f);
                    table.add(db).align(Align.left).grow();
                    dialogBoxContainer.add(table);
                    dialogBoxContainer.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2 + 400, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2  + 75);
                }
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
                db.textAnimation(manager.getDialogue().reader(nextStatement, "finishCheck", 1));
                manager.getMinigameChecker().setDone(true);
                manager.getDialogue().setStatementEnd(true);
            }
            else {
                db.textAnimation(manager.getDialogue().reader(nextStatement, "finishCheck", 2));
                manager.getDialogue().setStatementEnd(true);
            }
            setReadiness(false);
        }

        if(isInPlayroom() && isHintFlag()){
            System.out.println(hintIndex);
            db.textAnimation(manager.getDialogue().reader(hintIndex, "hints", 0));
            hintIndex++;
            if(hintIndex == 4){
                hintIndex = 0;
            }
            setHintFlag(false);
        }

        if((!isReady() || !isHintFlag()) && isTableTouched()){
            doneChecker = false;
        }
        

        if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && !isIntroDialogFlag() && !doneChecker){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            db.textAnimation((manager.getDialogue().reader(nextStatement, dialogSet, index)));
            setTableTouched(false);
        }


        if((manager.getDialogue().isStatementEnd() && db.isOpen() && !isIntroDialogFlag() && !doneChecker)){
            setTalking(false);
            //if at the end resets the table and the statement to the first index
            dialogBoxContainer.setVisible(false);
            db.setOpen(false);
            nextStatement = 0;
            setTableTouched(false);

        }

        manager.getStage().setDebugAll(true);

    }

    public void autoDialog(){
        if(isInPlayroom() && isIntroDialogFlag()){
            setTalking(true);
            dialogBoxContainer.setVisible(true);
            if(!db.isOpen() && isIntroDialogFlag()) {
                db.textAnimation(manager.getDialogue().reader(nextStatement, "minigameintrodialogue", index));
                if(!dialogBoxContainer.hasChildren()){
                    dialogBoxContainer.defaults().size(700,100);
                    table.add(image).align(Align.left).height(100).padRight(15f);
                    table.add(db).align(Align.left).grow();
                    dialogBoxContainer.add(table);
                    dialogBoxContainer.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2 + 400, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2  + 75);
                }
            }
        }

//        if(dialogSet.equals("hints")  && !db.isOpen()){
//            db.textAnimation(manager.getDialogue().reader(manager.getHintsIndex(), dialogSet, index));
//            manager.getDialogue().setStatementEnd(true);
//        }
//        if(manager.getDialogue().isStatementEnd() && dialogSet.equals("hints") && db.isOpen()){
//            table.reset();
//        }

        if(!manager.getDialogue().isStatementEnd() && isTableTouched() && db.isOpen() && isIntroDialogFlag()){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            db.textAnimation((manager.getDialogue().reader(nextStatement, "minigameintrodialogue", index)));
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
        }

    }

    public String getDialogSet() {
        return dialogSet;
    }

    public void setDialogSet(String dialogSet) {
        this.dialogSet = dialogSet;
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

    public boolean isDoneChecker() {
        return doneChecker;
    }

    public void setDoneChecker(boolean doneChecker) {
        this.doneChecker = doneChecker;
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
}
