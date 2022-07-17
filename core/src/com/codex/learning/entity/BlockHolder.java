package com.codex.learning.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.codex.learning.entity.characters.Character;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class BlockHolder extends Entity{
    private ShapeRenderer shapeRenderer;
    private Rectangle rectangle;
    private int r, g, b;
    private boolean inContact;

    public BlockHolder(Manager manager, int r, int g, int b) {
        super(manager);
        this.r = r;
        this.g = g;
        this.b = b;
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
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
//        body.createFixture(fixtureDef);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        shapeRenderer = new ShapeRenderer();
        rectangle = new Rectangle();
        rectangle.set(this.size.x, this.size.y,
                this.size.x * Constants.PPM,
                this.size.y * Constants.PPM);



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
        shapeRenderer.setColor(this.r/255f, this.g/255f, this.b/255f, 0.0f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        shapeRenderer.rect((this.size.x  * 2 + (Constants.PPM * body.getPosition().x)),
//                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
//                (this.size.x * Constants.PPM),
//                - (this.size.y * Constants.PPM));
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        shapeRenderer.end();


    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public void isInRectangle(Character character){
        if(rectangle.contains(character.getBody().getPosition().x, character.getBody().getPosition().y)){
            System.out.println("I AM INSIDE");
        }
        else{
            System.out.println("I AM NOT INSIDE");
        }
    }
}