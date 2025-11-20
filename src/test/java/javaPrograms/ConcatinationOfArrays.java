package javaPrograms;

import java.util.Arrays;

public class ConcatinationOfArrays
{
    public static void main(String[] args)
    {
        int[] ar = {1,2,1};
        //Output: [1,2,1,1,2,1]
        System.out.println(Arrays.toString(getConcatenation(ar)));
    }
    public static int[] getConcatenation(int[] nums) {
        int len = nums.length*2;
        int[] ans = new int[len];
        int j=0;
        for(int i=0; i<len; i++)
        {
            System.out.println(nums.length-1+" "+j);
            ans[i]=nums[j];
            if((nums.length-1) == j)
            {
                System.out.println("Entering in if");
                j=0;
            } else {
                j++;
            }

        }
        return ans;
    }

}
