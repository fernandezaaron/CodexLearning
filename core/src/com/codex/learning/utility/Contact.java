package com.codex.learning.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;

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

        if(isBlockContact(fa, fb)) {
            Blocks blocks;
            Character jedisaur;
            if (fa.getUserData() instanceof Blocks) {
                blocks = (Blocks) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            } else {
                jedisaur = (Character) fa.getUserData();
                blocks = (Blocks) fb.getUserData();
            }

            blocks.setInContact(true);
            if (jedisaur.isCarrying()) {

                System.out.println("Block yes");

                if (blocks.isPreDefinedContact()) {
                    blocks.setInContact(false);

                    jedisaur.setPickUpAble(false);
                } else {
                    blocks.setInContact(true);
                    if (jedisaur.isCarrying()) {
                        jedisaur.setPickUpAble(false);
                    } else {
                        jedisaur.setPickUpAble(true);
                    }
                }

            }
        }

        if(isDispenserContact(fa, fb)){
            BlockDispenser blockDispenser;
            Character jedisaur;
            if(fa.getUserData() instanceof BlockDispenser){
                blockDispenser = (BlockDispenser) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                blockDispenser = (BlockDispenser) fb.getUserData();
            }
            if(jedisaur.isCarrying()){
                jedisaur.setPickUpAble(false);
                blockDispenser.setInContact(false);
            }
            else{
                if(blockDispenser.getLimit() == 0){
                    jedisaur.setPickUpAble(false);
                    blockDispenser.setInContact(false);
                }else {
                    jedisaur.setPickUpAble(true);
                    blockDispenser.setInContact(true);
                }

            }

        }

        if(isBlockHolderContact(fa, fb)){
            BlockHolder blockHolder;
            Character jedisaur;
            if(fa.getUserData() instanceof BlockHolder){
                blockHolder = (BlockHolder) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                blockHolder = (BlockHolder) fb.getUserData();
            }
            blockHolder.setInContact(true);
            if(jedisaur.isCarrying()){
                jedisaur.setPickUpAble(false);
            }
            else{
                jedisaur.setPickUpAble(true);
            }
        }

        if(isComputerContact(fa, fb)){
            Computer computer;
            Character jedisaur;
            if(fa.getUserData() instanceof Computer){
                computer = (Computer) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                computer = (Computer) fb.getUserData();
            }
            computer.setInContact(true);
        }

        if(isNPCContact(fa, fb)){
            NPC npc;
            Character jedisaur;

            if(fa.getUserData() instanceof NPC){
                npc = (NPC) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                npc = (NPC) fb.getUserData();
            }
            npc.setInContact(true);
            if(npc.isInContact()){
                switch (jedisaur.getDirection()){
                    case "north":
                        npc.setDirection("south");
                        break;
                    case "south":
                        npc.setDirection("north");
                        break;
                    case "east":
                        npc.setDirection("west");
                        break;
                    case "west":
                        npc.setDirection("east");
                        break;
                }
            }
            else{
                npc.setDirection("south");
            }
            System.out.println("NPC CONTACT");
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

        if(isBlockContact(fa, fb)){
            Blocks blocks;
            Character jedisaur;
            if(fa.getUserData() instanceof Blocks){
                blocks = (Blocks) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                blocks = (Blocks) fb.getUserData();
            }

            blocks.setInContact(false);
            jedisaur.setPickUpAble(false);
        }

        if(isDispenserContact(fa, fb)){
            BlockDispenser blockDispenser;
            Character jedisaur;
            if(fa.getUserData() instanceof BlockDispenser){
                blockDispenser = (BlockDispenser) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                blockDispenser = (BlockDispenser) fb.getUserData();
            }
            blockDispenser.setInContact(false);
            jedisaur.setPickUpAble(false);

        }

        if(isBlockHolderContact(fa, fb)){
            BlockHolder blockHolder;
            Character jedisaur;
            if(fa.getUserData() instanceof BlockHolder){
                blockHolder = (BlockHolder) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                blockHolder = (BlockHolder) fb.getUserData();
            }
            blockHolder.setInContact(false);
            jedisaur.setPickUpAble(false);
        }

        if(isComputerContact(fa, fb)){
            Computer computer;
            Character jedisaur;
            if(fa.getUserData() instanceof Computer){
                computer = (Computer) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                computer = (Computer) fb.getUserData();
            }
            computer.setInContact(false);
        }

        if(isNPCContact(fa, fb)){
            NPC npc;
            Character jedisaur;

            if(fa.getUserData() instanceof NPC){
                npc = (NPC) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                npc = (NPC) fb.getUserData();
            }
            npc.setInContact(false);
        }

        Gdx.app.log("END CONTACT", "");
    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {

    }

    private boolean isBlockContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character) {
            if(a.getUserData() instanceof Blocks || b.getUserData() instanceof Blocks) {
                return true;
            }
        }
        return false;
    }

    private boolean isDispenserContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof BlockDispenser || b.getUserData() instanceof BlockDispenser){
                return true;
            }
        }
        return false;
    }

    private boolean isBlockHolderContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof BlockHolder || b.getUserData() instanceof BlockHolder){
                return true;
            }
        }
        return false;
    }

    private boolean isComputerContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof Computer || b.getUserData() instanceof Computer){
                return true;
            }
        }
        return false;
    }

    private boolean isNPCContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof NPC || b.getUserData() instanceof NPC){
                return true;
            }
        }
        return false;
    }
}
