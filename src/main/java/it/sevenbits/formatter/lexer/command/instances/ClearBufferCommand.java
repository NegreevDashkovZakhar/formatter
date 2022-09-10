package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.command.ILexerCommand;

/**
 * Lexer command clearing buffer
 */
public class ClearBufferCommand implements ILexerCommand {
    private final ILexerBuffer buffer;

    /**
     * Default constructor saving buffer to be cleared
     *
     * @param buffer - buffer to be cleared
     */
    public ClearBufferCommand(final ILexerBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void execute() {
        buffer.clear();
    }
}
