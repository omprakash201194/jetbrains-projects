import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long firstNumber = scanner.nextLong();
        String operator = scanner.next();
        long secondNumber = scanner.nextLong();
        long result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Division by 0!");
                    System.exit(0);
                }
                result = firstNumber / secondNumber;
                break;
            default:
                System.out.println("Unknown operator");
                System.exit(0);
                break;
        }
        System.out.println(result);
    }
}