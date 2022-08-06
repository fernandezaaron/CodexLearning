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
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;

public class BlockDispenser extends Entity {
    private TextureRegion blockDispenser;
    private boolean inDispenser;
    private boolean spawned;
    private boolean cloned;
    private String direction;
    private String id;
    private String name;
    private int limit;
    private ShapeRenderer blockID;
    private Blocks[] blocks;
    private Vector2 blockSize;
    private Blocks sample;

    public BlockDispenser(Manager manager, String direction, String id, String name, int limit, Vector2 blockSize) {
        super(manager);
        this.direction = direction;
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.blockSize = blockSize;

        blocks = new Blocks[this.limit + 1];
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

//        shape.setAsBox(this.size.x , this.size.y,
//                new Vector2(0, -(this.size.y - this.size.y / 3)), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inDispenser = false;
        spawned = false;
        cloned = false;

        blockID = new ShapeRenderer();
        createDispenser();
    }

    @Override
    public void update(float delta) {
//        createBlock();
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

        sprite.begin();
        createIDDispenser(sprite);
        sprite.end();

        if(spawned){
//            blocks[limit] = sample;
            limit--;

            spawned = false;
            cloned = true;
        }

//        if(cloned){
//            for(Blocks i: blocks){
//                if (i != null) {
//                    i.render(sprite);
//                }
//                else{
//                    continue;
//                }
//            }
//        }
    }

    public void createBlock(Vector2 position){
        if(isInDispenser() && limit > 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)){

//            sample = new Blocks(manager, id, name);
//            sample.create(new Vector2(this.position.x, this.position.y - limit * 3),
//                    blockSize,0);

            blocks[limit] = new Blocks(manager, id, name);
            blocks[limit].create(new Vector2(position.x, position.y + 3),
                    blockSize,0);
            blocks[limit].setInContact(true);
            spawned = true;
        }
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

    public void createIDDispenser(SpriteBatch sprite){
        switch (direction){
            case "Down":
                manager.getFont().draw(sprite, this.id,
                        adjustFontPosition(this.id.length()),
                       (this.size.y + (this.size.y * (Constants.PPM * 1.5f))) + (Constants.PPM * body.getPosition().y));
                       // this.position.y + (this.position.y / this.size.y * (Constants.PPM + this.size.y)));
//                        this.size.y - body.getPosition().y);

            break;
            case "Left":
            case "Right":
                manager.getFont().draw(sprite, this.id,
                        adjustFontPosition(this.id.length()),
                        (this.size.y + (this.size.y * (Constants.PPM * 1.9f))) + (Constants.PPM * body.getPosition().y));
//                        (this.size.y / (Constants.PPM / body.getPosition().y * this.size.y)));
            break;
        }
    }

    public float adjustFontPosition(int num){
        float x = 0f;
        switch(num){
            case 1:
                x = this.size.x - (this.size.x * (Constants.PPM * 0.3f)) + (Constants.PPM * body.getPosition().x);
            break;
            case 2:
                x = this.size.x - (this.size.x * Constants.PPM * 1.5f) + (Constants.PPM * body.getPosition().x);
            break;
            case 3:
                x = this.size.x - (this.size.x * Constants.PPM * 2.4f) + (Constants.PPM * body.getPosition().x);
            break;
            case 4:
                x = this.size.x - (this.size.x * Constants.PPM * 3.2f) + (Constants.PPM * body.getPosition().x);
            break;
        }
        return x;
    }

    public boolean isInDispenser() {
        return inDispenser;
    }

    public void setInDispenser(boolean inDispenser) {
        this.inDispenser = inDispenser;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public Blocks getCurrentBlock(){
        return blocks[limit];
    }

}