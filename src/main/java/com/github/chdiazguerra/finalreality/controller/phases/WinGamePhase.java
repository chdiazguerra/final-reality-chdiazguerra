package com.github.chdiazguerra.finalreality.controller.phases;

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
