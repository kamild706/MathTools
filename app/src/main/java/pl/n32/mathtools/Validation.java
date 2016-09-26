package pl.n32.mathtools;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Validation
{
    public static boolean isInteger(String s)
    {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String s)
    {
        try {
            Long.parseLong(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
