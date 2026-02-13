package javaPrograms;

import java.util.ArrayList;

public class RemoveDuplicatesFromArray {
    public static void main(String[] args) {
        int[] a = {4, 2, 4, 5, 2, 1, 5};
        ArrayList<Integer> ar = removeDuplicatedFromArray(a);
        System.out.println(ar);
    }

    public static ArrayList<Integer> removeDuplicatedFromArray(int[] arr) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (Integer a : arr) {
            if (!intArray.contains(a)) {
                intArray.add(a);
            }
        }
        return intArray;
    }
}
