
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

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private char advance() {
        return source.charAt(current++);
    }

    private boolean match(char expected) {
        if (isAtEnd()) {
            return false;
        }
        if (source.charAt(current) != expected) {
            return false;
        }

        current++;
        return true;
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }
        return source.charAt(current + 1);
    }

    private void comment() {
        while (peek() != '\n' && !isAtEnd()) {
            advance();
        }
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }
            advance();
        }

        if (isAtEnd()) {
            Main.error(line, "Unterminated string.");
            return;
        }

        // The closing ".
        advance();

        String text = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, text);
    }

    private void number() {
        while (isDigit(peek()) && !isAtEnd()) {
            advance();
        }

        // Look for a fractional part.
        if (peek() == '.' && isDigit(peekNext())) {
            // Consume the '.'
            advance();
            while (isDigit(peek())) {
                advance();
            }
        }

        String number = source.substring(start, current);
        addToken(TokenType.NUMBER, Double.valueOf(number));
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
            case '=' ->
                addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
            case '!' ->
                addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
            case '<' ->
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
            case '>' ->
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
            case '/' -> {
                if (match('/')) {
                    comment();
                } else {
                    addToken(TokenType.SLASH);
                }
            }
            case ' ', '\r', '\t' -> {
            }
            case '\n' ->
                line++;
            case '"' ->
                string();
            default -> {
                if (isDigit(c)) {
                    number();
                } else {
                    Main.error(line, "Unexpected character: " + c);
                }
            }
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
