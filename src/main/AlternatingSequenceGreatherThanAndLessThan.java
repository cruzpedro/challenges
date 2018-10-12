package main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlternatingSequenceGreatherThanAndLessThan {

    public static int solution(int[] periods) {
        List<Integer> arr = Arrays.stream(periods).boxed().collect(Collectors.toList());
        List<Integer> arr2 = Arrays.stream(periods).boxed().collect(Collectors.toList());

        return Math.max(verify(arr, true), verify(arr2, false));
    }

    public static int verify(List<Integer> list, boolean isGreater) {
        int count = 0;
        while (!list.isEmpty()) {
            List<Integer> listAux = isGreater ? greaterThan(list) : lessThan(list);
            int countAux = listAux.size() + 1;
            isGreater = !isGreater;

            if(countAux > count) {
                count = countAux;
            }

            if (listAux.isEmpty()) {
                list.remove(0);
            }
        }

        return count;
    }

    public static List<Integer> greaterThan(List<Integer> periods) {
        List<Integer> listAux = new ArrayList<>();
        if (periods.size() > 1) {
            if(periods.get(0) > periods.get(1)) {
                listAux.add(periods.get(0));
                periods.remove(0);
                listAux.addAll(lessThan(periods));
            }
        }

        return listAux;
    }

    public static List<Integer> lessThan(List<Integer> periods) {
        List<Integer> listAux = new ArrayList<>();
        if (periods.size() > 1) {
            if(periods.get(0) < periods.get(1)) {
                listAux.add(periods.get(0));
                periods.remove(0);
                listAux.addAll(greaterThan(periods));
            }
        }

        return listAux;
    }

    public static void main(String[] args) {
        int[] a = {9,4,2,10,7,8,8,1,9};
        int[] b = {10,1,15,30,2,6};
        int[] c= {100};

        System.out.println(solution(a));
        System.out.println(solution(b));
        System.out.println(solution(c));
    }

}