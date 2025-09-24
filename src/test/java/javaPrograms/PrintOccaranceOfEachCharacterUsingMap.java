package javaPrograms;

import java.util.LinkedHashMap;
import java.util.Map;

public class PrintOccaranceOfEachCharacterUsingMap
{
    public static void main(String[] args)
    {
        // i/p - Selenium
        // o/p - S = 1, e = 2, l = 1, n = 1, i = 1, u = 1, m = 1

        String s= "Selenium";
        printOccaranceOfEachCharacterUsingMap(s);
    }

    public static void printOccaranceOfEachCharacterUsingMap(String s)
    {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map= new LinkedHashMap<Character, Integer>();
        for(char c : ch)
        {
            map.put(c, map.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> entrySet : map.entrySet())
        {
            System.out.println(entrySet.getKey()+" = "+entrySet.getValue());
        }
    }
}
