package javaPrograms;

import java.util.*;

public class OccurrenceOfVowelsInAString
{
    public static void main(String[] args)
    {

        String s = "Java is a programming language";
//        checkVowelUsingSet(s);
        checkVowelUsingMap(s);

    }

    public static void checkVowelUsingSet(String s)
    {
        Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u');
        char[] ch = s.toLowerCase().toCharArray();

        for(Character c : set)
        {
            int count=0;
            for(int j=0; j<ch.length;j++)
            {
                if(c == ch[j])
                {
                    count++;
                }
            }
            System.out.println(c +" : "+ count);
        }
    }

    public static void checkVowelUsingMap(String s)
    {
        Map<Character,Integer> map =new HashMap<>();
        char[] ch = s.toLowerCase().toCharArray();

        for(Character c : ch)
        {
            if("aeiou".indexOf(c) != -1)
            {
                map.put(c, map.getOrDefault(c,0)+1);
            }
        }
        System.out.println(map);
    }
}
