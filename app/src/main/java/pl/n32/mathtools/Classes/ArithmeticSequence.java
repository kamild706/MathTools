package pl.n32.mathtools.Classes;

import java.math.BigInteger;

public class ArithmeticSequence
{
    private long a;
    private long r;
    private long n;

    /**
     * @param a first item of series
     * @param r difference of series
     * @param n number of items in series
     */
    public ArithmeticSequence(long a, long r, long n)
    {
        this.a = a;
        this.r = r;
        this.n = n;
    }

    public String getSum()
    {
        // 2a + (n-1)r
        // -----------   * n
        //     2
        BigInteger sum = BigInteger.valueOf(2).multiply(BigInteger.valueOf(a)); // 2a
        sum = sum.add(BigInteger.valueOf(r).multiply(BigInteger.valueOf(n).subtract(BigInteger.valueOf(1)))); // 2a + r(n-1)
        sum = sum.multiply(BigInteger.valueOf(n)); // ( 2a + r(n-1) ) * n
        sum = sum.divide(BigInteger.valueOf(2)); // ( 2a + r(n-1) ) * n / 2

        return sum.toString();
    }

    public String getProduct()
    {
        BigInteger product = BigInteger.valueOf(a);
        for (long i = 1; i < n; i++)
            product = product.multiply(BigInteger.valueOf(a + i * r));

        return product.toString();
    }
}
