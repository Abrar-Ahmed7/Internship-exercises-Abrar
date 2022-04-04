package exercise;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClassifyTest {
    Classify classify = new Classify();

    @Test
    public void itShouldReturnPrime(){
        assertThat(classify.oddOrEvenOrPrime(7),is("prime"));
        assertThat(classify.oddOrEvenOrPrime(37),is("prime"));
        assertThat(classify.oddOrEvenOrPrime(197),is("prime"));

    }

    @Test
    public void itShouldReturnOdd(){
        assertThat(classify.oddOrEvenOrPrime(33),is("odd"));
        assertThat(classify.oddOrEvenOrPrime(33123),is("odd"));

    }

    @Test
    public void itShouldReturnEven(){
        assertThat(classify.oddOrEvenOrPrime(48),is("even"));
        assertThat(classify.oddOrEvenOrPrime(0),is("even"));
    }

    @Test
    public void itShouldReturnMap(){
        assertThat(classify.checkingASet(1,10).get(1),is("odd"));
        assertThat(classify.checkingASet(1,10).get(2),is("prime"));
        assertThat(classify.checkingASet(1,10).get(3),is("prime"));
        assertThat(classify.checkingASet(1,10).get(4),is("even"));
        assertThat(classify.checkingASet(1,10).get(5),is("prime"));
        assertThat(classify.checkingASet(1,10).get(6),is("even"));
        assertThat(classify.checkingASet(1,10).get(7),is("prime"));
        assertThat(classify.checkingASet(1,10).get(8),is("even"));
        assertThat(classify.checkingASet(1,10).get(9),is("odd"));
        assertThat(classify.checkingASet(1,10).get(10),is("even"));
    }
}
