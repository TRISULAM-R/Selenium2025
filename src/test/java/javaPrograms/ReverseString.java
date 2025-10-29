package javaPrograms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;
import java.lang.reflect.Method;



public class ReverseString
{
    public static void main(String[] args)
    {
        String s = "Most Wanted";
        System.out.println(reverseAString(s));
        System.out.println(usingReverseMethod(s));
        System.out.println(usingRecursion(s));
        System.out.println(usingArraySwapping(s));
        Arrays.stream(HashSet.class.getDeclaredMethods())
                .map(Method::getName)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }



    public static String reverseAString(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--)
        {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static String usingReverseMethod(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static String usingRecursion(String s)
    {
        if(s==null || s.isBlank() || s.length() <= 1)
        {
            return s;
        }
        return usingRecursion(s.substring(1)) + s.charAt(0);
    }

    public static String usingArraySwapping(String s)
    {
        char[] ch = s.toCharArray();
        int left =0;
        int right = ch.length -1;

        while(left<right)
        {
            char temp = ch[left];
            ch[left]=ch[right];
            ch[right]= temp;
            left ++;
            right--;
        }
        return new String(ch);
    }
}
