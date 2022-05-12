package com.codex.learning.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Animation;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class Jedisaur extends entity{
    private Animation front, side, up;
    private Animation walkFront, walkUp, walkRight;
    private String direction;
    private boolean isLeft;
    private Box2DDebugRenderer b2dr;

    public Jedisaur(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(this.position);
        def.fixedRotation = true;

        isLeft = true;
        direction = "south";

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y);

        b2dr = new Box2DDebugRenderer();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;


        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef);
        shape.dispose();

        front = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 0), Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);
        side = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 1), Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);
        up = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 2) + 3, Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);


        walkFront = new Animation(manager.getSpriteSheet(), 80,0,160,114,2,1);
        walkUp = new Animation(manager.getSpriteSheet(), 80,230,160,114,2,1);
        walkRight = new Animation(manager.getSpriteSheet(), 80, 115, 160, 114,2,1);

    }
    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            walkFront.update(delta);
            direction = "south";
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            walkUp.update(delta);
            direction = "north";
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            walkRight.update(delta);
            direction = "west";
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            walkRight.update(delta);
            direction = "east";
        }
        else{
            front.update(delta);
            side.update(delta);
            up.update(delta);
        }
        cameraUpdate();
        input(delta);
    }
    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            sprite.draw(walkFront.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkFront.getFrame().getRegionWidth()/2),
                    body.getPosition().y * Constants.PPM - ((float)walkFront.getFrame().getRegionHeight()/2));

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            sprite.draw(walkRight.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkRight.getFrame().getRegionWidth()/2)
                    , body.getPosition().y * Constants.PPM - ((float)walkRight.getFrame().getRegionHeight()/2));

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            sprite.draw(walkUp.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkUp.getFrame().getRegionWidth()/2)
                    , body.getPosition().y * Constants.PPM - ((float)walkUp.getFrame().getRegionHeight()/2));

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            sprite.draw(walkRight.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkRight.getFrame().getRegionWidth()/2)
                    , body.getPosition().y * Constants.PPM - ((float)walkRight.getFrame().getRegionHeight()/2));

        }
        else {
            checkDirection(sprite);
        }
        sprite.end();
    }
    private void checkDirection(SpriteBatch sprite){
        switch(direction){
            case "north":
                sprite.draw(up.getFrame(), body.getPosition().x * Constants.PPM - ((float)front.getFrame().getRegionWidth()/2),
                        body.getPosition().y * Constants.PPM - ((float)front.getFrame().getRegionHeight()/2));
            break;
            case "south":
                sprite.draw(front.getFrame(), body.getPosition().x * Constants.PPM - ((float)front.getFrame().getRegionWidth()/2),
                        body.getPosition().y * Constants.PPM - ((float)front.getFrame().getRegionHeight()/2));
            break;
            case "east":
            case "west":
                sprite.draw(side.getFrame(), body.getPosition().x * Constants.PPM - ((float)front.getFrame().getRegionWidth()/2),
                        body.getPosition().y * Constants.PPM - ((float)front.getFrame().getRegionHeight()/2));
            break;
        }
    }

    public void input(float delta){
        float horizontalForce = 0;
        float verticalForce = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            horizontalForce -= 1.2;
            System.out.println("moving left");
            if(isLeft){
                walkRight.flip();
                side.flip();
                isLeft = false;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            horizontalForce += 1.2;
            System.out.println("moving right");
            if(!isLeft){
                walkRight.flip();
                side.flip();
                isLeft = true;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            verticalForce += 1.2;
            System.out.println("moving up");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            verticalForce -= 1.2;
            System.out.println("moving down");
        }
        body.setLinearVelocity(horizontalForce * Constants.JEDI_VELOCITY, verticalForce * Constants.JEDI_VELOCITY);
    }
    private void cameraUpdate(){
        Vector3 position = manager.getCamera().position;
        position.x = this.position.x * Constants.PPM;
        position.y = this.position.y * Constants.PPM;
        manager.getCamera().position.set(position);
        manager.getCamera().update();
    }
}
