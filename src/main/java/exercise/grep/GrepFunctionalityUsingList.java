package exercise.grep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GrepFunctionalityUsingList {
    public List<String> readFromFile(Path srcFile, String searchString) {
        List<String> matchedString = new ArrayList<>();
        try(Stream<String> allLines = Files.lines(srcFile)) {
            allLines.forEach(line->{
                if (line.toUpperCase().contains(searchString.toUpperCase())) matchedString.add(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchedString;
    }

public Boolean isStringPresent(Path srcFile, String searchString) {
        try(Stream<String> allLines = Files.lines(srcFile)) {
            return (allLines.anyMatch(w -> w.toUpperCase().contains(searchString.toUpperCase())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void writeToFile(List<String> matchedLines, Path dstFile) {
//        This is for converting List of matched lines to a string to write.
        StringBuilder linesToWrite = new StringBuilder();
        for (String line : matchedLines) {
            linesToWrite.append(line).append("\n");
        }
        try{
            Files.write(dstFile, linesToWrite.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAndWrite(Path srcFile, Path dstFile, String searchString) {
        List<String> matchedLines = readFromFile(srcFile, searchString);
        writeToFile(matchedLines, dstFile);
    }

    public Map<String, List<String>> recursiveRead(Path folder, String match) {
        Map<String, List<String>> resMap = new LinkedHashMap<>();
         try (Stream<Path> allFiles = Files.walk(folder)) {
            allFiles.forEach(file -> {
                if (Files.isRegularFile(file) && (isStringPresent(file, match))) {
                        resMap.put(file.toString(),readFromFile(file, match));
                    }
                });
        }    catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    public static void main(String[] args) {
        GrepFunctionalityUsingList grepFunctionalityUsingList = new GrepFunctionalityUsingList();
        System.out.println(grepFunctionalityUsingList.recursiveRead(Path.of("src", "test","resources"),"is"));
    }
}
