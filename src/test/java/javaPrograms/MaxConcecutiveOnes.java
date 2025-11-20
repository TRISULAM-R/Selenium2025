package javaPrograms;

import java.util.Arrays;

public class MaxConcecutiveOnes
{
    public static void main(String[] args)
    {
        int[] ar = {1,0,1,1,0,1};
        //Output: 2
        System.out.println(findMaxConsecutiveOnes(ar));
    }
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }

}
