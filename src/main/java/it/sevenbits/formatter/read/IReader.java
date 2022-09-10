package it.sevenbits.formatter.read;

/**
 * Interface for reading code from some sort of source
 */
public interface IReader {
    /**
     * Method checks whether source has more symbols to read
     * @return true if source has unread code. unless returns false.
     */
    boolean hasNext();

    /**
     * Method reads symbol from source
     * AND moves the pointer to the new one
     *
     * @return next symbol from source
     * @throws ReaderEndException when there are no symbols to read
     */
    char read() throws ReaderEndException;
}
