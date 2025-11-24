package javaPrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveConsicutiveCharacterInString
{
    public static void main(String[] args) {

        String s ="abccba";
        for (int i=1;i<s.length();i++)
        {
            if(s.charAt(i-1) == s.charAt(i))
            {
                s= s.substring(0,i-1)+s.substring(i+1);
                i=0;
            }
        }
        if(s.isEmpty())
        {
            System.out.println("the given String is consicutive");
        } else {
            System.out.println(s);
        }
    }
}
