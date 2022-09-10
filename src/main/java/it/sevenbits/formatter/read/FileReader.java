package it.sevenbits.formatter.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Reader from file source
 */
public class FileReader implements IReader, AutoCloseable {
    private final Reader reader;
    private char currentChar;
    private boolean hasNext;

    /**
     * Default constructor for FileReader
     *
     * @param file    with source code
     * @param charset of the given file
     * @throws ReaderException when file can not be read
     */
    public FileReader(final File file, final Charset charset) throws ReaderException {
        try {
            reader = new InputStreamReader(new FileInputStream(file), charset);
            updateCurrentChar();
        } catch (Exception e) {
            throw new ReaderException();
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public char read() throws ReaderEndException {
        if (!hasNext) {
            throw new ReaderEndException();
        }
        char result = currentChar;
        updateCurrentChar();
        return result;
    }

    @Override
    public void close() throws ReaderException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ReaderException();
        }
    }

    private void updateCurrentChar() {
        try {
            char[] temp = new char[1];
            if (reader.read(temp) == -1) {
                throw new Exception();
            }
            currentChar = temp[0];
            hasNext = true;
        } catch (Exception e) {
            hasNext = false;
        }
    }
}
