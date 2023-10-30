package menu;

import appointment.ModifyAppointment;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

public class ChooseMenuOption {
    private Main main;
    private SystemMessages systemMessages;
    private ModifyAppointment modifyAppointment;

    // Constructor
    public ChooseMenuOption(Main main) {
        this.main = main;
        this.systemMessages = new SystemMessages(main);
        this.modifyAppointment = new ModifyAppointment(main);
    }

    // CHOOSE MENU OPTIONS \\

    // Choose option in role selection menu
    public void chooseRoleSelectionMenuOption() {
        switch (UI.promptInt()) {
            case 1 -> main.setCurrentRole("customer");
            case 2 -> main.setCurrentRole("harry");
            case 3 -> main.setCurrentRole("accountant");
            case 9 -> systemMessages.quitSystem();
            default -> systemMessages.tryAgain();
        }
    }

    // Choose option in Customer's Menu
    public void chooseCustomerMenuOption() {
        if (main.isRunning()) {
            int choice = UI.promptInt();
            switch (choice) {
                case 1 -> {modifyAppointment.viewAvailableDates(); systemMessages.pressEnterToContinue();}
                case 9 -> systemMessages.quitSystem();
                default -> systemMessages.tryAgain();
            }
        }
    }

    // Choose option in Harrys Menu
    public void chooseHarrysMenuOption() {
        if (main.isRunning()) {
            int choice = UI.promptInt();
            switch (choice) {
                case 1 -> {modifyAppointment.bookAppointment(); systemMessages.pressEnterToContinue();}
                //case 2 -> {secondChoice = "2"; systemMessages.pressEnterToContinue();}
                case 3 -> {modifyAppointment.deleteAppointment(); systemMessages.pressEnterToContinue();}
                case 4 -> {modifyAppointment.viewAppointment(); systemMessages.pressEnterToContinue();}
                case 9 -> systemMessages.quitSystem();
                default -> systemMessages.tryAgain();
            }
        }
    }

    // Choose option in Accountant Menu
    public void chooseAccountantMenuOption() {
        if (main.isRunning()) {
            int choice = UI.promptInt();
            switch (choice) {
                case 1 -> {modifyAppointment.viewAllSortedAppointments(); systemMessages.pressEnterToContinue();}
                case 9 -> systemMessages.quitSystem();
                default -> systemMessages.tryAgain();
            }
        }
    }
}
