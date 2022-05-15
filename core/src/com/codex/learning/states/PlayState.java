package com.codex.learning.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Collision;
import com.codex.learning.entity.JediGrandpa;

import com.codex.learning.entity.Blocks;

import com.codex.learning.entity.Jedisaur;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Contact;
import com.codex.learning.utility.Manager;

import java.awt.*;

public class PlayState extends State{

    private Jedisaur character;
    private Collision house;
    private JediGrandpa jediGrandpa;
    private Texture badLogic;
    private Blocks blocks;

    public PlayState(Manager manager) {
        super(manager);

        house = new Collision(manager);
        house.create(new Vector2(0,0), new Vector2(5,3),0);

        character = new Jedisaur(manager);
        character.create(new Vector2(0,0),new Vector2(1.4f, 1.75f),1.6f);

        jediGrandpa = new JediGrandpa(manager);
        jediGrandpa.create(new Vector2(2,0), new Vector2(1,1.4f),0);

        blocks = new Blocks(manager);
        blocks.create(new Vector2(50,0),new Vector2(50, 50),1.6f );


    }

    @Override
    public void update(float delta) {

        manager.getWorld().step(1/60f,6,2);
       // jediGrandpa.update(delta);
        character.update(delta);
        //blocks.update(delta);


    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        //sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //sprite.draw(badLogic, 200, 0, 100, 100);
        sprite.end();
        jediGrandpa.render(sprite);
        character.render(sprite);


        blocks.render(sprite);


    }

    @Override
    public void dispose() {
        character.disposeBody();
    }
}
