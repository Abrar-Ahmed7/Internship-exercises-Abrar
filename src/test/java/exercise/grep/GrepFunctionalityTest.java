package exercise.grep;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;


public class GrepFunctionalityTest {
    GrepFunctionality grepFunctionality = new GrepFunctionality();

    @Test
    public void itShouldReturnMatchedLines() {
//        Given
        String expectedRes = "and like other forms of punctuation they are meant to make written material easy to read.”\n" +
                "you simply cannot clearly convey sequential points and their relationships to one another.";
//        When
        String res = grepFunctionality.readFromFile(Path.of("src\\test\\resources\\sample.txt"),"to");
//        Then
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldWriteToFile() {
//        Given
        String expectedRes = "paragraphs are “in essence—a form of punctuation,\n" +
            "and like other forms of punctuation they are meant to make written material easy to read.”\n" +
            "Effective paragraphs are the fundamental units of academic writing;";
//        When
        grepFunctionality.readAndWrite(Path.of("src\\test\\resources\\sample.txt"),
                Path.of("src\\test\\resources\\output.txt"),
                "aRE");
        String res = grepFunctionality.readFromFile(Path.of("src\\test\\resources\\output.txt"), "aRE");
//        Then
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldPrintTheMatchedLinesFromAllRegularFiles() {
//        D:\Internship-tasks\www\w
        grepFunctionality.recursiveSearch(Path.of("src\\resources"),"tHe");
    }

}




