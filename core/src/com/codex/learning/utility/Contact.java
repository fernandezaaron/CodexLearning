package com.codex.learning.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.Blocks;

//This class will allow the player to have collision detection
public class Contact implements ContactListener {
    @Override
    public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null){
            return;
        }

        if(fa.getUserData() == null || fb.getUserData() == null) {
            return;
        }

        if(isBlockContact(fa)){
            Blocks blockA = (Blocks) fa.getUserData();
            blockA.setInContact(true);
        }
        else if(isDispenserContact(fa)){
            BlockDispenser blockDispenserA = (BlockDispenser) fa.getUserData();
            blockDispenserA.setInDispenser(true);
        }
        Gdx.app.log("BEGIN CONTACT", "");

    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null)
            return;
        if(fa.getUserData() == null || fb.getUserData() == null)
            return;

        if(isBlockContact(fa)){
            Blocks blockA = (Blocks) fa.getUserData();
            blockA.setInContact(false);
        }
        else if(isDispenserContact(fa)){
            BlockDispenser blockDispenserA = (BlockDispenser) fa.getUserData();
            blockDispenserA.setInDispenser(false);
        }


        Gdx.app.log("END CONTACT", "");
    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {

    }

    private boolean isBlockContact(Fixture a){
        if(a.getUserData() instanceof Blocks){
            return true;
        }
        return false;
//        return (a.getUserData() instanceof Blocks && a.getUserData() instanceof Character);
    }

    private boolean isDispenserContact(Fixture a){
        if(a.getUserData() instanceof BlockDispenser){
            return true;
        }
        return false;
    }

}
