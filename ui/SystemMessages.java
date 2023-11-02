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

    // Print opening hours
    public void printOpeningHours() {
        UI.println(ConsoleColors.BLUE_BRIGHT + "Opening hours: 10-18" + ConsoleColors.RESET);
    }

    // Print user date dates
    public void printUserDate(int year, int month, int day, String dayOfWeek) {
        UI.printf("%s%04d-%02d/%02d, %s: %s", ConsoleColors.YELLOW, year, month, day, dayOfWeek, ConsoleColors.RESET);
    }

    // Prints appointment information
    public void printAppointment(Appointment appointment) {
        UI.printf("%s%04d-%02d/%02d%s, %02d:%02d: %-10s (Added product:   %s%7s%s)%n", ConsoleColors.YELLOW,
                Integer.parseInt(appointment.getYear()),
                Integer.parseInt(appointment.getMonth()),
                Integer.parseInt(appointment.getDay()),
                ConsoleColors.RESET,
                Integer.parseInt(appointment.getHour()),
                Integer.parseInt(appointment.getMinute()),
                appointment.getCustomerName(),
                ConsoleColors.BLUE_BRIGHT,
                appointment.getAddedProduct(),
                ConsoleColors.RESET);
    }

    // Prints appointment with financial information
    public void printFinancialAppointment(Appointment appointment) {
        UI.printf("%s%04d-%02d/%02d%s, %02d:%02d: %-10s Total price: %s%.2f DKK%s, (Added product:   %s%7s%s)%n", ConsoleColors.YELLOW,
                Integer.parseInt(appointment.getYear()),
                Integer.parseInt(appointment.getMonth()),
                Integer.parseInt(appointment.getDay()),
                ConsoleColors.RESET,
                Integer.parseInt(appointment.getHour()),
                Integer.parseInt(appointment.getMinute()),
                appointment.getCustomerName() + ",",
                ConsoleColors.GREEN_BRIGHT,
                appointment.getTotalPrice(),
                ConsoleColors.RESET,
                ConsoleColors.BLUE_BRIGHT,
                appointment.getAddedProduct(),
                ConsoleColors.RESET);
    }

    // Prints daily turnover information
    public void printDailyTurnover(String year, String month, String day, double totalTurnover) {
        UI.printf("The total turnover for %s%04d-%02d/%02d%s is: %s%.2f DKK%s%n%n", ConsoleColors.YELLOW,
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day),
                ConsoleColors.RESET,
                ConsoleColors.GREEN_BRIGHT,
                totalTurnover,
                ConsoleColors.RESET);
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

    // Print green colored text
    public void printGreenColoredText(String text) {
        UI.println(ConsoleColors.GREEN_BRIGHT + text + ConsoleColors.RESET);
    }

    // Print red colored text
    public void printRedColoredText(String text) {
        UI.println(ConsoleColors.RED + text + ConsoleColors.RESET);
    }

    // Exit system
    public void quitSystem() {
        UI.println("Cya!");
        main.setRunning(false);
    }

}
