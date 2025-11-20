package javaPrograms;

import java.util.Arrays;

public class ShuffleArray
{
    public static void main(String[] args)
    {
        int[] ar = {2,5,1,3,4,7};
        int num =3;
        System.out.println(Arrays.toString(shuffle(ar, num)));
    }
    public static int[] shuffle(int[] nums, int n) {
        int[] arr = new int[nums.length];
        int k=0;
        for(int i=0; i<nums.length; i++) {
            if(i%2==0) {
                arr[i]=nums[k];
                k++;
            } else {
                arr[i]=nums[n];
                n++;
            }
        }
        return arr;
    }
}
