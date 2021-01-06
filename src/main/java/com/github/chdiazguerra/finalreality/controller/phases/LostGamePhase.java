package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the lost game.
 *
 * @author Christian Diaz Guerra
 */
public class LostGamePhase extends Phase{
    @Override
    public void next() {
        controller.lostScene();
    }

    @Override
    public boolean isLost(){
        return true;
    }
}
