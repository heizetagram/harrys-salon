package accounting;

import appointment.Appointment;
import date.PromptDate;
import date.SortDate;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.time.LocalDateTime;


public class FinancialAppointmentInfo {
    private Main main;
    private SortDate sortDate;
    private PromptDate promptDate;
    private SystemMessages systemMessages;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    private boolean hasPrinted;

    // Constructor
    public FinancialAppointmentInfo(Main main) {
        this.main = main;
        sortDate = new SortDate(main);
        promptDate = new PromptDate();
        systemMessages = new SystemMessages(main);
        hasPrinted = false;
    }

    // View appointments with financial information on a specific date from <= today
    public void viewFinancialAppointment() {
        sortDate.sortAllAppointments();

        UI.promptString(); // Scanner bug
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();

        for (Appointment appointment : main.getAppointments()) { // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(stringUserYear)
                    && appointment.getMonth().contentEquals(stringUserMonth)
                    && appointment.getDay().contentEquals(stringUserDay)
                    && appointment.getDateTime().isBefore(LocalDateTime.now())) {
                systemMessages.printFinancialAppointment(appointment);
                hasPrinted = true;
            }
        }
        if (!hasPrinted) {
            systemMessages.printRedColoredText("Cannot view future appointments");
        }
        UI.println(""); // Empty line
    }
}
