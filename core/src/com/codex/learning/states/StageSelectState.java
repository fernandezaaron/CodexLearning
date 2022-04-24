package com.codex.learning.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class StageSelectState extends State{

    private TextureRegion stageSelect;

    public StageSelectState(Manager manager){
        super(manager);

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));
    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {

    }

    @Override
    public void dispose() {

    }
}
