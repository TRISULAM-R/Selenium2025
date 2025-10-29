package javaPrograms;

import java.util.Arrays;

public class AppendZerosAtTheLast
{
    public static void main(String[] args)
    {
        int[] arr2 = {2,0,2,0,5,0,6,0,8,3,0,5};
        int[] ar = appendZerosAtTheLast(arr2);
        System.out.println(Arrays.toString(ar));

    }
    public static int[] appendZerosAtTheLast(int[] arr)
    {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int firstIndex = 0;
        int lastIndex = arr.length-1;
        int[] b = new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]!=0)
            {
                b[firstIndex] = arr[i];
                firstIndex++;
            } else {
                b[lastIndex] = arr[i];
                lastIndex--;
            }
        }
        return b;
    }
}
