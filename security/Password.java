package security;

import ui.ConsoleColors;
import ui.UI;

public class Password {
    private int passwordAttemptsLeft;
    private boolean passwordIsCorrect;

    // Constructor
    public Password() {
        passwordAttemptsLeft = 2;
        passwordIsCorrect = false;
    }

    // Getter
    public boolean isPasswordCorrect() {
        return passwordIsCorrect;
    }

    // Checks password
    // Gives user three tries to enter the correct password
    public void checkPassword(String userInput, String password) {
        while (!userInput.equals(password) && passwordAttemptsLeft > 0) {
            UI.println(ConsoleColors.RED + "Incorrect password" + ConsoleColors.RESET+ ", tries left: " + passwordAttemptsLeft);
            userInput = UI.promptString();
            passwordAttemptsLeft--;
        }
        if (userInput.equals(password)) {
            passwordIsCorrect = true;
            UI.println(ConsoleColors.GREEN_BRIGHT + "\nSuccessfully logged in\n" + ConsoleColors.RESET);
        } else {
            UI.println(ConsoleColors.RED + "Failed to Login" + ConsoleColors.RESET);
        }
    }
}