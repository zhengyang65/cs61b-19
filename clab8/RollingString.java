import java.util.Objects;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString {
    private StringBuilder strb;
    private int patlength;

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s) {
        strb = new StringBuilder(s);
        assert (strb.length() == s.length());
    }
    public RollingString(String s, int length) {
        strb = new StringBuilder(s);
        assert (strb.length() == s.length());
        patlength = length;
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        strb.substring(0);
        strb.append(c);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    @Override
    public String toString() {
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return strb.length();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        RollingString aka = (RollingString) o;
        return Objects.equals(aka.toString(), o.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        int h = 0;
        for (int i = 0; i < strb.length(); i++) {
            h = ((UNIQUECHARS * h) + strb.charAt(i)) % PRIMEBASE;
        }
        return h;
    }
    public int hashCode(int length) {
        int h = 0;
        for (int i = 0; i < length; i++) {
            h = ((UNIQUECHARS * h) + strb.charAt(i)) % PRIMEBASE;
        }
        return h;
    }
    public int hashCode(int hash, int index, int length) {
        int rm = Rm(patlength);
        int txthash = hash;
        txthash = (txthash + PRIMEBASE - rm * strb.charAt(index - length) % PRIMEBASE) % PRIMEBASE;
        txthash = (txthash * UNIQUECHARS + strb.charAt(index)) % PRIMEBASE;
        return txthash;
    }
    public int Rm(int length) {
        int rm = 1;
        for (int i = 1; i <= length - 1; i++) {
            rm = (UNIQUECHARS * rm) % PRIMEBASE;
        }
        return rm;
    }
}
