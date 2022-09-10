package it.sevenbits.formatter.write;

/**
 * Interface for writing code to some sort of container
 */
public interface IWriter {
    /**
     * Method adding symbol to container
     *
     * @param symbol which will be added
     * @throws WriterException when error occurs
     */
    void write(char symbol) throws WriterException;
}
