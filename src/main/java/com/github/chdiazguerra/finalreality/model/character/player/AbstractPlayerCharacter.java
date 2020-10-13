package com.github.chdiazguerra.finalreality.model.character.player;


import com.github.chdiazguerra.finalreality.model.character.AbstractCharacter;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

    protected IWeapon equippedWeapon = null;

    /**
     * Creates a new character.
     *
     * @param name
     *      the character's name
     * @param turnsQueue
     *      the queue with the characters waiting for their turn
     */
    public AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                    @NotNull String name) {
        super(turnsQueue, name);
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

}