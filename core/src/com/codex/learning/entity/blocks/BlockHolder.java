package com.codex.learning.entity.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.codex.learning.entity.Entity;
import com.codex.learning.entity.characters.Character;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class BlockHolder extends Entity {
    private boolean inContact;
    private boolean occupied;
    private boolean isBlockHolder;
    private Blocks copyBlock;
    private TextureRegion normalBlock, highlightBlock;
    private String correctID;


    public BlockHolder(Manager manager, String correctID) {
        super(manager);
        this.correctID = correctID;
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
        shape.setAsBox(this.size.x, this.size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inContact = false;
        occupied = false;
        isBlockHolder = true;
        setCopyBlock(null);

        normalBlock = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH), Constants.BLOCK_X, Constants.BLOCK_Y_NORMAL, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT/2);
        highlightBlock = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH), Constants.BLOCK_X, Constants.BLOCK_Y_HIGHLIGHT, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT/2);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);

        sprite.begin();
        if(isInContact()){
            sprite.draw(highlightBlock,
                    body.getPosition().x * Constants.PPM - highlightBlock.getRegionWidth() / 2,
                    body.getPosition().y * Constants.PPM - highlightBlock.getRegionHeight() / 2);
        }
        else{
            sprite.draw(normalBlock,
                    body.getPosition().x * Constants.PPM - highlightBlock.getRegionWidth() / 2,
                    body.getPosition().y * Constants.PPM - highlightBlock.getRegionHeight() / 2);
        }
        sprite.end();

    }

    public void createFixture(float x, float y){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(x, y / 0.78f,
                new Vector2(0, (y - y / 1.2f)), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.friction = 5;

        body.createFixture(fixtureDef).setUserData(this);
    }

    public void createDefaultFixture(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.friction = 5;

        body.createFixture(fixtureDef).setUserData(this);
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Blocks getCopyBlock() {
        return copyBlock;
    }

    public void setCopyBlock(Blocks copyBlock) {
        this.copyBlock = copyBlock;
    }

    public void setActive(boolean setActive){
        body.setActive(setActive);
    }

    public boolean isBlockHolder() {
        return isBlockHolder;
    }

    public void setBlockHolder(boolean blockHolder) {
        isBlockHolder = blockHolder;
    }

    public String getCorrectID() {
        return correctID;
    }

    public void setCorrectID(String correctID) {
        this.correctID = correctID;
    }
}


//package com.codex.learning.entity.blocks;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Intersector;
//import com.codex.learning.entity.Entity;
//import com.codex.learning.entity.characters.Character;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.FixtureDef;
//import com.badlogic.gdx.physics.box2d.PolygonShape;
//import com.codex.learning.utility.Constants;
//import com.codex.learning.utility.Manager;
//
//public class BlockHolder extends Entity {
//    private TextureRegion normalBlock, highlightBlock;
//    private boolean inContact;
//    private boolean occupied;
//    private Blocks copyBlock;
//    private String correctID;
//
//
//    public BlockHolder(Manager manager, String correctID) {
//        super(manager);
//        this.correctID = correctID;
//    }
//
//    @Override
//    public void create(Vector2 position, Vector2 size, float density) {
//        this.position = position;
//        this.size = size;
//
//        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.StaticBody;
//        def.position.set(this.position);
//        def.fixedRotation = true;
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(this.size.x , this.size.y);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.density = density;
//        fixtureDef.shape = shape;
//        fixtureDef.isSensor = true;
//        fixtureDef.friction = 5;
//
//        body = manager.getWorld().createBody(def);
//        body.createFixture(fixtureDef).setUserData(this);
//        body.setLinearVelocity(0, 0);
//        shape.dispose();
//
//        inContact = false;
//        occupied = false;
//        setCopyBlock(null);
//
//        normalBlock = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH), Constants.BLOCK_X, Constants.BLOCK_Y_NORMAL, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT/2);
//        highlightBlock = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH), Constants.BLOCK_X, Constants.BLOCK_Y_HIGHLIGHT, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT/2);
//    }
//
//    @Override
//    public void update(float delta) {
//    }
//
//    @Override
//    public void render(SpriteBatch sprite) {
//        sprite.enableBlending();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
//
//        sprite.begin();
//        if(isInContact()){
//                sprite.draw(highlightBlock,
//                        body.getPosition().x * Constants.PPM - highlightBlock.getRegionWidth() / 2,
//                        body.getPosition().y * Constants.PPM - highlightBlock.getRegionHeight() / 2);
//            }
//            else{
//                sprite.draw(normalBlock,
//                        body.getPosition().x * Constants.PPM - highlightBlock.getRegionWidth() / 2,
//                        body.getPosition().y * Constants.PPM - highlightBlock.getRegionHeight() / 2);
//        }
//        sprite.end();
//
//    }
//
//    public void createFixture(float x, float y){
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(x, y / 0.78f,
//                new Vector2(0, (y - y / 1.2f)), 0);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.density = density;
//        fixtureDef.shape = shape;
//        fixtureDef.isSensor = true;
//        fixtureDef.friction = 5;
//
//        body.createFixture(fixtureDef).setUserData(this);
//    }
//
//    public void createDefaultFixture(){
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(this.size.x, this.size.y);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.density = density;
//        fixtureDef.shape = shape;
//        fixtureDef.isSensor = true;
//        fixtureDef.friction = 5;
//
//        body.createFixture(fixtureDef).setUserData(this);
//    }
//
//    public boolean isInContact() {
//        return inContact;
//    }
//
//    public void setInContact(boolean inContact) {
//        this.inContact = inContact;
//    }
//
//    public boolean isOccupied() {
//        return occupied;
//    }
//
//    public void setOccupied(boolean occupied) {
//        this.occupied = occupied;
//    }
//
//    public Blocks getCopyBlock() {
//        return copyBlock;
//    }
//
//    public void setCopyBlock(Blocks copyBlock) {
//        this.copyBlock = copyBlock;
//    }
//}
