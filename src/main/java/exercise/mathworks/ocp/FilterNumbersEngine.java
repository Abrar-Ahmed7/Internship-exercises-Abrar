package exercise.mathworks.ocp;


import java.util.List;
import java.util.stream.Collectors;

interface Command {
    boolean apply(Integer num);
}

public class FilterNumbersEngine {
    public List<Command> chainCommands(){
        return null;
    }

    public List<Integer> filterAllMatches(List<Integer> numbers, List<Command> commands){
        return numbers.stream()
                .filter(number->commands.stream().allMatch(command -> command.apply(number)))
                .collect(Collectors.toList());
    }
    public List<Integer> filterAnyMatches(List<Integer> numbers, List<Command> commands){
        return numbers.stream()
                .filter(number->commands.stream().anyMatch(command -> command.apply(number)))
                .collect(Collectors.toList());
    }

}
