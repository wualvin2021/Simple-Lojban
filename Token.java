/*
 * Defines the Token class and all possible variables.
 * Each token has a type and a value.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
    public enum TokenType {
        // Token categories for this project
        KEYWORD_I,
        SHORT_WORD,
        PREDICATE_WORD,
        NAME,
        NUMBER,
        WHITESPACE,
        UNKNOWN
    }

    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final int line;

    // Full constructor (for advanced usage)
    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    // Simplified constructor (used by Scanner)
    public Token(TokenType type, String lexeme) {
        this(type, lexeme, null, -1);
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Object getLiteral() {
        return literal;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return type + " : " + lexeme;
    }
}
