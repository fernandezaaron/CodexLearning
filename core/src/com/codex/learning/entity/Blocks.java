package com.codex.learning.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class Blocks extends Entity{
    private TextureRegion button[] = new TextureRegion[20];
    public Blocks(Manager manager){
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(this.position);
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public void update(float delta) {
        cameraUpdate();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.draw(manager.getBadLogic(), 100, 0, this.size.x, this.size.y);
        sprite.end();
    }

    private void cameraUpdate(){
        Vector3 position = manager.getCamera().position;
        position.x = this.position.x * Constants.PPM;
        position.y = this.position.y * Constants.PPM;
        manager.getCamera().position.set(position);
        manager.getCamera().update();
    }

}
