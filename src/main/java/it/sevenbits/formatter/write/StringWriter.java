package it.sevenbits.formatter.write;

/**
 * Writer class
 * Writes to given StringBuilder
 */
public class StringWriter implements IWriter {
    private final StringBuilder container;

    /**
     * Default constructor for StringBuilder
     *
     * @param container which data will be written to
     */
    public StringWriter(final StringBuilder container) {
        this.container = container;
    }

    @Override
    public void write(final char symbol) {
        container.append(symbol);
    }
}
