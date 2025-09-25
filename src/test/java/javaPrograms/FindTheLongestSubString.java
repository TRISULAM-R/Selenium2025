package javaPrograms;

import java.util.HashSet;
import java.util.Set;

public class FindTheLongestSubString
{
    public static void main(String[] args)
    {
        // i/p - a=10, b=20
        // o/p - a=20, b=10
        String s = "abcabcbb";
        findLongestUniqueSubstring(s);
    }
    public static void findLongestUniqueSubstring(String str) {
        int start = 0, maxLength = 0;
        int longestStart = 0;
        Set<Character> seen = new HashSet<>();

        for (int end = 0; end < str.length(); end++) {
            while (seen.contains(str.charAt(end))) {
                seen.remove(str.charAt(start));
                start++;
            }
            seen.add(str.charAt(end));

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                longestStart = start;
            }
        }

        String longestSubstring = str.substring(longestStart, longestStart + maxLength);
        System.out.println("Longest substring without repeating characters: " + longestSubstring);
    }
}
