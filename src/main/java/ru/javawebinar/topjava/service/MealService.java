package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    Meal create(Meal meal, int userID);

    void delete(int id, int userID) throws NotFoundException;

    Meal get(int id, int userID) throws NotFoundException;

    void update(Meal meal, int userID);

    List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate);

    List<Meal> getAll(int userID);
}