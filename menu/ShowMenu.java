package menu;

import ui.ConsoleColors;
import ui.UI;

public class ShowMenu {

    // SHOW MENUS \\
    public void showRoleSelection() {
        UI.println("Welcome to Hairy Harry's!\nAre you: ");
        UI.println("(1) Customer");
        UI.println("(2) Harry");
        UI.println("(3) Accountant");
        UI.println("(9) Quit");
    }

    public void showCustomerMenu() {
        UI.println("(1) View available dates");
        UI.println("(9) Quit");
    }

    public void showAccountantMenu() {
        UI.println("(1) View all appointments");
        UI.println("(2) View appointment details on a specific date");
        UI.println("(3) Calculate daily turnover for a specific date ");
        UI.println("(9) Quit");
    }

    public void showHarryMenu() {
        UI.println("(1) Book appointment");
        UI.println("(2) Delete appointment");
        UI.println("(3) Edit appointment");
        UI.println("(4) View appointments on specific date");
        UI.println("(9) Quit");
    }

    public void showAddProductMenu() {
        UI.println("Enter additional purchase:");
        UI.printf("%s%s%5d DKK%s%n", "(1) Shampoo", ConsoleColors.GREEN_BRIGHT, 50, ConsoleColors.RESET);
        UI.printf("%s%s%7d DKK%s%n", "(2) Balsam", ConsoleColors.GREEN_BRIGHT, 100, ConsoleColors.RESET);
        UI.printf("%s%s%5d DKK%s%n", "(3) Hairnet", ConsoleColors.GREEN_BRIGHT, 60, ConsoleColors.RESET);
        UI.print(ConsoleColors.BLACK_BRIGHT + "Press ENTER to skip" + ConsoleColors.RESET);
    }
}