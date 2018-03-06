package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private int id;

    public int getId() {
        return id;
    }

    public Meal(LocalDateTime dateTime, String description, int calories, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
