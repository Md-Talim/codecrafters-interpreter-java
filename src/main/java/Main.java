
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    static void report(int line, String where, String message) {
        System.err.printf("[line %d] Error%s: %s\n", line, where, message); 
        hadError = true;
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
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

    private static void evaluate(List<Token> tokens) {
        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(expression);
    }

    private static void run(List<Token> tokens) {
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.getStatements();

        Interpreter interpreter = new Interpreter();
        interpreter.run(statements);
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
                case "tokenize" -> tokenize(tokens);
                case "parse" -> parse(tokens);
                case "evaluate" -> evaluate(tokens);
                case "run" -> run(tokens);
            }

            if (hadError) {
                System.exit(65);
            }

            if (hadRuntimeError) {
                System.exit(70);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

    }
}
