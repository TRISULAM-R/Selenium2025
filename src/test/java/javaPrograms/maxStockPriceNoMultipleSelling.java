package javaPrograms;

public class maxStockPriceNoMultipleSelling
{
    public static void main(String[] args)
    {
        int[] ar = {7, 1, 5, 3, 6, 4};
        // Output: 5   - buy at 1 (Day 2) and sell it on 6 (day 5) so 1 - 6 =5.
        System.out.println(maxProfit(ar));
    }

    public static int maxProfit(int[] prices)
    {
        int maxProfit = 0, minProfit = 0;
        for (int i = 0; i < prices.length; i++)
        {
            for (int j = i + 1; j < prices.length; j++)
            {
                if (prices[i] > prices[j])
                {
                    break;
                }
                minProfit = prices[j] - prices[i];
                if (minProfit > maxProfit)
                {
                    maxProfit = minProfit;
                }
            }
        }
        return maxProfit;
    }

}
