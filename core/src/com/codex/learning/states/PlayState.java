package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.*;

import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.utility.*;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;
    private Blocks sample;
    private Blocks sample2;
    private Blocks sample3;
    private BlockHolder blockHolder;
    private BlockHolder blockHolder2;

    public PlayState(Manager manager) {
        super(manager);

        house = new HouseMap(manager);

//        sample = new Blocks(manager, "class", "class HelloWorld{");
//        sample.create(new Vector2(1.2f, 0), new Vector2(3.5f, 0.7f), 0);
//
//        sample2 = new Blocks(manager, "}", " } ");
//        sample2.create(new Vector2(4.2f, -5), new Vector2(0.3f, 0.7f), 0);
//
//        sample3 = new Blocks(manager, "args", "String[] args)");
//        sample3.create(new Vector2(5.9f, 5), new Vector2(2.9f, 0.7f), 0);

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

        blockHolder = new BlockHolder(manager, 0, 0, 0);
        blockHolder.create(new Vector2(1f, 1f), new Vector2(2f, 2f), 0);
//
//        blockHolder2 = new BlockHolder(manager, 0, 0, 0);
//        blockHolder2.create(new Vector2(5f, 5f), new Vector2(2f, 2f), 0);
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);

//        if(sample.isInContact()){
//            jedisaur.setPickUpAble(true);
//            jedisaur.carryBlock(sample);
//        }
//        else if(sample2.isInContact()){
//            jedisaur.setPickUpAble(true);
//            jedisaur.carryBlock(sample2);
//        }
//        else if(sample3.isInContact()){
//            jedisaur.setPickUpAble(true);
//            jedisaur.carryBlock(sample3);
//        }
//        else{
//            jedisaur.setPickUpAble(false);
//        }
//        sample.update(delta);
//        sample2.update(delta);
//        sample3.update(delta);
        blockHolder.update(delta);
//        blockHolder2.update(delta);
        house.exitDoor(jedisaur);
        jediGrandpa.update(delta);
        jedisaur.update(delta);

        blockHolder.isInRectangle(jedisaur);
//        blockHolder2.isInRectangle(jedisaur);
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.end();
        house.render(sprite);
//        sample.render(sprite);
//        sample2.render(sprite);
//        sample3.render(sprite);
        blockHolder.render(sprite);
//        blockHolder2.render(sprite);
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
        blockHolder.disposeBody();
//        blockHolder2.disposeBody();
    }
}
