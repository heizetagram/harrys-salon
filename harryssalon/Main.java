package harryssalon;

import appointment.Appointment;
import appointment.FileHandlingAppointment;
import date.AvailableDate;
import menu.ChooseMenuOption;
import security.Login;
import menu.ShowMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Appointment> appointments;
    ArrayList<Appointment> sortedAppointments;
    ArrayList<Appointment> availableDates;
    ShowMenu showMenu;
    ChooseMenuOption chooseMenuOption;
    String currentRole;
    Login login;
    FileHandlingAppointment fileHandlingAppointment;
    AvailableDate availableDate;
    boolean running;

    // Initialize variables
    void initVars() {
        appointments = new ArrayList<>();
        sortedAppointments = new ArrayList<>();
        availableDates = new ArrayList<>();
        showMenu = new ShowMenu();
        chooseMenuOption = new ChooseMenuOption(this);
        currentRole = "";

        fileHandlingAppointment = new FileHandlingAppointment(this);
        fileHandlingAppointment.loadAppointmentsFromFile();

        login = new Login(this);

        availableDate = new AvailableDate(this);
        running = true;
    }

    public static void main(String[] args) {
        new Main().run();
    }

    // Run method
    private void run() {
        initVars();

        availableDate.createAvailableDates();
        availableDate.checkIfAppointmentOnDate();

        while (running) {
            showMenu.showRoleSelection();
            chooseMenuOption.chooseRoleSelectionMenuOption();

            switch (currentRole) {
                case "customer" -> login.loginCustomer(); // Customer's menu options
                case "harry" -> login.loginHarry(); // Harry's menu options
                case "accountant" -> login.loginAccountant(); // Accountant's menu options
            }
        }
    }
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    // GETTERS AND SETTERS \\

    // Getters
    public boolean isRunning() {
        return running;
    }
    public ArrayList<Appointment> getAvailableDates() {
        return availableDates;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public ArrayList<Appointment> getSortedAppointments() {
        return sortedAppointments;
    }

    // Setters
    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}