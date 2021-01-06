package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the won game.
 *
 * @author Christian Diaz Guerra
 */
public class WinGamePhase extends Phase{


    @Override
    public void next() {
        controller.winScene();
    }

    @Override
    public boolean isWon(){
        return true;
    }
}
