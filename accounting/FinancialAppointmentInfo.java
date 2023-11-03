package accounting;

import appointment.Appointment;
import date.PromptDate;
import date.SortDate;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class FinancialAppointmentInfo {
    private Main main;
    private SortDate sortDate;
    private PromptDate promptDate;
    private SystemMessages systemMessages;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    LocalDateTime dateTimeNow;
    LocalDateTime userDateTime;
    ArrayList<Appointment> appointmentsForUserDate;
    ArrayList<Appointment> appointmentsForDate;

    // Constructor
    public FinancialAppointmentInfo(Main main) {
        this.main = main;
        sortDate = new SortDate(main);
        promptDate = new PromptDate();
        systemMessages = new SystemMessages(main);
    }

    // View appointments with financial information on a specific date from <= today
    public void viewFinancialAppointment() {
        sortDate.sortAllAppointments();

        UI.promptString(); // Scanner bug
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();
        dateTimeNow = LocalDateTime.now();
        userDateTime = LocalDateTime.of(Integer.parseInt(stringUserYear), Integer.parseInt(stringUserMonth), Integer.parseInt(stringUserDay), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond(), LocalDateTime.now().getNano());

        // Checks if user-given date is after today's date
        if (userDateTime.isAfter(dateTimeNow)) {
            systemMessages.printRedColoredText("Cannot view future appointments");
        } else {
            appointmentsForUserDate = getAppointmentsForDate(stringUserYear, stringUserMonth, stringUserDay);

            // Checks if there are appointments that day
            if (appointmentsForUserDate.isEmpty()) {
                systemMessages.printRedColoredText("No appointments that day");
            } else {
                for (Appointment appointment : appointmentsForUserDate) {
                    systemMessages.printFinancialAppointment(appointment);
                }
            }
        }

        UI.println(""); // Empty line
    }

    // Gets appointments for user-given date
    private ArrayList<Appointment> getAppointmentsForDate(String userYear, String userMonth, String userDay) {
        appointmentsForDate = new ArrayList<>();

        for (Appointment appointment : main.getAppointments()) { // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(userYear)
                    && appointment.getMonth().contentEquals(userMonth)
                    && appointment.getDay().contentEquals(userDay)
                    && appointment.getDateTime().isBefore(LocalDateTime.now())) {
                appointmentsForDate.add(appointment);
            }
        }
        return appointmentsForDate;
    }
}
