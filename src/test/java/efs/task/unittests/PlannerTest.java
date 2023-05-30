package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlannerTest {
    public Planner plan;
    @BeforeEach
    public void setObject(){
        plan = new Planner();
    }
    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectValues_whenGivenEnumValues(ActivityLevel activity) {
        User user = TestConstants.TEST_USER;

        int calculatedValue = plan.calculateDailyCaloriesDemand(user, activity);

        assertTrue(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activity) == calculatedValue);
    }
    @Test
    void shouldReturnCorrectDailyIntake_whenGivenTestUser() {
        DailyIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        DailyIntake calculatedIntake = plan.calculateDailyIntake(TestConstants.TEST_USER);

        assertEquals(calculatedIntake.getCalories(), expectedIntake.getCalories());
        assertEquals(calculatedIntake.getProtein(), expectedIntake.getProtein());
        assertEquals(calculatedIntake.getFat(), expectedIntake.getFat());
        assertEquals(calculatedIntake.getCarbohydrate(), expectedIntake.getCarbohydrate());
    }



}
