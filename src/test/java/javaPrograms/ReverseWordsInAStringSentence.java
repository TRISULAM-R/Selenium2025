package javaPrograms;

public class ReverseWordsInAStringSentence
{
    public static void main(String[] args)
    {
        // i/p - "I love automation"
        // o/p - "automation love I"

        String str = "I love automation";
        StringBuilder s= reverseWordsInAStringSentence(str);
        System.out.println(s);
    }

    public static StringBuilder reverseWordsInAStringSentence(String str)
    {
        String[] s = str.split("\\s+");
        StringBuilder newString = new StringBuilder();
        for(int i=s.length-1; i>=0; i--)
        {
            newString.append(s[i] +" ");
        }
        return newString;
    }
}
