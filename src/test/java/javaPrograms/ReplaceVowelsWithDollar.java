package javaPrograms;

public class ReplaceVowelsWithDollar
{
    public static void main(String[] args)
    {
        String str = "Java is a programming Language";
        System.out.println(str.toLowerCase().replaceAll("[aeiou]","\\$"));
    }


}
