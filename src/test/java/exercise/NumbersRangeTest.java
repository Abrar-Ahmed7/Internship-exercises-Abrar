package exercise;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class NumbersRangeTest {
    NumbersRange numbersRange = new NumbersRange();


    @Test
    public void itShouldClassifyNumbersBetweenTheRange(){
//        List<String> resList = numbersRange.inBetween(56776522,56776537);
//        Stream<String> testStream = Stream.of("odd","2","odd","even","5","even","7","even","odd","even");
//        List<String> testList = new ArrayList<>(Arrays.asList("even", "56776523", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd", "even", "odd"));
//        List<String> testList = new ArrayList<>(Arrays.asList("odd","2","odd","even","5","even","7","even","odd","even"));
//        assertThat(resStream.toArray(),is(testList));
        String[] testStringArray = {"odd", "2", "3", "even", "5", "even", "7", "even", "odd", "even"};
        Stream<String> resStream= numbersRange.inBetween(1,10);
        Assert.assertArrayEquals(testStringArray,resStream.toArray());
    }
}
