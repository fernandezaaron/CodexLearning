package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import sun.tools.jconsole.Tab;

public class Objective extends Entity {

    private boolean inContact;
    private boolean inObjective, objectiveInteracted;
    private TextureRegion textureRegion;
    private Table objectiveTable, textTable, headerTable, containerTable;
    private ImageButton xTable;
    private ImageButton.ImageButtonStyle imageButtonStyle;
    private Image titleObjective;
    private Label label;


    public Objective(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {

        containerTable = new Table();

        objectiveTable = new Table(manager.getSkin());
        objectiveTable.setBackground("objectives");

        textTable = new Table(manager.getSkin());
        headerTable = new Table(manager.getSkin());

        xTable = new ImageButton(manager.getSkin(), "Objective");
        xTable.setBackground("XbuttonObjectives");


        titleObjective = new Image(manager.getSkin(), "objectivesTitle");


        label = new Label("", manager.getSkin());
        label.setWrap(true);
        label.setFontScale(1.2f);
        label.getStyle().font.getData().setLineHeight(35);


        this.position = position;
        this.size = size;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(this.position);
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x , this.size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inContact = false;
        inObjective = false;
        objectiveInteracted = false;
//        manager.getFont().getData().setScale(1.5f);
    }

    @Override
    public void update(float delta) {
        createTable();
        if (isInObjective()) {
            manager.getStage().addActor(containerTable);

        }
        checkIfClicked();
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.enableBlending();

        sprite.end();
    }

    private void createTable(){
        xTable.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                setInObjective(false);
                containerTable.remove();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

            }
        });

        if(!containerTable.hasChildren()){
            headerTable.add(xTable).size(75).left().padRight(50f);
            headerTable.add(titleObjective).height(75).padRight(50f);
            label.setText(manager.getDialogue().getObjectiveDialogue(manager.getQuestionnaire().getQuestionID()-1));
            textTable.add(label).growX().align(Align.left);


            containerTable.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2 + 800 , manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2 + 350);
            containerTable.defaults().size(800,900);
            objectiveTable.left().top();
            objectiveTable.add(headerTable).height(75).padBottom(50f).padTop(50f);
            objectiveTable.row();
            objectiveTable.add(textTable).left().padLeft(100f);

            containerTable.add(objectiveTable).size(800,900);
        }
    }

    private void checkIfClicked(){
        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            setInObjective(true);
            setObjectiveInteracted(true);
        }
        if(isInObjective() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
            setInObjective(false);
        }
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public boolean isInObjective() {
        return inObjective;
    }

    public void setInObjective(boolean inObjective) {
        this.inObjective = inObjective;
    }

    public boolean isObjectiveInteracted() {
        return objectiveInteracted;
    }

    public void setObjectiveInteracted(boolean objectiveInteracted) {
        this.objectiveInteracted = objectiveInteracted;
    }
}
