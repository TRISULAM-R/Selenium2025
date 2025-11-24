package javaPrograms;

public class FetchEvenIndexCharacterOfString
{
    public static void main(String[] args)
    {
        String str = "abcdefghijklmnop";
        for (int i=1; i<str.length();i+=2)
        {
            System.out.print(str.charAt(i));
        }
    }


}
