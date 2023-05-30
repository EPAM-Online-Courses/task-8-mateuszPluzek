package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {
    public Planner plan;


    @BeforeEach
    public void setObject(){
        plan = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectValues_whenGivenEnumValues(ActivityLevel activity) {
        int calculatedValue = plan.calculateDailyCaloriesDemand(TestConstants.TEST_USER, activity);

        assertTrue(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activity) == calculatedValue);
    }

    @Test
    void shouldReturnCorrectDailyIntake_whenGivenTestUser() {
        DailyIntake calculatedIntake = plan.calculateDailyIntake(TestConstants.TEST_USER);
        assertTrue(calculatedIntake.getCalories() == TestConstants.TEST_USER_DAILY_INTAKE.getCalories());
        assertTrue(calculatedIntake.getProtein() == TestConstants.TEST_USER_DAILY_INTAKE.getProtein());
        assertTrue(calculatedIntake.getFat() == TestConstants.TEST_USER_DAILY_INTAKE.getFat());
        assertTrue(calculatedIntake.getCarbohydrate() == TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate());
    }



}
