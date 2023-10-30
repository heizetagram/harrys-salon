package ui;

import appointment.Appointment;
import harryssalon.Main;

import java.util.Scanner;

public class SystemMessages {
    private Main main;

    // Constructor
    public SystemMessages(Main main) {
        this.main = main;
    }

    // Prints appointment information
    public void printAppointment(Appointment appointment) {
        UI.printf("%s%04d-%02d/%02d%s, %02d:%02d, %s, %s%n", ConsoleColors.YELLOW,
                Integer.parseInt(appointment.getYear()),
                Integer.parseInt(appointment.getMonth()),
                Integer.parseInt(appointment.getDay()),
                ConsoleColors.RESET,
                Integer.parseInt(appointment.getHour()),
                Integer.parseInt(appointment.getMinute()),
                appointment.getCustomerName(),
                appointment.getAddedProduct());
    }

    // Press ENTER to continue
    public void pressEnterToContinue() {
        Scanner scan = new Scanner(System.in);
        UI.print(ConsoleColors.BLACK_BRIGHT + "Press ENTER to continue" + ConsoleColors.RESET);
        scan.nextLine(); // Does not work with UI.promptString() for some reason !!!
        UI.println("");
    }

    // Try again
    public void tryAgain() {
        UI.println(ConsoleColors.RED + "Try again!" + ConsoleColors.RESET);
        UI.promptString(); // User prompt - Enter to show menu again
    }

    // Exit system
    public void quitSystem() {
        UI.println("Cya!");
        main.setRunning(false);
    }

}
