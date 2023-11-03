package date;

import appointment.Appointment;
import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvailableDate {
    private Main main;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    private int intUserYear;
    private int intUserMonth;
    private int intUserDay;
    private SystemMessages systemMessages;
    private Appointment appointment;
    private ArrayList<Appointment> toRemove;
    private PromptDate promptDate;
    private LocalDateTime userDate;
    private LocalDateTime dateTimeNow;
    private LocalDateTime counterDate;
    private LocalDateTime endDate;

    // Constructor
    public AvailableDate(Main main) {
        this.main = main;
        toRemove = new ArrayList<>();
        systemMessages = new SystemMessages(main);
        promptDate = new PromptDate(main);
        counterDate = LocalDateTime.of(2023, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 12, 31, 17, 30);
    }
    
    // Creates available weekdays from 2023-2024
    // While startDate doesn't equal endDate, keep adding 30 minutes until the dates become equal.
    public void createAvailableDates() {
        while (!counterDate.isEqual(endDate)) {
            if (!counterDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !counterDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // Only adds weekdays to 'availableDates'
                main.getAvailableDates().add(appointment = new Appointment("N/A", Integer.toString(counterDate.getYear()), Integer.toString(counterDate.getMonthValue()), Integer.toString(counterDate.getDayOfMonth()), Integer.toString(counterDate.getHour()), Integer.toString(counterDate.getMinute()), "0", "N/A"));
                }
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

    // View available dates
    public void viewAvailableDates() {
        initDateVar();

        // Checks if user-given date is in the past
        if (userDate.isBefore(dateTimeNow)) {
            systemMessages.printRedColoredText("Cannot view past times");
        } else {
            systemMessages.printGreenColoredText("\nAvailable times:");
            int n = 0;
            if (isWeekend(userDate)) {
                systemMessages.printUserDate(userDate.getYear(), userDate.getMonthValue(), userDate.getDayOfMonth(), userDate.getDayOfWeek().toString());
                systemMessages.printRedColoredText("CLOSED");
                n++;
            }
            // If it's weekend, continue adding days to 'userDate' until it's a work day
            while (n != 5) {
                if (isWeekend(userDate)) {
                    userDate = userDate.plusDays(1);
                } else {
                    printAvailableTimesForDate(userDate);
                    userDate = userDate.plusDays(1);
                    n++;
                }
            }
        }
        UI.println("");
    }

    // Checks if date is weekend
    public boolean isWeekend(LocalDateTime userDate) {
        return userDate.getDayOfWeek() == DayOfWeek.SATURDAY || userDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    // Prints available times for date
    private void printAvailableTimesForDate(LocalDateTime userDate) {
        systemMessages.printUserDate(userDate.getYear(), userDate.getMonthValue(), userDate.getDayOfMonth(), userDate.getDayOfWeek().toString());

        int i = 0;
        for (Appointment availableDate : main.getAvailableDates()) { // Iterates through each appointment in appointments, and checks if it contains user input.
            if (availableDate.getYear().contentEquals(Integer.toString(userDate.getYear()))
                    && availableDate.getMonth().contentEquals(Integer.toString(userDate.getMonthValue()))
                    && availableDate.getDay().contentEquals(Integer.toString(userDate.getDayOfMonth()))) {
                if (i > 0) { // Creates a ", " before each time, except the first available time
                    UI.print(", ");
                }
                UI.printf("%02d:%02d", Integer.parseInt(availableDate.getHour()), Integer.parseInt(availableDate.getMinute()));
                i++;
            }
        }

        if (i == 0 && isWeekend(userDate)) { // If there aren't any available times, print the following
            systemMessages.printRedColoredText("CLOSED");
        } else if (i == 0) {
            systemMessages.printRedColoredText("FULLY BOOKED");
        } else {
            UI.println("");
        }
    }

    // Initializes variables
    private void initDateVar() {
        systemMessages.printOpeningHours();
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();
        intUserYear = Integer.parseInt(stringUserYear);
        intUserMonth = Integer.parseInt(stringUserMonth);
        intUserDay = Integer.parseInt(stringUserDay);
        dateTimeNow = LocalDateTime.now();
        userDate = LocalDateTime.of(intUserYear, intUserMonth, intUserDay, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond(), LocalDateTime.now().getNano());
    }
}