package appointment;

import harryssalon.Main;

import java.io.*;
import java.util.Scanner;

public class FileHandlingAppointment {
    private Main main;
    String[] parts;

    // Constructor
    public FileHandlingAppointment(Main main) {
        this.main = main;
    }

    // Read appointments logic
    public void loadAppointmentsFromFile() {
        File file = new File("appointments.txt"); {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    parts = line.split(",");
                    if (parts.length == 8) {
                        main.getAppointments().add(new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Save appointments logic
    public void saveAppointmentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment appointment : main.getAppointments()) {
                writer.write(appointment.getCustomerName() + "," + appointment.getYear() + "," + appointment.getMonth() + "," + appointment.getDay() + "," + appointment.getHour() + "," + appointment.getMinute() + "," + appointment.getTotalPrice() + "," + appointment.getAddedProduct());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
