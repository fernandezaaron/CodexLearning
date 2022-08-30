package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.Manager;

public class sample extends State {

    private BlockHolder[] blockHolders;
    private Blocks[] blocks;
    private Character jedisaur;

    public sample(Manager manager, Character jedisaur){
        super(manager);
        blockHolders = new BlockHolder[5];
        blocks = new Blocks[5];

        for(int i=0; i<5; i++){
            blockHolders[i] = new BlockHolder(manager, "asd");
            blockHolders[i].create(new Vector2(4*i,-4), new Vector2(1.5f, 1.5f), 0);


            blocks[i] = new Blocks(manager, "asd", String.valueOf(i), false);
            blocks[i].create(new Vector2(10, 3*i), new Vector2(0.8f, 0.8f), 0);
        }

        this.jedisaur = jedisaur;
    }

    @Override
    public void update(float delta) {
        for(int i=0; i<5; i++){
            if(blockHolders[i].isInContact()){
                jedisaur.dropBlock(blockHolders[i]);
            }
            blockHolders[i].update(delta);
            if(blocks[i].isInContact()){
                jedisaur.carryBlock(blocks[i]);
            }

            blocks[i].update(delta);


        }



    }

    @Override
    public void render(SpriteBatch sprite) {
        for(int i=0; i<5; i++){
            blockHolders[i].render(sprite);
        }

        for(int i=0; i<5; i++){
            blocks[i].render(sprite);
        }
    }

    @Override
    public void dispose() {
        for(int i=0; i<5; i++){
            blockHolders[i].disposeBody();
            blocks[i].disposeBody();
        }
    }
}
