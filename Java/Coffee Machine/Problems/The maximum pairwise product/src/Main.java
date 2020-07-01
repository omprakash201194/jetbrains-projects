import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        int size = SCANNER.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = SCANNER.nextInt();
        }
        int result = 0;
        for (int i = 1; i < size; i++) {
            int product = array[i] * array[i - 1];
            if (product > result) {
                result = product;
            }
        }
        System.out.println(result);
    }
}