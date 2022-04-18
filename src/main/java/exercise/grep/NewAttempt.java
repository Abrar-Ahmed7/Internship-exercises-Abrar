package exercise.grep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewAttempt {

    public String readFromFile(String srcFile, String searchString) {
        String matchedString = "";
        try(Stream<String> allLines = Files
                .lines(Path.of(srcFile))) {
           matchedString = allLines.filter(line->line.contains(searchString))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchedString;
    }

    public void writeToFile(String matchedString, String dstFile) {
        try{
            Files.write(Path.of(dstFile), matchedString.getBytes(), StandardOpenOption.CREATE);
            System.out.println("Written to "+dstFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAndWrite(String srcFile, String dstFile, String searchString) {
        String matchedString = readFromFile(srcFile,searchString);
        writeToFile(matchedString, dstFile);
    }

    public List<String> allFiles(String folderPath) {
        List<String> paths = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(Path.of(folderPath))) {

          stream.filter(Files::isRegularFile)
                    .forEach(file->paths.add(String.valueOf(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    public void recursiveSearch(List<String> paths) {

        paths.forEach(path -> System.out.println((readFromFile(path,"foo"))));
    }

    public static void main(String[] args) {
        NewAttempt newAttempt = new NewAttempt();
        String res = newAttempt.readFromFile("filename.txt","foo");
        newAttempt.writeToFile(res,"out1.txt");
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
//        System.out.println(newAttempt.allFiles("D:\\Internship-tasks\\Internship-exercises-Abrar"));
        newAttempt.recursiveSearch(newAttempt.allFiles("D:\\pendrive\\New folder (2)"));
    }
}
