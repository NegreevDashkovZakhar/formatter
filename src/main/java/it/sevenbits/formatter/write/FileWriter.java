package it.sevenbits.formatter.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Writer class
 * Writes into file
 */
public class FileWriter implements IWriter, AutoCloseable {
    private final BufferedWriter output;

    /**
     * Default constructor for FileWriter
     *
     * @param file    to write to
     * @param charset for file
     * @throws WriterException when IO error occurs
     */
    public FileWriter(final File file, final Charset charset) throws WriterException {
        try {
            if (file.exists()) {
                if (!file.delete()) {
                    throw new WriterException();
                }
            } else {
                //TODO: add folders creation if needed
                if (!file.createNewFile()) {
                    throw new WriterException();
                }
            }
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
        } catch (IOException e) {
            throw new WriterException("Could not create or open file");
        }
    }

    @Override
    public void write(final char symbol) throws WriterException {
        try {
            output.write(new char[]{symbol});
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    @Override
    public void close() throws WriterException {
        try {
            output.close();
        } catch (IOException e) {
            throw new WriterException();
        }
    }
}
