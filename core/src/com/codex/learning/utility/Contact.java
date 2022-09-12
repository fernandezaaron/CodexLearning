package com.codex.learning.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.codex.learning.entity.blocks.*;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;

//This class will allow the player to have collision detection
public class Contact implements ContactListener {
    private int numberOfCollision = 0;
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
                numberOfCollision = 1;
                System.out.println(numberOfCollision + " if --");
                if (blocks.isPreDefinedContact() ) {
                    blocks.setInContact(false);
                    jedisaur.setPickUpAble(false);
                }
                else {
                    blocks.setInContact(true);
                    if (jedisaur.isCarrying()) {
                        jedisaur.setPickUpAble(false);

                    }
                    else {
                        jedisaur.setPickUpAble(true);
                    }
                }
            }
            else {
                jedisaur.setPickUpAble(true);
                if(blocks.isPreDefinedContact()){
                    blocks.setInContact(false);
                    jedisaur.setPickUpAble(false);
                }
                else{
                    if(jedisaur.isDropped()){
                        numberOfCollision = 0;
                        jedisaur.setDropped(false);
                    }
                    numberOfCollision++;
                    System.out.println(numberOfCollision + " else ++");
                    jedisaur.setPickUpAble(true);
                }
                if(numberOfCollision>1){
                    blocks.setInContact(false);
                    jedisaur.setPickUpAble(false);
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
            if(blockHolder.isOccupied()){
                numberOfCollision = 0;
                System.out.println("occupied");
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

        if(isPlayMatContact(fa,fb)){
            System.out.println("playmat contact");
            PlayMat playMat;
            Character jedisaur;

            if(fa.getUserData() instanceof PlayMat){
                playMat = (PlayMat) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                playMat = (PlayMat) fb.getUserData();
            }


            playMat.setInContact(true);
        }

        if(isObjectiveContact(fa, fb)){
            Objective objective;
            Character jedisaur;

            if(fa.getUserData() instanceof Objective){
                objective = (Objective) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                objective = (Objective) fb.getUserData();
            }
            System.out.println("ASDWDQDWQDQWDQDW");
            objective.setInContact(true);
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
            if(!jedisaur.isCarrying()){
                if(!blocks.isPreDefinedContact()){
                    numberOfCollision--;
                    System.out.println(numberOfCollision + " collision");
                }

            }
            if(numberOfCollision < 0){
                numberOfCollision = 0;
            }

            if(numberOfCollision == 1){
                blocks.setInContact(true);
                jedisaur.setPickUpAble(true);
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

        if(isPlayMatContact(fa,fb)){
            System.out.println("playmat end contact");
            PlayMat playMat;
            Character jedisaur;

            if(fa.getUserData() instanceof PlayMat){
                playMat = (PlayMat) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                playMat = (PlayMat) fb.getUserData();
            }

            playMat.setInContact(false);
        }

        if(isObjectiveContact(fa, fb)){
            Objective objective;
            Character jedisaur;

            if(fa.getUserData() instanceof Objective){
                objective = (Objective) fa.getUserData();
                jedisaur = (Character) fb.getUserData();
            }
            else{
                jedisaur = (Character) fa.getUserData();
                objective = (Objective) fb.getUserData();
            }
            objective.setInContact(false);
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

    private boolean isPlayMatContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof PlayMat || b.getUserData() instanceof PlayMat){
                return true;
            }
        }
        return false;
    }

    private boolean isObjectiveContact(Fixture a, Fixture b){
        if(a.getUserData() instanceof Character || b.getUserData() instanceof Character){
            if(a.getUserData() instanceof Objective || b.getUserData() instanceof Objective){
                return true;
            }
        }
        return false;
    }
}
