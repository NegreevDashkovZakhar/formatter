package it.sevenbits.formatter.formatter.command;

import it.sevenbits.formatter.formatter.IFormatWriter;
import it.sevenbits.formatter.formatter.state.FormatterState;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Class for getting commands based on given state and last token
 */
public class FormatterCommandRepository {
    private final FormatterCommandMap commandMap;


    /**
     * Default constructor
     *
     * @param formatWriter - writer that will be used
     */
    public FormatterCommandRepository(final IFormatWriter formatWriter) {
        commandMap = new FormatterCommandMap(formatWriter);
    }

    /**
     * Method for getting commands based on given state and token
     *
     * @param state - current formatter state
     * @param token - last received token
     * @return right command based on state and token
     * @throws FormatterCommandRepositoryException when unknown state or token given
     */
    public IFormatterCommand getCommand(final FormatterState state, final IToken token) throws FormatterCommandRepositoryException {
        try {
            return commandMap.getCommand(state, token);
        } catch (Exception e) {
            throw new FormatterCommandRepositoryException("Can not get command", e);
        }
    }
}
