package exercise.grep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrepFunctionality {

    public String readFromFile(Path srcFile, String searchString) {
        String matchedString = "";
        try (Stream<String> allLines = Files.lines(srcFile)) {
            matchedString = allLines.filter(line -> line.toUpperCase().contains(searchString.toUpperCase()))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchedString;
    }

    public void writeToFile(String matchedLines, Path dstFile) {
        try {
            Files.write(dstFile, matchedLines.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Written to " + dstFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAndWrite(Path srcFile, Path dstFile, String searchString) {
        String matchedLines = readFromFile(srcFile, searchString);
        writeToFile(matchedLines, dstFile);
    }

    public void recursiveSearch(String folderPath, String matchString) {

        try (Stream<Path> stream = Files.walk(Path.of(folderPath))) {
            stream.filter(Files::isRegularFile)
                    .map(file -> file + ": " + readFromFile(file, matchString)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GrepFunctionality grepFunctionality = new GrepFunctionality();
        String res = grepFunctionality.readFromFile(Path.of("filename.txt"), "java");
        grepFunctionality.writeToFile(res, Path.of("out2.txt"));
//        String searchString = args[0];
//        String path = args[1];
//        String argRes = newAttempt.readFromFile(path, searchString);
//        System.out.println(argRes);

//        for (int i = 0; i < args.length; i++) {
//            if(i!=0 && args[i].contains("."))
//            {
//                path.concat(args[i]);
//                continue;
//            }
//            searchString.concat(args[i]);
//        }
//        System.out.println(searchString+"\n"+path);
        grepFunctionality.recursiveSearch("D:\\Internship-tasks\\www\\w", "the");
        grepFunctionality.readAndWrite(Path.of("filename.txt"), Path.of("out1.txt"), "java");
    }
}
