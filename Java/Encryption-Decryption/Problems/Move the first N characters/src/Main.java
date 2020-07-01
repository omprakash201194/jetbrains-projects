import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // put your code here
        String input = scanner.next();
        int num = scanner.nextInt();
        if (num > input.length()) {
            System.out.println(input);
        } else {
            System.out.println(input.substring(num) + input.substring(0,num));
        }
    }
}