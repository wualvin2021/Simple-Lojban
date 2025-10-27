/*
 * Groups tokens into logical statements.
 */

import java.util.ArrayList;
import java.util.List;

// Statement class to represent a group of tokens as a statement
public class Statement {
    public enum StatementType {
        DECLARATION,
        ASSERTION,
        QUESTION,
        UNKNOWN
    }

    // Fields
    private final StatementType type;
    private final List<Token> tokens;

    // Constructor
    public Statement(StatementType type, List<Token> tokens) {
        this.type = type;
        this.tokens = tokens;
    }

    public StatementType getType() {
        return type;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    // String representation of the statement
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(" : ");
        for (Token token : tokens) {
            sb.append(token.getLexeme()).append(" ");
        }
        return sb.toString().trim();
    }

    // Helper method to create statements from a token list
    public static List<Statement> fromTokens(List<Token> tokens) {
        List<Statement> statements = new ArrayList<>();
        List<Token> current = new ArrayList<>();

        for (Token token : tokens) {
            if (token.getType() == Token.TokenType.KEYWORD_I) {
                // Save the previous statement if not empty
                if (!current.isEmpty()) {
                    statements.add(new Statement(StatementType.UNKNOWN, current));
                }
                current = new ArrayList<>();
            }
            current.add(token);
        }

        // Add the final statement if any tokens remain
        if (!current.isEmpty()) {
            statements.add(new Statement(StatementType.UNKNOWN, current));
        }

        return statements;
    }
}

