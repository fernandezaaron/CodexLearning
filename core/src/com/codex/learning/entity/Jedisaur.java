package com.codex.learning.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.codex.learning.utility.Animation;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;


public class Jedisaur extends Entity {
    private Animation front, side, up;
    private Animation walkFront, walkUp, walkSide;
    private Animation carryFront, carryUp, carrySide;
    private String direction;
    private boolean isMoving;
    private boolean isLeft;

    private boolean atTop;
    private boolean atBot;
    private boolean atLeft;
    private boolean atRight;
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

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y/2, new Vector2(0, -this.size.y/2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef);
        shape.dispose();

        // Used to check if the character is in the border of the map
        atTop = false;
        atBot = false;
        atLeft = false;
        atRight = false;

        // Used to flip the sprite left to right vice versa
        isLeft = true;
        isMoving = false;

        // Used to know the last keyboard pressed of the user
        direction = "south";

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        front = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 0), Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);
        side = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 1), Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);
        up = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y + (Constants.JEDI_STAND_HEIGHT * 2) + 3, Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0);

        walkFront = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_WALK_FRONT_Y,(Constants.JEDI_STAND_WIDTH * 2),Constants.JEDI_STAND_HEIGHT,2,0.5f);
        walkUp = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_WALK_UP_Y,(Constants.JEDI_STAND_WIDTH * 2),Constants.JEDI_STAND_HEIGHT,2,0.5f);
        walkSide = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_WALK_SIDE_Y, (Constants.JEDI_STAND_WIDTH * 2), Constants.JEDI_STAND_HEIGHT,2,0.5f);

    }
    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            walkFront.update(delta);
            direction = "south";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            walkUp.update(delta);
            direction = "north";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            walkSide.update(delta);
            direction = "west";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            walkSide.update(delta);
            direction = "east";
            isMoving = true;
        }
        else{
            front.update(delta);
            side.update(delta);
            up.update(delta);
            isMoving = false;
        }
        cameraUpdate();
        input(delta);
    }
    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        checkDirection(sprite, isMoving);
        sprite.end();
    }
    private void checkDirection(SpriteBatch sprite, boolean isMoving){
        if(isMoving) {
            switch(direction){
                case "north":
                    sprite.draw(walkUp.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkUp.getFrame().getRegionWidth()/2)
                            , body.getPosition().y * Constants.PPM - ((float)walkUp.getFrame().getRegionHeight()/2));
                    break;
                case "south":
                    sprite.draw(walkFront.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkFront.getFrame().getRegionWidth()/2),
                            body.getPosition().y * Constants.PPM - ((float)walkFront.getFrame().getRegionHeight()/2));
                    break;
                case "east":
                case "west":
                    sprite.draw(walkSide.getFrame(), body.getPosition().x * Constants.PPM - ((float)walkSide.getFrame().getRegionWidth()/2)
                            , body.getPosition().y * Constants.PPM - ((float)walkSide.getFrame().getRegionHeight()/2));
                    break;
            }
        }
        else {
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
    }

    public void input(float delta){
        float horizontalForce = 0;
        float verticalForce = 0;
        if(!atLeft){
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                horizontalForce -= 1.2;
                if(isLeft){
                    walkSide.flip();
                    side.flip();
                    isLeft = false;
                }
            }
        }
        else{
            atLeft = false;
        }

        if(!atRight){
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                horizontalForce += 1.2;
                if(!isLeft){
                    walkSide.flip();
                    side.flip();
                    isLeft = true;
                }
            }
        }
        else{
            atRight = false;
        }

        if(!atTop){
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                verticalForce += 1.2;
            }
        }
        else{
            atTop = false;
        }
        if(!atBot){
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                verticalForce -= 1.2;
            }
        }
        else{
            atBot = false;
        }

        if(verticalForce != 0.0f && horizontalForce != 0.0f){
            // sqrt(1/2)
            verticalForce *= Constants.DIAGONAL_SPEED;
            horizontalForce *= Constants.DIAGONAL_SPEED;
        }


        if(body.getPosition().y + size.y + 2.7 > Constants.SCREEN_HEIGHT / 2 / Constants.PPM){
            atTop = true;
        }

        if(body.getPosition().y - size.y - 2.7 < -Constants.SCREEN_HEIGHT / 2 / Constants.PPM){
            atBot = true;
        }

        if(body.getPosition().x - size.x - 1.7 < -Constants.SCREEN_WIDTH / 2 / Constants.PPM){
            atLeft = true;
        }

        if(body.getPosition().x + size.x + 1.7 > Constants.SCREEN_WIDTH / 2 /Constants.PPM){
            atRight = true;
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
