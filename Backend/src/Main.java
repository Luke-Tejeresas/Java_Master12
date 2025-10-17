import java.util.Scanner;

//For testing purposes only
public class Main {
    public static void main(String[] args) {
        Registration reg = new Registration();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to the Login System ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option (1 or 2): ");
        int choice = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (choice == 1) {
            reg.registerUser(username, password);
        } else if (choice == 2) {
            boolean success = reg.loginUser(username, password);
            if (success) {
                System.out.println(" Access granted! Welcome, " + username);
            } else {
                System.out.println(" Access denied. Please check your credentials.");
            }
        } else {
            System.out.println(" Invalid choice. Please restart the program.");
        }

        sc.close();
    }
}