import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // put your code here
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        int index =0;
        int max = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (max < tokens[i].length()) {
                max = tokens[i].length();
                index = i;
            }
        }
        System.out.println(tokens[index]);
    }
}