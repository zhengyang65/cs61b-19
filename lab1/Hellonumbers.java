public class Hellonumbers {
    public static void main(String[] args) {
        int x = 0,sum=0;
        while (x < 10) {
            System.out.print(sum + " ");
            x = x + 1;
            sum = x + sum;
        }
    }
}