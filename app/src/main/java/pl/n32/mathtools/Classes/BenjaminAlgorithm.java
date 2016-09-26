package pl.n32.mathtools.Classes;

import java.util.HashMap;
import java.util.Map;

public class BenjaminAlgorithm
{
    private int day;
    private int month;
    private int year;

    private int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] monthOffset = new int[]{6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
    private int[] centuryOffset = new int[]{0, 5, 3, 1};

    public BenjaminAlgorithm(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement"})
    public boolean isDateValid()
    {
        if (day < 1 || day > 31 || year < 1560) return false;
        if (month < 1 || month > 12) return false;
        if (month == 2 && isYearLeap() && day > 29) return false;
        if (month == 2 && !isYearLeap() && day > 28) return false;
        if (month != 2 && day > daysInMonth[month - 1]) return false;

        return true;
    }

    public Map<String, Integer> getCalculations()
    {
        if (isYearLeap()) {
            monthOffset[0] = 5;
            monthOffset[1] = 1;
        }

        Map<String, Integer> calculations = new HashMap<>();
        calculations.put("day_value", day % 7);
        calculations.put("monthOffset_value", monthOffset[month - 1]);
        calculations.put("year1_value", year % 100 % 7);
        calculations.put("year2_value", (int) Math.floor(year % 100 / 4));
        calculations.put("centuryOffset_value", centuryOffset[year / 100 % 4]);

        int sum = 0;
        for (Map.Entry<String, Integer> entry : calculations.entrySet())
            sum += entry.getValue();

        calculations.put("sum", sum);
        calculations.put("day", day);
        calculations.put("month", month);
        calculations.put("year", year);

        return calculations;
    }

    private boolean isYearLeap()
    {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
