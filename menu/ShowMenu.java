package menu;

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
        UI.println("(1) View all Appointments");
        UI.println("(2) Search for specific day ");
        UI.println("(3) Calculate daily turnover for a specific date ");
        UI.println("(9) Quit");
    }

    public void showHarryMenu() {
        UI.println("(1) Book appointment");
        UI.println("(2) Delete appointment");
        UI.println("(3) View appointment");
        UI.println("(4) Edit appointment");
        UI.println("(9) Quit");
    }

    public void showAddProductMenu() {
        UI.println("(1) Shampoo");
        UI.println("(2) Hairnet");
        UI.println("(3) Balsam");
    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////

}