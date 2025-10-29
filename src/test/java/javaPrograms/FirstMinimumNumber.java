package javaPrograms;

public class FirstMinimumNumber
{
    public static void main(String[] args)
    {
        int[] arr = {2,2,5,6,8,3,5,7,8,3,7,6,-8};
        System.out.println(firstMinimumNumber(arr));
    }

    public static int firstMinimumNumber(int[] arr)
    {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int minNum = arr[0];
        for(int i=1; i<arr.length; i++)
        {
            if(minNum>arr[i])
            {
                minNum = arr[i];
            }
        }
        return minNum;
    }
}
