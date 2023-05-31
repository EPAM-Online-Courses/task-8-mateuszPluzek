package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        double weight = 69.5;
        double height = 1.72;

        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        assertFalse(recommended);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenWrongArgument() {
        double weight = 72.3;
        double height = 0.0;
        try {
            boolean recommended = FitCalculator.isBMICorrect(weight, height);
            fail("expected exception hasn't occured\n");
        }
        catch(IllegalArgumentException e) {
            ;
        }
    }

    @ParameterizedTest(name = "weight = {0}")
    @ValueSource(doubles = {90.3, 86.3, 82.4})
    void shouldReturnTrue_whenGivenValidWeights(double arg) {
        double height = 1.80;
        boolean recommended = FitCalculator.isBMICorrect(arg, height);
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1}")
    @CsvSource({
        "68.2, 1.72",
        "74.9, 1.82",
        "53.7, 1.65"
    })
    void shouldReturnFalse_whenGivenInvalidPairs(double weight, double height) {
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnTrue_whenGivenDataFromFile(double weight, double height) {
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        assertFalse(recommended);
    }
    //

    @Test
    void shouldReturnWorstUser_whenGivenUserList() {
        List<User> testList = TestConstants.TEST_USERS_LIST;

        User worstUser = FitCalculator.findUserWithTheWorstBMI(testList);

        assertEquals(worstUser.getWeight(),97.3);
        assertEquals(worstUser.getHeight(), 1.79);
    }

    @Test
    void shouldReturnNull_whenGivenEmptyUsersList() {
        List<User> testList = new ArrayList<>();

        User worstUser = FitCalculator.findUserWithTheWorstBMI(testList);

        assertNull(worstUser);
    }

    @Test
    void shouldReturnCorrectScore_whenGivenUsersList(){
        List<User> testList = TestConstants.TEST_USERS_LIST;

        double[] returnedScore = FitCalculator.calculateBMIScore(testList);

        for(int i = 0; i < returnedScore.length;i++) {
            assertTrue(returnedScore[i] ==  TestConstants.TEST_USERS_BMI_SCORE[i]);
        }
    }



}