package it.sevenbits.formatter.formatter.command;

import it.sevenbits.formatter.write.WriterException;

/**
 * Interface for commands changing formatter
 */
public interface IFormatterCommand {
    /**
     * Method executing command
     *
     * @throws WriterException when can not write to writer during execution
     */
    void execute() throws WriterException;
}
