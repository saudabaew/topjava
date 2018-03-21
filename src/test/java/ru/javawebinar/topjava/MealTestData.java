package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = START_SEQ + 2;
    public static final int MEAL_ID_2 = START_SEQ + 3;

    public static final Meal MEAL_1 = new Meal(MEAL_ID_1, LocalDateTime.of(2014, 4, 4, 20, 0, 0), "new food of 1 user", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_ID_2, LocalDateTime.of(2014, 4, 4, 12, 0, 0), "new food of 2 user", 900);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "datetime");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("datetime").isEqualTo(expected);
    }
}
