/*
 * reads raw input string and converts it into tokens using expressions.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Scanner class to tokenize input strings
public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
            "(I)|" +                     // KEYWORD_I
            "([a-z]{1,2})|" +            // SHORT_WORD
            "([a-z]{3,})|" +             // PREDICATE_WORD
            "([A-Z][a-zA-Z]*)|" +        // NAME
            "(\\d+)|" +                  // NUMBER
            "(\\s+)|" +                  // WHITESPACE
            "(.)"                        // UNKNOWN
    );
    // Constructor
    public Scanner(String source) {
        this.source = source;
    }

    // Determine which token type matched
    private Token.TokenType matchTokenType(Matcher matcher) {
        if (matcher.group(1) != null) return Token.TokenType.KEYWORD_I;
        if (matcher.group(2) != null) return Token.TokenType.SHORT_WORD;
        if (matcher.group(3) != null) return Token.TokenType.PREDICATE_WORD;
        if (matcher.group(4) != null) return Token.TokenType.NAME;
        if (matcher.group(5) != null) return Token.TokenType.NUMBER;
        if (matcher.group(6) != null) return Token.TokenType.WHITESPACE;
        return Token.TokenType.UNKNOWN;
    }

    // Main method to scan the source and produce tokens
    public List<Token> scanTokens() {
        Matcher matcher = TOKEN_PATTERNS.matcher(source);
        while (matcher.find()) {
            Token.TokenType type = matchTokenType(matcher);
            String lexeme = matcher.group();
            tokens.add(new Token(type, lexeme));
        }
        return tokens;
    }
}