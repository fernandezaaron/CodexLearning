package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.utility.*;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;

    private Blocks[] totalBlocks;
    private int blockCount;
    private boolean blockSpawn;

    private Blocks sample, sample2, sample3;

    private BlockHolder blockHolder;
    private BlockHolder blockHolder2;
    private BlockDispenser blockDispenser;
    private BlockDispenser blockDispenser2;
    private PauseState pause;
    public PlayState(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        house = new HouseMap(manager);

        sample = new Blocks(manager, "class", "class HelloWorld{");
        sample.create(new Vector2(1.2f, 0), new Vector2(3.5f, 0.85f), 0);

        sample2 = new Blocks(manager, "}", " } ");
        sample2.create(new Vector2(4.2f, -5), new Vector2(0.3f, 0.7f), 0);

        sample3 = new Blocks(manager, "args", "String[] args)");
        sample3.create(new Vector2(5.9f, 5), new Vector2(2.9f, 0.7f), 0);

        blockHolder = new BlockHolder(manager);
        blockHolder.create(new Vector2(-1f, -1f), new Vector2(0.7f, 0.8f), 0);

        blockHolder2 = new BlockHolder(manager);
        blockHolder2.create(new Vector2(-5f, -5f), new Vector2(0.7f, 0.8f), 0);

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

//        blockDispenser = new BlockDispenser(manager, "Down", "}", " } ", 3, new Vector2(0.3f, 0.7f));
//        blockDispenser.create(new Vector2(1, 5), new Vector2(0.3f, 1.3f), 0);
//
//        blockDispenser2 = new BlockDispenser(manager, "Right", "}", " } ", 3, new Vector2(0.3f, 0.7f));
//        blockDispenser2.create(new Vector2(6, 5), new Vector2(0.3f, 1.3f), 0);

        totalBlocks = new Blocks[6];
        blockCount = 0;
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(pause.isRunning()){
//            if(blockDispenser.isSpawned()){
//                totalBlocks[blockCount] = blockDispenser.getCurrentBlock();
//                blockCount++;

//                blockDispenser.getCurrentBlock().disposeBody();

//                blockDispenser.getCurrentBlock().setInContact(true);
//                jedisaur.carryBlock(blockDispenser.getCurrentBlock());
//            }


//        for(Blocks i: totalBlocks){
//            if (i != null) {
//                if(i.isInContact()){
//                    jedisaur.carryBlock(i);
//                }
//            }
//            else{
//                continue;
//            }
//        }

            sample.update(delta);
            sample2.update(delta);
            sample3.update(delta);

            if(sample.isInContact()){
                jedisaur.carryBlock(sample);
            }
            if(sample2.isInContact()){
                jedisaur.carryBlock(sample2);
            }
            if(sample3.isInContact()){
                jedisaur.carryBlock(sample3);
            }
            if(blockHolder.isInContact()){
                jedisaur.dropBlock(sample, blockHolder);
            }

            blockHolder.update(delta);
            blockHolder2.update(delta);

//            for(Blocks i: totalBlocks){
//                if (i != null) {
//                    i.update(delta);
//                }
//                else{
//                    continue;
//                }
//            }

//            blockDispenser.update(delta);
//            blockDispenser2.update(delta);

            house.exitDoor(jedisaur);
            jediGrandpa.update(delta);
            jedisaur.update(delta);

//            pause.update(delta);
        }else{
            if(jedisaur.isMoving()){
                jedisaur.setMoving(false);
                jedisaur.update(delta);
                jedisaur.getBody().setLinearVelocity(0,0);
            }
        }
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.end();

//        house.render(sprite);

        blockHolder.render(sprite);
        blockHolder2.render(sprite);

        sample.render(sprite);
        sample2.render(sprite);
        sample3.render(sprite);

//        blockDispenser.render(sprite);
//        blockDispenser2.render(sprite);

//        for(Blocks i: totalBlocks) {
//            if (i != null) {
//                i.render(sprite);
//            } else {
//                continue;
//            }
//        }
        jediGrandpa.render(sprite);
        jedisaur.render(sprite);
        pause.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();

        sample.disposeBody();
        sample2.disposeBody();
        sample3.disposeBody();

//        blockDispenser.disposeBody();
//        blockDispenser2.disposeBody();

        house.dispose();

        blockHolder.disposeBody();
        blockHolder2.disposeBody();
    }
}
