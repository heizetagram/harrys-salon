package security;

import menu.ChooseMenuOption;
import harryssalon.Main;
import menu.ShowMenu;
import ui.UI;

public class Login {
    private Main main;
    private ShowMenu showMenu;
    private ChooseMenuOption chooseMenuOption;
    private Password password;

    // Constructor
    public Login(Main main) {
        this.main = main;
        password = new Password();
        showMenu = new ShowMenu();
        chooseMenuOption = new ChooseMenuOption(main);
    }

    // Harry password
    public void loginHarry() {
        UI.println("Enter password");
        UI.promptString(); // Scanner bug
        password.checkPassword(UI.promptString(), "1");
        while (password.isPasswordCorrect() && main.isRunning()) { // Keep showing Harry's menu while logged in
            showMenu.showHarryMenu();
            chooseMenuOption.chooseHarrysMenuOption();
        }
        if (!password.isPasswordCorrect()) {
            main.setRunning(false);
        }
    }

    // Accountant password
    public void loginAccountant() {
        UI.println("Enter password");
        UI.promptString(); // Scanner bug
        password.checkPassword(UI.promptString(), "2");
        while (password.isPasswordCorrect() && main.isRunning()) { // Keep showing Accountant's menu while logged in
            showMenu.showAccountantMenu();
            chooseMenuOption.chooseAccountantMenuOption();
        }
        if (!password.isPasswordCorrect()) {
            main.setRunning(false);
        }
    }
}
