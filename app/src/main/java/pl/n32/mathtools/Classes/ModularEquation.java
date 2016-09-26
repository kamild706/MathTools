package pl.n32.mathtools.Classes;

import java.util.ArrayList;
import java.util.List;

public class ModularEquation
{
    private long a;
    private long b;
    private long c;

    public ModularEquation(long a, long b, long c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /*
    * ax = b (mod c)
    * Looking for two smallest numbers x satisfying the equation ax-b = 0 (mod c)
    * Having two solutions m & n determine formula for all solutions
    *
    * n - m is difference between next solutions, m is the smallest solution,
    * hence if ax = b (mod c), x = (n-m)t + m
    * with t belonging to integer numbers
    */

    public String[] solveEquation()
    {
        StringBuilder sb = new StringBuilder();
        List<Integer> vals = new ArrayList<>();
        int i = 0;

        sb.append((a > 1) ? a : "").append("x \u2261 ").append(b).append(" (mod ").append(c).append(")\n");

        if ((a == c && b != 0 && b != c) || c == 0)
            return new String[]{"0", sb.toString()};

        a %= c;
        b %= c;

        while (vals.size() != 2)
        {
            if (i > c * 2) return new String[]{"0", sb.toString()};

            if ((a * i - b) % c == 0) vals.add(i);
            i++;
        }

        if (vals.size() == 2)
            sb.append("x \u2261 ").append(vals.get(0)).append(" (mod ").append(vals.get(1) - vals.get(0)).append(")\n").
                    append("x = ").append(vals.get(1) - vals.get(0)).append("t ").append(vals.get(0) > 0 ? "+ " + vals.get(0) : "").append("\n").
                    append("x, t \u2208 \u2124");

        return new String[] {"1", sb.toString()};
    }
}
