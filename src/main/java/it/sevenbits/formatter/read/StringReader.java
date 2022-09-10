package it.sevenbits.formatter.read;

/**
 * Reader class
 * Reads from given string
 */
public class StringReader implements IReader {
    private final StringBuffer source;

    /**
     * @param source is a string from which data will be read
     */
    public StringReader(final String source) {
        this.source = new StringBuffer(source);
    }

    /**
     * @return true if source has unread code. unless returns false.
     */
    @Override
    public boolean hasNext() {
        return source.length() > 0;
    }

    /**
     * @return next symbol from source
     * AND moves the pointer to the new one
     * @throws ReaderEndException when there are no symbols to read
     */
    @Override
    public char read() throws ReaderEndException {
        if (!hasNext()) {
            throw new ReaderEndException();
        }
        char result = source.charAt(0);
        source.deleteCharAt(0);
        return result;
    }
}
