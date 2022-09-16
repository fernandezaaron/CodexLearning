package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.Manager;

public class BlockDispenser extends Entity {
    private TextureRegion blockDispenser;
    private boolean inContact, interacting;
    private boolean spawned;
    private boolean cloned;
    private String direction;
    private String id;
    private String name;
    private int limit;
    private ShapeRenderer blockID;
    private Blocks[] blocks;
    private Vector2 blockSize;
    private Label label;
    private Table table, containerTable;



    public BlockDispenser(Manager manager, String direction, String id, String name, int limit, Vector2 blockSize) {
        super(manager);
        this.direction = direction;
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.blockSize = blockSize;
        blocks = new Blocks[this.limit + 1];
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;
        label = new Label("", manager.getSkin());
//        label.setWrap(true);

        table = new Table(manager.getSkin());
        table.setBackground("dialogbox2");

        containerTable = new Table(manager.getSkin());
//        containerTable.setBackground("dialogbox1");


        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(this.position);
        def.fixedRotation = true;
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(this.size.x, (float) (this.size.y / 1.5),
                new Vector2(0, -(this.size.y - this.size.y / 2)), 0);

//        shape.setAsBox(this.size.x , this.size.y,
//                new Vector2(0, -(this.size.y - this.size.y / 3)), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 5;

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        inContact = false;
        spawned = false;
        cloned = false;

        blockID = new ShapeRenderer();
        createDispenser();
    }

    @Override
    public void update(float delta) {
        showBlockID();
//        manager.getStage().act();

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);

        sprite.begin();
        sprite.draw(blockDispenser,
                body.getPosition().x * Constants.PPM - blockDispenser.getRegionWidth() / 2,
                body.getPosition().y * Constants.PPM - blockDispenser.getRegionHeight() / 2);

        sprite.end();

        blockID.setProjectionMatrix(manager.getCamera().combined);
        blockID.setColor(246/255f, 228/255f, 216/255f, 0.0f);
        blockID.begin(ShapeRenderer.ShapeType.Filled);
        blockID.rect((this.size.x + (Constants.PPM * body.getPosition().x)),
                (this.size.y + (Constants.PPM * body.getPosition().y)),
                ((this.size.x * Constants.PPM)) * 6.3f,
                -(this.size.y * Constants.PPM / 2));
        blockID.end();

        sprite.begin();

        if(isInContact()){
            containerTable.draw(sprite, 1);
//            manager.getStage().draw();
        }

        sprite.end();

        if(spawned){
            limit--;

            spawned = false;
            cloned = true;
        }

    }

    public void createBlock(Vector2 position){
        if(isInContact() && limit > 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)){

            blocks[limit] = new Blocks(manager, id, name, false);
            blocks[limit].create(new Vector2(position.x, position.y + 3),
                    blockSize,0);
            blocks[limit].setInContact(true);
            spawned = true;
        }
    }

    public void createDispenser(){
        switch (direction){
            case "Down":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_FRONT_X,
                        Constants.BLOCK_MACHINE_FRONT_Y,
                        Constants.BLOCK_MACHINE_FRONT_WIDTH,
                        Constants.BLOCK_MACHINE_FRONT_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.1f,
                        this.size.y * Constants.PPM * 1.62f, 0);
            break;
            case "Left":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_LEFT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.05f,
                        this.size.y * Constants.PPM * 2.02f, 0);
            break;
            case "Right":
                blockDispenser = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH),
                        Constants.BLOCK_MACHINE_RIGHT_X,
                        Constants.BLOCK_MACHINE_Y,
                        Constants.BLOCK_MACHINE_WIDTH,
                        Constants.BLOCK_MACHINE_HEIGHT);
                blockID.translate(this.size.x - Constants.PPM / 1.228f,
                        this.size.y * Constants.PPM * 2.02f, 0);
            break;
        }
    }

    public void showBlockID(){

//        if(!isInContact()){
//            containerTable.reset();
//        }

        int length = this.id.length();
        containerTable.defaults().size(length*3.5f + 500,150);
        containerTable.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM - 250,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM - 350);


        if(isInContact() && isInteracting()){

            label.setText(this.id);
//            label.setWrap(true);



            setInteracting(false);
        }

        if(!containerTable.hasChildren()){
            label.setAlignment(Align.left);
            table.setFillParent(true);
            table.add(label).align(Align.left);
            containerTable.add(table).colspan(3);
            containerTable.pack();
            containerTable.setDebug(true);
        }
//        manager.getStage().addActor(containerTable);

    }

    public boolean isInteracting() {
        return interacting;
    }

    public void setInteracting(boolean interacting) {
        this.interacting = interacting;
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public Blocks[] getBlocks(){
        return blocks;
    }

    public boolean isCloned() {
        return cloned;
    }

    public void setCloned(boolean cloned) {
        this.cloned = cloned;
    }

    public int getLimit() {
        return limit;
    }
}