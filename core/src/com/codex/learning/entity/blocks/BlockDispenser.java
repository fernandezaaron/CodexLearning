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

public class BlockDispenser extends Entity {
    private TextureRegion blockDispenser;
    private boolean inDispenser;
    private String direction;
    private String id;
    private String name;
    private int limit;
    private final Blocks[] blocks;

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

        createDispenser();

        for(int i = 0; i < this.limit; i++){
            blocks[i] = new Blocks(manager, id, name);
            blocks[i].create(this.position, new Vector2(0.3f, 0.7f),0);
        }


    }

    @Override
    public void update(float delta) {

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

//        if(isInDispenser() && limit >= 0 && Gdx.input.isKeyJustPressed(Input.Keys.E))

        if(limit >= 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)){

            System.out.println("I SUMMONED");


            blocks[limit].render(sprite);
            limit--;
        }

        sprite.end();

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
