package PA4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PA1_4 {
    public static void main(String[] args) {
        String fileName;

        if (args.length == 0) {
            System.out.print("Enter the name of the Java file: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                fileName = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading the input");
                return;
            }
        } else if (args.length == 1) {
            fileName = args[0];
        } else {
            System.out.println("Usage: java PA1_4 <source-code-file>");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            Stack<Character> stack = new Stack<>();

            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            System.out.println("Error: Mismatched grouping symbol '" + ch + "' on line " + lineNumber);
                            return;
                        }
                    }
                }
                lineNumber++;
            }

            if (!stack.isEmpty()) {
                System.out.println("Error: Unmatched grouping symbol(s) found");
            } else {
                System.out.println("No errors: Correct grouping symbols");
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') || (left == '{' && right == '}') || (left == '[' && right == ']');
    }
}