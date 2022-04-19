package exercise.grep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
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

    public void writeToFile(List<String> matchedLines, Path dstFile) {
//        This is for converting List of matched lines to a string to write.
        StringBuilder linesToWrite = new StringBuilder();
        for (String line : matchedLines) {
            linesToWrite.append(line).append("\n");
        }
        try{
            Files.write(dstFile, linesToWrite.toString().getBytes(), StandardOpenOption.CREATE);
            System.out.println("Written to "+dstFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recursiveRead(Path folder, String match) {
         try (Stream<Path> allFiles = Files.walk(folder)) {
            allFiles.forEach(file -> {
                if (Files.isRegularFile(file)) {
                    List<String> matchedLines = readFromFile(file, match);
                    for (String line : matchedLines) {
                        System.out.println(file + ": " + line);
                    }
                }
            });
        }    catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GrepFunctionalityUsingList grepFunctionalityUsingList = new GrepFunctionalityUsingList();
        grepFunctionalityUsingList.recursiveRead(Path.of("D:\\Internship-tasks\\www\\w"),"hi");

    }
}
