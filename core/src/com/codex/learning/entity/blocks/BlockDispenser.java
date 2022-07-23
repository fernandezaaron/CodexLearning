package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private String direction;
    private String id;
    private String name;
    private int limit;
    private final Blocks[] blocks;
    private Blocks sample;
    private boolean spawned;
    private ArrayList<Blocks> blocksArrayList;

    public BlockDispenser(Manager manager, String direction, String id, String name, int limit) {
        super(manager);
        this.direction = direction;
        this.id = id;
        this.name = name;
        this.limit = limit;

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

        createDispenser();




    }

    public void createBlock(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            for(int i = limit; i > 0; i--){
                blocks[i] = new Blocks(manager, id, name);
                blocks[i].create(new Vector2(this.position.x+ i*50, this.position.y ), new Vector2(0.3f, 0.7f),0);
                System.out.println(this.position.x+ i*50);
                System.out.println("created");
            }

//            blocks[0] = new Blocks(manager, id, name);
//            blocks[0].create(this.position, new Vector2(3.5f, 0.7f), 0);

//            sample = new Blocks(manager, id, name);
//            sample.create(this.position, new Vector2(3.5f, 0.7f), 0);
            spawned = true;
        }

    }

    @Override
    public void update(float delta) {
        createBlock();
        if(spawned){
            //sample.update(delta);
//            blocks[0].update(delta);
//            for(int i=0; i<this.limit; i++){
//                blocks[i].update(delta);
//            }
        }
//        for(Blocks i : blocks){
//            i.update(delta);
//        }

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

        if(spawned){
           // sample.render(sprite);
           // blocks[0].render(sprite);
//            for(Blocks i : blocks){
//                i.render(sprite);
//            }
            for(int i = limit; i > 0; i--){
                blocks[i].render(sprite);
            }
           // blocks[1].render(sprite);
           // blocks[2].render(sprite);
            //blocks[limit].render(sprite);
           //limit--;
        }

//        if(isInDispenser() && limit >= 0 && Gdx.input.isKeyJustPressed(Input.Keys.E))

//        if(limit >= 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)){
//
//            System.out.println("I SUMMONED");
//
//

//        }



    }


    public void createDispenser(){
        switch (direction){
            case "Down":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_FRONT_X,
                        Constants.BLOCK_MACHINE_FRONT_Y,
                        Constants.BLOCK_MACHINE_FRONT_WIDTH,
                        Constants.BLOCK_MACHINE_FRONT_HEIGHT);
            break;
            case "Left":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_LEFT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
            break;
            case "Right":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_RIGHT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
            break;
        }
    }

    public boolean isInDispenser() {
        return inDispenser;
    }

    public void setInDispenser(boolean inDispenser) {
        this.inDispenser = inDispenser;
    }

    public Blocks[] getBlocks() {
        return blocks;
    }
}
