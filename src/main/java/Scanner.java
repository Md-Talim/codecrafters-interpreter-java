
import java.util.*;

class Scanner {

    String source;
    List<Token> tokens = new ArrayList<>();
    private int current = 0;
    private int start = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private void scanToken() {
        char c = advance();

        switch (c) {
            case '(' ->
                addToken(TokenType.LEFT_PAREN);
            case ')' ->
                addToken(TokenType.RIGHT_PAREN);
            case '{' ->
                addToken(TokenType.LEFT_BRACE);
            case '}' ->
                addToken(TokenType.RIGHT_BRACE);
            case ',' ->
                addToken(TokenType.COMMA);
            case '.' ->
                addToken(TokenType.DOT);
            case '-' ->
                addToken(TokenType.MINUS);
            case '+' ->
                addToken(TokenType.PLUS);
            case ';' ->
                addToken(TokenType.SEMICOLON);
            case '*' ->
                addToken(TokenType.STAR);
            default ->
                Main.error(line, "Unexpected character: " + c);
        }
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }
}
