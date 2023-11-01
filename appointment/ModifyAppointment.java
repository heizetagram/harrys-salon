package appointment;

import date.PromptDate;
import date.SortDate;
import harryssalon.Main;
import ui.ConsoleColors;
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
    private PromptDate promptDate;
    private String customerName;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    private String stringUserHour;
    private String stringUserMinute;
    private String currentYear;
    private String currentMonth;
    private String currentDay;
    private String currentHour;
    private String currentMinute;
    private String currentProduct;
    private String newName;
    private String newYear;
    private String newMonth;
    private String newDay;
    private String newHour;
    private String newMinute;
    private String newProduct;

    // Constructor
    public ModifyAppointment(Main main) {
        this.main = main;
        fileHandlingAppointment = new FileHandlingAppointment(main);
        systemMessages = new SystemMessages(main);
        sortDate = new SortDate(main);
        promptDate = new PromptDate();
    }

    // Booking of appointments logic
    public void bookAppointment() {
        UI.promptString(); // Scanner bug
        customerName = promptDate.promptCustomerName();
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();
        stringUserHour = promptDate.promptHour();
        stringUserMinute = promptDate.promptMinute();

        double totalPrice = 200;

        UI.println("Enter added buys (Shampoo, Balsam, Hairnet");
        UI.promptString(); // Scanner bug
        String addedProduct = UI.promptString();
        if (addedProduct.isEmpty()) {
            addedProduct = "N/A";
        }
        switch (addedProduct) {
            case "Shampoo" -> totalPrice += 50;
            case "Balsam" -> totalPrice += 60;
            case "Hairnet" -> totalPrice += 100;
            default ->
                    UI.println("No added hair product");

        }
        System.out.println(totalPrice);
        main.getAppointments().add(new Appointment(customerName, stringUserYear, stringUserMonth, stringUserDay, stringUserHour, stringUserMinute, Double.toString(totalPrice), addedProduct));
        UI.println(ConsoleColors.GREEN_BRIGHT + "Appointment added successfully\n" + ConsoleColors.RESET);

        fileHandlingAppointment.saveAppointmentsToFile();
    }

    // Delete appointments. creates a new arraylist (updatedAppointments), if the size of updated list is smaller than original, an appointment was deleted.
    public void deleteAppointment() {
        UI.promptString(); // Scanner bug
        customerName = promptDate.promptCustomerName();
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();

        updatedAppointments = new ArrayList<>();

        for (Appointment appointment : main.getAppointments()) {
            if (!(appointment.getCustomerName().equals(customerName)
                    && appointment.getYear().equals(stringUserYear)
                    && appointment.getMonth().equals(stringUserMonth)
                    && appointment.getDay().equals(stringUserDay))) {
                // Keep appointments that don't match the one to be deleted
                updatedAppointments.add(appointment);
            }
        }

        if (updatedAppointments.size() < main.getAppointments().size()) {
            UI.println(ConsoleColors.GREEN_BRIGHT + "Appointment deleted successfully!\n" + ConsoleColors.RESET);
            main.setAppointments(updatedAppointments); // Update the appointments list
            fileHandlingAppointment.saveAppointmentsToFile();
        } else {
            UI.println(ConsoleColors.RED + "No matching appointment found for deletion\n" + ConsoleColors.RESET);
        }
    }

    // View appointments on a specific date
    public void viewAppointment() {
        sortDate.sortAllAppointments();

        UI.promptString(); // Scanner bug
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();


        for (Appointment appointment : main.getAppointments()) // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(stringUserYear)
                    && appointment.getMonth().contentEquals(stringUserMonth)
                    && appointment.getDay().contentEquals(stringUserDay)) {
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

    // Edit appointment
    public void editAppointment() {
        UI.promptString(); // Scanner bug
        customerName = promptDate.promptCustomerName();
        currentYear = promptDate.promptYear();
        currentMonth = promptDate.promptMonth();
        currentDay = promptDate.promptDay();
        currentHour = promptDate.promptHour();
        currentMinute = promptDate.promptMinute();
        UI.promptString(); // Scanner bug
        currentProduct = promptDate.promptProduct();

        Appointment appointmentToEdit = null;
        for (Appointment appointment : main.getAppointments()) {
            if (appointment.getCustomerName().equals(customerName) &&
                    (appointment.getYear().equals(currentYear) &&
                            (appointment.getMonth().equals(currentMonth) &&
                            (appointment.getDay().equals(currentDay) &&
                                    (appointment.getHour().equals(currentHour) &&
                                     (appointment.getMinute().equals(currentMinute) &&
                                     (appointment.getAddedProduct().equals(currentProduct)))))))) {
                appointmentToEdit = appointment;

                UI.print(ConsoleColors.YELLOW_BOLD + "\nENTER NEW INFO");
                UI.println(ConsoleColors.YELLOW);
                newName = promptDate.promptCustomerName();
                newYear = promptDate.promptYear();
                UI.print(ConsoleColors.YELLOW);
                newMonth = promptDate.promptMonth();
                UI.print(ConsoleColors.YELLOW);
                newDay = promptDate.promptDay();
                UI.print(ConsoleColors.YELLOW);
                newHour = promptDate.promptHour();
                UI.print(ConsoleColors.YELLOW);
                newMinute = promptDate.promptMinute();
                UI.promptString(); // Scanner bug
                newProduct = promptDate.promptProduct();
                UI.print(ConsoleColors.RESET);

                UI.println(ConsoleColors.GREEN_BRIGHT + "Successfully edited appointment!\n" + ConsoleColors.RESET);

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
        if (appointmentToEdit == null) {
            UI.println(ConsoleColors.RED + "No appointment found\n" + ConsoleColors.RESET);
        }
    }
}