package date;

import appointment.Appointment;
import harryssalon.Main;

import java.time.LocalDateTime;

public class SortDate {
    private Main main;

    // Constructor
    public SortDate(Main main) {
        this.main = main;
    }

    // Sorts all appointments
    // Finds the earliest appointment in each iteration, removes it from 'appointments' List, and adds it to 'sortedAppointments' ArrayList.
    // Keeps doing this until 'assignments' is empty
    public void sortAllAppointments() {
        parseStringToDateFormat(); // Sets the LocalDateTime for each appointment
        while (!main.getAppointments().isEmpty()) {
            LocalDateTime min = LocalDateTime.MAX;
            Appointment minimum = null;
            for (int i = 0; i < main.getAppointments().size(); i++) {
                if (main.getAppointments().get(i).getDateTime().isBefore(min)) {
                    min = main.getAppointments().get(i).getDateTime();
                    minimum = main.getAppointments().get(i);
                }
            }
            main.getAppointments().remove(minimum);
            main.getSortedAppointments().add(minimum);
        }
        resetSortedList(); // Returns all sorted appointments back to 'appointments'
    }

    // Parses String to LocalDateTime and sets the Date for the corresponding Appointment
    private void parseStringToDateFormat() {
        try {
            for (Appointment appointment : main.getAppointments()) {
                int intYear = Integer.parseInt(appointment.getYear());
                int intMonth = Integer.parseInt(appointment.getMonth());
                int intDay = Integer.parseInt(appointment.getDay());
                int intHour = Integer.parseInt(appointment.getHour());
                int intMinute = Integer.parseInt(appointment.getMinute());

                appointment.setDateTime(LocalDateTime.of(intYear, intMonth, intDay, intHour, intMinute));
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Resets the sorted list (e.g. used when adding a new booking, then viewing appointments while the program is still running)
    private void resetSortedList() {
        main.getAppointments().addAll(main.getSortedAppointments());
        main.getSortedAppointments().clear();
    }
}
