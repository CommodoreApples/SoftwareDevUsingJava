package PA4;
import java.io.*;
import java.util.*;

public class CountKeywords {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the Java file: ");
        String filename = input.nextLine();

        File file = new File(filename);

        if (file.exists()) {
            try {
                System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("The file " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        String[] keywordString = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private", "protected", "public", "return",
                "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while", "true", "false", "null"};

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inComment = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//"));
                }
                if (line.contains("/*")) {
                    inComment = true;
                    line = line.substring(0, line.indexOf("/*"));
                }
                if (line.contains("*/")) {
                    inComment = false;
                    line = line.substring(line.indexOf("*/") + 2);
                }

                if (!inComment) {
                    String[] words = line.split("\"");
                    for (int i = 0; i < words.length; i += 2) {
                        String[] tokens = words[i].split("\\s+|\\W+");
                        for (String token : tokens) {
                            if (keywordSet.contains(token)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}