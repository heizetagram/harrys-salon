package harryssalon;

import ui.ConsoleColors;
import ui.Menu;
import ui.UI;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Appointment> appointments;
    ArrayList<Appointment> sortedAppointments;
    Menu menu;
    Password password;
    boolean running;
    String currentRole;
    String secondChoice;
    String thirdChoice;
    LocalDateTime dateTime;
    String[] parts;
    Integer[] intParts;

    // Initialize variables
    void initVars() {
        sortedAppointments = new ArrayList<>();
        menu = new Menu();
        password = new Password();
        running = true;
        currentRole = "";
        secondChoice = null;
        thirdChoice = null;
        initAppointments();
    }

    public static void main(String[] args) {
        new Main().run();
    }

    // Run method
    private void run() {
        initVars();

        while (running) {
            menu.showRoleSelection();

            chooseRoleSelectionMenuOption();

            switch (currentRole) {
                //TO DO: case "customer" -> chooseCustomerMenuOption(); // Customer's menu options
                case "harry" -> loginHarry(); // Harry's menu options
                case "accountant" -> loginAccountant(); // Accountant's menu options
            }

            if ("2".equals(secondChoice)) {
                menu.showAddProductMenu();
                int secondMenuChoice = UI.promptInt();
                switch (secondMenuChoice) {
                    case 1 -> thirdChoice = "Shampoo has been added to reservation, press 1 for status";
                    case 2 -> thirdChoice = "Hairnet has been added to reservation, press 1 for status";
                    case 3 -> thirdChoice = "Balsam has been added to reservation, press 1 for status";
                    default -> tryAgain();
                }
                UI.println(thirdChoice);
            }
        }
    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // CHOOSE MENU OPTIONS \\

    // Choose option in role selection menu
    void chooseRoleSelectionMenuOption() {
        switch (UI.promptInt()) {
            case 1 -> currentRole = "customer";
            case 2 -> currentRole = "harry";
            case 3 -> currentRole = "accountant";
            case 9 -> quitSystem();
            default -> tryAgain();
        }
    }

    // Choose option in Harrys Menu
    void chooseHarrysMenuOption() {
        if (running) {
            int choice = UI.promptInt();
            switch (choice) {
                case 1 -> {bookAppointment(); pressEnterToContinue();}
                case 2 -> {secondChoice = "2"; pressEnterToContinue();}
                case 3 -> {deleteAppointment(); pressEnterToContinue();}
                case 4 -> {viewAppointment(); pressEnterToContinue();}
                case 9 -> quitSystem();
                default -> tryAgain();
            }
        }
    }

    // Choose option in Accountant Menu
    void chooseAccountantMenuOption() {
        if (running) {
            int choice = UI.promptInt();
            switch (choice) {
                case 1 -> {viewAllSortedAppointments(); pressEnterToContinue();}
                case 9 -> quitSystem();
                default -> tryAgain();
            }
        }
    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // PASSWORDS \\

    // Harry password
    void loginHarry() {
        UI.println("Enter password");
        UI.promptString(); // Scanner bug
        password.checkPassword(UI.promptString(), "1");
        while (password.isPasswordCorrect() && running) { // Keep showing Harry's menu while logged in
            menu.showHarryMenu();
            chooseHarrysMenuOption();
        }
        if (!password.isPasswordCorrect()) {
            running = false;
        }
    }

    // Accountant password
    void loginAccountant() {
        UI.println("Enter password");
        UI.promptString(); // Scanner bug
        password.checkPassword(UI.promptString(), "2");
        while (password.isPasswordCorrect() && running) { // Keep showing Accountant's menu while logged in
            menu.showAccountantMenu();
            chooseAccountantMenuOption();
        }
        if (!password.isPasswordCorrect()) {
            running = false;
        }
    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // Appointments \\

    // Initialize appointments logic
    private void initAppointments() {
        appointments = new ArrayList<>();
        loadAppointmentsFromFile();
    }

    // Read appointments logic
    private void loadAppointmentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parts = line.split(",");
                if (parts.length == 7) {
                    appointments.add(new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save appointments logic
    private void saveAppointmentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) { // True doesn't overwrite any existing code, and if the file doesn't exist, it will create a new.
            for (Appointment appointment : appointments) {
                writer.write(appointment.getCustomerName() + "," + appointment.getYear() + "," + appointment.getMonth() + "," + appointment.getDay() + "," + appointment.getHour() + "," + appointment.getMinute() + "," + appointment.getAddedProduct());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Booking of appointments logic
    private void bookAppointment() {
        UI.promptString(); // Scanner bug
        UI.println("Enter costumer name");
        String costumerName = UI.promptString();
        UI.println("Enter appointment year (yyyy)");
        String year = Integer.toString(UI.promptInt());
        UI.println("Enter appointment month (mm)");
        String month = Integer.toString(UI.promptInt());
        UI.println("Enter appointment day (dd)");
        String day = Integer.toString(UI.promptInt());
        UI.println("Enter appointment hour (hh)");
        String hour = Integer.toString(UI.promptInt());
        UI.println("Enter appointment minute (hh)");
        String minute = Integer.toString(UI.promptInt());

        UI.println("Enter added buys (Shampoo, balsam, hairnet");
        UI.promptString(); // Scanner bug
        String addedProduct = UI.promptString();

        if (addedProduct.isEmpty()) {
            addedProduct = "N/A";
        }
        appointments.add(new Appointment(costumerName, year, month, day, hour, minute, addedProduct));
        UI.println("Appointment added successfully! :D");

        saveAppointmentsToFile();
    }

    // Delete appointments. creates a new arraylist (updatedAppointments), if the size of updated list is smaller than original, an appointment was deleted.
    private void deleteAppointment() {
        UI.promptString(); // Scanner bug
        UI.println("Enter customer name: ");
        String customerName = UI.promptString();
        UI.println("Enter appointment year (yyyy)");
        String year = Integer.toString(UI.promptInt());
        UI.println("Enter appointment month (mm)");
        String month = Integer.toString(UI.promptInt());
        UI.println("Enter appointment day (dd)");
        String day = Integer.toString(UI.promptInt());

        List<Appointment> updatedAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (!(appointment.getCustomerName().equals(customerName)
                    && appointment.getYear().equals(year)
                    && appointment.getMonth().equals(month)
                    && appointment.getDay().equals(day))) {
                // Keep appointments that don't match the one to be deleted
                updatedAppointments.add(appointment);
            }
        }

        if (updatedAppointments.size() < appointments.size()) {
            UI.println("Appointment deleted successfully!");
            appointments = updatedAppointments; // Update the appointments list
            saveAppointmentsToFile();
        } else {
            UI.println("No matching appointment found for deletion.");
        }
    }


    // View appointments on a specific date
    private void viewAppointment() {
        sortAllAppointments();
        UI.promptString(); // Scanner bug
        UI.println("Enter year (yyyy)");
        String userYear = Integer.toString(UI.promptInt());

        UI.println("Enter month (m)");
        String userMonth = Integer.toString(UI.promptInt());

        UI.println("Enter day (d)");
        String userDay = Integer.toString(UI.promptInt());


        for (Appointment appointment : appointments) // Iterates through each appointment in appointments, and checks if it contains user input.
            if (appointment.getYear().contentEquals(userYear) && appointment.getMonth().contentEquals(userMonth) && appointment.getDay().contentEquals(userDay)) {
                printAppointment(appointment);
            }
        UI.println(""); // Empty line
    }

    // View all sorted appointments
    private void viewAllSortedAppointments() {
        sortAllAppointments();
        for (Appointment appointment : appointments) {
            printAppointment(appointment);
        }
        UI.println(""); // Empty line
    }

    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // Dates \\ TO DO

    // Parses String to LocalDateTime and sets the Date for the corresponding Appointment
    void parseStringToDateFormat() {
        try {
            for (Appointment appointment : appointments) {
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

    // Sorts all appointments
    // Finds the earliest appointment in each iteration, removes it from 'appointments' List, and adds it to 'sortedAppointments' ArrayList.
    // Keeps doing this until 'assignments' is empty
    void sortAllAppointments() {
        parseStringToDateFormat(); // Sets the LocalDateTime for each appointment
        while (!appointments.isEmpty()) {
            LocalDateTime min = LocalDateTime.MAX;
            Appointment minimum = null;
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getDateTime().isBefore(min)) {
                    min = appointments.get(i).getDateTime();
                    minimum = appointments.get(i);
                }
            }
            appointments.remove(minimum);
            sortedAppointments.add(minimum);
        }
        resetSortedList();
    }

    // Resets the sorted list (e.g. used when adding a new booking, then viewing appointments while the program is still running)
    void resetSortedList() {
        appointments.addAll(sortedAppointments);
        sortedAppointments.clear();
    }

    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // System messages \\

    // Prints appointment information
    void printAppointment(Appointment appointment) {
        UI.println(ConsoleColors.YELLOW + appointment.getYear() + "-"
                + appointment.getMonth() + "/"
                + appointment.getDay() + ConsoleColors.RESET + ", "
                + appointment.getHour() + ":"
                + appointment.getMinute() + ", "
                + appointment.getCustomerName() + ", "
                + appointment.getAddedProduct());
    }

    // Press ENTER to continue
    void pressEnterToContinue() {
        Scanner scan = new Scanner(System.in);
        UI.print(ConsoleColors.BLACK_BRIGHT + "Press ENTER to continue" + ConsoleColors.RESET);
        scan.nextLine(); // Does not work with UI.promptString() for some reason !!!
        UI.println("");
    }

    // Try again
    void tryAgain() {
        UI.println(ConsoleColors.RED + "Try again!" + ConsoleColors.RESET);
        UI.promptString(); // User prompt - Enter to show menu again
    }

    // Exit system
    void quitSystem() {
        UI.println("Cya!");
        running = false;
    }
}