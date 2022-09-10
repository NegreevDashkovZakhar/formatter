package it.sevenbits.formatter.formatter.command;

import it.sevenbits.formatter.formatter.IFormatWriter;
import it.sevenbits.formatter.formatter.command.instances.DecreaseParagraphTextCommand;
import it.sevenbits.formatter.formatter.command.instances.DeeperEmptyLineTextCommand;
import it.sevenbits.formatter.formatter.command.instances.IncreaseParagraphTextCommand;
import it.sevenbits.formatter.formatter.command.instances.WriteParagraphTextCommand;
import it.sevenbits.formatter.formatter.command.instances.WriteSpaceTextCommand;
import it.sevenbits.formatter.formatter.command.instances.WriteTextCommand;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Class containing methods creating command creators for formatter
 */
public class FormatterCommandCreatorRepository {
    private final IFormatWriter formatWriter;

    /**
     * Default constructor saving format writer which will be used
     *
     * @param formatWriter - format writer for writing tokens content
     */
    public FormatterCommandCreatorRepository(final IFormatWriter formatWriter) {
        this.formatWriter = formatWriter;
    }

    /**
     * Method for getting creator of command decreasing paragraph depth and writing text in new line
     *
     * @return creator of command decreasing paragraph depth and writing text in new line
     */
    public ICreateCommand getDecreaseParagraphCommandCreator() {
        return (final IToken token) -> new DecreaseParagraphTextCommand(token, formatWriter);
    }

    /**
     * Method for getting creator of command decreasing paragraph depth ,writing empty new line, increasing paragraph depth
     * and writing text in new line
     *
     * @return creator of command decreasing paragraph depth ,writing empty new line, increasing paragraph depth
     * and writing text in new line
     */
    public ICreateCommand getDeeperEmptyLineTextCommandCreator() {
        return (final IToken token) -> new DeeperEmptyLineTextCommand(token, formatWriter);
    }

    /**
     * Method for getting creator of command increasing paragraph depth and writing text in new line
     *
     * @return creator of command increasing paragraph depth and writing text in new line
     */
    public ICreateCommand getIncreaseParagraphTextCommandCreator() {
        return (final IToken token) -> new IncreaseParagraphTextCommand(token, formatWriter);
    }

    /**
     * Method for getting creator of command writing text in new line
     *
     * @return creator of command writing text in new line
     */
    public ICreateCommand getWriteParagraphTextCommandCreator() {
        return (final IToken token) -> new WriteParagraphTextCommand(token, formatWriter);
    }

    /**
     * Method for getting creator of command writing text after single space
     *
     * @return creator of command writing text after single space
     */
    public ICreateCommand getSpaceTextCommandCreator() {
        return (final IToken token) -> new WriteSpaceTextCommand(token, formatWriter);
    }

    /**
     * Method for getting creator of command writing text just after previous written text
     *
     * @return creator of command writing text just after previous written text
     */
    public ICreateCommand getWriteTextCommandCreator() {
        return (final IToken token) -> new WriteTextCommand(token, formatWriter);
    }
}
