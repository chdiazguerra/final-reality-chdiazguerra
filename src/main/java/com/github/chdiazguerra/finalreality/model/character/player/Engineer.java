package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a Engineer class character.
 *
 * @author Christian Díaz Guerra
 */

public class Engineer extends AbstractPlayerCharacter {

    /**
     * Creates a new character of Engineer class, with a name and the queue.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */

    public Engineer(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue, int life, int defense) {
        super(turnsQueue, name, life, defense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Engineer.class, getName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer that = (Engineer) o;
        return getName().equals(that.getName());
    }

    @Override
    public void equip(IWeapon weapon) {
        weapon.equippedByEngineer(this);
    }
}