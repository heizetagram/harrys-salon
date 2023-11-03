package appointment;

import date.AvailableDate;
import date.PromptDate;
import date.SortDate;
import harryssalon.Main;
import menu.ShowMenu;
import ui.ConsoleColors;
import ui.SystemMessages;
import ui.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModifyAppointment {
    private Main main;
    private FileHandlingAppointment fileHandlingAppointment;
    private List<Appointment> updatedAppointments;
    private SystemMessages systemMessages;
    private SortDate sortDate;
    private PromptDate promptDate;
    private ShowMenu showMenu;
    private AvailableDate availableDate;
    private String customerName;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    private String stringUserHour;
    private String stringUserMinute;
    private LocalDateTime userDate;
    private boolean foundAppointment;
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
        promptDate = new PromptDate(main);
        showMenu = new ShowMenu();
        availableDate = new AvailableDate(main);
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

        showMenu.showAddProductMenu();
        UI.promptString(); // Scanner bug
        String addedProduct = UI.promptString();
        switch (addedProduct) {
            case "1" -> addedProduct = "Shampoo";
            case "2" -> addedProduct = "Balsam";
            case "3" -> addedProduct = "Hairnet";
            default -> addedProduct = "N/A";
        }

        switch (addedProduct) {
            case "Shampoo" -> totalPrice += 50;
            case "Balsam" -> totalPrice += 60;
            case "Hairnet" -> totalPrice += 100;
            default -> systemMessages.printRedColoredText("No added hair product");
        }

        userDate = LocalDateTime.of(Integer.parseInt(stringUserYear), Integer.parseInt(stringUserMonth), Integer.parseInt(stringUserDay), Integer.parseInt(stringUserHour), Integer.parseInt(stringUserMinute));
        checkIfBookingIsPossible(customerName, stringUserYear, stringUserMonth, stringUserDay, stringUserHour, stringUserMinute, userDate, totalPrice, addedProduct);

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
            systemMessages.printRedColoredText("No matching appointment found for deletion\n");
        }
    }

    // View appointments on a specific date
    public void viewAppointment() {
        sortDate.sortAllAppointments();

        UI.promptString(); // Scanner bug
        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();

        foundAppointment = false;

        for (Appointment appointment : main.getAppointments()) { // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(stringUserYear)
                    && appointment.getMonth().contentEquals(stringUserMonth)
                    && appointment.getDay().contentEquals(stringUserDay)) {
                systemMessages.printAppointment(appointment);
                foundAppointment = true;
            }
        }
        if (!foundAppointment) {
            systemMessages.printRedColoredText("No appointments found");
        } else {
            UI.println(""); // Empty line
        }
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
        initCurrentAppointmentPrompts();

        Appointment appointmentToEdit = null;
        for (Appointment appointment : main.getAppointments()) {
            if (appointment.getCustomerName().equals(customerName)
                    && (appointment.getYear().equals(currentYear)
                    && (appointment.getMonth().equals(currentMonth)
                    && (appointment.getDay().equals(currentDay)
                    && (appointment.getHour().equals(currentHour)
                    && (appointment.getMinute().equals(currentMinute)
                    && (appointment.getAddedProduct().equals(currentProduct)))))))) {
                appointmentToEdit = appointment;

                UI.print(ConsoleColors.YELLOW_BOLD + "\nENTER NEW INFO");
                initNewAppointmentPrompts();

                systemMessages.printGreenColoredText("Successfully edited appointment\n");

                setValueOfAppointmentToEdit(appointmentToEdit);

                fileHandlingAppointment.saveAppointmentsToFile();
                }
            }
        if (appointmentToEdit == null) {
            systemMessages.printRedColoredText("No appointment found\n");
        }
    }

    // Initializes current appointment prompts
    private void initCurrentAppointmentPrompts() {
        UI.promptString(); // Scanner bug
        customerName = promptDate.promptCustomerName();
        currentYear = promptDate.promptYear();
        currentMonth = promptDate.promptMonth();
        currentDay = promptDate.promptDay();
        currentHour = promptDate.promptHour();
        currentMinute = promptDate.promptMinute();
        UI.promptString(); // Scanner bug
        currentProduct = promptDate.promptProduct();
    }

    // Initializes new appointment prompts
    private void initNewAppointmentPrompts() {
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
    }

    // Sets value of appointmentToEdit
    private void setValueOfAppointmentToEdit(Appointment appointmentToEdit) {
        appointmentToEdit.setName(newName);
        appointmentToEdit.setYear(newYear);
        appointmentToEdit.setMonth(newMonth);
        appointmentToEdit.setDay(newDay);
        appointmentToEdit.setHour(newHour);
        appointmentToEdit.setMinute(newMinute);
        appointmentToEdit.setAddedProduct(newProduct);
    }

    // Checks if time is already booked
    private boolean checkIfTimeIsAlreadyBooked(String userYear, String userMonth, String userDay, String userHour, String userMinute) {
        boolean isAlreadyBooked = false;
        for (Appointment appointment : main.getAppointments()) {
            if (appointment.getYear().equals(userYear) && appointment.getMonth().equals(userMonth) && appointment.getDay().equals(userDay) && appointment.getHour().equals(userHour) && appointment.getMinute().equals(userMinute)) {
                isAlreadyBooked = true;
            }
        }
        return isAlreadyBooked;
    }

    // Checks if booking is possible on given date
    private void checkIfBookingIsPossible(String customerName, String userYear, String userMonth, String userDay, String userHour, String userMinute, LocalDateTime userDate, double totalPrice, String addedProduct) {
        // Checks if time is already booked or if it's on a weekend
        if (checkIfTimeIsAlreadyBooked(userYear, userMonth, userDay, userHour, userMinute)) {
            systemMessages.printRedColoredText("Date is already booked\n");
        } else if (availableDate.isWeekend(userDate)) {
            systemMessages.printRedColoredText("Cannot book appointments on weekends");
        } else {
            main.getAppointments().add(new Appointment(customerName, userYear, userMonth, userDay, userHour, userMinute, Double.toString(totalPrice), addedProduct));
            fileHandlingAppointment.saveAppointmentsToFile();
            systemMessages.printGreenColoredText("Appointment added successfully\n");
        }
    }
}