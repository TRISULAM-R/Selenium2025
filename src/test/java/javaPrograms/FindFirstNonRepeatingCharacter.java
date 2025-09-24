package javaPrograms;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FindFirstNonRepeatingCharacter
{
    public static void main(String[] args)
    {
        // i/p - "automation"
        // o/p - "u" -- first non repeating character

        String str = "automation";

        findFirstNonRepeatingCharacter(str);


    }

    public static void findFirstNonRepeatingCharacter(String str)
    {
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character,Integer> entryMap:map.entrySet())
        {
            System.out.println( entryMap.getKey()+" :"+entryMap.getValue());
        }
        for(char c:ch)
        {
            if(map.get(c)==1)
            {
                System.out.println("First repreating Character : "+ c);
                return;
            }
        }
        System.out.println("No repeating character found.");
    }
}
