package javaPrograms;

public class Palindrome
{
    public static void main(String[] args)
    {
        String s = "madam";
        System.out.println("The String is Palindrome : "+checkStringIsPalindrome(s));
        System.out.println("The String is Palindrome : "+usingStringBuilder(s));
    }

    public static boolean checkStringIsPalindrome(String s)
    {
        boolean flag = true;
        for(int i=0,j=s.length()-1; i<s.length()/2;i++,j--)
        {
            if(s.charAt(i) != s.charAt(j))
            {
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static boolean usingStringBuilder(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        return s.contentEquals(sb.reverse());
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
