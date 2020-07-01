import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        String[] directions = {"do not move", "move up", "move down", "move left", "move right"};
        if (choice < directions.length) {
            System.out.println(directions[choice]);
        } else {
            System.out.println("error!");
        }
    }
}