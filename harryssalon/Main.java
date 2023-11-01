package harryssalon;

import appointment.Appointment;
import appointment.FileHandlingAppointment;
import date.AvailableDate;
import menu.ChooseMenuOption;
import security.Login;
import security.Password;
import menu.ShowMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Appointment> appointments;
    ArrayList<Appointment> sortedAppointments;
    ArrayList<Appointment> availableDates;
    ShowMenu showMenu;
    ChooseMenuOption chooseMenuOption;
    Password password;
    boolean running;
    String currentRole;
    String secondChoice;
    String thirdChoice;
    Login login;
    FileHandlingAppointment fileHandlingAppointment;
    AvailableDate availableDate;

    // Initialize variables
    void initVars() {
        availableDates = new ArrayList<>();
        password = new Password();
        running = true;
        currentRole = "";
        secondChoice = null;
        thirdChoice = null;

        fileHandlingAppointment = new FileHandlingAppointment(this);
        appointments = new ArrayList<>();
        sortedAppointments = new ArrayList<>();
        fileHandlingAppointment.loadAppointmentsFromFile();

        showMenu = new ShowMenu();
        chooseMenuOption = new ChooseMenuOption(this);
        login = new Login(this);

        availableDate = new AvailableDate(this);
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