package com.codex.learning.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import static java.lang.Math.*;

public class Blocks extends Entity{
    private String name;
    private TextureRegion block;
    private Vector2 positionSheet, sizeSheet;
    public Blocks(Manager manager, String name, Vector2 positionSheet, Vector2 sizeSheet) {
        super(manager);
        this.name = name;
        this.positionSheet = positionSheet;
        this.sizeSheet = sizeSheet;
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
        shape.setAsBox(this.size.x, this.size.y,
                new Vector2(0, (float) -((this.size.y / 1.5 )- this.size.y)), 0);


//        new Vector2(((this.size.y - (this.size.y / 2))),
//                        (this.size.x - (this.size.x / 2)))
//        (this.size.x - (this.size.x / 2)) -

//         - (this.size.y - (this.size.y / 2))
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef);
        shape.dispose();

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        block = new TextureRegion(manager.getBlockSheet(), (int) positionSheet.x,
                (int) positionSheet.y, (int) sizeSheet.x, (int) sizeSheet.y);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.draw(block, body.getPosition().x * Constants.PPM - block.getRegionWidth() / 2,
                body.getPosition().y * Constants.PPM - block.getRegionHeight() / 2);
        sprite.end();
    }
}
