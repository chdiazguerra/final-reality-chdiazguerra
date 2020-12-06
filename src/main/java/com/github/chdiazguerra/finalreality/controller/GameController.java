package com.github.chdiazguerra.finalreality.controller;

import com.github.chdiazguerra.finalreality.controller.handlers.DeadEnemyHandler;
import com.github.chdiazguerra.finalreality.controller.handlers.DeadPlayerCharacterHandler;
import com.github.chdiazguerra.finalreality.controller.handlers.IHandler;
import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.character.player.classes.*;
import com.github.chdiazguerra.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController {

    private final IHandler deadPlayerCharacterHandler = new DeadPlayerCharacterHandler(this);
    private final IHandler deadEnemyHandler = new DeadEnemyHandler(this);

    private BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();

    private final List<IPlayerCharacter> playerCharacters = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<IWeapon> inventory = new ArrayList<>();
    private final HashMap<IWeapon, IPlayerCharacter> weaponToPlayer = new HashMap<>();

    //For now, this is for the tests
    public boolean win = false;
    public boolean lose = false;

    public GameController(){

    }


    public List<IPlayerCharacter> getAllPlayerCharacters() {
        return playerCharacters;
    }

    public void createBlackMage(String name, int life, int defense) {
        BlackMage newPlayerCharacter = new BlackMage(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        playerCharacters.add(newPlayerCharacter);
    }

    public void createEngineer(String name, int life, int defense) {
        Engineer newPlayerCharacter = new Engineer(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        playerCharacters.add(newPlayerCharacter);
    }


    public void createKnight(String name, int life, int defense){
        Knight newPlayerCharacter = new Knight(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        playerCharacters.add(newPlayerCharacter);
    }

    public void createThief(String name, int life, int defense) {
        Thief newPlayerCharacter = new Thief(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        playerCharacters.add(newPlayerCharacter);
    }

    public void createWhiteMage(String name, int life, int defense) {
        WhiteMage newPlayerCharacter = new WhiteMage(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        playerCharacters.add(newPlayerCharacter);
    }

    public IPlayerCharacter getPlayerCharacter(int index) {
        return playerCharacters.get(index);
    }

    public List<Enemy> getAllEnemies() {
        return enemies;
    }

    public void createEnemy(String name, int weight, int life, int defense, int attack) {
        Enemy newEnemy = new Enemy(name, weight, turnsQueue, life, defense, attack);
        newEnemy.addDeadListener(deadEnemyHandler);
        enemies.add(newEnemy);
    }

    public Enemy getEnemy(int index) {
        return enemies.get(index);
    }

    public List<IWeapon> getInventory() {
        return inventory;
    }

    private void putWeapon(IWeapon weapon){
        inventory.add(weapon);
        weaponToPlayer.put(weapon, null);
    }

    public void createAxe(String name, int damage, int weight){
        putWeapon(new Axe(name, damage, weight));
    }

    public void createBow(String name, int damage, int weight){
        putWeapon(new Bow(name, damage, weight));
    }

    public void createKnife(String name, int damage, int weight){
        putWeapon(new Knife(name, damage, weight));
    }

    public void createStaff(String name, int damage, int weight){
        putWeapon(new Staff(name, damage, weight));
    }

    public void createSword(String name, int damage, int weight){
        putWeapon(new Sword(name, damage, weight));
    }

    public IWeapon getInventoryWeapon(int index) {
        return inventory.get(index);
    }

    public IWeapon getEquippedWeapon(int index) {
        return getPlayerCharacter(index).getEquippedWeapon();
    }

    public void equipWeaponFromInventory(int indexWeapon, IPlayerCharacter character) {
        if(!weaponIsEquipped(indexWeapon)) {
            weaponToPlayer.put(character.getEquippedWeapon(), null);
            character.equip(getInventoryWeapon(indexWeapon));
            weaponToPlayer.put(character.getEquippedWeapon(), character);
        }
    }

    public boolean weaponIsEquipped(int index){
        return weaponToPlayer.get(getInventoryWeapon(index)) != null;
    }


    public int getCharacterLife(ICharacter character) {
        return character.getLife();
    }


    public String getCharacterName(ICharacter character) {
        return character.getName();
    }

    public int getCharacterDefense(ICharacter character) {
        return character.getDefense();
    }

    public void attackToEnemy(IPlayerCharacter playerCharacter, int indexEnemy) {
        ((ICharacter) playerCharacter).attack(getEnemy(indexEnemy));
    }

    public void attackToPlayer(Enemy enemy, int indexPlayer) {
        enemy.attack((ICharacter) getPlayerCharacter(indexPlayer));
    }

    public void deadPlayerCharacter(IPlayerCharacter playerCharacter) {
        playerCharacters.remove(playerCharacter);
        if(playerCharacters.size()==0){
            gameOverLost();
        }
    }

    public void gameOverLost(){
        //do something
        //For now, do this
        lose = true;
    }

    public void deadEnemy(Enemy enemy) {
        enemies.remove(enemy);
        if(enemies.size()==0){
            gameOverWin();
        }
    }

    private void gameOverWin() {
        //do something
        //For now, do this
        win = true;
    }
}
