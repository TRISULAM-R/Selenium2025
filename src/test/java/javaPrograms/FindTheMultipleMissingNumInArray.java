package javaPrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTheMultipleMissingNumInArray
{
    public static void main(String[] args) {
        int[] ar={2,10,5,9,4,5};

        // Approach -1
        int min = ar[0];
        int max = ar[1];
        for(int a : ar)
        {
            if(a>max)
            {
                max = a;
            } else if (a<min)
            {
                min = a;
            }
        }

        for(int i=min; i<=max; i++)
        {
            boolean flag = false;
            for(int j=0; j<ar.length;j++)
            {
                if(ar[j] == i)
                {
                    flag = true;
                    break;
                }
            }
            if(!flag)
            {
                System.out.println(i);
            }
        }

        // Approach - 2
        System.out.println(findMissingValuesInArray(ar));

    }

    public static List<Integer> findMissingValuesInArray(int[] ar)
    {
        Arrays.sort(ar);
        int minValue = ar[0];

        List<Integer> list = new ArrayList<>();
        for (int i=0; i<ar.length;i++)
        {
            while (minValue<ar[i])
            {
                list.add(minValue);
                minValue++;
            }
            minValue = ar[i]+1;
        }
        return list;
    }
}
