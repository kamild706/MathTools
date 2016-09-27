package pl.n32.mathtools.Classes;

import android.util.Log;

import java.util.Arrays;

public class NumberBaseConverter {
    private final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String number;
    private int base;

    public NumberBaseConverter(String number, int base) throws Exception {
        validateBase(base);
        this.base = base;

        number = number.toUpperCase();
        validateNumber(number);
        this.number = number;
    }

    private void validateBase(int base) throws Exception {
        if (!isInRange(base, 2, this.alphabet.length())) {
            throw new Exception("Base is not in supported range from 2 to " + alphabet.length());
        }
    }

    private void validateNumber(String number) throws Exception {
        String[] characters = number.split("");
        for (String c : characters) {
            if (!isInRange(this.alphabet.indexOf(c), 0, this.base - 1)) {
                throw new Exception(String.format("The number is not written in %d based system", this.base));
            }
        }
    }

    private boolean isInRange(int value, int a, int b) {
        return value >= a && value <= b;

    }

    public String convertToBase(int newBase) throws Exception {
        validateBase(newBase);

        String result = this.getRequestedRepresentation(this.getDecimalRepresentation(), newBase);
        this.base = newBase;

        return result;
    }

    private long getDecimalRepresentation() {
        String[] characters = new StringBuilder(this.number).reverse().toString().split("");
        Log.d("123232333", new StringBuilder(this.number).reverse().toString());
        Log.d("12323233666663", String.valueOf(new StringBuilder(this.number).reverse().toString().length()));
        Log.d("DDDDDDDhhhhhhhhh", Arrays.toString(characters));
        long decimalRepresentation = 0;
        int exponent = 0;

        for (String c : characters) {
            if (c.equals("")) continue;

            decimalRepresentation += this.alphabet.indexOf(c) * Math.pow(this.base, exponent);
            exponent++;
        }
        return decimalRepresentation;
    }

    private String getRequestedRepresentation(long decimal_representation, int newBase) {
        if (decimal_representation == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (decimal_representation >= 1) {
            sb.append(this.alphabet.charAt((int) (decimal_representation % newBase)));
            decimal_representation /= newBase;
        }

        return sb.reverse().toString();
    }
}
