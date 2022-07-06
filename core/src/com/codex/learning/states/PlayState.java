package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.*;

import com.codex.learning.entity.Character;
import com.codex.learning.utility.*;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;
    private Blocks sample;
    private Blocks sample2;
    private Blocks sample3;
    private BitmapFont font;

    public PlayState(Manager manager) {
        super(manager);

        house = new HouseMap(manager);

//        sample = new Blocks(manager, "class", "class HelloWorld{", new Vector2(40, Constants.BLOCK_FIRST_ROW), new Vector2(330, Constants.BLOCK_HEIGHT));
//        sample.create(new Vector2(5, 0), new Vector2(4.6f, 0.7f), 0);
//
//        sample2 = new Blocks(manager, "}", "}", new Vector2(380, Constants.BLOCK_FIRST_ROW), new Vector2(46, Constants.BLOCK_HEIGHT));
//        sample2.create(new Vector2(5, -5), new Vector2(0.3f, 0.7f), 0);
//
//        sample3 = new Blocks(manager, "args", "String[] args)", new Vector2(380, Constants.BLOCK_SECOND_ROW), new Vector2(206, Constants.BLOCK_HEIGHT));
//        sample3.create(new Vector2(5, 5), new Vector2(2.6f, 0.7f), 0);

//        sample = new Blocks(manager, "class", "class HelloWorld{");
//        sample.create(new Vector2(5, 0), new Vector2(4.6f, 0.7f), 0);
//
//        sample2 = new Blocks(manager, "}", "}");
//        sample2.create(new Vector2(5, -5), new Vector2(0.3f, 0.7f), 0);
//
//        sample3 = new Blocks(manager, "args", "String[] args)");
//        sample3.create(new Vector2(5, 5), new Vector2(2.6f, 0.7f), 0);

        sample = new Blocks(manager, "class", "class HelloWorld{");
        sample.create(new Vector2(1.2f, 0), new Vector2(3.5f, 0.7f), 0);

        sample2 = new Blocks(manager, "}", "}");
        sample2.create(new Vector2(4.2f, -5), new Vector2(0.2f, 0.7f), 0);

        sample3 = new Blocks(manager, "args", "String[] args)");
        sample3.create(new Vector2(5.9f, 5), new Vector2(2.9f, 0.7f), 0);

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);


    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(sample.isPickUp()){
            jedisaur.setPickUpAble(true);
            jedisaur.carryBlock(sample);
        }
        else if(sample2.isPickUp()){
            jedisaur.setPickUpAble(true);
            jedisaur.carryBlock(sample2);
        }else if(sample3.isPickUp()){
            jedisaur.setPickUpAble(true);
            jedisaur.carryBlock(sample3);
        }
        else{
            jedisaur.setPickUpAble(false);
        }
        sample.update(delta);
        sample2.update(delta);
        sample3.update(delta);
        house.exitDoor(jedisaur);
        jediGrandpa.update(delta);
        jedisaur.update(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
//        font.draw(sprite, "tite", jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y);
        sprite.end();
        house.render(sprite);
        sample.render(sprite);
        sample2.render(sprite);
        sample3.render(sprite);
        jediGrandpa.render(sprite);
        jedisaur.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();
        sample.disposeBody();
        sample2.disposeBody();
        sample3.disposeBody();
        house.dispose();
    }
}
