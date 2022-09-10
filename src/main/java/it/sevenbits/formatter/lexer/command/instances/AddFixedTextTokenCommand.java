package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.Queue;

/**
 * Command adding text token with text that is given in constructor
 */
public class AddFixedTextTokenCommand implements ILexerCommand {
    private final Queue<IToken> tokenQueue;
    private final String text;

    /**
     * Default constructor saving used queue and token text
     *
     * @param tokenQueue queue to which token will be added
     * @param text       for text token
     */
    public AddFixedTextTokenCommand(final Queue<IToken> tokenQueue, final String text) {
        this.tokenQueue = tokenQueue;
        this.text = text;
    }

    @Override
    public void execute() {
        IToken token = new Token("TEXT", text);
        tokenQueue.add(token);
    }
}
