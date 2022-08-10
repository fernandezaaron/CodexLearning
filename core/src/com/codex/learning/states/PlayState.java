package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.utility.*;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;
    private Computer computer;

    private Blocks[] totalBlocks;
    private int blockCount;
    private boolean blockSpawn;

    private Blocks[] blocks;
    private BlockHolder[] blockHolders;
    private BlockDispenser[] blockDispensers;

    private PauseState pause;


    public PlayState(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        house = new HouseMap(manager);


        // WILL BE USED, DON'T ERASE
        blocks = new Blocks[4];
        blockHolders = new BlockHolder[10];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];

        computer = new Computer(manager);
        computer.create(new Vector2(-18, 2.8f), new Vector2(0.6f, 0.6f), 0);

        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < 10; i++){
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
            blockHolders[i] = new BlockHolder(manager, "}");
            blockHolders[i].create(new Vector2(6f , i * 2), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
        }
        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < 2; i++){
            if(i == 0){
                blockDispensers[i] = new BlockDispenser(manager, "Down", "ey", "   }  ",
                        3, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            }
            else{
                blockDispensers[i] = new BlockDispenser(manager, "Right", "class", "class hello  ",
                        3, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            }
            blockDispensers[i].create(new Vector2(15 * i, -6), new Vector2(0.3f, 1.3f), 0);
        }

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

        totalBlocks = new Blocks[6];
        blockCount = 0;

        if(!manager.isMusicPaused()){
            manager.setMusic(Constants.HOUSE_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        }else {
            manager.setMusic(Constants.HOUSE_MUSIC);
        }

        System.out.println(manager.getCamera().position.x + " " + manager.getCamera().position.y);


    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(pause.isRunning()){
            if(!computer.getCodeRiddle().isInComputer()){
                // WILL BE USED, DON'T ERASE
                for(int i = 0; i < 3; i++){
                    blockHolders[i].update(delta);
//                blocks[i].update(delta);
                }
//                // WILL BE USED, DON'T ERASE
//
                for(int i = 0; i < 2; i++){
//                blockDispensers[i].update(delta);
                    blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
                }
//
                for(int i = 0; i < 2; i++) {
                    if(blockDispensers[i].isCloned()){
                        for (Blocks b : blockDispensers[i].getBlocks()) {
                            if (b != null) {
                                b.update(delta);
                                if(b.isInContact()){
                                    jedisaur.carryBlock(b);
                                }
                            }
                            else{
                                continue;
                            }
                        }
                    }
                }

                // WILL BE USED, DON'T ERASE
                for(int i = 0; i < 10; i++){
//                if(blocks[i].isInContact()){
//                    jedisaur.carryBlock(blocks[i]);
//                }
                    if(blockHolders[i].isInContact()){
                        jedisaur.dropBlock(blockHolders[i]);
                    }
                }
                // WILL BE USED, DON'T ERASE

                house.exitDoor(jedisaur);
                jediGrandpa.update(delta);
                jedisaur.update(delta);
                computer.update(delta);
//            pause.update(delta);
            }
            else{
                if(jedisaur.isMoving()){
                    jedisaur.setMoving(false);
                    jedisaur.update(delta);
                    jedisaur.getBody().setLinearVelocity(0,0);
                }
                if(computer.getCodeRiddle().isInComputer() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
                    computer.getCodeRiddle().setInComputer(false);
                }
            }
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
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);


        sprite.end();


      //  house.render(sprite);
//
        for(int i = 0; i < 10; i++){
            blockHolders[i].render(sprite);
        }
//
        for(int i = 0; i < 2; i++){
            blockDispensers[i].render(sprite);
            if(blockDispensers[i].isCloned()){
                for (Blocks b : blockDispensers[i].getBlocks()) {
                    if (b != null) {
                        b.render(sprite);
                    }
                    else{
                        continue;
                    }
                }
            }
        }


//        for(int i = 0; i < 3; i++){
//            blocks[i].render(sprite);
//        }
//
        jediGrandpa.render(sprite);

        if(computer.getCodeRiddle().isInComputer()){
            jedisaur.render(sprite);
            computer.render(sprite);
        }
        else{
            computer.render(sprite);
            jedisaur.render(sprite);
        }
//
        pause.render(sprite);

    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();
        computer.disposeBody();

        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < 3; i++){
            blockHolders[i].disposeBody();
//            blocks[i].disposeBody();
        }
        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < 2; i++){
            blockDispensers[i].disposeBody();
            if(blockDispensers[i].isCloned()){
                for (Blocks b : blockDispensers[i].getBlocks()) {
                    if (b != null) {
                        b.disposeBody();
                    }
                    else{
                        continue;
                    }
                }
            }
        }



        house.dispose();
    }
}
