package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int mealID) {
        log.info("update {} with id={}", meal, AuthorizedUser.id());
        ValidationUtil.assureIdConsistent(meal, mealID);
        service.update(meal, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAllFilteredByDates(String startDateString, String endDateForString) {
        log.info("getAllFilteredByDates");

        LocalDate startLocalDate;
        LocalDate endLocalDate;

        if (startDateString.isEmpty()) startLocalDate = LocalDate.MIN;
        else startLocalDate = LocalDate.parse(startDateString);
        if (endDateForString.isEmpty()) endLocalDate = LocalDate.MAX;
        else endLocalDate = LocalDate.parse(endDateForString);

        return MealsUtil.getWithExceeded(service.getAllFilteredByDates(AuthorizedUser.id(), startLocalDate, endLocalDate),
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}