package exercise.grep;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;


public class GrepFunctionalityTest {
    GrepFunctionality grepFunctionality = new GrepFunctionality();

    @Test
    public void itShouldReturnMatchedLines() {
        String expectedRes = "and like other forms of punctuation they are meant to make written material easy to read.”\n" +
                "you simply cannot clearly convey sequential points and their relationships to one another.";
        String res = grepFunctionality.readFromFile(Path.of("src\\test\\resources\\sample.txt"),"to");
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldWriteToFile() {
        grepFunctionality.readAndWrite(Path.of("src\\test\\resources\\sample.txt"),
                Path.of("src\\test\\resources\\output.txt"),
                "aRE");
        String expectedRes = "paragraphs are “in essence—a form of punctuation,\n" +
                "and like other forms of punctuation they are meant to make written material easy to read.”\n" +
                "Effective paragraphs are the fundamental units of academic writing;";
        String res = grepFunctionality.readFromFile(Path.of("src\\test\\resources\\output.txt"), "aRE");
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldPrintTheMatchedLinesFromAllRegularFiles() {
//        D:\Internship-tasks\www\w
        grepFunctionality.recursiveSearch(Path.of("D:\\Internship-tasks\\www\\w"),"hi");
    }

}




