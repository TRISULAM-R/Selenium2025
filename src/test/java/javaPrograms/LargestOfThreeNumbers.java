package javaPrograms;

public class LargestOfThreeNumbers
{
    public static void main(String[] args)
    {
        int num = 9;
        int num2 = 10;
        int num3 = 13;
        int temp = num;
        if (temp < num2)
        {
            temp = num2;
        }
        if (temp < num3)
        {
            temp = num3;
        }
        System.out.println(temp);
    }

}
