public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        RollingString res = new RollingString(pattern);
        RollingString txt = new RollingString(input, pattern.length());
        int pathash = res.hashCode();
        int txthash = txt.hashCode(pattern.length());
        if (pathash == txthash) {return 0;}
        for (int i = pattern.length(); i < input.length(); i++) {
            txthash = txt.hashCode(txthash, i, pattern.length());
            if (pathash == txthash) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

}
