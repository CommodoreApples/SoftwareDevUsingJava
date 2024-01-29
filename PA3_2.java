import java.util.Scanner;

public class PA3_2 {
    public static void main(String[] args) {
        // Test program
        try {
            try (Scanner input = new Scanner(System.in)) {
                System.out.print("Enter a binary number (composed of 0s and 1s): ");
                String binaryString = input.nextLine();

                System.out.println("Decimal equivalent: " + bin2Dec(binaryString));
            }
        } catch (BinaryFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int bin2Dec(String binaryString) {
        if (!isBinary(binaryString)) {
            throw new BinaryFormatException();
        }

        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char ch = binaryString.charAt(i);
            decimalValue = decimalValue * 2 + (ch - '0');
        }

        return decimalValue;
    }

    private static boolean isBinary(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != '0' && ch != '1') {
                return false;
            }
        }
        return true;
    }

    public static class BinaryFormatException extends RuntimeException {
        public BinaryFormatException() {
            super("Not a binary number");
        }
    }
}
