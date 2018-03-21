package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_1;
import static ru.javawebinar.topjava.MealTestData.MEAL_ID_1;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID_1, USER_ID);
        assertMatch(meal, MEAL_1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID_1, USER_ID);
        assertMatch(service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void getAlien() throws Exception {
        service.get(MEAL_ID_1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlien() throws Exception {
        service.delete(MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> mealsBetweenDates = service.getBetweenDates(LocalDate.of(2013, 4, 4),
                                                                LocalDate.of(2018, 4, 4),
                                                                USER_ID);
        assertMatch(mealsBetweenDates, MEAL_1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> mealsBetweenDates = service.getBetweenDateTimes(LocalDateTime.of(2013, 4, 4, 12, 10, 20),
                                                                    LocalDateTime.of(2017, 4, 4, 12, 10, 20),
                                                                    USER_ID);
        assertMatch(mealsBetweenDates, MEAL_1);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_1);
    }

    @Test
    public void update() throws Exception {
        Meal updateMeal = new Meal(MEAL_1);
        updateMeal.setDescription("new update-food of 1 user");
        service.update(updateMeal, USER_ID);
        assertMatch(updateMeal, service.get(MEAL_ID_1, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateAlien() throws Exception {
        Meal updateMeal = new Meal(MEAL_1);
        updateMeal.setDescription("new update-food of 1 user");
        service.update(updateMeal, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2013, 5, 6, 9, 10, 0), "new test-food of 1 user", 500);
        service.create(newMeal, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL_1, newMeal);
    }

}