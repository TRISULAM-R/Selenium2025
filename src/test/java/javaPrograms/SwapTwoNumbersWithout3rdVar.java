package javaPrograms;

import java.util.LinkedHashMap;
import java.util.Map;

public class SwapTwoNumbersWithout3rdVar
{
    public static void main(String[] args)
    {
        // i/p - a=10, b=20
        // o/p - a=20, b=10

        int a=10;
        int b=20;

        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println("a = "+ a+" & "+ "b = "+b);
    }
}
