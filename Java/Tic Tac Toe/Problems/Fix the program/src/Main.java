import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int d = scanner.nextInt();

        int sum = a + b + d;

        System.out.println(sum);
    }
}