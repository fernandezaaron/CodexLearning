package com.codex.learning.entity.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Animation;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import static java.lang.Math.abs;


public class Character extends Entity {
    private Animation front, side, up;
    private Animation walkFront, walkSide, walkUp;
    private Animation pickUpFront, pickUpSide, pickUpUp;
    private Animation carryFront, carrySide, carryUp;
    private Animation carryWalkFront, carryWalkSide, carryWalkUp;

    private String direction;
    private boolean isMoving;
    private boolean isLeft;
    private boolean isCarrying;
    protected boolean pickUpAble;
    private boolean dropped;
    private int carry;

    private boolean atTop;
    private boolean atBot;
    private boolean atLeft;
    private boolean atRight;

    private Box2DDebugRenderer b2dr;

    public Character(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
//        Create a body without collision yet.
        this.position = position;
        this.size = size;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(this.position);
        def.fixedRotation = true;

//        Create a rectangle for the character to have collision detection.
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y/2, new Vector2(0, -this.size.y/2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();

        // Used to check if the character is in the border of the map
        atTop = false;
        atBot = false;
        atLeft = false;
        atRight = false;


        // Used to flip the sprite left to right vice versa
        isLeft = true;
        isMoving = false;

        // Used to check if the character is carrying a block
        isCarrying = false;

        // Used to know the last keyboard pressed of the user
        direction = "south";

        carry = 0;
        dropped = true;

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        front = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_FIRST_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1, 0);
        side = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_SECOND_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1, 0);
        up = new Animation(manager.getSpriteSheet(), Constants.JEDI_STAND_X, Constants.JEDI_THIRD_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1, 0);

        walkFront = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_FIRST_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);
        walkSide = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_SECOND_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);
        walkUp = new Animation(manager.getSpriteSheet(), Constants.JEDI_WALK_X, Constants.JEDI_THIRD_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);

        pickUpFront = new Animation(manager.getSpriteSheet(), Constants.JEDI_PICK_UP_X, Constants.JEDI_FIRST_ROW, (Constants.JEDI_WIDTH), Constants.JEDI_HEIGHT, 1, 0);
        pickUpSide = new Animation(manager.getSpriteSheet(), Constants.JEDI_PICK_UP_X, Constants.JEDI_SECOND_ROW, (Constants.JEDI_WIDTH), Constants.JEDI_HEIGHT, 1, 0);
        pickUpUp = new Animation(manager.getSpriteSheet(), Constants.JEDI_PICK_UP_X, Constants.JEDI_THIRD_ROW, (Constants.JEDI_WIDTH), Constants.JEDI_HEIGHT, 1, 0);

        carryFront = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_X, Constants.JEDI_FIRST_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1,0);
        carrySide = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_X, Constants.JEDI_SECOND_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1,0);
        carryUp = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_X, Constants.JEDI_THIRD_ROW, Constants.JEDI_WIDTH, Constants.JEDI_HEIGHT,1,0);

        carryWalkFront = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_WALK_X, Constants.JEDI_FIRST_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);
        carryWalkSide = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_WALK_X, Constants.JEDI_SECOND_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);
        carryWalkUp = new Animation(manager.getSpriteSheet(), Constants.JEDI_CARRY_WALK_X, Constants.JEDI_THIRD_ROW, (Constants.JEDI_WIDTH * 2), Constants.JEDI_HEIGHT,2,0.5f);
    }
    @Override
    public void update(float delta) {
        logicInput(delta);
        cameraUpdate();
        input(delta);

    }
    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        checkDirection(sprite, isMoving, isCarrying, pickUpAble);
        sprite.end();
    }

    private void checkDirection(SpriteBatch sprite, boolean isMoving, boolean isCarrying, boolean isPicked){
        if(isPicked && !isCarrying && !isMoving){
            switch (direction) {
                case "north":
                    sprite.draw(pickUpUp.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkUp.getFrame().getRegionWidth() / 2)
                            , body.getPosition().y * Constants.PPM - ((float) walkUp.getFrame().getRegionHeight() / 2));
                    break;
                case "south":
                    sprite.draw(pickUpFront.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkFront.getFrame().getRegionWidth() / 2),
                            body.getPosition().y * Constants.PPM - ((float) walkFront.getFrame().getRegionHeight() / 2));
                    break;
                case "east":
                case "west":
                    sprite.draw(pickUpSide.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkSide.getFrame().getRegionWidth() / 2)
                            , body.getPosition().y * Constants.PPM - ((float) walkSide.getFrame().getRegionHeight() / 2));
                    break;
            }
        }
        else if(isCarrying) {
            if(isMoving) {
                switch (direction) {
                    case "north":
                        sprite.draw(carryWalkUp.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkUp.getFrame().getRegionWidth() / 2)
                                , body.getPosition().y * Constants.PPM - ((float) walkUp.getFrame().getRegionHeight() / 2));
                        break;
                    case "south":
                        sprite.draw(carryWalkFront.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkFront.getFrame().getRegionWidth() / 2),
                                body.getPosition().y * Constants.PPM - ((float) walkFront.getFrame().getRegionHeight() / 2));
                        break;
                    case "east":
                    case "west":
                        sprite.draw(carryWalkSide.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkSide.getFrame().getRegionWidth() / 2)
                                , body.getPosition().y * Constants.PPM - ((float) walkSide.getFrame().getRegionHeight() / 2));
                        break;
                }
            } else {
                switch (direction) {
                    case "north":
                        sprite.draw(carryUp.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkUp.getFrame().getRegionWidth() / 2)
                                , body.getPosition().y * Constants.PPM - ((float) walkUp.getFrame().getRegionHeight() / 2));
                        break;
                    case "south":
                        sprite.draw(carryFront.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkFront.getFrame().getRegionWidth() / 2),
                                body.getPosition().y * Constants.PPM - ((float) walkFront.getFrame().getRegionHeight() / 2));
                        break;
                    case "east":
                    case "west":
                        sprite.draw(carrySide.getFrame(), body.getPosition().x * Constants.PPM - ((float) walkSide.getFrame().getRegionWidth() / 2)
                                , body.getPosition().y * Constants.PPM - ((float) walkSide.getFrame().getRegionHeight() / 2));
                        break;
                }
            }
        } else{
            if(isMoving){
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
            else{
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
    }

    public void logicInput(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if(isCarrying)
                carryWalkFront.update(delta);
            walkFront.update(delta);
            direction = "south";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(isCarrying)
                carryWalkUp.update(delta);
            walkUp.update(delta);
            direction = "north";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(isCarrying)
                carryWalkSide.update(delta);
            walkSide.update(delta);
            direction = "west";
            isMoving = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(isCarrying)
                carryWalkSide.update(delta);
            walkSide.update(delta);
            direction = "east";
            isMoving = true;


        }
        else{
            if(!isCarrying){
                pickUpFront.update(delta);
                pickUpSide.update(delta);
                pickUpUp.update(delta);
                carryFront.update(delta);
                carrySide.update(delta);
                carryUp.update(delta);
            }
            front.update(delta);
            side.update(delta);
            up.update(delta);
            isMoving = false;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E) && isPickUpAble()){
            if (isCarrying) {
                isCarrying = false;
            }
            else {
                isCarrying = true;
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
                    carryWalkSide.flip();
                    carrySide.flip();
                    pickUpSide.flip();
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
                    carryWalkSide.flip();
                    carrySide.flip();
                    pickUpSide.flip();
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

        // END OF MAP DETECTOR
        if(body.getPosition().y + size.y + 2.7 > Constants.SCREEN_HEIGHT / 2 / Constants.PPM){
            atTop = true;
        }

        if(body.getPosition().y - size.y - 3 < -Constants.SCREEN_HEIGHT / 2 / Constants.PPM){
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

    public boolean isPickUpAble() {
        return pickUpAble;
    }

    public void setPickUpAble(boolean pickUpAble) {
        this.pickUpAble = pickUpAble;
    }

    public void carryBlock(Blocks block){
        if(isCarrying && block.isInContact() && carry == 0){
            carry = 1;
            dropped = false;
            block.getBody().setTransform(body.getPosition().x, body.getPosition().y + 3f, 0);
//            System.out.println(body.getPosition().x + " " + body.getPosition().y);
            block.getBody().setType(BodyDef.BodyType.DynamicBody);
        }
        else{
            carry = 0;
            dropped = true;
//            if(!isCarrying){
//                switch (direction){
//                    case "north":
//                        block.body.setTransform(body.getPosition().x, body.getPosition().y + 1.2f, 0);
//                        break;
//                    case "south":
//                        block.body.setTransform(body.getPosition().x, body.getPosition().y - 1.2f, 0);
//                        break;
//                    case "east":
//                        block.body.setTransform(body.getPosition().x + 1.2f, body.getPosition().y, 0);
//                        break;
//                    case "west":
//                        block.body.setTransform(- body.getPosition().x -1.2f, body.getPosition().y, 0);
//                        break;
//                }
//            }
            block.getBody().setType(BodyDef.BodyType.StaticBody);
        }
//        else{
//            carry = 0;
//            block.body.setType(BodyDef.BodyType.StaticBody);
//        }
    }


    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

}