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
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class HowToPlay extends Entity {
    private TextureRegion[] images;
    private int currentImage;
    private boolean inContact;
    private boolean inHowToPlay;
    private boolean introDialogue;

    public HowToPlay(Manager manager) {
        super(manager);
        images = new TextureRegion[4];
        currentImage = 0;
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
        fixtureDef.isSensor = false;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inContact = false;
        images[0] = new TextureRegion(new Texture(Constants.FILL_IN_THE_BLOCKS), 0,0, 1600, 900);
        images[1] = new TextureRegion(new Texture(Constants.MYSTERY_CODE), 0, 0, 1600, 900);
        images[2] = new TextureRegion(new Texture(Constants.CODE_ORDER), 0, 0, 1600, 900);
        images[3] = new TextureRegion(new Texture(Constants.CODE_IT), 0, 0, 1600, 900);

    }

    @Override
    public void update(float delta) {
        checkIfClicked();
        checkCurrentPage();
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.enableBlending();
        if(isInHowToPlay()){
            sprite.draw(images[currentImage],
                    (manager.getCamera().position.x / Constants.PPM - images[currentImage].getRegionWidth() / 2) + 25,
                    (manager.getCamera().position.y / Constants.PPM - images[currentImage].getRegionHeight() / 2) + 15);
        }

        sprite.end();
    }

    public void setCurrentImage(int index, SpriteBatch spriteBatch){
        spriteBatch.begin();
        spriteBatch.draw(images[index-1],(manager.getCamera().position.x / Constants.PPM - images[currentImage].getRegionWidth() / 2) + 25,
                (manager.getCamera().position.y / Constants.PPM - images[currentImage].getRegionHeight() / 2) + 15);
        spriteBatch.end();
        setInHowToPlay(true);
        setIntroDialogue(true);
    }

    private void checkCurrentPage(){
        if(isInHowToPlay() && Gdx.input.isKeyJustPressed(Input.Keys.A) && currentImage > 0) {
            currentImage--;
        }
        else if(isInHowToPlay() && Gdx.input.isKeyJustPressed(Input.Keys.D) && currentImage < 3){
            currentImage++;
        }
    }

    private void checkIfClicked(){
        if(isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            setInHowToPlay(true);
        }
        if(isInHowToPlay() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
            setInHowToPlay(false);
            setIntroDialogue(false);
            currentImage = 0;
        }
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public boolean isInHowToPlay() {
        return inHowToPlay;
    }

    public void setInHowToPlay(boolean inHowToPlay) {
        this.inHowToPlay = inHowToPlay;
    }

    public boolean isIntroDialogue() {
        return introDialogue;
    }

    public void setIntroDialogue(boolean introDialogue) {
        this.introDialogue = introDialogue;
    }
}
