import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        char[][] color = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 4; j++) {
                color[i][j] = line.charAt(j);
            }
        }
        boolean isPretty = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (color[i][j] == color[i][j + 1]) {
                    char col = color[i][j];
                    if (col == color[i + 1][j] && col == color[i + 1][j + 1]) {
                        isPretty = false;
                        break;
                    }
                }
            }
        }
        if (isPretty) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}