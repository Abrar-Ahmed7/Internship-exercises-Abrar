package exercise.mathworks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;

public class FilterNumbersTest {
    FilterNumbers filter = new FilterNumbers();

    @Test
    public void itShouldClassifyNumbersUponGivenConditions() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> evenList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20));
        List<Integer> oddList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19));
        List<Integer> primeList = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        List<Integer> oddPrimeList = new ArrayList<>(Arrays.asList(3, 5, 7, 11, 13, 17, 19));
        List<Integer> evenAndMultipleOf5List = new ArrayList<>(Arrays.asList(10, 20));
        List<Integer> oddAndMultipleOf3GreaterThan10List = new ArrayList<>(List.of(15));
        List<Integer> evenAndLessThan15MultipleOf3List = new ArrayList<>(Arrays.asList( 6, 12));
        List<Integer> primeOrGreaterThan15MultipleOf5List = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 10, 11, 13, 15, 16, 17, 18, 19, 20 ));

        List<String> odd = List.of("odd");
        List<String> even = List.of("even");
        List<String> prime = List.of("prime");
        List<String> oddPrime = Arrays.asList("odd", "prime");
        List<String> evenAndMultipleOf5 = Arrays.asList("even", "multiple of 5");
        List<String> oddAndMultipleOf3GreaterThan10 = Arrays.asList("odd", "multiple of 3", "greater than 10");
        List<String> evenAndLessThan15MultipleOf3 = Arrays.asList("even", "less than 15", "multiple of 3");
        List<String> primeOrGreaterThan15MultipleOf5 = Arrays.asList("prime", "greater than 15", "multiple of 5");

        List<Predicate<Integer>> oddPredicate = filter.predicatesList(odd);
        List<Predicate<Integer>> evenPredicate = filter.predicatesList(even);
        List<Predicate<Integer>> primePredicate = filter.predicatesList(prime);
        List<Predicate<Integer>> oddPrimePredicate = filter.predicatesList(oddPrime);
        List<Predicate<Integer>> evenAndMultipleOf5Predicate = filter.predicatesList(evenAndMultipleOf5);
        List<Predicate<Integer>> oddAndMultipleOf3GreaterThan10Predicate = filter.predicatesList(oddAndMultipleOf3GreaterThan10);
        List<Predicate<Integer>> evenAndLessThan15MultipleOf3Predicate = filter.predicatesList(evenAndLessThan15MultipleOf3);
        List<Predicate<Integer>> primeOrGreaterThan15MultipleOf5Predicate = filter.predicatesList(primeOrGreaterThan15MultipleOf5);

        Assert.assertThat(filter.allConditions(sampleNumbers, evenPredicate, "all"), is(evenList));
        Assert.assertThat(filter.allConditions(sampleNumbers, oddPredicate,"all"), is(oddList));
        Assert.assertThat(filter.allConditions(sampleNumbers, primePredicate, "all"), is(primeList));
        Assert.assertThat(filter.allConditions(sampleNumbers, oddPrimePredicate, "all"), is(oddPrimeList));
        Assert.assertThat(filter.allConditions(sampleNumbers, evenAndMultipleOf5Predicate, "all"), is(evenAndMultipleOf5List));
        Assert.assertThat(filter.allConditions(sampleNumbers, oddAndMultipleOf3GreaterThan10Predicate,"all"), is(oddAndMultipleOf3GreaterThan10List));
        Assert.assertThat(filter.allConditions(sampleNumbers, evenAndLessThan15MultipleOf3Predicate, "all"), is(evenAndLessThan15MultipleOf3List));

        //If satisfies any conditions
        Assert.assertThat(filter.allConditions(sampleNumbers, primeOrGreaterThan15MultipleOf5Predicate, "any"), is(primeOrGreaterThan15MultipleOf5List));
    }
}
