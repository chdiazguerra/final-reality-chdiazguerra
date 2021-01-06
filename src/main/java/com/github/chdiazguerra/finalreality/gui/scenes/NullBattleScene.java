package com.github.chdiazguerra.finalreality.gui.scenes;

/**
 * Class for the null battle scene. The methods are empty.
 *
 * @author Christian Diaz Guerra
 */
public class NullBattleScene implements IBattleScene{
    @Override
    public void refreshWaitingNext() {
        //do nothing
    }

    @Override
    public void refreshEnemyColumns(int indexEnemy) {
        //do nothing
    }

    @Override
    public void refreshInfoColumn(int indexPlayer) {
        //do nothing
    }

    @Override
    public void waitingText() {
        //do nothing
    }

    @Override
    public void playerTurnBox() {
        //do nothing
    }

    @Override
    public void selectTargetText() {
        //do nothing
    }

    @Override
    public void attackInfo(String attackingName, int damage, String attackedName) {
        //do nothing
    }

    @Override
    public void inventoryBox() {
        //do nothing
    }

    @Override
    public void turnBox() {
        //do nothing
    }

    @Override
    public void enemyChoosing() {
        //do nothing
    }

    @Override
    public void winGame() {
        //do nothing
    }

    @Override
    public void lostGame() {
        //do nothing
    }
}
