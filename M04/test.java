package PA4;

public class test {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        
        if ((a > 5) && (b < 30)) {
            System.out.println("Both conditions are true.");
        }
        
        if ((a > 5) && (b < 30) {
            System.out.println("Missing closing parenthesis.");
        }
        
        if ((a > 5) && (b < 30)) {
            System.out.println("Nested if statement missing closing parenthesis.");
        }
        
        if (a > 5) {
            if (b < 30) {
                System.out.println("Nested if statement.");
            }
        }
    }
}

