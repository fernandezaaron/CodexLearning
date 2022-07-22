package com.codex.learning.entity.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class NPC extends Entity {

    private TextureRegion jediGrandpa;
    public NPC(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
//        Create a body without collision yet.
        this.position = position;
        this.size = size;

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
        body.createFixture(fixtureDef);
        shape.dispose();

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        jediGrandpa = new TextureRegion(manager.getSpriteSheet(), Constants.JEDI_GRANDPA_X, Constants.JEDI_GRANDPA_Y,
                Constants.JEDI_GRANDPA_WIDTH,Constants.JEDI_GRANDPA_HEIGHT);
    }

    @Override
    public void update(float delta) {
//        cameraUpdate();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.draw(jediGrandpa, body.getPosition().x * Constants.PPM - jediGrandpa.getRegionWidth() / 2,
                body.getPosition().y * Constants.PPM - jediGrandpa.getRegionHeight() / 2);
        sprite.end();
    }
//    private void cameraUpdate(){
//        Vector3 position = manager.getCamera().position;
//        position.x = this.position.x * Constants.PPM;
//        position.y = this.position.y * Constants.PPM;
//        manager.getCamera().position.set(position);
//        manager.getCamera().update();
//    }

}