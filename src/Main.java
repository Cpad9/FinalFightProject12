//Chris Padgett
//Last edited: 11-17-18

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  Main[] fight;
  //set myPlayer
  static Player myPlayer = GameSceneController.myPlayer;
  //Array of Players
  static Player[] players;


  private Player humanPlayer;
  private Player aiPlayer;


  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
    primaryStage.setTitle("Primary Stage");
    Scene scene1 = new Scene(root, 800, 500);
    primaryStage.setScene(scene1);
    scene1.getStylesheets().add(Main.class.getResource("GameSceneStyle.css").toExternalForm());
    primaryStage.show();

  }

  /**
   * Main Method.
   **/
  public static void main(String[] args) {
    try {
      DataBaseMethods.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
    }
    launch(args);
    askUser();
  }

  /**
   * Prompts user.
   **/
  public static void askUser() {
    System.out.println("1. Play Game\n"
        + "2. Create/Check DataBase\n"
        + "3. Print DataBase \n"
        + "4. Add own info into DataBase\n"
        + "5. UPDATE Players health to 200 where name =\n"
        + "6. DELETE Player where name = ");
    Scanner scan = new Scanner(System.in);
    int input = scan.nextInt();
    userInput(input);
  }

  /**
   * Reads userInput, Calls according mehthods.
   **/

  public static void userInput(int in) {
    switch (in) {
      case 1:
        Scanner sca = new Scanner(System.in);
        System.out.println("Enter amount of people you want to fight");
        int players = sca.nextInt();
        createPlayerArray(players);
        startFights();
        System.out.println("Starting game");
        askUser();
        break;
      case 2:
        //Call Create Database
        try {
          DataBaseMethods.createTable();
        } catch (Exception e) {
          System.out.println(e);
        }
        askUser();
        break;
      case 3:
        //Get
        try {
          DataBaseMethods.get();
        } catch (Exception e) {
          System.out.println(e);
        }
        askUser();
        break;
      case 4:
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name you want to add to DataBase");
        String name = scan.nextLine();
        System.out.println("Enter health you want to add to DataBase");
        String health = scan.nextLine();
        try {
          DataBaseMethods.post(name, health);
        } catch (Exception e) {
          System.out.println(e);
        }
        askUser();
        break;
      case 5:
        Scanner scann = new Scanner(System.in);
        System.out.println(
            "Enter the name of person and I will change their health in the database to 200");
        String iname = scann.nextLine();
        try {
          DataBaseMethods.updateHealth(iname);
        } catch (Exception e) {
          System.out.println(e);
        }
        askUser();
        break;
      case 6:
        Scanner scanne = new Scanner(System.in);
        System.out
            .println("Enter the name of person and I will delete their section of the DataBase");
        String jname = scanne.nextLine();
        try {
          DataBaseMethods.deleteDatabase(jname);
        } catch (Exception e) {
          System.out.println(e);
        }
        askUser();
        break;
      default:
        System.out.println("Type in 1-6");
    }
  }

  int randomGenerator(int min, int max) {
    int range = (max - min) + 1;
    return (int) (Math.random() * range) + min;
    //To potentially  use for Attack modifier
    //(randomWithRange(5,10)*.1)
    //This will have an attack do anywhere from 50% to 100% damage
  }


  /**
   Creates enemy array of user defined size.
   **/

  public static void createPlayerArray(int num) {
    System.out.println("Creating PlayerArray of size " + num);
    players = new Player[num];

    for (int i = 0; i < players.length; i++) {
      //Making Player Array

      int random = (int) (Math.random() * 3 + 1);
      String numberAsString = Integer.toString(random);
      //Creation of players with random specialities
      players[i] = new Player("Player" + (i + 1), numberAsString);
    }
    System.out.println("Created Player Array");
  }

  /**
   Method to print array if wanted.
   **/

  public static void printPlayerArray() {
    for (int i = 0; i < players.length; i++) {
      System.out.println(players[i].name + players[i].speciality);

    }
  }


  /**
   Method to start fight.
   **/

  public static void startFights() {
    Fight[] fight = new Fight[players.length];
    for (int i = 0; i < players.length; i++) {
      if (myPlayer.isAlive()) {
        fight[i] = new Fight(myPlayer, players[i]);
        fight[i].fight();
        System.out.println("Here is +100 Health for partaking in that fight");
        myPlayer.health += 100;
      } else {
        System.out.println("YOU ARE DEAD");
      }
    }
    System.out.println("You have completed all your fights on this journey");
    System.out.println("Your name now goes on the leader board with your remaining health");
    String str = myPlayer.health + "";
    DataBaseMethods.post(myPlayer.name, str);
  }


}
