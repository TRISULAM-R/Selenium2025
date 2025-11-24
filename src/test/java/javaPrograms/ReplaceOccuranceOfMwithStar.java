package javaPrograms;

public class ReplaceOccuranceOfMwithStar
{
    public static void main(String[] args)
    {
        String str = "aMbcdM1g2M";
        //output: a*bcd**1g2***
        System.out.println(replaceCharacterWithValue(str, 'M', "*"));

        String str2 = "tomorrow";
        //output: t$m$$rr$$$w
        System.out.println(replaceCharacterWithValue(str2, 'o', "$"));
    }

    public static String replaceCharacterWithValue(String str, char value, String replaceWith)
    {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == value)
            {
                count++;
                for (int j = 0; j < count; j++)
                {
                    builder.append(replaceWith);
                }
            } else
            {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }
}
