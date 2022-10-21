package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.codex.learning.utility.Manager;
import jdk.javadoc.internal.doclets.formats.html.markup.Table;

public class ReportCardState extends State{

    private Table backgroundTable, textButtonContainer, reportCardTable, containerTable;
    private TextButton[] textButtons;
//    private Label

    public ReportCardState(Manager manager){
        super(manager);



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
