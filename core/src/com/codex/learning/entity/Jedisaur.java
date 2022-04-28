package com.codex.learning.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.codex.learning.utility.Animation;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class Jedisaur extends entity{
    Animation front, walk_front, walk_up, walk_right;
    private Rectangle rect;
    TextureRegion jedisaur;
    Box2DDebugRenderer b2dr;
    private float maxPosY;


    public Jedisaur(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(this.position);
        def.fixedRotation = true;
        body = manager.getWorld().createBody(def);

        this.size.x /= Constants.PPM;
        this.size.y /= Constants.PPM;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.size.x, this.size.y);

        b2dr = new Box2DDebugRenderer();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("body");
        System.out.println("Body Created!");
        shape.dispose();


        TextureRegion spritesheet = new TextureRegion(new Texture(Constants.CHARACTER_SHEET_PATH));
        //rect = new Rectangle(150,10,Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT);
        jedisaur = new TextureRegion(spritesheet,Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y, Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT);
       // front = new Animation(spritesheet,12, Constants.JEDI_STAND_Y, Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT, 0.5f, false);
 //       front = new Animation(spritesheet, Constants.JEDI_STAND_X, Constants.JEDI_STAND_Y, Constants.JEDI_STAND_WIDTH, Constants.JEDI_STAND_HEIGHT,1, 0, false);
//        walk_front = new Animation(spritesheet, 80,0,160,114,2,1,false);
//        walk_up = new Animation(spritesheet, 80,230,160,114,2,1,false);
//        walk_right = new Animation(spritesheet, 80, 115, 160, 114,2,1,false);

        maxPosY = body.getPosition().y;


    }
    private void cameraUpdate(){

        if (maxPosY < body.getPosition().y) {
            maxPosY = body.getPosition().y;
        }

        Vector3 position = manager.getCamera().position;
        position.x = this.position.x * Constants.PPM;
        position.y = maxPosY * Constants.PPM;
        manager.getCamera().position.set(position);
        manager.getCamera().update();
    }

    @Override
    public void update(float delta) {

//        if(Gdx.input.isKeyPressed(Input.Keys.S)){
//            walk_front.update(delta);
//        }
//        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
//            walk_up.update(delta);
//        }
//        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
//            walk_right.update(delta);
//        }
//        else{
//            front.update(delta);
//        }
        cameraUpdate();
        input(delta);
    }
    private boolean isLeft = false;
    public void input(float delta){
        int horizontalForce = 0;
        int verticalForce = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            horizontalForce -= 1;
            System.out.println("moving left");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            horizontalForce += 1;
            System.out.println("moving right");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            verticalForce += 1;
            System.out.println("moving up");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            verticalForce -= 1;
            System.out.println("moving down");
        }

        body.setLinearVelocity(horizontalForce * 5, body.getLinearVelocity().y);

    }

    @Override
    public void render(SpriteBatch sprite) {

        b2dr.render(manager.getWorld(), manager.getCamera().combined.scl(Constants.PPM));
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        sprite.draw(jedisaur, body.getPosition().x, body.getPosition().y, 16,16);

//        if(Gdx.input.isKeyPressed(Input.Keys.S)){
//            sprite.draw(walk_front.getFrame(), body.getPosition().x * Constants.PPM - ((float)walk_front.getFrame().getRegionWidth()/2)
//                    , body.getPosition().y * Constants.PPM - ((float)walk_front.getFrame().getRegionHeight()/2));
//        }
//        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
//            sprite.draw(walk_up.getFrame(), body.getPosition().x * Constants.PPM , body.getPosition().y * Constants.PPM);
//        }
//        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
//            sprite.draw(walk_right.getFrame(), body.getPosition().x * Constants.PPM, body.getPosition().y * Constants.PPM);
//        }
//        else {
//            sprite.draw(front.getFrame(), body.getPosition().x * Constants.PPM - ((float) front.getFrame().getRegionWidth()/2),
//                    body.getPosition().y * Constants.PPM - ((float) front.getFrame().getRegionHeight()/2));
//        }
        //System.out.println(body.getPosition().x + " " + body.getPosition().y);
        sprite.end();
    }
}
