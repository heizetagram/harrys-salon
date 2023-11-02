package date;

import ui.ConsoleColors;
import ui.UI;

public class PromptDate {

    //Prompts user for customer name
    public String promptCustomerName() {
        UI.print("Enter customer name: ");
        return UI.promptString();
    }

    // Prompts user for year
    // Entered year must be between 2023-2024
    public String promptYear() {
        int intUserYear = 0;
        UI.print("Enter year (2023-2024): ");

        while (intUserYear < 2023 || intUserYear > 2024) {
            intUserYear = UI.promptInt();

            if (intUserYear < 2023 || intUserYear > 2024) {
                UI.println(ConsoleColors.RED + "Year must be between 2023-2024" + ConsoleColors.RESET);
            }
        }
        return Integer.toString(intUserYear);
    }

    // Prompts user for month
    // Entered month must be between 1-12
    public String promptMonth() {
        int intUserMonth = 0;
        UI.print("Enter month (1-12): ");

        while (intUserMonth < 1 || intUserMonth > 12) {
            intUserMonth = UI.promptInt();

            if (intUserMonth < 1 || intUserMonth > 12) {
                UI.println(ConsoleColors.RED + "Month must be between 1-12" + ConsoleColors.RESET);
            }
        }
        return Integer.toString(intUserMonth);
    }

    // Prompts user for day
    // Entered day must be between 1-31
    public String promptDay() {
        int intUserDay = 0;
        UI.print("Enter day (1-31): ");

        while (intUserDay < 1 || intUserDay > 31) {
            intUserDay = UI.promptInt();

            if (intUserDay < 1 || intUserDay > 31) {
                UI.println(ConsoleColors.RED + "Day must be between 1-31" + ConsoleColors.RESET);
            }
        }
        return Integer.toString(intUserDay);
    }

    // Prompts user for hour
    // Entered hour must be between 10-17
    public String promptHour() {
        int intUserHour = 0;
        UI.print("Enter hour (10-17): ");

        while (intUserHour < 10 || intUserHour > 17) {
            intUserHour = UI.promptInt();

            if (intUserHour < 10 || intUserHour > 17) {
                UI.println(ConsoleColors.RED + "Hour must be between 10-17" + ConsoleColors.RESET);
            }
        }
        return Integer.toString(intUserHour);
    }

    // Prompts user for minute
    // Entered minute must either 00 or 30
    public String promptMinute() {
        int intUserMinute = 1;
        UI.print("Enter minute (00 or 30): ");

        while (intUserMinute != 0 && intUserMinute != 30) {
            intUserMinute = UI.promptInt();

            if (intUserMinute != 0 && intUserMinute != 30) {
                UI.println(ConsoleColors.RED + "Minute must be 00 or 30" + ConsoleColors.RESET);
            }
        }
        return Integer.toString(intUserMinute);
    }

    // Prompts user for added product
    public String promptProduct() {
        UI.print("Enter added product: ");
        String product = UI.promptString();
        if (product.isEmpty()) {
            product = "N/A";
        }
        return product;
    }
}
