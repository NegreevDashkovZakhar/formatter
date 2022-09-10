package it.sevenbits.formatter.formatter.command;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Interface for creating lambda expressions to create commands
 */
public interface ICreateCommand {
    /**
     * Method for getting command including info from token
     *
     * @param token - token which will be represented in command
     * @return created command with content about token
     */
    IFormatterCommand run(IToken token);
}
