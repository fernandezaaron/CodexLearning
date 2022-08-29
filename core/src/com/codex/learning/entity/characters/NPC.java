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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Dialogue;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.Manager;

public class NPC extends Entity {

    private TextureRegion jediGrandpaDown, jediProfDown;
    private TextureRegion jediGrandpaLeft, jediProfLeft;
    private TextureRegion jediGrandpaRight, jediProfRight;
    private TextureRegion jediGrandpaTop, jediProfTop;
    private String direction;
    private boolean inContact;
    private boolean talking;
    private Skin skin;
    private Stage stage;
    private Table table, image;
    private DialogueBox db;
    private Label.LabelStyle labelStyle;
    private Dialogue dialogue;
    private int nextStatement;
    private int stageSelect;
    public NPC(Manager manager, int stage) {
        super(manager);
        this.stageSelect = stage;
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
        if(stageSelect >= 1 && stageSelect < 5){
            image.setBackground("jediGrandpaAvatar");
        }else if(stageSelect >=5 && stageSelect < 12){
            image.setBackground("jediProfAvatar");
        }

        //style of the label (text) that will be added in the table
        labelStyle = new Label.LabelStyle();
        labelStyle.font = manager.getFont();
        labelStyle.font.setColor(Color.BLACK);
        manager.getSkin().add("default", labelStyle);

        manager.getFont().setColor(Color.BLACK);
        manager.getSkin().add("pokemon", manager.getFont());

        //animates the text
        db = new DialogueBox(manager.getSkin(), "dialogbox2");
        //dialogue of the NPC
        dialogue = new Dialogue(stageSelect);

        talking = false;


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

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;


        jediGrandpaDown = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_X, Constants.JEDI_GRANDPA_Y,
                Constants.JEDI_GRANDPA_WIDTH,Constants.JEDI_GRANDPA_HEIGHT);
        jediProfDown = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_X, Constants.JEDI_PROF_Y, Constants.JEDI_PROF_WIDTH, Constants.JEDI_PROF_HEIGHT);

        jediGrandpaLeft = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_LEFT_X, Constants.JEDI_GRANDPA_SIDE_Y, Constants.JEDI_GRANDPA_SIDE_WIDTH, Constants.JEDI_GRANDPA_SIDE_HEIGHT);
        jediProfLeft = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_LEFT_X, Constants.JEDI_PROF_SIDE_Y, Constants.JEDI_PROF_SIDE_WIDTH, Constants.JEDI_PROF_SIDE_HEIGHT);

        jediGrandpaRight = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_RIGHT_X, Constants.JEDI_GRANDPA_SIDE_Y, Constants.JEDI_GRANDPA_SIDE_WIDTH, Constants.JEDI_GRANDPA_SIDE_HEIGHT);

        jediGrandpaTop = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_TOP_X, Constants.JEDI_GRANDPA_TOP_Y, Constants.JEDI_GRANDPA_TOP_WIDTH, Constants.JEDI_GRANDPA_TOP_HEIGHT);
        jediProfTop = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_PROF_TOP_X, Constants.JEDI_PROF_TOP_Y, Constants.JEDI_PROF_TOP_WIDTH, Constants.JEDI_PROF_TOP_HEIGHT);

        direction = "south";
//        dialogueSkin = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);
    }

    @Override
    public void update(float delta) {
        npcInteraction(delta);
        db.act(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        if(stageSelect >= 1 && stageSelect < 5){
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

        }else if(stageSelect >= 5 && stageSelect < 12) {
            sprite.draw(jediProfDown, body.getPosition().x * Constants.PPM - jediGrandpaDown.getRegionWidth() / 2,
                    body.getPosition().y * Constants.PPM - jediGrandpaDown.getRegionHeight() / 2);
        }

        table.draw(sprite, 1);
        sprite.end();
    }

    public void npcInteraction(float delta){
        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            setTalking(true);
            System.out.println("Jedigrandpa");;
            if(!db.isOpen()){
                //if the dialogue box is not yet open then animate the text and add it to the table to draw it
                System.out.println("here");
                db.textAnimation(dialogue.reader(nextStatement));

                table.add(image).align(Align.left).height(250).width(250).padRight(15f);
                table.add(db).align(Align.right).width(1000);
                table.setHeight(250);
                table.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2 - 400);
            }
        }

        if(!dialogue.isStatementEnd() && Gdx.input.justTouched() && db.isOpen()){
            //proceeds to the next statement if it is not the end
            nextStatement++;
            System.out.println(nextStatement);
            db.textAnimation(dialogue.reader(nextStatement));
        }
        if(dialogue.isStatementEnd() && Gdx.input.justTouched() && db.isOpen()){
            setTalking(false);
            //if at the end resets the table and the statement to the first index
            table.reset();
            db.setOpen(false);
           nextStatement = 0;
        }


        manager.getStage().addActor(table);
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
}
