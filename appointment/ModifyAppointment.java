package appointment;

import harryssalon.Main;
import ui.SystemMessages;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

public class ModifyAppointment {
    private Main main;
    private FileHandlingAppointment fileHandlingAppointment;
    private List<Appointment> updatedAppointments;
    private SystemMessages systemMessages;
    private SortDate sortDate;

    // Constructor
    public ModifyAppointment(Main main) {
        this.main = main;
        fileHandlingAppointment = new FileHandlingAppointment(main);
        systemMessages = new SystemMessages(main);
        sortDate = new SortDate(main);
    }

    // View available dates
    public void viewAvailableDates() {
        UI.println("Opening hours: 10-18");
        UI.println("Enter year (yyyy)");
        String userYear = Integer.toString(UI.promptInt());

        UI.println("Enter month (m)");
        String userMonth = Integer.toString(UI.promptInt());

        UI.println("Enter day (d)");
        String userDay = Integer.toString(UI.promptInt());

        main.getAvailableDates().add(new Appointment("Michael", "2023", "12", "12", "15", "30", "N/A"));
        for (Appointment availableDate : main.getAvailableDates())
            for (Appointment appointment : main.getAppointments()) {
                if (availableDate.getCustomerName().equals(appointment.getCustomerName()) && availableDate.getYear().equals(appointment.getYear()) && availableDate.getMonth().equals(appointment.getMonth()) && availableDate.getDay().equals(appointment.getDay())) {
                    System.out.println("test");
                }
            }
    }

    // Booking of appointments logic
    public void bookAppointment() {
        UI.promptString(); // Scanner bug
        UI.println("Enter costumer name");
        String costumerName = UI.promptString();
        UI.println("Enter appointment year (yyyy)");
        String year = Integer.toString(UI.promptInt());
        UI.println("Enter appointment month (m)");
        String month = Integer.toString(UI.promptInt());
        UI.println("Enter appointment day (d)");
        String day = Integer.toString(UI.promptInt());
        UI.println("Enter appointment hour (h)");
        String hour = Integer.toString(UI.promptInt());
        UI.println("Enter appointment minute (m)");
        String minute = Integer.toString(UI.promptInt());

        UI.println("Enter added buys (Shampoo, balsam, hairnet");
        UI.promptString(); // Scanner bug
        String addedProduct = UI.promptString();

        if (addedProduct.isEmpty()) {
            addedProduct = "N/A";
        }
        main.getAppointments().add(new Appointment(costumerName, year, month, day, hour, minute, addedProduct));
        UI.println("Appointment added successfully! :D");

        fileHandlingAppointment.saveAppointmentsToFile();
    }

    // Delete appointments. creates a new arraylist (updatedAppointments), if the size of updated list is smaller than original, an appointment was deleted.
    public void deleteAppointment() {
        UI.promptString(); // Scanner bug
        UI.println("Enter customer name: ");
        String customerName = UI.promptString();
        UI.println("Enter appointment year (yyyy)");
        String year = Integer.toString(UI.promptInt());
        UI.println("Enter appointment month (m)");
        String month = Integer.toString(UI.promptInt());
        UI.println("Enter appointment day (d)");
        String day = Integer.toString(UI.promptInt());

        updatedAppointments = new ArrayList<>();

        for (Appointment appointment : main.getAppointments()) {
            if (!(appointment.getCustomerName().equals(customerName)
                    && appointment.getYear().equals(year)
                    && appointment.getMonth().equals(month)
                    && appointment.getDay().equals(day))) {
                // Keep appointments that don't match the one to be deleted
                updatedAppointments.add(appointment);
            }
        }

        if (updatedAppointments.size() < main.getAppointments().size()) {
            UI.println("Appointment deleted successfully!");
            main.setAppointments(updatedAppointments); // Update the appointments list
            fileHandlingAppointment.saveAppointmentsToFile();
        } else {
            UI.println("No matching appointment found for deletion.");
        }
    }


    // View appointments on a specific date
    public void viewAppointment() {
        sortDate.sortAllAppointments();
        UI.promptString(); // Scanner bug
        UI.println("Enter year (yyyy)");
        String userYear = Integer.toString(UI.promptInt());

        UI.println("Enter month (m)");
        String userMonth = Integer.toString(UI.promptInt());

        UI.println("Enter day (d)");
        String userDay = Integer.toString(UI.promptInt());


        for (Appointment appointment : main.getAppointments()) // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(userYear)
                    && appointment.getMonth().contentEquals(userMonth)
                    && appointment.getDay().contentEquals(userDay)) {
                systemMessages.printAppointment(appointment);
            }
        UI.println(""); // Empty line
    }

    // View all sorted appointments
    public void viewAllSortedAppointments() {
        sortDate.sortAllAppointments();
        for (Appointment appointment : main.getAppointments()) {
            systemMessages.printAppointment(appointment);
        }
        UI.println(""); // Empty line
    }

    public void editAppointment() {
        UI.promptString();
        UI.println("Enter Costumer name");
        String costumerName = UI.promptString();
        UI.println("Enter Years");
        String currentYear = UI.promptString();
        UI.println("Enter month");
        String currentMonth = UI.promptString();
        UI.println("Enter day");
        String currentDay = UI.promptString();
        UI.println("Enter hours");
        String currentHour = UI.promptString();
        UI.println("Enter Minute");
        String currenMinute = UI.promptString();
        UI.println("Enter added product");
        String currentProduct = UI.promptString();

        Appointment appointmentToEdit = null;
        for (Appointment appointment : main.getAppointments()) {
            if (appointment.getCustomerName().equals(costumerName) &&
                    (appointment.getYear().equals(currentYear) &&
                            (appointment.getMonth().equals(currentMonth) &&
                            (appointment.getDay().equals(currentDay) &&
                                    (appointment.getHour().equals(currentHour) &&
                                     (appointment.getMinute().equals(currenMinute) &&
                                     (appointment.getAddedProduct().equals(currentProduct)))))))) {
                appointmentToEdit = appointment;

                if (appointmentToEdit == null) {
                    UI.println("No appointment found");

                } else {
                    UI.println("Enter new name");
                    String newName = Integer.toString(UI.promptInt());
                    UI.println("Enter new year");
                    String newYear = Integer.toString(UI.promptInt());
                    UI.println("enter new month");
                    String newMonth = Integer.toString(UI.promptInt());
                    UI.println("enter new day");
                    String newDay = Integer.toString(UI.promptInt());

                    UI.println("Enter new hour");
                    String newHour = Integer.toString(UI.promptInt());
                    UI.println("Enter new minute");
                    String newMinute = Integer.toString(UI.promptInt());
                    UI.println("Enter New added products");
                    String newProduct = Integer.toString(UI.promptInt());

                    appointmentToEdit.setName(newName);
                    appointmentToEdit.setYear(newYear);
                    appointmentToEdit.setMonth(newMonth);
                    appointmentToEdit.setDay(newDay);
                    appointmentToEdit.setHour(newHour);
                    appointmentToEdit.setMinute(newMinute);
                    appointmentToEdit.setAddedProduct(newProduct);

                    fileHandlingAppointment.saveAppointmentsToFile();



                }
            }
        }
    }
}



