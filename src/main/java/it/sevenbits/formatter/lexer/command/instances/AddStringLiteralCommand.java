package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.Queue;

/**
 * Command adding literal token to queue
 */
public class AddStringLiteralCommand implements ILexerCommand {
    private final Queue<IToken> tokenQueue;
    private final ILexerBuffer buffer;

    /**
     * Default constructor adding literal token with given text to given token queue
     *
     * @param tokenQueue - queue to which new token will be added
     * @param buffer     - text buffer of the literal token
     */
    public AddStringLiteralCommand(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) {
        this.tokenQueue = tokenQueue;
        this.buffer = buffer;
    }

    @Override
    public void execute() {
        tokenQueue.add(new Token("STRING-LITERAL", buffer.getText()));
    }
}
