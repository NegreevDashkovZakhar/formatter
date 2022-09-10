package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.write.IWriter;
import it.sevenbits.formatter.write.WriterException;

/**
 * Default IFormatWriter realisation
 * Has four spaces as a paragraph depth measurement
 */
public class FormatWriter implements IFormatWriter {
    private static final int SPACES_PER_DEPTH = 4;
    private final IWriter writer;
    private int depth;

    /**
     * Default constructor setting depth to 0
     *
     * @param writer - writer defining destination for data
     */
    public FormatWriter(final IWriter writer) {
        this.writer = writer;
        depth = 0;
    }

    @Override
    public void write(final String text) throws WriterException {
        for (char symbol : text.toCharArray()) {
            writer.write(symbol);
        }
    }

    @Override
    public void write(final char symbol) throws WriterException {
        writer.write(symbol);
    }

    @Override
    public void increaseDepth() {
        depth++;
    }

    @Override
    public void decreaseDepth() {
        depth--;
    }

    @Override
    public void newParagraph() throws WriterException {
        writer.write('\n');
        int spaces = depth * SPACES_PER_DEPTH;
        for (int i = 0; i < spaces; i++) {
            writer.write(' ');
        }
    }
}
