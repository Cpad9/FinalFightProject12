//
//
//import java.awt.event.ActionListener;
//import java.util.Scanner;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TextArea;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//
//
//public class FightSceneController implements ActionListener {
//
//
//  private Player humanPlayer;
//  private Player aiPlayer;
//
//  public FightSceneController(Player humanPlayer, Player aiPlayer) {
//    this.aiPlayer = aiPlayer;
//    this.humanPlayer = humanPlayer;
//  }
//
//  FightSceneController[] fight;
//  //set myPlayer
//  Player myPlayer = GameSceneController.myPlayer;
//  //Array of Players
//  Player[] players;
//
//
//  @FXML
//  private Label playerName;
//
//  @FXML
//  private ProgressBar humanHealthBar;
//
//  @FXML
//  private TextArea combatInfo;
//
//  @FXML
//  private ImageView playerImage = new ImageView();
//
//  @FXML
//  private ImageView enemyImage = new ImageView();
//
//  @FXML
//  private Button spellButton;
//
//
//  @FXML
//  private Button attackButton;
//
//  //Set Images
//  Image rogueImg = new Image("images/roguePic.png");
//  Image warImg = new Image("images/warriorPic1.png");
//  Image wizardImg = new Image("images/wizardPic.png");
//
//  //MyNum for HealthBar
//  MyNumber myNum = new MyNumber();
//
//  //Way of setting health perc.
//  double maxHealth = GameSceneController.myPlayer.health;
//  double health = GameSceneController.myPlayer.health;
//
//  double healthPerc = health / maxHealth;
//
//  //Beginning of Methods
//
//
//  public FightSceneController() {
//  }
//
//  public void createPlayerArray() {
//    players = new Player[3];
//
//    System.out.println("Printing Player array");
//    for (int i = 0; i < players.length; i++) {
//      //Making Player Array
//
//      int random = (int) (Math.random() * 3 + 1);
//      String numberAsString = Integer.toString(random);
//      //Creation of players with random specialities
//      players[i] = new Player("Player" + (i + 1), numberAsString);
//      System.out.println(players[i].name);
//      System.out.println(players[i].speciality);
//      System.out.println("Created Player Array");
//
//    }
//  }
//
//  public void startFights() {
////    FightSceneController[] fight = new FightSceneController[players.length];
//    fight = new FightSceneController[players.length];
//    for (int i = 0; i < players.length; i++) {
//      if (myPlayer.isAlive()) {
//        fight[i] = new FightSceneController(myPlayer, players[i]);
//        fight[i].fight();
//        System.out.println("Congratz on winning! Heres a 100 health bonus");
//        myPlayer.health += 100;
//      } else {
//        System.out.println("YOU ARE DEAD");
//      }
//      System.out.println("You have completed all your fights on this journey");
//
//    }
//  }
//
//  public void spellButtonClicked(ActionEvent event) throws Exception {
//    System.out.println(event.getSource());
//    System.out.println(spellButton);
//    //THESE ARE THE SAME
//
//  }
//
//  public void attackButtonClicked(ActionEvent event) throws Exception {
//    System.out.println(event);
//    setBattleText("Yo");
//
//  }
//
//  public void setBattleText(String str) {
//    combatInfo.setText(str);
//  }
//
//  public void initialize() {
//
////    //Beginning text
////    combatInfo.setText("Begin your fight!");
//
//    //Set player Images
//    playerImage.setImage(wizardImg);
//    enemyImage.setImage(warImg);
//
//    //HealthBar Stuff
//    humanHealthBar.progressProperty().bind(myNum.numberProperty());
//
//    myNum.setNumber(healthPerc);
//    System.out.println(healthPerc);
//
//    //INITIATE STUFF
//
//    playerName.setText("Yo");
//    combatInfo.setText("Hi");
//
//    //Create Array
//    createPlayerArray();
//    startFights();
//
//    System.out.println(myPlayer.name + myPlayer.speciality);
//
//
//  }
//
//  public void fight() {
////    System.out.println("You have begun your battle with " + aiPlayer.name +
////        ". What is your first move?");
////    System.out.println("I am changing the combatLog");
//
//    while (humanPlayer.isAlive() && aiPlayer.isAlive()) {
//      fightLoop();
//      enemyAttack();
//      displayFightStats();
//      checkWinner();
//    }
//  }
//
//
//  //Method asking what type of attack to use and Uses
//  //SpellAttack or PhysicalAttack accordingly
//  public void fightLoop() {
//    System.out.println("----------------------------------------------");
//    System.out.println("1.Physical Attack             2.Spell Attack");
//    System.out.println("----------------------------------------------");
//    Scanner scanner = new Scanner(System.in);
//    int input = scanner.nextInt();
//    switch (input) {
//      case 1:
//        humanPlayer.physicalAttack(aiPlayer);
//        break;
//      case 2:
//        humanPlayer.spellAttack(aiPlayer);
//        break;
//      default:
//        System.out.println("Pick 1 or 2");
//        break;
//    }
//  }
//
//  //Enemy attack using a random generator to decide if it is a
//  //Physical or Spell attack
//  public void enemyAttack() {
//
//    switch (randomGenerator(1, 2)) {
//      case 1: //Select 1
//        System.out.println(aiPlayer.name + " used a Physical Attack!");
//        aiPlayer.physicalAttack(humanPlayer);
//        break;
//      case 2: //Select 2
//        System.out.println(aiPlayer.name + " used a Spell Attack!");
//        aiPlayer.spellAttack(humanPlayer);
//        break;
//      default:
//        System.out.println("The randomGenerator is broken.");
//        break;
//    }
//
//  }
//
//  //Method to check winner, for use in other methods
//  //Declares winner/Loser
//  public void checkWinner() {
//    if (humanPlayer.isAlive() == false) {
//      System.out
//          .println("********************************You died********************************");
//    }
//    if (aiPlayer.isAlive() == false) {
//      System.out
//          .println("********************************You win!********************************");
//    }
//  }
//
//  //Fight statistics to display in between attack selection
//  public void displayFightStats() {
//    System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
//    System.out.println("                   " + humanPlayer.name + " :");
//    System.out.println(
//        "Health: " + humanPlayer.health + " Armor: " + humanPlayer.armor + " Resistance: "
//            + humanPlayer.resistance);
//    System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
//    System.out.println("                   " + aiPlayer.name + " :");
//    System.out.println("Health: " + aiPlayer.health + " Armor: " +
// aiPlayer.armor + " Resistance: "
//        + aiPlayer.resistance);
//    System.out.println("||||||||||||||||||||||||||||||||||||||||||||||");
//
//  }
//
//  int randomGenerator(int min, int max) {
//    int range = (max - min) + 1;
//    return (int) (Math.random() * range) + min;
//    //To potentially  use for Attack modifier
//    //(randomWithRange(5,10)*.1)
//    //This will have an attack do anywhere from 50% to 100% damage
//  }
//
//
//  @Override
//  public void actionPerformed(java.awt.event.ActionEvent e) {
//    if (e.getSource() == spellButton){
//      System.out.println("Sup");
//    }
//    else{
//      System.out.println(e);
//    }
//  }
//
//}
