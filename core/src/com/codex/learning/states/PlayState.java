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

    private Blocks[] blocks;
//    private BlockHolder[] blockHolders;
    private BlockDispenser[] blockDispensers;
//    private BlockDispenser blockDispenser, blockDispenser2;

    private PauseState pause;
    public PlayState(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        house = new HouseMap(manager);

        // WILL BE USED, DON'T ERASE
//        blocks = new Blocks[4];
//        blockHolders = new BlockHolder[4];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];

        for(int i = 0; i < 2; i++){
            if(i == 0){
                blockDispensers[i] = new BlockDispenser(manager, "Down", "}", " } ",
                        3, new Vector2(0.3f, 0.7f));
            }
            else{
                blockDispensers[i] = new BlockDispenser(manager, "Right", "}", " } ",
                        3, new Vector2(0.3f, 0.7f));
            }
            blockDispensers[i].create(new Vector2(5 * i, -2), new Vector2(0.3f, 1.3f), 0);

        }

        // WILL BE USED, DON'T ERASE
//        for(int i = 0; i < 3; i++){
//            if(i == 0){
//                blocks[i] = new Blocks(manager, "}", "   } ");
//                blocks[i].create(new Vector2(1.2f + (i * 10), 0),
//                        new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT), 0);
//            }
//            if(i == 1){
//                blocks[i] = new Blocks(manager, "class", "class HelloWorld{");
//                blocks[i].create(new Vector2(1.2f + (i * 10), 0),
//                        new Vector2(Constants.BLOCKS_CLASS_WIDTH, Constants.BLOCKS_HEIGHT), 0);
//            }
//            if(i == 2){
//                blocks[i] = new Blocks(manager, "args", "String[] args)");
//                blocks[i].create(new Vector2(1.2f + (i * 10), 0),
//                        new Vector2(Constants.BLOCKS_ARGS_WIDTH, Constants.BLOCKS_HEIGHT), 0);
//            }
//            blockHolders[i] = new BlockHolder(manager, "}");
//            blockHolders[i].create(new Vector2(6f * i, 0), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
//        }
        // WILL BE USED, DON'T ERASE

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

//        blockDispenser = new BlockDispenser(manager, "Down", "}", " } ", 3, new Vector2(0.3f, 0.7f));
//        blockDispenser.create(new Vector2(1, 2), new Vector2(0.3f, 1.3f), 0);
//
//        blockDispenser2 = new BlockDispenser(manager, "Left", "}", " } ", 3, new Vector2(0.3f, 0.7f));
//        blockDispenser2.create(new Vector2(13, -2), new Vector2(0.3f, 1.3f), 0);

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

//            for(Blocks i: totalBlocks){
//                if (i != null) {
//                    i.update(delta);
//                }
//                else{
//                    continue;
//                }
//            }

            // WILL BE USED, DON'T ERASE
//            for(int i = 0; i < 3; i++){
//                blockHolders[i].update(delta);
//                blocks[i].update(delta);
//            }
            // WILL BE USED, DON'T ERASE

            for(int i = 0; i < 2; i++){
                blockDispensers[i].update(delta);
            }

            // WILL BE USED, DON'T ERASE
//            for(int i = 0; i < 3; i++){
//                if(blocks[i].isInContact()){
//                    jedisaur.carryBlock(blocks[i]);
//                }
//                if(blockHolders[i].isInContact()){
//                    jedisaur.dropBlock(blockHolders[i]);
//                }
//            }
            // WILL BE USED, DON'T ERASE

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

//        for(int i = 0; i < 3; i++){
//            blockHolders[i].render(sprite);
//        }

        for(int i = 0; i < 2; i++){
            blockDispensers[i].render(sprite);
        }

//        for(int i = 0; i < 3; i++){
//            blocks[i].render(sprite);
//        }

//        for(Blocks i: totalBlocks) {
//            if (i != null) {
//                i.render(sprite);
//            } else {
//                continue;
//            }
//        }

//        blockDispenser.render(sprite);
//        blockDispenser2.render(sprite);

        jediGrandpa.render(sprite);
        jedisaur.render(sprite);
        pause.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();

        for(int i = 0; i < 2; i++){
            blockDispensers[i].disposeBody();
        }

        // WILL BE USED, DON'T ERASE
//        for(int i = 0; i < 3; i++){
//            blockHolders[i].disposeBody();
//            blocks[i].disposeBody();
//        }
        // WILL BE USED, DON'T ERASE

//        blockDispenser.disposeBody();
//        blockDispenser2.disposeBody();

        house.dispose();
//        blockHolder.disposeBody();
//        blockHolder2.disposeBody();
    }
}
