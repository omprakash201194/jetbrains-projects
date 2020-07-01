import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        int size = SCANNER.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = SCANNER.nextInt();
        }
        int number = SCANNER.nextInt();
        boolean found = false;
        for (int num : array) {
            if (num == number) {
                found = true;
                break;
            }
        }
        System.out.println(found);
    }
}