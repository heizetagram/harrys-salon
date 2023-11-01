package menu;

import appointment.ModifyAppointment;
import date.AvailableDate;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

public class ChooseMenuOption {
    private Main main;
    private SystemMessages systemMessages;
    private ModifyAppointment modifyAppointment;
    private AvailableDate availableDate;

    // Constructor
    public ChooseMenuOption(Main main) {
        this.main = main;
        systemMessages = new SystemMessages(main);
        modifyAppointment = new ModifyAppointment(main);
        availableDate = new AvailableDate(main);
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
        switch (UI.promptInt()) {
            case 1 -> {availableDate.viewAvailableDates(); systemMessages.pressEnterToContinue();}
            case 9 -> systemMessages.quitSystem();
            default -> systemMessages.tryAgain();
        }
    }

    // Choose option in Harry's Menu
    public void chooseHarryMenuOption() {
        switch (UI.promptInt()) {
            case 1 -> {modifyAppointment.bookAppointment(); systemMessages.pressEnterToContinue();}
            case 2 -> {modifyAppointment.deleteAppointment(); systemMessages.pressEnterToContinue();}
            case 3 -> {modifyAppointment.viewAppointment(); systemMessages.pressEnterToContinue();}
            case 4 -> {modifyAppointment.editAppointment(); systemMessages.pressEnterToContinue();}
            case 9 -> systemMessages.quitSystem();
            default -> systemMessages.tryAgain();
        }
    }

    // Choose option in Accountant Menu
    public void chooseAccountantMenuOption() {
        switch (UI.promptInt()) {
            case 1 -> {modifyAppointment.viewAllSortedAppointments(); systemMessages.pressEnterToContinue();}
            case 2 -> {modifyAppointment.viewAppointment(); systemMessages.pressEnterToContinue();}
            case 9 -> systemMessages.quitSystem();
            default -> systemMessages.tryAgain();
        }
    }
}
