package com.codex.learning.entity.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.Entity;
import com.codex.learning.entity.blocks.PlayMat;
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
    private boolean fixture;
    private int carry;

    private int numberOfBlockInteraction;

    private boolean atTop;
    private boolean atBot;
    private boolean atLeft;
    private boolean atRight;
    private boolean isDropped, pickedUp;

    private Box2DDebugRenderer b2dr;

    private Blocks copyBlock;

    public Character(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {

        // Create a body without collision yet.
        this.position = position;
        this.size = size;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(this.position);
        def.fixedRotation = true;

        // Create a rectangle for the character to have collision detection.
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y/2, new Vector2(0, -this.size.y/2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.75f;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();

        numberOfBlockInteraction = 0;

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
        setCopyBlock(null);
        isDropped = false;
        pickedUp = false;

        // Used to know the last keyboard pressed of the user
        direction = "south";

        fixture = false;

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
    public void update(float delta){
        logicInput(delta);
        cameraUpdate();
        input(delta);
        checkIfStuck();
    }

    @Override
    public void render(SpriteBatch sprite){
        manager.getCamera().update();
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        checkDirection(sprite);
        sprite.end();
    }

    private void checkDirection(SpriteBatch sprite){
        if(isPickUpAble() && !isCarrying() && !isMoving()){
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
        else if(isCarrying()) {
            if(isMoving()) {
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
            if(isMoving()){
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
            if(isCarrying())
                carryWalkFront.update(delta);
            walkFront.update(delta);
            direction = "south";
            setMoving(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(isCarrying())
                carryWalkUp.update(delta);
            walkUp.update(delta);
            direction = "north";
            setMoving(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(isCarrying())
                carryWalkSide.update(delta);
            walkSide.update(delta);
            direction = "west";
            setMoving(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(isCarrying())
                carryWalkSide.update(delta);
            walkSide.update(delta);
            direction = "east";
            setMoving(true);
        }
        else{
            if(!isCarrying()){
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
            setMoving(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E) && isPickUpAble()){
            if (isCarrying()) {
                setCarrying(false);
            }
            else {
                setCarrying(true);
            }
        }
//        if(Gdx.input.isKeyJustPressed(Input.Keys.E) && isInteractingDispenser()){
//            if (isCarrying()) {
//                setCarrying(false);
//            }
//            else {
//                setCarrying(true);
//            }
//        }
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
        position.x = Constants.SCREEN_WIDTH/2/Constants.PPM;
        position.y = Constants.SCREEN_HEIGHT/2/Constants.PPM;
        manager.getCamera().position.set(position);
//        manager.getCamera().position.set(new Vector2(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT))
        manager.getCamera().update();

    }

    private void checkIfStuck(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            System.out.println("HERE");
            body.getPosition().set(10, 10);
        }
    }

    public void carryBlock(Blocks block){
        if(isCarrying() && carry == 0){
            carry = 1;
            numberOfBlockInteraction++;
            setCopyBlock(block);
            System.out.println(copyBlock + " carrying");
        }
        if(getCopyBlock() != null){
            getCopyBlock().getBody().setType(BodyDef.BodyType.DynamicBody);
            getCopyBlock().getBody().setTransform(body.getPosition().x - (block.getDupliSize().x), body.getPosition().y + 3f, 0);
//            System.out.println(this.size.x + " sa character");
        }
        block.getBody().setType(BodyDef.BodyType.StaticBody);
    }

    public void dropBlock(BlockHolder blockHolder){

//        System.out.println("Occupied - " + blockHolder.isOccupied());

        // To prevent pickup in an empty block holder
        if(blockHolder.isOccupied() && !isCarrying()){
            setPickUpAble(true);
        }
        else{
            setPickUpAble(false);
        }

        if(blockHolder.isOccupied()){
            blockHolder.setCopyBlock(getCopyBlock());
            setFixture(true);
//            System.out.println(numberOfBlockInteraction);
        }
        else{
            blockHolder.setCopyBlock(null);
            setFixture(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.E) &&
                getCopyBlock() != null && blockHolder.getCopyBlock() == null){
            System.out.println(blockHolder.getBody().getPosition());
            // Blocks Adjustment
            getCopyBlock().getBody().setTransform(
                    blockHolder.getBody().getPosition().x,
                    Constants.BLOCK_HOLDER_HEIGHT + blockHolder.getBody().getPosition().y,
                    0);
            getCopyBlock().setInContact(false);
            getCopyBlock().getBody().setType(BodyDef.BodyType.StaticBody);

            // BlockHolder Adjustment
            blockHolder.setOccupied(true);
            if(!isFixture()){
                blockHolder.createFixture(getCopyBlock().getDupliSize().x, getCopyBlock().getDupliSize().y);
            }

            blockHolder.setCopyBlock(getCopyBlock());
            System.out.println(blockHolder.getCopyBlock() + "character");
            // Character Adjustment
            setDropped(true);
            setCopyBlock(null);
            carry = 0;
            setPickUpAble(false);
            setCarrying(false);
            setPickedUp(false);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.E) && isFixture() && blockHolder.isOccupied()){

            blockHolder.setCopyBlock(null);
            if(!isCarrying()){
                blockHolder.getBody().destroyFixture(blockHolder.getBody().getFixtureList().first());
                for(int i = 0; i < blockHolder.getBody().getFixtureList().size; i++){
                    blockHolder.getBody().destroyFixture(blockHolder.getBody().getFixtureList().removeIndex(i));
                }
                blockHolder.createDefaultFixture();
            }



            if(isCarrying()){
                blockHolder.setOccupied(true);
                setPickUpAble(false);
            }
            else{
                blockHolder.setOccupied(false);
                setPickUpAble(true);
            }
            setDropped(false);
            setPickedUp(true);

        }
    }

    public void dropBlock(PlayMat playmat){
        if(playmat.isInContact() && Gdx.input.isKeyJustPressed(Input.Keys.E) && getCopyBlock() != null && isCarrying()){

            switch (direction){
                case "north":
                    getCopyBlock().getBody().setTransform(
                            getBody().getPosition().x ,
                            getBody().getPosition().y + 0.5f,
                            0);
                    break;
                case "south":
                    getCopyBlock().getBody().setTransform(
                            getBody().getPosition().x,
                            getBody().getPosition().y - 1.5f,
                            0);
                    break;
                case "east":
                    getCopyBlock().getBody().setTransform(
                            getBody().getPosition().x + 1,
                            getBody().getPosition().y,
                            0);
                    break;
                case "west":
                    getCopyBlock().getBody().setTransform(
                            getBody().getPosition().x - 1,
                            getBody().getPosition().y,
                            0);
                    break;

            }

            getCopyBlock().setInContact(false);
            getCopyBlock().getBody().setType(BodyDef.BodyType.StaticBody);

            setDropped(true);
            setCopyBlock(null);
            carry = 0;
            setPickUpAble(false);
            setCarrying(false);
            setPickedUp(false);
        }

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Blocks getCopyBlock() {
        return copyBlock;
    }

    public void setCopyBlock(Blocks copyBlock) {
        this.copyBlock = copyBlock;
    }

    public boolean isPickUpAble() {
        return pickUpAble;
    }

    public void setPickUpAble(boolean pickUpAble) {
        this.pickUpAble = pickUpAble;
    }

    public boolean isCarrying() {
        return isCarrying;
    }

    public void setCarrying(boolean carrying) {
        isCarrying = carrying;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isFixture() {
        return fixture;
    }

    public void setFixture(boolean fixture) {
        this.fixture = fixture;
    }

    public boolean isDropped() {
        return isDropped;
    }

    public void setDropped(boolean dropped) {
        isDropped = dropped;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public int getNumberOfBlockInteraction() {
        return numberOfBlockInteraction;
    }

    public void setNumberOfBlockInteraction(int numberOfBlockInteraction) {
        this.numberOfBlockInteraction = numberOfBlockInteraction;
    }
}