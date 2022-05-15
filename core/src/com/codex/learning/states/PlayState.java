package com.codex.learning.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Blocks;
import com.codex.learning.entity.JediGrandpa;

import com.codex.learning.entity.Jedisaur;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class PlayState extends State{

    // HOUSE X Y BOX
    private Jedisaur jedisaur;
    private Blocks upBorder, downBorder;
    private JediGrandpa jediGrandpa;
    private Texture badLogic;

    public PlayState(Manager manager) {
        super(manager);

        upBorder = new Blocks(manager);
        upBorder.create(new Vector2(-6,8), new Vector2(0.2f,4),0);

        downBorder = new Blocks(manager);
        downBorder.create(new Vector2(-6,-8), new Vector2(0.2f,4),0);

        jedisaur = new Jedisaur(manager);
        jedisaur.create(new Vector2(0,0),new Vector2(1.2f, 1.75f),1.6f);

        jediGrandpa = new JediGrandpa(manager);
        jediGrandpa.create(new Vector2(-10,0), new Vector2(1,1.4f),0);

    }

    @Override
    public void update(float delta) {

        manager.getWorld().step(1/60f,6,2);
        jediGrandpa.update(delta);
        jedisaur.update(delta);
        //blocks.update(delta);


    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.disableBlending();
        sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //sprite.draw(badLogic, 200, 0, 100, 100);
        sprite.end();
        jediGrandpa.render(sprite);
        jedisaur.render(sprite);


    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
    }
}
