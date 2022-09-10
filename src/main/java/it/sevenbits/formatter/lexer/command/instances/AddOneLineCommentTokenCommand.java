package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.Queue;

/**
 * Command adding one line comment to token queue
 */
public class AddOneLineCommentTokenCommand implements ILexerCommand {
    private final Queue<IToken> tokenQueue;
    private final ILexerBuffer buffer;

    /**
     * Default constructor saving queue and buffer with text which will be used
     *
     * @param tokenQueue - queue to which one line comment will be added
     * @param buffer     - buffer containing text for token
     */
    public AddOneLineCommentTokenCommand(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) {
        this.tokenQueue = tokenQueue;
        this.buffer = buffer;
    }

    @Override
    public void execute() {
        IToken token = new Token("ONE-LINE-COMMENT", buffer.getText());
        tokenQueue.add(token);
    }
}
