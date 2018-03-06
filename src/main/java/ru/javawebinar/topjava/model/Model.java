package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Model {

    private static Model instance = new Model();

    private Map<Integer, Meal> mealMap;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        mealMap = new ConcurrentHashMap<>();
    }

    public void add(Meal meal)
    {
        mealMap.put(meal.getId(), meal);
    }

    public Map<Integer, Meal> mealMap(){
        return mealMap;
    }
}
