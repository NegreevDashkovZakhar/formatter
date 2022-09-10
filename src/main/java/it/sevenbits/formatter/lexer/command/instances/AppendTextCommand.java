package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.command.ILexerCommand;

/**
 * ICommand realisation for adding signal to token text
 */
public class AppendTextCommand implements ILexerCommand {
    private final char textToAppend;
    private final ILexerBuffer buffer;

    /**
     * Default constructor saving context text and signal to append
     *
     * @param textToAppend - symbol that will be added to token text
     * @param buffer       - text buffer to which symbol will be appended
     */
    public AppendTextCommand(final char textToAppend, final ILexerBuffer buffer) {
        this.textToAppend = textToAppend;
        this.buffer = buffer;
    }

    @Override
    public void execute() {
        buffer.append(textToAppend);
    }
}
