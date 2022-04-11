package exercise.mathworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterNumbers {
    Predicate<String> numRegex = r->r.matches(".*[0-9]");
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
        List<String> conditions = Arrays.asList("odd");
        List<Predicate<Integer>> allConditionsPredicate = new ArrayList<Predicate<Integer>>();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 50, 24, 40, 11, 22, 23, 33, 99, 100, 13, 14, 15, 16, 17, 18, 19, 20, 8, 2 );
        FilterNumbers filterNumbers = new FilterNumbers();
        allConditionsPredicate = filterNumbers.predicatesList(conditions);
        System.out.println(filterNumbers.allConditions(numbers, allConditionsPredicate,"all"));
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(result);
//        String[] conditions = scanner.nextLine().split(",");
//        System.out.println(Arrays.asList(conditions));
    }
}
