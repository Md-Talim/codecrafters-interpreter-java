
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    static boolean hadError = false;

    static void error(int line, String message) {
        System.err.printf("[line %d] Error: %s\n", line, message);
        hadError = true;
    }

    private static void tokenize(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    private static void parse(List<Token> tokens) {
        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();

        System.out.println(new AstPrinter().print(expression));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: ./your_program.sh tokenize <filename>");
            System.exit(1);
        }

        String command = args[0];
        String filename = args[1];

        String fileContents = "";
        try {
            fileContents = Files.readString(Path.of(filename));
            Scanner scanner = new Scanner(fileContents);
            List<Token> tokens = scanner.scanTokens();

            switch (command) {
                case "tokenize" ->
                    tokenize(tokens);
                case "parse" -> parse(tokens);
            }

            if (hadError) {
                System.exit(65);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

    }
}
