package it.sevenbits.formatter.lexer;

/**
 * Interface for classes collecting chars for token text field
 */
public interface ILexerBuffer {
    /**
     * Method deleting all collected symbols
     */
    void clear();

    /**
     * Method concatenating symbol to all symbol
     *
     * @param symbol - symbol to concatenate
     */
    void append(char symbol);

    /**
     * Method concatenating text to all symbol
     *
     * @param text - text to concatenate
     */
    void append(String text);

    /**
     * Method for getting saved in buffer text
     *
     * @return - saved text result
     */
    String getText();
}
