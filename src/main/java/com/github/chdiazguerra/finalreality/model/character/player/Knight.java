package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Knight extends AbstractPlayerCharacter {

    /**
     *
     * @param name
     * @param turnsQueue
     */

    public Knight(@NotNull String name,
                           @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(turnsQueue, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Knight.class, getName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight that = (Knight) o;
        return getName().equals(that.getName());
    }

    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
    }
}
