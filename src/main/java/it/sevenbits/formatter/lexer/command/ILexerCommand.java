package it.sevenbits.formatter.lexer.command;

/**
 * Interface for command pattern
 * Used to change lexer tokens
 */
public interface ILexerCommand {
    /**
     * Method executing instructions for lexer modification
     */
    void execute();
}
