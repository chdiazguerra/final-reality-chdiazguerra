Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---

The code is composed of two main parts: characters and weapons.

As for the characters, because they have certain behaviors in common, there is an interface with these common methods and
an abstract class (*AbstractCharacter*) that implements one of them. Because enemies and player characters have different
way to measure how long they wait in the queue, waitTurn is left as an abstract method.
Enemies are implemented with the *Enemy* class, which inherits from the *AbstractCharacter* class. It has the weight of the enemy and also
implements waitTurn based on this.  

The player's characters have a common interface (*IPlayerCharacter*), which has the common methods *equip* and *getEquippedWeapon*.  

*AbstractPlayerCharacter* extends *AbstractCharacter* and implements the *IPlayerCharacter* interface. Because all the player's characters
have a weapon, it has this field. It also implements *waitTurn* and *getEquippedWeapon*, which are common for all the player's characters,
based on the character's equipped weapon.  

Since different character classes will implement the *equip* method differently (for now, they are the same, but later
will not be), this is left in each class. Also, because there are several classes, it is better to leave them apart. 
This also facilitates the implementation of the method *getEquals*, and the differentiation between different character classes.  

As for the weapons, you have an interface that contains the common methods. AbstractWeapon implements the common methods for all
the types of weapons. Since there are several types, it was decided to leave them in separate classes, in addition to the fact that some have magic damage and others
no (this is not implemented at the moment). This also facilitates the implementation of the method *getEquals* and the differentiation between different types.

