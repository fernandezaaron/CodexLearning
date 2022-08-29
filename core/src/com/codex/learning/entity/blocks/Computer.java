package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.entity.Entity;
import com.codex.learning.states.minigames.CodeRiddle;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

public class Computer extends Entity {
    private TextureRegion pc;
    private boolean inContact, isDone;
    private CodeRiddle codeRiddle;
    private FuzzyLogic fuzzyLogic;
    private int stage;


    public Computer(Manager manager, FuzzyLogic fuzzyLogic, int stage) {
        super(manager);
        this.fuzzyLogic = fuzzyLogic;
        this.stage = stage;
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
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
        isDone = false;

        pc = new TextureRegion(manager.getPcStateSheet(), Constants.PC_X, Constants.PC_Y, Constants.PC_WIDTH, Constants.PC_HEIGHT);
        codeRiddle = new CodeRiddle(manager, stage);
    }

    @Override
    public void update(float delta) {
        checkIfClicked();
        checkIfDone();
        codeRiddle.update(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
            sprite.draw(pc,
                    body.getPosition().x * Constants.PPM - pc.getRegionWidth() / 2,
                    body.getPosition().y * Constants.PPM - pc.getRegionHeight() / 2);
        sprite.end();
        codeRiddle.render(sprite);
    }

    private void checkIfClicked(){
        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            codeRiddle.setInComputer(true);
        }

    }

    private void checkIfDone(){
        if(codeRiddle.isDone()){
            setDone(true);
        }
        else {
            setDone(false);
        }
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public CodeRiddle getCodeRiddle() {
        return codeRiddle;
    }

    public void setCodeRiddle(CodeRiddle codeRiddle) {
        this.codeRiddle = codeRiddle;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
