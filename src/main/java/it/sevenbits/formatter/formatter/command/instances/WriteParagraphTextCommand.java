package it.sevenbits.formatter.formatter.command.instances;

import it.sevenbits.formatter.formatter.IFormatWriter;
import it.sevenbits.formatter.formatter.command.IFormatterCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.write.WriterException;

/**
 * Command writing text after paragraph with unchanged depth
 */
public class WriteParagraphTextCommand implements IFormatterCommand {
    private final String text;
    private final IFormatWriter writer;

    /**
     * Default constructor getting text from token
     *
     * @param token  - token from which text will be taken
     * @param writer - writer to which text will be written
     */
    public WriteParagraphTextCommand(final IToken token, final IFormatWriter writer) {
        this.text = token.getText();
        this.writer = writer;
    }

    /**
     * Method executing command
     */
    @Override
    public void execute() throws WriterException {
        writer.newParagraph();
        writer.write(text);
    }
}