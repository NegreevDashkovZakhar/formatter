package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.command.CommandRepository;
import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.state.LexerState;
import it.sevenbits.formatter.lexer.state.LexerStateTransition;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.read.IReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Default implementation for lexer interface
 */
public class Lexer implements ILexer {
    private final List<String> tokensLoseStates;
    private final Logger logger = LoggerFactory.getLogger(Lexer.class);
    private final Queue<IToken> tokenQueue;
    private final IReader reader;

    /**
     * Default constructor for Lexer
     *
     * @param reader from which code will be read
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        tokenQueue = new LinkedList<>();
        logger.info("Lexer was created.");
        tokensLoseStates = new ArrayList<>();
        tokensLoseStates.add("TEXT");
        tokensLoseStates.add("ONE-LINE-COMMENT");
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext() || !tokenQueue.isEmpty();
    }

    @Override
    public IToken getNextToken() throws LexerException {
        if (!tokenQueue.isEmpty()) {
            return tokenQueue.remove();
        }
        try {
            LexerState state = new LexerState("START");
            ILexerBuffer buffer = new StringBuilderLexerBuffer();
            CommandRepository commandRepository = new CommandRepository(tokenQueue, buffer);
            LexerStateTransition stateTransition = new LexerStateTransition();
            logger.info("Started token extraction");
            while (reader.hasNext()) {
                char signal = reader.read();
                ILexerCommand currentCommand = commandRepository.getCommand(state, signal);
                currentCommand.execute();
                state = stateTransition.getNextState(state, signal);
                if (state.getContext().equals("END")) {
                    logger.info("End state reached");
                    break;
                }
            }
            // if there is text at the end of reader it will not be proceeded unless we check that
            if (tokensLoseStates.contains(state.getContext())) {
                commandRepository.getTerminationTokenCommand(state).execute();
            }
            logger.info("Ended token extraction");

            if (tokenQueue.isEmpty()) {
                logger.error("Could not return token due to empty token queue");
                throw new LexerException("No token found");
            }
            logger.info("Returned token successfully");
            return tokenQueue.remove();
        } catch (Exception e) {
            logger.error("Could not return token");
            throw new LexerException("Lexer can't read next token", e);
        }
    }
}
