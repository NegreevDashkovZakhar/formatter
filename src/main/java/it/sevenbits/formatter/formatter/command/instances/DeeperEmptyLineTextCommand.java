package it.sevenbits.formatter.formatter.command.instances;

import it.sevenbits.formatter.formatter.IFormatWriter;
import it.sevenbits.formatter.formatter.command.IFormatterCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.write.WriterException;

/**
 * Command for writing an empty line one level deeper than current, returning to current depth adding paragraph and text
 * like this
 * {<-starts after this symbol
 * <- still have spaces here
 * }<-writes this as text
 */
public class DeeperEmptyLineTextCommand implements IFormatterCommand {
    private final IFormatWriter writer;
    private final String text;

    /**
     * Default constructor
     *
     * @param writer - format writer that will be used
     * @param token  - last received token
     */
    public DeeperEmptyLineTextCommand(final IToken token, final IFormatWriter writer) {
        this.writer = writer;
        this.text = token.getText();
    }

    /**
     * Method executing command
     */
    @Override
    public void execute() throws WriterException {
        writer.increaseDepth();
        writer.newParagraph();
        writer.decreaseDepth();
        writer.newParagraph();
        writer.write(text);
    }
}
