//Chris Padgett
//Last edited: 11-17-18

import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Fight {


  private final Player humanPlayer;
  private final Player aiPlayer;

  Player[] players;
  Player myPlayer = GameSceneController.myPlayer;


  int randomGenerator(int min, int max) {
    int range = (max - min) + 1;
    return (int) (Math.random() * range) + min;
    //To potentially  use for Attack modifier
    //(randomWithRange(5,10)*.1)
    //This will have an attack do anywhere from 50% to 100% damage
  }

  public Fight(Player humanPlayer, Player aiPlayer) {
    this.aiPlayer = aiPlayer;
    this.humanPlayer = humanPlayer;
  }

  /**
   * Initiates fight.
   **/
  public void fight() {
    System.out.println();
    System.out.println("You have begun your battle with " + aiPlayer.name
        + ". What is your first move?");
    System.out.println();
    while (humanPlayer.isAlive() && aiPlayer.isAlive()) {
      fightLoop();
      enemyAttack();
      displayFightStats();
      checkWinner();
    }
  }

  /**
   * //Method asking what type of attack to use and Uses
   * //SpellAttack or PhysicalAttack accordingly.
   **/
  public void fightLoop() {
    System.out.println();
    System.out.println("----------------------------------------------");
    System.out.println("1.Physical Attack             2.Spell Attack");
    System.out.println("----------------------------------------------");
    System.out.println();
    Scanner scanner = new Scanner(System.in);
    int input = scanner.nextInt();
    System.out.println();
    switch (input) {
      case 1:
        System.out.println("You used a Physical Attack! - " + humanPlayer.damage);
        humanPlayer.physicalAttack(aiPlayer);
        break;
      case 2:
        System.out.println("You used a Spell Attack! - " + humanPlayer.spellDamage);
        humanPlayer.spellAttack(aiPlayer);
        break;
      default:
        System.out.println("Pick 1 or 2");
        break;
    }
  }

  /**
  //Enemy attack using a random generator to decide if it is a.
  //Physical or Spell attack.
   **/
  public void enemyAttack() {

    switch (randomGenerator(1, 2)) {
      case 1: //Select 1
        System.out.println(aiPlayer.name + " used a Physical Attack! - " + aiPlayer.damage);
        aiPlayer.physicalAttack(humanPlayer);
        break;
      case 2: //Select 2
        System.out.println(aiPlayer.name + " used a Spell Attack! - " + aiPlayer.spellDamage);
        aiPlayer.spellAttack(humanPlayer);
        break;
      default:
        System.out.println("The randomGenerator is broken.");
        break;
    }

  }

  /**
  //Method to check winner, for use in other methods
  //Declares winner/Loser.
   **/
  public void checkWinner() {
    if (humanPlayer.isAlive() == false) {
      System.out.println();
      System.out.println();
      System.out.println("************************You died************************");
    }
    if (aiPlayer.isAlive() == false) {
      System.out.println();
      System.out.println();
      System.out.println("************************You win!************************");
    }
  }

  /**
  //Fight statistics to display in between attack selection.
   **/
  public void displayFightStats() {
    System.out.println("----------------------------------------------");
    System.out.println("                   " + humanPlayer.name + " :");
    System.out.println(
        "Health: " + humanPlayer.health + " Armor: " + humanPlayer.armor + " Resistance: "
            + humanPlayer.resistance);
    System.out.println();
    System.out.println("                   " + aiPlayer.name + " :");
    System.out.println("Health: " + aiPlayer.health + " Armor: " + aiPlayer.armor + " Resistance: "
        + aiPlayer.resistance);
    System.out.println("----------------------------------------------");

  }
}
