package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS) {
            this.save(meal, meal.getUserId());
        }
    }

    @Override
    public Meal save(Meal meal, int userID) {
        meal.setId(counter.incrementAndGet());
        meal.setUserId(userID);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userID) {
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userID && null != repository.remove(id);
    }

    @Override
    public Meal update(Meal meal, int userID) {
        if (meal != null && meal.getId()!= null &&
                repository.get(meal.getId()).getUserId() == userID) {
            meal.setUserId(userID);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public List<Meal> getAllFilteredByDates(int userID, LocalDate startDate, LocalDate endDate) {
        return getAll(userID).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    @Override
    public Meal get(int id, int userID) {
        Meal meal = repository.get(id);
        if (meal.getUserId() == userID)
            return meal;
        return null;
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userID)
                .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime)))
                .collect(Collectors.toList());
    }
}

