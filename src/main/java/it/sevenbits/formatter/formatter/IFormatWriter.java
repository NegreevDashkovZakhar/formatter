package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.write.WriterException;

/**
 * Interface for writing data according to paragraph depth
 */
public interface IFormatWriter {
    /**
     * Method for writing text to writer
     *
     * @param text - text that will be written
     * @throws WriterException when could not write text
     */
    void write(String text) throws WriterException;

    /**
     * Method for writing symbol to writer
     *
     * @param symbol - symbol to be written
     * @throws WriterException when could not write text
     */
    void write(char symbol) throws WriterException;

    /**
     * Method increasing depth of paragraph
     */
    void increaseDepth();

    /**
     * Method decreasing depth of paragraph
     */
    void decreaseDepth();

    /**
     * Method writing new paragraph to writer according to current depth
     *
     * @throws WriterException when can not write to writer
     */
    void newParagraph() throws WriterException;
}
