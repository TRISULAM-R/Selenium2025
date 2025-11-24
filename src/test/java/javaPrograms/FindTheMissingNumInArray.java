package javaPrograms;

public class FindTheMissingNumInArray
{
    public static void main(String[] args) {
        int[] ar={1,2,3,5};
        int len = ar.length+1;
        int sum = len*(len+1)/2;

        for(int i=0; i<ar.length;i++)
        {
            sum = sum-ar[i];
        }
        System.out.println(sum);
    }
}
