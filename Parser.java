/*
 * Takes the tokens produced by Scanner.java and groups them into Statement objects.
 * The parser also tries to classify the type of statement: DECLARATION, QUESTION, or ASSERTION.
 */

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Statement> parse(List<Token> tokens) {
        List<Statement> statements = new ArrayList<>();
        List<Token> currentTokens = new ArrayList<>();

        for (Token token : tokens) {
            currentTokens.add(token);

            // Check if this token ends the current statement
            if (token.getType() == Token.TokenType.PUNCTUATION &&
                    (token.getValue().equals(".") || token.getValue().equals("?") || token.getValue().equals("!"))) {

                // Determine statement type
                Statement.StatementType type = determineType(currentTokens);
                statements.add(new Statement(type, new ArrayList<>(currentTokens)));

                // Reset for next statement
                currentTokens.clear();
            }
        }

        // If there are remaining tokens without punctuation
        if (!currentTokens.isEmpty()) {
            statements.add(new Statement(Statement.StatementType.UNKNOWN, new ArrayList<>(currentTokens)));
        }

        return statements;
    }

    // Helper method to determine what kind of statement this is
    private static Statement.StatementType determineType(List<Token> tokens) {
        if (tokens.isEmpty()) {
            return Statement.StatementType.UNKNOWN;
        }

        Token first = tokens.get(0);

        // A statement ends when a sentence terminator (., ?, !) is reached.
        if (first.getValue().equalsIgnoreCase("is") || first.getValue().equalsIgnoreCase("are")) {
            return Statement.StatementType.ASSERTION;
        } else if (first.getValue().endsWith("?")) {
            return Statement.StatementType.QUESTION;
        } else if (tokens.stream().anyMatch(t -> t.getValue().equalsIgnoreCase("let") || t.getValue().equalsIgnoreCase("var"))) {
            return Statement.StatementType.DECLARATION;
        } else if (tokens.get(tokens.size() - 1).getValue().equals("?")) {
            return Statement.StatementType.QUESTION;
        } else {
            return Statement.StatementType.UNKNOWN;
        }
    }
}
