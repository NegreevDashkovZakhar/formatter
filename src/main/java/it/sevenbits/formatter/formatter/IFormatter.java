package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.read.IReader;
import it.sevenbits.formatter.write.IWriter;

/**
 * Interface for formatting code
 */
public interface IFormatter {
    /**
     * Method writing all data in reader to writer in formatted form
     *
     * @param reader a source of the code to format
     * @param writer a destination of formatted code
     * @throws FormatterException when can not format due to some error
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
