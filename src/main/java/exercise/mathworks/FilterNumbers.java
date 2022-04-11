package exercise.mathworks;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterNumbers {
//    Predicate<String> numRegex = r->r.matches(".*[0-9]");
    Predicate<Integer> evenPredicate = num -> num % 2 == 0;
    Predicate<Integer> oddPredicate = evenPredicate.negate();
    Predicate<Integer> primePredicate = num-> num > 1 && IntStream
            .rangeClosed(2, (int) (Math.sqrt(num)))
            .allMatch(n -> num % n != 0);

    public  List<Predicate<Integer>> predicatesList (List<String> conditions){
        List<Predicate<Integer>> allPredicates = new ArrayList<>();
        conditions.forEach(condition-> {
            if (condition.contains("multiple of")) {
                if (condition.matches(".*[0-9].*")) {
                    String[] conditionSplit = condition.split(" ");
                    int multiplyingNumber = Integer.parseInt(conditionSplit[conditionSplit.length - 1]);
                    allPredicates.add(num -> num % multiplyingNumber == 0);
                }
            }
            else if (condition.contains("greater than")) {
                if (condition.matches(".*[0-9]")) {
                    String[] conditionSplit = condition.split(" ");
                    int lowerLimit = Integer.parseInt(conditionSplit[conditionSplit.length - 1]);
                    allPredicates.add(num -> num > lowerLimit);
                }
            }
            else if (condition.contains("less than")) {
                if (condition.matches(".*[0-9]")) {
                    String[] conditionSplit = condition.split(" ");
                    int upperLimit = Integer.parseInt(conditionSplit[conditionSplit.length - 1]);
                    allPredicates.add(num -> num < upperLimit);
                }
            }
            else if(condition.contains("odd")){
                allPredicates.add(oddPredicate);
            }
            else if (condition.contains("even")) {
                allPredicates.add(evenPredicate);
            } else if (condition.contains("prime")) {
                allPredicates.add(primePredicate);
            }
        });
        return allPredicates;
    }

    public List<Integer> allConditions(List<Integer> numbers, List<Predicate<Integer>> allPredicates, String type) {
        if (type.equals("all")) {
            return numbers.stream()
                    .filter(allPredicates.stream().reduce(x->true, Predicate::and))
                    .collect(Collectors.toList());
        }
        if (type.equals("any")) {
            return numbers.stream()
                    .filter(allPredicates.stream().reduce(x -> false, Predicate::or))
                    .collect(Collectors.toList());
        }
        return numbers;
    }

    public static void main(String[] args) {
        List<Predicate<Integer>> allConditionsPredicate;
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 50, 24, 40, 11, 22, 23, 33, 99, 100, 13, 14, 15, 16, 17, 18, 19, 20, 8, 2 );
        List<Integer> numbers = new ArrayList<>();
        FilterNumbers filterNumbers = new FilterNumbers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the numbers as comma separated values: \n Example: '1,2,3,4,5,6'");
        String[] data = scanner.nextLine().split(",");
        for (String s : data) numbers.add(Integer.parseInt(s.trim()));
        System.out.println("Enter the conditions as comma separated values: \n Example: 'multiple of 5, odd'");
        String[] conditions = scanner.nextLine().split(",");
        System.out.println(Arrays.asList(conditions));
        System.out.println("\nType 'all' for executing all conditions \n or \n Type 'or' for executing any conditions");
        String type = scanner.nextLine();
        type=type.toLowerCase();
        allConditionsPredicate = filterNumbers.predicatesList(Arrays.asList(conditions));
        System.out.println(filterNumbers.allConditions(numbers, allConditionsPredicate,"all"));
    }
}
