package date;

import appointment.Appointment;
import harryssalon.Main;
import ui.ConsoleColors;
import ui.SystemMessages;
import ui.UI;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvailableDate {
    private Main main;
    private LocalDateTime date;
    private String userYear;
    private String userMonth;
    private String userDay;
    private String stringYear;
    private String stringMonth;
    private String stringDay;
    private String stringHour;
    private String stringMinute;
    private SystemMessages systemMessages;
    private Appointment appointment;
    private ArrayList<Appointment> toRemove;
    private PromptDate promptDate;

    public AvailableDate(Main main) {
        this.main = main;
        toRemove = new ArrayList<>();
        systemMessages = new SystemMessages(main);
        promptDate = new PromptDate();
    }

    // View available dates
    public void viewAvailableDates() {
        UI.println(ConsoleColors.BLUE_BRIGHT + "Opening hours: 10-18" + ConsoleColors.RESET);
        userYear = promptDate.promptYear();
        userMonth = promptDate.promptMonth();
        userDay = promptDate.promptDay();


        UI.println(ConsoleColors.GREEN_BRIGHT + "Available times: " + ConsoleColors.RESET);
        UI.printf("%s%s-%s/%s: %s", ConsoleColors.YELLOW, userYear, userMonth, userDay, ConsoleColors.RESET);

        int i = 0;
        for (Appointment availableDate : main.getAvailableDates()) { // Iterates through each appointment in appointments, and checks if it contains user input.
            if (availableDate.getYear().contentEquals(userYear) && availableDate.getMonth().contentEquals(userMonth) && availableDate.getDay().contentEquals(userDay)) {
                if (i > 0) {
                    UI.print(", ");
                }
                UI.printf("%02d:%02d", Integer.parseInt(availableDate.getHour()), Integer.parseInt(availableDate.getMinute()));
                i++;
            }
        }
        UI.println("\n");
    }

    // Creates available weekdays from 2023-2024
    // VERY UGLY CODE
    public void createAvailableDates() {
        for (int year = 2023; year <= 2024; year++) {
            for (int month = 1; month <= 12; month++) {
                for (int day = 1; day <= 31; day++) {
                    for (int hour = 10; hour <= 17; hour++) {
                        for (int minute = 0; minute <= 30; minute += 30) {
                            parseIntToString(year, month, day, hour, minute);
                            try { // Checks if given date is a valid date in the Gregorian Calendar
                                date = LocalDateTime.of(year, month, day, hour, minute);
                                if (!date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) { // Only adds weekdays to 'availableDates'
                                    main.getAvailableDates().add(appointment = new Appointment("N/A", stringYear, stringMonth, stringDay, stringHour, stringMinute, "0", "N/A"));
                                    //systemMessages.printAppointment(appointment);
                                }
                            } catch (DateTimeException e) {
                                // Intentionally left blank
                            }
                        }
                    }
                }
            }
        }
    }

    // Parses int to String
    private void parseIntToString(int year, int month, int day, int hour, int minute) {
        stringYear = Integer.toString(year);
        stringMonth = Integer.toString(month);
        stringDay = Integer.toString(day);
        stringHour = Integer.toString(hour);
        stringMinute = Integer.toString(minute);
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
