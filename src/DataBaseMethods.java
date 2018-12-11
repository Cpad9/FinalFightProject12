import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseMethods {

  //Database tablename: Players
  //CHAR: NAME
  //CHAR: HEALTH

  /**
   * Connection to Database.
   **/
  public static Connection getConnection() throws Exception {

    try {
      String dataBaseUrl = "jdbc:derby:C:\\Users\\Chris\\"
          + "Desktop\\FinalFightProject\\lib\\LetsFightDb";

      Connection connection = DriverManager.getConnection(dataBaseUrl);
      return connection;

    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * //Create table if it doesn't already exist.
   **/

  public static void createTable() throws Exception {
    try {
      System.out.println("Created DataBase Table");
      Connection con = getConnection();
      PreparedStatement create = con.prepareStatement(
          "CREATE TABLE IF NOT EXISTS Players"
              + " (NAME VARCHAR(255),HEALTH VARCHAR(255))");  //INSERT TABLE NAME HERE
      create.executeUpdate();
    } catch (Exception e) {
      System.out.println("DataBase Already Exists.");
    }
  }

  /**
   * //Method to print out array Database //SELECT.
   **/
  public static ArrayList<String> get() {
    try {
      Connection connection = getConnection();
      Statement statement = connection.prepareStatement("SELECT * FROM Players");
      ResultSet result = ((PreparedStatement) statement).executeQuery();
      // ArrayList<String> array = new ArrayList<String>();

      ResultSetMetaData metaData = result.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      for (int i = 1; i <= numberOfColumns; i++) {
        System.out.printf("%-10s\t", metaData.getColumnName(i));
      }
      System.out.println();
      while (result.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
          System.out.printf("%-10s\t", result.getObject(i));
        }
        System.out.println();
      }

      System.out.println("All records have been selected");
      return null;

    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * //Posts name/health to DataBase.
   **/
  public static void post(String name, String health) {
    try {
      Connection con = getConnection();
      PreparedStatement posted = con.prepareStatement(
          "INSERT INTO Players (NAME,HEALTH) VALUES ('" + name + "','" + health + "')");
      posted.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      System.out.println("Insert Done.");
    }
  }

  /**
   * //UPDATE DATABASE. //Change health to 150 to whoever has your name.
   **/
  public static void updateHealth(String name) {
    try {
      Connection con = getConnection();
      PreparedStatement posted = con
          .prepareStatement("UPDATE Players SET HEALTH ='200' WHERE NAME = '" + name + "' ");
      posted.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      System.out.println("Update Done.");
    }
  }

  /**
   * //Delete Database.
   **/
  public static void deleteDatabase(String name) {
    try {
      Connection con = getConnection();
      PreparedStatement posted = con
          .prepareStatement("DELETE FROM Players WHERE name = '" + name + "' ");
      posted.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      System.out.println("Delete Done");
    }
  }

}

