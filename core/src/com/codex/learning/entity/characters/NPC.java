package com.codex.learning.entity.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.Manager;

public class NPC extends Entity {

    private TextureRegion jediGrandpa, dialogueSkin;
    private boolean inContact;
    private Skin skin;
    private Stage stage;
    private Table table, image;
    private DialogueBox db;
    private Label.LabelStyle labelStyle;
    private TextureAtlas atlas;
    public NPC(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
//        Create a body without collision yet.
        this.position = position;
        this.size = size;
        table = new Table();
        image = new Table(manager.getSkin());
        image.setBackground("dialogbox2");

        labelStyle = new Label.LabelStyle();
        labelStyle.font = manager.getFont();
        labelStyle.font.setColor(Color.BLACK);
        manager.getSkin().add("default", labelStyle);

        manager.getFont().setColor(Color.BLACK);
        manager.getSkin().add("pokemon", manager.getFont());

        db = new DialogueBox(manager.getSkin(), "dialogbox2");

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

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        jediGrandpa = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_X, Constants.JEDI_GRANDPA_Y,
                Constants.JEDI_GRANDPA_WIDTH,Constants.JEDI_GRANDPA_HEIGHT);

        dialogueSkin = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);
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
        sprite.draw(jediGrandpa, body.getPosition().x * Constants.PPM - jediGrandpa.getRegionWidth() / 2,
                body.getPosition().y * Constants.PPM - jediGrandpa.getRegionHeight() / 2);
        table.draw(sprite, 1);

        sprite.end();

    }

    public void npcInteraction(float delta){
        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            System.out.println("Jedigrandpa");;
            if(!db.isOpen()){
                System.out.println("here");
                db.textAnimation("JEDIGRANDPA BABYYYYYYYYYY");
                table.add(image).align(Align.left).width(250).padRight(15f);
                table.add(db).align(Align.right).width(1000);
                table.setHeight(250);
                table.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/Constants.PPM/2, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/Constants.PPM/2 - 400);
            }
        }
        if(Gdx.input.justTouched() && db.isOpen()){
           table.reset();
           db.setOpen(false);
        }
        manager.getStage().addActor(table);
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }
}
