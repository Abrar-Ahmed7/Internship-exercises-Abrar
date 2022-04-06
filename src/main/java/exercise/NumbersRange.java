package exercise;

import java.util.*;
import java.util.stream.IntStream;

public class NumbersRange {

    public Map<Long, String> checkingASet(long start, long end) {
        Map<Long, String> resMap = new HashMap<>();
        for (long i = start; i <= end; i++) {
            resMap.put(i, oddOrEvenOrPrime(i));
        }
        return resMap;
    }

    public List<String> inBetween(long start, long end) {
        List<String> result = new ArrayList<>();
        IntStream
                .range((int) start, (int) end + 1)
                .forEach(num -> {
                    result.add(oddOrEvenOrPrime(num));
                });
        return result;
    }

    public String oddOrEvenOrPrime(long number) {
        String res;
        if (isPrime(number)) {
            return String.valueOf(number);
        } else {
            if (number % 2 == 0) {
                return "even";
            } else {
                return "odd";
            }
        }
    }

    public boolean isPrime(long number) {

        if (number == 0 || number == 1) {
            return false;
        }
        for (long i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Long start, end;
        System.out.println("\n Start of the range:");
        start = in.nextLong();
        System.out.println("\n End of the range:");
        end = in.nextLong();
        NumbersRange c = new NumbersRange();
        System.out.println(c.inBetween(start, end));

    }
}
