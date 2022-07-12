package com.codex.learning.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class Blocks extends Entity{
    private String id, name;
    private TextureRegion block;
    private Vector2 positionSheet, sizeSheet;
    private Rectangle rectangle;
    private ShapeRenderer shapeRenderer;
    protected boolean inContact;
//    public Blocks(Manager manager, String id, String name, Vector2 positionSheet, Vector2 sizeSheet) {
//        super(manager);
//        this.id = id;
//        this.name = name;
//        this.positionSheet = positionSheet;
//        this.sizeSheet = sizeSheet;
//    }
    public Blocks(Manager manager, String id, String name) {
        super(manager);
        this.id = id;
        this.name = name;

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
//        shape.setAsBox(this.size.x, this.size.y,
//                new Vector2(0, (float) -((this.size.y / 1.5 ) - this.size.y)), 0);
        shape.setAsBox(this.size.x, this.size.y,
                new Vector2(0, -(this.size.y - this.size.y / 3)), 0);
//        shape.setAsBox(this.size.x, this.size.y);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        shapeRenderer = new ShapeRenderer();
//        block = new TextureRegion(manager.getBlockSheet(), (int) positionSheet.x,
//                (int) positionSheet.y, (int) sizeSheet.x, (int) sizeSheet.y);

        inContact = false;
    }


    @Override
    public void update(float delta) {

    }


    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        shapeRenderer.setProjectionMatrix(manager.getCamera().combined);
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect((this.size.x  * 2 + (Constants.PPM * body.getPosition().x)),
                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
                (this.name.length() + (this.size.x * Constants.PPM)) * 2,
                - (this.size.y * Constants.PPM * 2));
        shapeRenderer.end();

        sprite.begin();
//        sprite.draw(block, body.getPosition().x * Constants.PPM - block.getRegionWidth() / 2,
//                body.getPosition().y * Constants.PPM - block.getRegionHeight() / 2);

        manager.getFont().draw(sprite, this.name,
                (this.size.x + (Constants.PPM * body.getPosition().x)),
                (this.size.y + (Constants.PPM * body.getPosition().y)));

        sprite.end();

    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

}
