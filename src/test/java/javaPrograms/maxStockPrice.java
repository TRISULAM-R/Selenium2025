package javaPrograms;

public class maxStockPrice
{
    public static void main(String[] args)
    {
        int[] ar = {7, 1, 5, 3, 6, 4};
        System.out.println(maxStockPrice(ar));
    }

    static int maxStockPrice(int[] arr)
    {
        if (arr == null || arr.length < 2)
        {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] > arr[i - 1])
            {
                profit += arr[i] - arr[i - 1];
            }
        }
        return profit;
    }
}
