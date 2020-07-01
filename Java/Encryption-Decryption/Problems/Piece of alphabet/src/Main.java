import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // put your code here
        boolean found = false;
        int previousValue = 0;
        String input = scanner.nextLine();
        for (char c : input.toCharArray()) {
            if (previousValue == 0) {
                found = true;
            }else if ((int)c != previousValue + 1) {
                found = false;
                break;
            }
            previousValue = c;
        }
        System.out.println(found);
    }
}