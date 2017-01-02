package com.example.dalls.pregnancycalander;

/**
 * Created by Dalls on 7/26/2016.
 */
public class CalendarData {
    private String month;
    private String date_range;
    private String week;
    private String day;
    private String description;

    public CalendarData() {
    }

    //getters and setters
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getDate_range() {
        return date_range;
    }
    public void setDate_range(String date_range) {
        this.date_range = date_range;
    }
    public String getWeek  () {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
