import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        String line;
        List<String[]> items = new ArrayList<>();
        do {
            line = scanner.nextLine();
            if (!"end".equals(line)) {
                items.add(line.split(" "));
            }
        } while (!"end".equals(line));

        String[][] data = items.toArray(new String[0][0]);
        int rowSize = data.length;
        int colSize = data[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int value = Integer.parseInt(data[(i - 1 + rowSize) % rowSize][j])
                        + Integer.parseInt(data[(i + 1 + rowSize) % rowSize][j])
                        + Integer.parseInt(data[i][(j - 1 + colSize) % colSize])
                        + Integer.parseInt(data[i][(j + 1 + colSize) % colSize]);
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}