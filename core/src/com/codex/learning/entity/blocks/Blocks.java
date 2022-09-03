package com.codex.learning.entity.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.codex.learning.entity.Entity;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import org.graalvm.compiler.serviceprovider.IsolateUtil;

public class Blocks extends Entity {
    private String id, name;
    private ShapeRenderer shadowColor;
    private ShapeRenderer mainColor;
    protected boolean inContact;
    private boolean preDefinedContact;
    private boolean isPredefined;
    private Vector2 dupliSize;
    public Blocks(Manager manager, String id, String name, boolean isPredefined) {
        super(manager);
        this.id = id;
        this.name = name;
        this.isPredefined = isPredefined;
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;
        this.dupliSize = size;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(this.position);
        def.fixedRotation = true;
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(this.size.x, this.size.y,
                new Vector2(this.size.x + this.size.x / 3, -(this.size.y - this.size.y / 3)), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        fixtureDef.friction = 5;

        if(isPredefined){
            fixtureDef.isSensor = true;
        }

        body = manager.getWorld().createBody(def);
        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(0, 0);
        shape.dispose();

        shadowColor = new ShapeRenderer();
        shadowColor.translate(this.size.x, 0, 0);

        mainColor = new ShapeRenderer();
        mainColor.translate(this.size.x + (Constants.PPM / 5f), - (this.size.y * Constants.PPM) / 10f, 0);

        inContact = false;
        preDefinedContact = false;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        shadowColor.setProjectionMatrix(manager.getCamera().combined);
        shadowColor.setColor(201/255f, 186/255f, 176/255f, 0.0f);
        shadowColor.begin(ShapeRenderer.ShapeType.Filled);
        shadowColor.rect((this.size.x  * 2 + (Constants.PPM * body.getPosition().x)) + 5,
                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
                (this.name.length() + (this.size.x * Constants.PPM)) * 2.9f,
                - (this.size.y * Constants.PPM * 2.1f));
        shadowColor.end();

        mainColor.setProjectionMatrix(manager.getCamera().combined);
        mainColor.setColor(246/255f, 228/255f, 216/255f, 0.0f);
        mainColor.begin(ShapeRenderer.ShapeType.Filled);
        mainColor.rect((this.size.x * 2 + (Constants.PPM * body.getPosition().x)),
                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
                (this.name.length() + (this.size.x * Constants.PPM)) * 2.7f,
                - (this.size.y * Constants.PPM * 1.7f));
        mainColor.end();

        sprite.begin();
        if(this.size.x <= 3)
            manager.getFont().draw(sprite, this.name,
                    (this.size.x * Constants.PPM * 0.3f + 10f + (Constants.PPM * body.getPosition().x)),
                    (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)));
        else
            manager.getFont().draw(sprite, this.name,
                (this.size.x + 10 + (Constants.PPM * body.getPosition().x)),
                (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)));

        if(isUppercase(this.name)){
            manager.getFont().draw(sprite, this.name,
                    (this.size.x + 10 + (Constants.PPM * body.getPosition().x)),
                    (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)), this.name.length()*1.2f, 1, true);
        }

        sprite.end();
    }

    private boolean isUppercase(String string){
        char[] chars = string.toCharArray();

        for(int i=0; i<chars.length; i++){
            if(!Character.isUpperCase(i)){
                return false;
            }
        }

        return true;
    }

    public boolean isPreDefinedContact() {
        return preDefinedContact;
    }

    public void setPreDefinedContact(boolean preDefinedContact) {
        this.preDefinedContact = preDefinedContact;
    }

    public boolean isInContact() {
        return inContact;
    }

    public void setInContact(boolean inContact) {
        this.inContact = inContact;
    }

    public String getId() {
        return id;
    }

    public Vector2 getDupliSize() {
        return dupliSize;
    }

    public void setDupliSize(Vector2 dupliSize) {
        this.dupliSize = dupliSize;
    }

    public void setActive(boolean active){
        body.setActive(active);
    }

}



//        body = manager.getWorld().createBody(def);
//        body.createFixture(fixtureDef).setUserData(this);
//        body.setLinearVelocity(0, 0);
//        shape.dispose();
//
//        shadowColor = new ShapeRenderer();
//        shadowColor.translate(this.size.x, 0, 0);
////        shadowColor.translate((-(this.size.x * Constants.PPM) * 1.22f), 0, 0);
//
//        mainColor = new ShapeRenderer();
//        mainColor.translate(this.size.x + (Constants.PPM / 5f), - (this.size.y * Constants.PPM) / 10f, 0);
////        mainColor.translate((-(this.size.x * Constants.PPM) * 1.16f), - (this.size.y * Constants.PPM) / 10f, 0);
//
//        inContact = false;
//        preDefinedContact = false;
//    }

//    @Override
//    public void update(float delta) {
//
//    }
//
//    @Override
//    public void render(SpriteBatch sprite) {
//        sprite.enableBlending();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
//        shadowColor.setProjectionMatrix(manager.getCamera().combined);
//        shadowColor.setColor(201/255f, 186/255f, 176/255f, 0.0f);
//        shadowColor.begin(ShapeRenderer.ShapeType.Filled);
//        shadowColor.rect((this.size.x * 2 + (Constants.PPM * body.getPosition().x)),
//                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
//                (this.name.length() + (this.size.x * Constants.PPM)) * 2,
//                - (this.size.y * Constants.PPM * 2.1f));
//        shadowColor.end();
//
//        mainColor.setProjectionMatrix(manager.getCamera().combined);
//        mainColor.setColor(246/255f, 228/255f, 216/255f, 0.0f);
//        mainColor.begin(ShapeRenderer.ShapeType.Filled);
//        mainColor.rect((this.size.x * 2 + (Constants.PPM * body.getPosition().x)),
//                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
//                (this.name.length() + (this.size.x * Constants.PPM)) * 1.9f,
//                - (this.size.y * Constants.PPM * 1.7f));
//        mainColor.end();
//
//        sprite.begin();
//        if(this.size.x == 0.5 || this.size.x == 1 || this.size.x == 1.5)
//            manager.getFont().draw(sprite, this.name,
//                    (this.size.x - (this.size.x * (Constants.PPM * 0.6f)) + (Constants.PPM * body.getPosition().x)),
//                    (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)));
//        else
//            manager.getFont().draw(sprite, this.name,
//                    (this.size.x - (this.size.x * (Constants.PPM * 1.1f)) + (Constants.PPM * body.getPosition().x)),
//                    (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)));
//        sprite.end();
//    }

//public class Blocks extends Entity {
//    private String id, name;
//    private ShapeRenderer shadowColor;
//    private ShapeRenderer mainColor;
//    protected boolean inContact;
//    private Vector2 dupliSize;
//
//    public Blocks(Manager manager, String id, String name) {
//        super(manager);
//        this.id = id;
//        this.name = name;
//    }
//
//    @Override
//    public void create(Vector2 position, Vector2 size, float density) {
//        this.position = position;
//        this.size = size;
//        this.dupliSize = size;
//
//        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.StaticBody;
//        def.position.set(this.position);
//        def.fixedRotation = true;
//        PolygonShape shape = new PolygonShape();
//
//        shape.setAsBox(this.size.x , this.size.y,
//                new Vector2(0, -(this.size.y - this.size.y / 3)), 0);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.density = density;
//        fixtureDef.shape = shape;
//        fixtureDef.friction = 5;
//
//        body = manager.getWorld().createBody(def);
//        body.createFixture(fixtureDef).setUserData(this);
//        body.setLinearVelocity(0, 0);
//        shape.dispose();
//
//        shadowColor = new ShapeRenderer();
//        shadowColor.translate((-(this.size.x * Constants.PPM) * 1.22f), 0, 0);
//
//        mainColor = new ShapeRenderer();
//        mainColor.translate((-(this.size.x * Constants.PPM) * 1.16f), - (this.size.y * Constants.PPM) / 10f, 0);
//
//        inContact = false;
//    }

//    @Override
//    public void render(SpriteBatch sprite) {
//        sprite.enableBlending();
//        sprite.setProjectionMatrix(manager.getCamera().combined);
//        shadowColor.setProjectionMatrix(manager.getCamera().combined);
//        shadowColor.setColor(201/255f, 186/255f, 176/255f, 0.0f);
//        shadowColor.begin(ShapeRenderer.ShapeType.Filled);
//        shadowColor.rect((this.size.x  * 2 + (Constants.PPM * body.getPosition().x)),
//                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
//                (this.name.length() + (this.size.x * Constants.PPM)) * 2,
//                - (this.size.y * Constants.PPM * 2.1f));
//        shadowColor.end();
//
//        mainColor.setProjectionMatrix(manager.getCamera().combined);
//        mainColor.setColor(246/255f, 228/255f, 216/255f, 0.0f);
//        mainColor.begin(ShapeRenderer.ShapeType.Filled);
//        mainColor.rect((this.size.x * 2 + (Constants.PPM * body.getPosition().x)),
//                (this.size.y * 2 + (Constants.PPM * body.getPosition().y)),
//                (this.name.length() + (this.size.x * Constants.PPM)) * 1.9f,
//                - (this.size.y * Constants.PPM * 1.7f));
//        mainColor.end();
//
//        sprite.begin();
//        manager.getFont().draw(sprite, this.name,
//                (this.size.x - (this.size.x * (Constants.PPM * 1.1f)) + (Constants.PPM * body.getPosition().x)),
//                (this.size.y - (this.size.y * (Constants.PPM * 0.5f)) + (Constants.PPM * body.getPosition().y)));
//        sprite.end();
//    }
