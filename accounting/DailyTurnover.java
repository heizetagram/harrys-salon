package accounting;

import appointment.Appointment;
import date.PromptDate;
import date.SortDate;
import harryssalon.Main;
import ui.SystemMessages;

import java.time.LocalDateTime;

public class DailyTurnover {
    private Main main;
    private PromptDate promptDate;
    private SystemMessages systemMessages;
    private SortDate sortDate;
    private String stringUserYear;
    private String stringUserMonth;
    private String stringUserDay;
    LocalDateTime dateTimeNow;
    private LocalDateTime userDate;
    private boolean isInTheFuture;
    private double totalTurnover;

    // Constructor
    public DailyTurnover(Main main) {
        this.main = main;
        promptDate = new PromptDate();
        systemMessages = new SystemMessages(main);
        sortDate = new SortDate(main);
    }


    // Calculate daily turnover for appointments on a specific date
    public void calculateDailyTurnover() {
        sortDate.sortAllAppointments();

        stringUserYear = promptDate.promptYear();
        stringUserMonth = promptDate.promptMonth();
        stringUserDay = promptDate.promptDay();

        userDate = LocalDateTime.of(Integer.parseInt(stringUserYear), Integer.parseInt(stringUserMonth), Integer.parseInt(stringUserDay), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond() , LocalDateTime.now().getNano());
        dateTimeNow = LocalDateTime.now();

        totalTurnover = 0;

        for (Appointment appointment : main.getAppointments()) {
            if (appointment.getYear().equals(stringUserYear)
                    && (appointment.getMonth().equals(stringUserMonth))
                    && (appointment.getDay().equals(stringUserDay))) {
                totalTurnover += appointment.getTotalPrice();
            }
        }
        if (userDate.isAfter(dateTimeNow)) { // Checks if the userDate is after LocalDateTime now
            systemMessages.printRedColoredText("You cannot view future appointments");
        } else {
            systemMessages.printDailyTurnover(stringUserYear, stringUserMonth, stringUserDay, totalTurnover);
        }
    }
}
