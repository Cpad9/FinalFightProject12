//Chris Padgett
//Last edited: 11-17-18

public class Player {

  protected String name;
  protected double maxHealth;
  protected double health;
  protected double damage;
  protected double armor;
  protected double spellDamage;
  protected double resistance;
  protected String speciality;

  /**
   * //Generic Player constructor.
   **/
  public Player() {
    name = "Player1";
    health = 100;
    armor = 20;
    damage = 5;
    spellDamage = 5;
    resistance = 5;
  }

  /**
   * //Constructor overload (Uses Name).
   **/

  public Player(String name) {
    this.name = name;
    health = 100;
    armor = 20;
    damage = 5;
    spellDamage = 5;
    resistance = 5;
  }

  /**
   * //Constructor overload (Name & userSpec).
   **/

  public Player(String name, String userSpec) {
    this.name = name;
    setSpeciality(userSpec);
    //System.out.println(name + " is a " +speciality + "!");
    //System.out.println("Health: " + health);
    //System.out.println("Armor: " + armor);
    //System.out.println("Damage: " + damage);
    //System.out.println("Spell Damage: " + spellDamage);
    //System.out.println("Resistance: " + resistance);
    //System.out.println("----------------------------");
    //System.out.println();
  }

  /**
   * //Method for setting name of player.
   */


  public void setName(String name) {
    this.name = name;
  }

  /**
   * //Method to set speciality of player.
   **/
  public void setSpeciality(String userSpec) {
    switch (userSpec) {
      //Wizard
      case "Wizard":
      case "1":
        maxHealth = 100;
        speciality = "Wizard";
        health = 100;
        armor = 10;
        damage = 10;
        spellDamage = 40;
        resistance = 10;
        break;
      //Warrior
      case "Warrior":
      case "2":
        maxHealth = 150;
        speciality = "Warrior";
        health = 150;
        armor = 30;
        damage = 30;
        spellDamage = 0;
        resistance = 30;
        break;
      //Rogue
      case "Rogue":
      case "3":
        maxHealth = 125;
        speciality = "Rogue";
        health = 125;
        armor = 20;
        damage = 20;
        spellDamage = 20;
        resistance = 20;
        break;
      default:
        System.out.println("You need to pick '1,2,or 3'");
        break;
    }

  }

  //Method to return player name and speciality
  public String returnPlayerData() {
    return name + " is a " + speciality + "!";
  }

  //Method to check if Player is alive (For use in other methods)
  public boolean isAlive() {
    return health > 0;
  }

  //Method to check if Player has Armor (For use in other methods)
  public boolean hasArmor() {
    return armor > 0;
  }

  //Method to check if Player has Resistance (For use in other methods)
  public boolean hasResistance() {
    return resistance > 0;
  }

  /**
   * //Method physicalAttack logic for use in other methods.
   **/
  public void physicalAttack(Player enemy) {

    if (enemy.isAlive()) {
      if (enemy.hasArmor()) {
        enemy.armor = enemy.armor - (damage * .5);
        //If armor falls below 0, adjust remaining damage
        if (enemy.armor < 0) {
          double temp;
          temp = enemy.armor * -2;
          enemy.health = enemy.health - temp;
          enemy.armor = 0;
        }
      } else {
        //Attack when no armor
        enemy.health = enemy.health - damage;
        //If statement to never display negative health
        if (enemy.health <= 0) {
          enemy.health = 0;
        }
      }
    } else {
      System.out.println(enemy.name + " is already dead.");
    }
  }

  /**
   * //Method spellAttack logic for use in other methods.
   **/
  public void spellAttack(Player enemy) {
    if (enemy.isAlive()) {
      if (enemy.hasResistance()) {
        enemy.resistance = enemy.resistance - (spellDamage * .75);
        //If resistance falls below 0, adjust remaining damage
        if (enemy.resistance < 0) {
          double temp;
          temp =
              enemy.resistance * -(.4);
          enemy.health = enemy.health - temp;
          enemy.resistance = 0;
        }
      } else {
        //Attack when no armor
        enemy.health = enemy.health - spellDamage;
        //If statement to never display negative health
        if (enemy.health <= 0) {
          enemy.health = 0;
        }
      }
    } else {
      System.out.println(enemy.name + " is already dead");
    }
  }


}
