package exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumbersRangeTest {
    NumbersRange numbersRange = new NumbersRange();


    @Test
    public void itShouldClassifyNumbersBetweenTheRange(){
        List<String> resList = numbersRange.inBetween(56776522,56776537);
        List<String> testList = new ArrayList<>(Arrays.asList("even", "56776523", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd"));
        assertThat(resList,is(testList));
    }
}
