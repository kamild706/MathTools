package pl.n32.mathtools.Classes;

import java.math.BigInteger;

public class Combinatorics
{
    public static String combination(long n, long k)
    {
        if (k < 0 || n < k) return "0";
        if (k == 0) return "1";

        k = Math.min(k, n - k);
        BigInteger capabilities = BigInteger.valueOf(1);
        for (long i = 1; i <= k; i++)
            capabilities = capabilities.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));

        return capabilities.toString();
    }

    public static String multicombination(long n, long k)
    {
        return combination(k + n - 1, k);
    }

    public static String permutationWithRepetitions(long n, long k)
    {
        BigInteger capabilities = BigInteger.valueOf(1);
        for (long i = 0; i < k; i++)
            capabilities = capabilities.multiply(BigInteger.valueOf(n));

        return capabilities.toString();
    }

    public static String permutation(long n, long k)
    {
        BigInteger capabilities = BigInteger.valueOf(1);
        for (long i = 0; i < k; i++)
            capabilities = capabilities.multiply(BigInteger.valueOf(n - i));

        return capabilities.toString();
    }

    public static String permutation(long n)
    {
        return permutation(n, n);
    }

    public static String risingFactorial(long n, long k)
    {
        return permutation(n + k - 1, k);
    }
}
