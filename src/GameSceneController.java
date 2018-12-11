//Last edited: 11-17-18

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameSceneController {

  @FXML
  private Button lockNameButton;

  private static String userName = "Player";
  protected static Player myPlayer = new Player();
  @FXML
  private TextField userNameInput;

  @FXML
  private Text userInfoText;

  @FXML
  private Button selectRogueButton;

  @FXML
  private TextArea rogueInfoText;

  @FXML
  private TextArea wizardInfoText;

  @FXML
  private TextArea warriorInfoText;

  @FXML
  private Button createHeroButton;


  public GameSceneController() {
  }

  /**
  //Initialization method to set up text on Hero Creation Screen.
   **/
  public void initialize() {
    rogueInfoText.setText(
        "Rogues have medium health\nMedium attack power\nMedium spell power "
            + "\nMedium resistance & armor\nThey are a jack of all trades.");
    wizardInfoText.setText(
        "Wizards have low health\nLow attack power\nHigh spellpower\n"
            + "Low armor, High resistance\nYou want to use your spells.");
    warriorInfoText.setText(
        "Warriors are mighty\nThey have high health\nNo spell power\n"
            + "High resistance & armor\nStrong physical attacks");
  }


  //Create Hero clicks
  //Creates Hero and prints the Database with him added.
  @FXML
  void createHeroClicked(ActionEvent event) throws Exception {
    if (userNameInput.getText().equals("")) {
      System.out.println("Must have a name in the name field.");
    } else {
      String myPlayerHealth = Double.toString(myPlayer.health);
      //Insert Player stats into Database
      try {
        Connection connection = DataBaseMethods.getConnection();
        Statement posted = connection.prepareStatement(
            "INSERT INTO Players (NAME,HEALTH) VALUES ('" + userName + "','" + myPlayerHealth
                + "')");
        ((PreparedStatement) posted).executeUpdate();

        //Open up new scene
        Stage stage;

        // Close Stage
        stage = (Stage) createHeroButton.getScene().getWindow();
        stage.close();


      } catch (Exception e) {
        System.out.println(e);
      } finally {
        System.out.println("Inserted into Database");

        Main.askUser();

      }
    }
  }

  //Locks in Name of hero
  //Displays it in text below textField
  @FXML
  void lockNameClicked(ActionEvent event) throws Exception {
    //userName ------- Variable created
    userName = userNameInput.getText();
    userInfoText.setText(userName);
    myPlayer.setName(userName);
  }

  //Sets speciality to Rogue & prints to text field
  @FXML
  void rogueButtonClicked(ActionEvent event) throws Exception {
    try {
      myPlayer.setSpeciality("Rogue");
      userInfoText.setText(userName + "\nis a Rogue!");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  //Sets speciality to Warrior & prints to text field

  @FXML
  void warriorButtonClicked(ActionEvent event) {
    try {
      myPlayer.setSpeciality("Warrior");
      userInfoText.setText(userName + "\nis a Warrior!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  //Sets speciality to Wizard & prints to text field

  @FXML
  void wizardButtonClicked(ActionEvent event) throws Exception {
    try {
      myPlayer.setSpeciality("Wizard");
      userInfoText.setText(userName + "\nis a Wizard!");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
