import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class Registration {

    private Connection con;

    private static final String DB_NAME = "users"; 
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public Registration() {
        connectToDatabase();
    }

    //For database connection checking
    private void connectToDatabase() {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println(" Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" Database connection error: " + e.getMessage());
        }
    }

    //For user registration
    public void registerUser(String username, String password) {
    if (!username.matches("^[a-zA-Z0-9_.]+$")) {
        System.out.println(" Username can only contain letters, numbers, underscores, and dots.");
        return;
    }
    
    //For password complexity
    if (password.length() < 8 || 
        !password.matches(".*[a-zA-Z].*") || 
        !password.matches(".*\\d.*") || 
        !password.matches(".*[^a-zA-Z0-9].*")) {
        System.out.println(" Password must be at least 8 characters and include letters, numbers, and symbols.");
        return;
    }
  
    if (userExists(username)) {
        System.out.println(" Username already exists. Please choose another.");
        return;
    }

    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    String query = "INSERT INTO accinfo (accUsername, accPassword) VALUES (?, ?)";
    try (PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, username);
        pst.setString(2, hashedPassword);
        int rowsInserted = pst.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println(" User registered successfully!");
        } else {
            System.out.println(" Registration failed.");
        }
    } catch (SQLException e) {
        System.out.println(" SQL Error: " + e.getMessage());
    }
}

    //Check if user exists
    public boolean userExists(String username) {
        String query = "SELECT * FROM accinfo WHERE BINARY accUsername = ?";
    try (PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, username);
        return pst.executeQuery().next(); // true if a match is found
    } catch (SQLException e) {
        System.out.println(" SQL Error: " + e.getMessage());
        return false;
    }
}

    //For user login
    public boolean loginUser(String username, String inputPassword) {
    String query = "SELECT accPassword FROM accinfo WHERE BINARY accUsername = ?";
    try (PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, username);
        var rs = pst.executeQuery();

        if (rs.next()) {
            String storedHash = rs.getString("accPassword");

            if (BCrypt.checkpw(inputPassword, storedHash)) {
                System.out.println(" Login successful!");
                return true;
            } else {
                System.out.println(" Incorrect password.");
                return false;
            }
        } else {
            System.out.println(" Username not found.");
            return false;
        }
    } catch (SQLException e) {
        System.out.println(" SQL Error: " + e.getMessage());
        return false;
    }
}

}
