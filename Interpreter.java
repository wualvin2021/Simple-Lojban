/*
 * Evaluates the parsed statements and produces outputs or actions.
 * For each statement, it determines the type and processes it accordingly.
 */

import java.util.List;

public class Interpreter {

    // Main method to interpret a list of statements
    public void interpret(List<Statement> statements) {
        for (Statement statement : statements) {
            switch (statement.getType()) {
                case DECLARATION:
                    handleDeclaration(statement);
                    break;
                case ASSERTION:
                    handleAssertion(statement);
                    break;
                case QUESTION:
                    handleQuestion(statement);
                    break;
                default:
                    reportError("Unknown statement type: " + statement);
                    break;
            }
        }
    }

    // Logic to handle declarations
    private void handleDeclaration(Statement statement) {
        System.out.println("Handling declaration: " + statement);
    }

    // Logic to handle assertions
    private void handleAssertion(Statement statement) {
        System.out.println("Handling assertion: " + statement);
    }

    // Logic to handle questions
    private void handleQuestion(Statement statement) {
        System.out.println("Handling question: " + statement);
    }

    // Error reporting method
    private void reportError(String message) {
        System.err.println("[Error] " + message);
    }
}
