import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // put your code here
        int size = scanner.nextInt();
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            int value = -i;
            for (int j = 0; j < size; j++) {
                arr[i][j] = Math.abs(value++);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}