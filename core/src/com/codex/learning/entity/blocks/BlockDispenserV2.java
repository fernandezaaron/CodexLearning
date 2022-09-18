package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class BlockDispenserV2 extends Entity {
    private TextureRegion blockDispenser;
    private boolean inContact, interacting;
    private boolean spawned;
    private boolean cloned;
    private String direction;
    private int limit;
    private boolean[] clone;
    private ShapeRenderer blockID;
    private Blocks[] blocks;
    private int currentBlock;


    public BlockDispenserV2(Manager manager, String direction, int limit) {
        super(manager);
        this.direction = direction;
        this.limit = limit;
        blocks = new Blocks[limit + 1];
        clone = new boolean[limit + 1];
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

        shape.setAsBox(this.size.x, (float) (this.size.y / 1.5),
                new Vector2(0, -(this.size.y - this.size.y / 2)), 0);



        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inContact = false;
        spawned = false;
        cloned = false;


        currentBlock = 1;

        blockID = new ShapeRenderer();
        createDispenser();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);

        sprite.begin();
        sprite.draw(blockDispenser,
                body.getPosition().x * Constants.PPM - blockDispenser.getRegionWidth() / 2,
                body.getPosition().y * Constants.PPM - blockDispenser.getRegionHeight() / 2);

        sprite.end();

        blockID.setProjectionMatrix(manager.getCamera().combined);
        blockID.setColor(246/255f, 228/255f, 216/255f, 0.0f);
        blockID.begin(ShapeRenderer.ShapeType.Filled);
        blockID.rect((this.size.x + (Constants.PPM * body.getPosition().x)),
                (this.size.y + (Constants.PPM * body.getPosition().y)),
                ((this.size.x * Constants.PPM)) * 6.3f,
                -(this.size.y * Constants.PPM / 2));
        blockID.end();

        if(spawned){
            currentBlock++;
            spawned = false;
            cloned = true;
        }

    }


    public void getBlock(){
        if(isInContact() && currentBlock >= limit && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            blocks[currentBlock].setInContact(true);
            clone[currentBlock] = true;
            spawned = true;
        }
    }

    public void createBlock(int index, Blocks block, Vector2 position){
        blocks[index] = block;
        blocks[index].create(new Vector2(position.x, position.y + 3),
                new Vector2(3f, Constants.BLOCKS_HEIGHT),0);
    }

    public void createDispenser(){
        switch (direction){
            case "Down":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_FRONT_X,
                        Constants.BLOCK_MACHINE_FRONT_Y,
                        Constants.BLOCK_MACHINE_FRONT_WIDTH,
                        Constants.BLOCK_MACHINE_FRONT_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.1f,
                        this.size.y * Constants.PPM * 1.62f, 0);
                break;
            case "Left":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_LEFT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.05f,
                        this.size.y * Constants.PPM * 2.02f, 0);
                break;
            case "Right":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_RIGHT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.228f,
                        this.size.y * Constants.PPM * 2.02f, 0);
                break;
        }
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public Blocks[] getBlocks(){
        return blocks;
    }

    public boolean isCloned() {
        return cloned;
    }

    public void setCloned(boolean cloned) {
        this.cloned = cloned;
    }

    public int getLimit() {
        return limit;
    }

    public int getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(int currentBlock) {
        this.currentBlock = currentBlock;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public boolean getClone(int index) {
        return clone[index];
    }

    public void setClone(boolean[] clone) {
        this.clone = clone;
    }
}