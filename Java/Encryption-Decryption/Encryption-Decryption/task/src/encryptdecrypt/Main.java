package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

interface AlgorithmTool {
    void encrypt(String input, int key);
    void decrypt(String input, int key);
    void print(String output);
}

abstract class BaseAlgorithm implements AlgorithmTool {
    String out;

    public BaseAlgorithm(String out) {
        this.out = out;
    }

    @Override
    public void print(String output) {
        if (out == null) {
            System.out.println(output);
        } else {
            try(PrintWriter writer = new PrintWriter(new File(out))){
                writer.write(output);
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
            }
        }
    }
}

class ShiftAlgorithm extends BaseAlgorithm {

    public ShiftAlgorithm(String out) {
        super(out);
    }

    @Override
    public void encrypt(String input, int key) {
        char[] output = new char[input.length()];
        int index = 0;
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (Character.isLowerCase(c)) {
                    int value = c + key;
                    if (value > 123) {
                        value = (value % 123) + 97;
                    }
                    output[index++] = (char)value;
                } else {
                    int value = c + key;
                    if (value >= 90) {
                        value = (value % 90) + 65;
                    }
                    output[index++] = (char)value;
                }
            } else {
                output[index++] = c;
            }
        }
        print(new String(output));
    }

    @Override
    public void decrypt(String input, int key) {
        char[] output = new char[input.length()];
        int index = 0;
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (Character.isLowerCase(c)) {
                    int value = c - key;
                    if (value < 97) {
                        value = 123 - (97 - value);
                    }
                    output[index++] = (char) value;
                } else {
                    int value = c - key;
                    if (value < 65) {
                        value = 91 - (65 - value);
                    }
                    output[index++] = (char) value;
                }
            } else {
                output[index++] = c;
            }
        }
        print(new String(output));
    }
}
class UnicodeShiftAlgorithm extends BaseAlgorithm {

    public UnicodeShiftAlgorithm(String out) {
        super(out);
    }

    @Override
    public void encrypt(String input, int key) {
        char[] output = new char[input.length()];
        int index = 0;
        for (char c : input.toCharArray()) {
            output[index++] = (char) (c + key);
        }
        print(new String(output));
    }

    @Override
    public void decrypt(String input, int key) {
        char[] output = new char[input.length()];
        int index = 0;
        for (char c : input.toCharArray()) {
            output[index++] = (char) (c - key);
        }
        print(new String(output));
    }
}
enum ALGORITHM {
    SHIFT, UNICODE
}
public class Main {
    private static String out;
    public static void main(String[] args) {

        String operation = "enc";
        int key = 0;
        String data = "";
        String in = "";
        ALGORITHM alg = ALGORITHM.SHIFT;
        AlgorithmTool algorithmTool;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i = i+2) {
                switch (args[i]) {
                    case "-mode":
                        operation = args[i + 1];
                        break;
                    case "-key":
                        key = Integer.parseInt(args[i + 1]);
                        break;
                    case "-data":
                        data = args[i + 1];
                        break;
                    case "-in":
                        in = args[i + 1];
                        break;
                    case "-out":
                        out = args[i + 1];
                        break;
                    case "-alg":
                        alg = ALGORITHM.valueOf(args[i + 1].toUpperCase());
                        break;
                }
            }
        }
        if (data.isEmpty()) {
            try (Scanner scanner = new Scanner(new File(in))){
                StringBuilder input = new StringBuilder();
                while (scanner.hasNext()) {
                    input.append(scanner.nextLine());
                }
                data = input.toString();
            } catch (FileNotFoundException e) {
                System.out.println("Input FileNotFoundException");
            }
        }
        switch (alg) {
            case UNICODE:
                algorithmTool = new UnicodeShiftAlgorithm(out);
                break;
            case SHIFT:
            default:
                algorithmTool = new ShiftAlgorithm(out);

        }
        switch (operation) {
            case "enc":
                algorithmTool.encrypt(data, key);
                break;
            case "dec":
                algorithmTool.decrypt(data, key);
                break;
        }

    }
}
