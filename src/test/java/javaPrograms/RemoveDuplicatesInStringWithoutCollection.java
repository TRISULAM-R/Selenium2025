package javaPrograms;

public class RemoveDuplicatesInStringWithoutCollection
{
    public static void main(String[] args)
    {
        String s1 = "Hi java Hi Hello world Hello";

        String[] str = s1.split(" ");
        String result = "";
        for(int i=0; i<str.length;i++)
        {
            boolean flag = false;
            for(int j=0;j<i;j++)
            {
                if(str[i].equals(str[j]))
                {
                    flag = true;
                    break;
                }
            }
            if(!flag)
            {
                result= result + str[i]+" ";
            }
        }
        System.out.println(result);
    }
}
