package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a Thief class character.
 *
 * @author Christian Díaz Guerra
 */
public class Thief extends AbstractPlayerCharacter {

    /**
     * Creates a new character of Thief class, with a name and the queue.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */

    public Thief(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(turnsQueue, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Thief.class, getName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief that = (Thief) o;
        return getName().equals(that.getName());
    }

    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
    }
}
