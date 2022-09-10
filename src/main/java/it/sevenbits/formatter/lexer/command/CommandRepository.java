package it.sevenbits.formatter.lexer.command;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.LexerSignal;
import it.sevenbits.formatter.lexer.state.LexerState;
import it.sevenbits.formatter.lexer.token.IToken;

import java.util.Queue;

/**
 * Class for getting new command for lexer according to lexer state and signal
 */
public class CommandRepository {
    private final LexerCommandMap commandMap;

    /**
     * Default constructor having parameters for commands
     *
     * @param tokenQueue - queue which will be changed by commands
     * @param buffer     - container for token text
     * @throws NoSuchMethodException - when there is an inner error in Lexer command map
     */
    public CommandRepository(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) throws NoSuchMethodException {
        commandMap = new LexerCommandMap(tokenQueue, buffer);
    }

    /**
     * Method for getting next command for lexer
     *
     * @param state  - current lexer state
     * @param signal - last signal for lexer
     * @return next command for lexer
     * @throws CommandException when state is unknown or command factory error occurs
     */
    public ILexerCommand getCommand(
            final LexerState state,
            final char signal) throws CommandException {
        try {
            return commandMap.getCommand(state, new LexerSignal(signal));
        } catch (Exception e) {
            throw new CommandException("Could not get new command", e);
        }
    }

    /**
     * Method for getting command transforming left in buffer text into token
     *
     * @param state - state in which text in buffer lefts
     * @return command transforming left in buffer text into token
     * @throws CommandException when state is unknown or command factory error occurs
     */
    public ILexerCommand getTerminationTokenCommand(final LexerState state) throws CommandException {
        try {
            return commandMap.getCommand(state, new LexerSignal("TERMINATION", '_'));
        } catch (Exception e) {
            throw new CommandException("Could not get new command", e);
        }
    }
}
