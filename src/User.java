// User.java

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class User {

    private Scanner sc;
    private String userName;
    private String password;
    private String email;
    private boolean loginStatus;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.loginStatus = false;
        this.email = email;
        this.sc = new Scanner(System.in);
    }

    public boolean verify(String userName, String password) {
        return loginStatus = this.userName.equals(userName)
                && this.password.equals(password);
    }

    public boolean resetPassword_pw() {
        if (loginStatus) {
            try {
                System.out.print("Please enter your previous password: ");
                String password = sc.nextLine();
                if (this.password.equals(password)) {
                    System.out.println("You may now enter a new password: ");
                    String newPassword = sc.nextLine();
                    this.password = newPassword;
                    System.out.println("Password successfully changed");
                    return true;
                } else {
                    System.out.println("Passwords do not match.");
                    return false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                return false;
            }
        } else {
            System.out.println("You must log in first.");
            return false;
        }
    }

    public boolean resetPassword_mail() {
        if (loginStatus) {
            System.out.println("Password reset link sent to " + email);
            return true;
        }
        System.out.println("You must log in first.");
        return false;
    }

    public boolean changeUserName() {
        if (loginStatus) {
            while (true) {
                try {
                    System.out.println("\nHello " + this.userName
                            + ", how would you prefer your username to be updated? "
                            + "\n(Enter 'exit' to quit.)");
                    String newName = sc.nextLine();
                    if (newName.equalsIgnoreCase("exit")) {
                        return false;
                    } else if (AuthManager.isUsernameTaken(newName)) {
                        System.out.println("Username already taken.");
                    } else {
                        this.userName = newName;
                        System.out.println("Username changed successfully.");
                        return true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    sc.nextLine();
                }
            }
        } else {
            System.out.println("You must log in first.");
            return false;
        }
    }

    public boolean changeMail() {
        if (loginStatus) {
            System.out.println("Mail reset link sent to " + email);
            return true;
        }
        System.out.println("You must log in first.");
        return false;
    }

    @Override
    public String toString() {
        return "Username: " + userName
                + "\nE-mail: " + email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
