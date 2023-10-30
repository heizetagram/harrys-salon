package harryssalon;

import java.time.LocalDateTime;

public class Appointment {
    private String customerName;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String addedProduct;
    private LocalDateTime dateTime;

    public Appointment(String customerName, String year, String month, String day, String hour, String minute, String addedProduct){
        this.customerName = customerName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.addedProduct = addedProduct;

    }
    public String getCustomerName(){
        return customerName;
    }
    public String getHour(){
        return hour;
    }
    public String getMinute() {
        return minute;
    }
    public String getYear() {
        return year;
    }
    public String getMonth() {
        return month;
    }
    public String getDay() {
        return day;
    }
    public String getAddedProduct(){
        return addedProduct;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
