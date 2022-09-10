package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.Queue;

/**
 * ICommand realisation adding text token to queue
 */
public class AddTextTokenCommand implements ILexerCommand {
    private final Queue<IToken> tokenQueue;
    private final ILexerBuffer buffer;

    /**
     * Default constructor
     *
     * @param tokenQueue - queue to which will be added token
     * @param buffer     - text buffer for token
     */
    public AddTextTokenCommand(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) {
        this.tokenQueue = tokenQueue;
        this.buffer = buffer;
    }

    @Override
    public void execute() {
        tokenQueue.add(new Token("TEXT", buffer.getText()));
    }
}
