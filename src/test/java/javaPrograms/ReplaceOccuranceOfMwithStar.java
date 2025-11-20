package javaPrograms;

public class ReplaceOccuranceOfMwithStar
{
    public static void main(String[] args)
    {
        String str = "aMbcdM1g2M";
        //output: a*bcd**1g2***
        System.out.println(replaceMWithStar(str));
    }
    public static String replaceMWithStar(String str) {

        int count=0;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i) == 'M')
            {
                count++;
                for(int j=0; j<count; j++)
                {
                    builder.append("*");
                }
            } else {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }

}
