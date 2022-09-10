package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.command.FormatterCommandRepository;
import it.sevenbits.formatter.formatter.command.FormatterCommandRepositoryException;
import it.sevenbits.formatter.formatter.command.IFormatterCommand;
import it.sevenbits.formatter.formatter.state.FormatterState;
import it.sevenbits.formatter.formatter.state.FormatterStateTransition;
import it.sevenbits.formatter.formatter.state.FormatterStateTransitionException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.read.IReader;
import it.sevenbits.formatter.write.IWriter;
import it.sevenbits.formatter.write.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for formatting java code
 */
public class Formatter implements IFormatter {
    private final Logger logger = LoggerFactory.getLogger(Formatter.class);

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        logger.info("Started formation");
        IFormatWriter formatWriter = new FormatWriter(writer);
        FormatterCommandRepository commandRepository = new FormatterCommandRepository(formatWriter);
        FormatterState state = new FormatterState("START");
        FormatterStateTransition stateTransition = new FormatterStateTransition();

        ILexer lexer = new Lexer(reader);
        while (lexer.hasNext()) {
            try {
                IToken currentToken = lexer.getNextToken();
                IFormatterCommand command = commandRepository.getCommand(state, currentToken);
                command.execute();
                state = stateTransition.getNext(state, currentToken);
            } catch (LexerException e) {
                logger.warn("Lexer exception occurred, so process was terminated");
                break;
            } catch (FormatterStateTransitionException e) {
                logger.error("Could not change state");
                throw new FormatterException("Could not format source", e);
            } catch (WriterException e) {
                logger.error("Command could not write data");
                throw new FormatterException("Could not write data", e);
            } catch (FormatterCommandRepositoryException e) {
                logger.error("Command repository could not return new command");
                throw new FormatterException("Could not get next command", e);
            }
        }
        logger.info("Ended formation");
    }
}
