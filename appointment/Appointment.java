package appointment;

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
    private String totalPrice;

    // Constructor
    public Appointment(String customerName, String year, String month, String day, String hour, String minute, String totalPrice, String addedProduct){
        this.customerName = customerName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.totalPrice = totalPrice;
        this.addedProduct = addedProduct;
    }

    // Getters
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
    public LocalDateTime getDateTime() {
        return dateTime;
    }
public String getTotalPrice() {
        return totalPrice;
}
    // Setters
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setYear(String year){
        this.year = year;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public void setDay(String day){
        this.day = day;
    }
    public void setHour(String hour){
        this.hour = hour;
    }
    public void setMinute(String minute){
        this.minute = minute;
    }
    public void setName(String name){
        this.customerName = name;
    }
    public void setAddedProduct(String addedProduct){
        this.addedProduct = addedProduct;
    }
}
