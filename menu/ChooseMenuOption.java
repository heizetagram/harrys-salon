package menu;

import appointment.Appointment;
import appointment.ModifyAppointment;
import date.AvailableDate;
import date.PromptDate;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChooseMenuOption {
    private Main main;
    private SystemMessages systemMessages;
    private ModifyAppointment modifyAppointment;
    private AvailableDate availableDate;
    private PromptDate promptDate;
    // Constructor
    public ChooseMenuOption(Main main) {
        this.main = main;
        systemMessages = new SystemMessages(main);
        modifyAppointment = new ModifyAppointment(main);
        availableDate = new AvailableDate(main);
        promptDate = new PromptDate();
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
            case 3 -> calculateDailyTurnover();
            case 9 -> systemMessages.quitSystem();
            default -> systemMessages.tryAgain();
        }
    }
    // Calculate daily turnover for appointments on a specific date
    public void calculateDailyTurnover() {
       /* UI.println("Enter the date to calculate the daily turnover (YYYY-MM-DD):");
        UI.promptString();
        String dateInput = UI.promptString();
*/
        String stringUserYear = promptDate.promptYear();
        String stringUserMonth = promptDate.promptMonth();
        String stringUserDay = promptDate.promptDay();
        try {
           // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //Date targetDate = dateFormat.parse(dateInput);

            double totalTurnover = 0;

            for (Appointment appointment : main.getAppointments()) {

                    if (appointment.getYear().equals(stringUserYear)&&(appointment.getMonth().equals(stringUserMonth)&&(appointment.getDay().equals(stringUserDay)))) {
                        totalTurnover += appointment.getTotalPrice();
                    }
                }


            UI.println("The total turnover for " + stringUserYear + "-" + stringUserMonth + "/" + stringUserDay + " is: DKK" + totalTurnover);
        } catch (Exception e) {
            UI.println("Invalid date format. Please use the format YYYY-MM-DD.");
        }
    }
}
