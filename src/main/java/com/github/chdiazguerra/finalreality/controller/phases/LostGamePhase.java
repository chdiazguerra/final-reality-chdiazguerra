package com.github.chdiazguerra.finalreality.controller.phases;

public class LostGamePhase extends Phase{
    @Override
    public void next() {
        controller.lostScene();
    }
}
