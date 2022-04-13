package exercise.mathworks.ocp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;

class OddCommand implements Command {
    @Override
    public boolean apply(Integer num) {
        return num % 2 != 0;
    }
}

class EvenCommand implements Command {
    @Override
    public boolean apply(Integer num) {
        return num % 2 == 0;
    }
}

class PrimeCommand implements Command {
    @Override
    public boolean apply(Integer num) {
        return num > 1 && IntStream
                .rangeClosed(2, (int) (Math.sqrt(num)))
                .allMatch(n -> num % n != 0);
    }
}

class GreaterThanX implements Command {
    private final Integer x;

    public GreaterThanX(Integer x) {
        this.x = x;
    }
    @Override
    public boolean apply(Integer num) {
        return num > x;
    }
}

class LessThanX implements Command {
    private final Integer x;

    public LessThanX(Integer x) {
        this.x = x;
    }
    @Override
    public boolean apply(Integer num) {
        return num < x;
    }
}

class MultipleOfX implements Command {
    private final Integer x;

    public MultipleOfX(Integer x) {
        this.x = x;
    }
    @Override
    public boolean apply(Integer num) {
        return num % x == 0;
    }
}

public class FilterNumbersTest {
    FilterNumbersEngine filter = new FilterNumbersEngine();

    @Test
    public void itShouldReturnEvenNumbers() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> evenList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20));

        List<Command> command = new ArrayList<>(List.of(new EvenCommand()));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(evenList));

    }

    @Test
    public void itShouldReturnOddNumbers() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> oddList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19));

        List<Command> command = new ArrayList<>(List.of(new OddCommand()));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(oddList));

    }

    @Test
    public void itShouldReturnPrimeNumbers() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> primeList = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));

        List<Command> command = new ArrayList<>(List.of(new PrimeCommand()));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(primeList));

    }

    @Test
    public void itShouldReturnOddPrimeNumbers() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> oddPrimeList = new ArrayList<>(Arrays.asList(3, 5, 7, 11, 13, 17, 19));

        List<Command> command = new ArrayList<>(List.of(new OddCommand(),new PrimeCommand()));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(oddPrimeList));

    }

    @Test
    public void itShouldReturnEvenAndMultipleOf5() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> evenAndMultipleOf5List = new ArrayList<>(Arrays.asList(10, 20));

        List<Command> command = new ArrayList<>(List.of(new EvenCommand(),new MultipleOfX(5)));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(evenAndMultipleOf5List));

    }

    @Test
    public void itShouldReturnOddAndMultipleOf3GreaterThan10() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> oddAndMultipleOf3GreaterThan10List = new ArrayList<>(List.of(15));

        List<Command> command = new ArrayList<>(List.of(new OddCommand(),new MultipleOfX(3), new GreaterThanX(10)));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(oddAndMultipleOf3GreaterThan10List));

    }

    @Test
    public void itShouldReturnEvenAndLessThan15MultipleOf3() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> evenAndLessThan15MultipleOf3List = new ArrayList<>(Arrays.asList( 6, 12));

        List<Command> command = new ArrayList<>(List.of(new EvenCommand(), new LessThanX(15), new MultipleOfX(3)));
        List<Integer> resultList = filter.filterAllMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(evenAndLessThan15MultipleOf3List));

    }

    @Test
    public void itShouldReturnPrimeOrGreaterThan15MultipleOf5() {
        List<Integer> sampleNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        List<Integer> primeOrGreaterThan15MultipleOf5List = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 10, 11, 13, 15, 16, 17, 18, 19, 20 ));

        List<Command> command = new ArrayList<>(List.of(new PrimeCommand(), new GreaterThanX(15), new MultipleOfX(5)));
        List<Integer> resultList = filter.filterAnyMatches(sampleNumbers,command);
        Assert.assertThat(resultList, is(primeOrGreaterThan15MultipleOf5List));

    }

}
