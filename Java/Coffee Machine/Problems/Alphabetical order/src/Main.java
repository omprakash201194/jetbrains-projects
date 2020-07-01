import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        List<String> array = new ArrayList<>();
        while (SCANNER.hasNext()) {
            array.add(SCANNER.next());
        }
        boolean result = true;
        for (int i = 1; i < array.size(); i++) {
            int compareResult = array.get(i).compareTo(array.get(i - 1));
            if (compareResult < 0) {
                result = false;
                break;
            }
        }
        System.out.println(result);
    }
}