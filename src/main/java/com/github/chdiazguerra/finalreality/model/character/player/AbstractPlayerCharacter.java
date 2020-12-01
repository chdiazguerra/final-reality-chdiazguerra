package com.github.chdiazguerra.finalreality.model.character.player;


import com.github.chdiazguerra.finalreality.model.character.AbstractCharacter;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of the player characters of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

    protected IWeapon equippedWeapon = null;


    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                    @NotNull String name, int life, int defense) {
        super(turnsQueue, name, life, defense);
    }

    @Override
    public void setEquippedWeapon(IWeapon weapon){
        equippedWeapon = weapon;
    }

    @Override
    public abstract void equip(IWeapon weapon);

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }


    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    }

    @Override
    public void attack(ICharacter character) {
        if(this.isAlive) {
            character.attacked(equippedWeapon.getDamage());
        }
    }

}