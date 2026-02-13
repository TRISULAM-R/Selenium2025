package javaPrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class insertWithLogic {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(20, 30, 10, 8, 50));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a input : ");
        int input = scanner.nextInt();
        inputWithLogic(list, input);
        System.out.println(list);
    }

    public static void inputWithLogic(ArrayList<Integer> list, int input) {
        if (list.contains(input)) {
            int index = list.indexOf(input);
            list.add(index + 1, input);
        } else {
            int smallestGreater = Integer.MAX_VALUE;
            int insertIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                int current = list.get(i);
                if (current > input && current < smallestGreater) {
                    smallestGreater = current;
                    insertIndex = i;
                }
            }
            if (insertIndex != -1) {
                list.add(insertIndex, input);
            } else {
                list.add(input);
            }
        }
    }
}
