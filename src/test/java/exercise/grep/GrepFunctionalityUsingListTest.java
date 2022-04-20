package exercise.grep;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;

public class GrepFunctionalityUsingListTest {
    GrepFunctionalityUsingList grepFunctionalityUsingList = new GrepFunctionalityUsingList();

    @Test
    public void itShouldReturnMatchedLines() {
//        Given
        List<String> expectedRes = new ArrayList<>(Arrays.asList("and like other forms of punctuation they are meant to make written material easy to read.”",
                "you simply cannot clearly convey sequential points and their relationships to one another."));
//        When
        List<String> res = new ArrayList<>(grepFunctionalityUsingList.readFromFile(Path.of("src", "test", "resources", "sample.txt"), "to"));

//        Then
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldWriteToFile() {
//        Given
        List<String> expectedRes = new ArrayList<>(Arrays.asList("paragraphs are “in essence—a form of punctuation,",
                "and like other forms of punctuation they are meant to make written material easy to read.”",
                "Effective paragraphs are the fundamental units of academic writing;"));

//        When
        grepFunctionalityUsingList.readAndWrite(Path.of("src", "test", "resources", "sample.txt"),
                Path.of("src", "test", "resources", "output.txt"),
                "aRE");
        List<String> res = grepFunctionalityUsingList.readFromFile(Path.of("src", "test", "resources", "output.txt"), "aRE");

//        Then
        Assert.assertThat(res, is(expectedRes));
    }

    @Test
    public void itShouldPrintTheMatchedLinesFromAllRegularFiles() {
//        Given
        Map<String, List<String>> expectedRes = new LinkedHashMap<>();
        expectedRes.put("src\\test\\resources\\folder1\\out1.txt",
                Arrays.asList("Sam is a cat whose owner left him astray in street.",
                        "Files in Java might be tricky but it is fun enough!",
                        "but it is fun enough!",
                        "java with 'w' (i.e., jawa) is a motorbike"));
        expectedRes.put("src\\test\\resources\\folder2\\dummyFolder\\sample1.txt",
                Arrays.asList("The development of this new system contains the following activities,",
                        "User Friendliness is provided",
                        "This can give the good security for user information because data is",
                        "not in client machine. Authentication is",
                        "provided for this application only registered users",
                        "There is no risk of data management at any level while the",
                        "project development is under process.",
                        "User can track the information regarding of his course.",
                        "Report generation features is provided using crystal reports"));

//        When
        Map<String, List<String>> res = new LinkedHashMap<>(grepFunctionalityUsingList.recursiveRead(Path.of("src", "test","resources"), "is"));

//        Then
        Assert.assertThat(res,is(expectedRes));
    }
}
