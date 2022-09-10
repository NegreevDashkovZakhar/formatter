package it.sevenbits.formatter.lexer;

/**
 * Lexer buffer class based on string builder class
 */
public class StringBuilderLexerBuffer implements ILexerBuffer {
    private final StringBuilder stringBuilder;

    /**
     * Default constructor initializing empty buffer
     */
    public StringBuilderLexerBuffer() {
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public void clear() {
        stringBuilder.setLength(0);
    }

    @Override
    public void append(final char symbol) {
        stringBuilder.append(symbol);
    }

    @Override
    public void append(final String text) {
        stringBuilder.append(text);
    }

    @Override
    public String getText() {
        return stringBuilder.toString();
    }
}
