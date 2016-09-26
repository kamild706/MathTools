package pl.n32.mathtools.Classes;

import java.util.ArrayList;

public class Numbers
{
    public static String[] getDivisorsOfNumber(long n)
    {
        StringBuilder sb = new StringBuilder();
        ArrayList<Long> divisors = new ArrayList<>();

        for (long i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sb.append(i).append(", ");
                divisors.add(i);
            }
        }

        int countOfDivisors = divisors.size() * 2;
        for (int i = divisors.size() - 1; i >= 0; i--) {
            if (divisors.get(i) == Math.sqrt(n)) {
                countOfDivisors--;
                continue;
            }
            sb.append(String.valueOf(n / divisors.get(i))).append(", ");
        }

        sb.setLength(sb.length() - 2);
        return new String[]{
                sb.toString(),
                String.valueOf(countOfDivisors)
        };
    }

    public static String[] getFactorsOfNumber(long n)
    {
        StringBuilder sb = new StringBuilder();
        int countOfFactors = 0;

        if (n == 1) {
            sb.append("1, ");
            countOfFactors = 1;
        }

        long k = 2;
        long x = (long) Math.sqrt(n);
        while (n > 1 && k <= x) {
            while (n % k == 0) {
                sb.append(k).append(", ");
                n /= k;
                countOfFactors++;
            }
            ++k;
        }
        if (n > 1) {
            sb.append(n).append(", ");
            countOfFactors++;
        }

        sb.setLength(sb.length() - 2);
        return new String[]{
                sb.toString(),
                String.valueOf(countOfFactors)
        };
    }

    public static String getMultiplesOfNumber(long n, long c)
    {
        StringBuilder sb = new StringBuilder();
        for (long i = 1; i <= c; i++)
            sb.append(String.valueOf(n * i)).append(", ");

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static long getGreatestCommonDivisor(long a, long b)
    {
        long c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    /**
     * @param n number of prime numbers to generate
     * @return String containing prime numbers
     */
    public static String getPrimeNumbers(long n)
    {
        if (n == 1) return "2";

        int status = 1;
        int num = 3;

        StringBuilder sb = new StringBuilder();
        if (n > 1) sb.append(2).append(',').append(' ');

        for (int i = 2; i <= n; ) {
            for (int j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    status = 0;
                    break;
                }
            }
            if (status != 0) {
                sb.append(num).append(',').append(' ');
                i++;
            }
            status = 1;
            num++;
        }

        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
