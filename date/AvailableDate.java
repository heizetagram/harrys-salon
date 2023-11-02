package date;

import appointment.Appointment;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvailableDate {
    private Main main;
    private LocalDateTime date;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    LocalDateTime dateTimeNow;
    private LocalDateTime userDate;
    private SystemMessages systemMessages;
    private Appointment appointment;
    private ArrayList<Appointment> toRemove;
    private PromptDate promptDate;
    LocalDateTime counterDate;
    LocalDateTime endDate;

    public AvailableDate(Main main) {
        this.main = main;
        toRemove = new ArrayList<>();
        systemMessages = new SystemMessages(main);
        promptDate = new PromptDate();
        counterDate = LocalDateTime.of(2023, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 12, 31, 17, 30);
    }

    // View available dates
    public void viewAvailableDates() {
        systemMessages.printOpeningHours();
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();

        dateTimeNow = LocalDateTime.now();
        userDate = LocalDateTime.of(Integer.parseInt(stringUserYear), Integer.parseInt(stringUserMonth), Integer.parseInt(stringUserDay), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond(), LocalDateTime.now().getNano());

        if (userDate.isBefore(dateTimeNow)) {
            systemMessages.printRedColoredText("Cannot view times in the past");
        } else {
            systemMessages.printGreenColoredText("Available times:");
            systemMessages.printUserDate(Integer.parseInt(stringUserYear), Integer.parseInt(stringUserMonth), Integer.parseInt(stringUserDay), userDate.getDayOfWeek().toString());

            int i = 0;
            for (Appointment availableDate : main.getAvailableDates()) { // Iterates through each appointment in appointments, and checks if it contains user input.
                if (availableDate.getYear().contentEquals(stringUserYear) && availableDate.getMonth().contentEquals(stringUserMonth) && availableDate.getDay().contentEquals(stringUserDay)) {
                    if (i > 0) {
                        UI.print(", ");
                    }
                    UI.printf("%02d:%02d", Integer.parseInt(availableDate.getHour()), Integer.parseInt(availableDate.getMinute()));
                    i++;
                }
            }
            if (i == 0) {
                systemMessages.printRedColoredText("CLOSED\n");
            } else {
                UI.println("\n");
            }
        }
    }

    // Creates available weekdays from 2023-2024
    // While startDate doesn't equal endDate, keep adding 30 minutes until the dates become equal.
    public void createAvailableDates() {
        while (!counterDate.isEqual(endDate)) {
            main.getAvailableDates().add(appointment = new Appointment("N/A", Integer.toString(counterDate.getYear()), Integer.toString(counterDate.getMonthValue()), Integer.toString(counterDate.getDayOfMonth()), Integer.toString(counterDate.getHour()), Integer.toString(counterDate.getMinute()), "0", "N/A"));
            counterDate = counterDate.plusMinutes(30);

            // If startDate's hour is higher than 17, add 16 hours to skip to 10 o'clock the next day.
            if (counterDate.getHour() > 17) {
                counterDate = counterDate.plusHours(16);
            }
        }
    }

    // Removes 'availableDate' on dates with appointments
    public void checkIfAppointmentOnDate() {
        for (Appointment availableDate : main.getAvailableDates()) {
            for (Appointment appointment : main.getAppointments()) {
                if (availableDate.getYear().equals(appointment.getYear())
                        && availableDate.getMonth().equals(appointment.getMonth())
                        && availableDate.getDay().equals(appointment.getDay())
                        && availableDate.getHour().equals(appointment.getHour())
                        && availableDate.getMinute().equals(appointment.getMinute())) {
                    toRemove.add(availableDate); // Adds all booked dates to 'toRemove' ArrayList
                }
            }
        }
        main.getAvailableDates().removeAll(toRemove); // Removes all 'toRemove' appointments from 'availableDates'
    }
}
