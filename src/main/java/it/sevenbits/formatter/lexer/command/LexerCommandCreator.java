package it.sevenbits.formatter.lexer.command;

import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.ILexerSignal;
import it.sevenbits.formatter.lexer.command.instances.AddFixedTextTokenCommand;
import it.sevenbits.formatter.lexer.command.instances.AddOneLineCommentTokenCommand;
import it.sevenbits.formatter.lexer.command.instances.AddStringLiteralCommand;
import it.sevenbits.formatter.lexer.command.instances.AddSymbolTokenCommand;
import it.sevenbits.formatter.lexer.command.instances.AddTextTokenCommand;
import it.sevenbits.formatter.lexer.command.instances.AppendTextCommand;
import it.sevenbits.formatter.lexer.command.instances.ArrayCommand;
import it.sevenbits.formatter.lexer.command.instances.ClearBufferCommand;
import it.sevenbits.formatter.lexer.command.instances.DoNothingCommand;
import it.sevenbits.formatter.lexer.token.IToken;

import java.util.Queue;

/**
 * Class containing method creating commands for lexer command map
 */
public class LexerCommandCreator {
    private final Queue<IToken> tokenQueue;
    private final ILexerBuffer buffer;

    /**
     * Default constructor saving queue and buffer for usage in commands
     *
     * @param tokenQueue - queue to which tokens will be added
     * @param buffer     - for collecting data about token's text
     */
    public LexerCommandCreator(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) {
        this.tokenQueue = tokenQueue;
        this.buffer = buffer;
    }

    /**
     * Method creating {@link DoNothingCommand}
     *
     * @param signal - not used
     * @return {@link DoNothingCommand} instance
     */
    public ILexerCommand getDoNothingCommand(final ILexerSignal signal) {
        return new DoNothingCommand();
    }

    /**
     * Method creating {@link AppendTextCommand}
     *
     * @param signal - signal containing content to appended
     * @return {@link AppendTextCommand} instance
     */
    public ILexerCommand getAppendTextCommand(final ILexerSignal signal) {
        return new AppendTextCommand(signal.getSymbol(), buffer);
    }

    /**
     * Method creating {@link AddSymbolTokenCommand}
     *
     * @param signal - signal defining symbol token type
     * @return {@link AddSymbolTokenCommand} instance
     * @throws CommandException when can not create command
     */
    public ILexerCommand getSymbolTokenCommand(final ILexerSignal signal) throws CommandException {
        return new AddSymbolTokenCommand(tokenQueue, signal.getSymbol());
    }

    /**
     * Method creating {@link AddTextTokenCommand}
     *
     * @param signal - not used
     * @return {@link AddTextTokenCommand} instance
     */
    public ILexerCommand getTextTokenCommand(final ILexerSignal signal) {
        return new AddTextTokenCommand(tokenQueue, buffer);
    }

    /**
     * Method creating complex command adding text token and symbol token
     *
     * @param signal - signal defining symbol token type
     * @return complex command adding text token and symbol token
     * @throws CommandException when can not create command
     */
    public ILexerCommand getTextSymbolTokensCommand(final ILexerSignal signal) throws CommandException {
        ILexerCommand textCommand = new AddTextTokenCommand(tokenQueue, buffer);
        ILexerCommand symbolCommand = new AddSymbolTokenCommand(tokenQueue, signal.getSymbol());
        return new ArrayCommand(new ILexerCommand[]{textCommand, symbolCommand});
    }

    /**
     * Method creating complex command adding text token, clearing buffer and appending signal symbol to buffer
     *
     * @param signal - signal containing content which will be appended
     * @return complex command adding text token, clearing buffer and appending signal symbol to buffer
     */
    public ILexerCommand getTextTokenAndAppendCommand(final ILexerSignal signal) {
        ILexerCommand command1 = new AddTextTokenCommand(tokenQueue, buffer);
        ILexerCommand command2 = new ClearBufferCommand(buffer);
        ILexerCommand command3 = new AppendTextCommand(signal.getSymbol(), buffer);
        return new ArrayCommand(new ILexerCommand[]{command1, command2, command3});
    }

    /**
     * Method creating complex command appending signal symbol to buffer and adding string literal token
     *
     * @param signal - signal used to complete string literal token text
     * @return complex command appending signal symbol to buffer and adding string literal token
     */
    public ILexerCommand getStringLiteralCommand(final ILexerSignal signal) {
        ILexerCommand command1 = new AppendTextCommand(signal.getSymbol(), buffer);
        ILexerCommand command2 = new AddStringLiteralCommand(tokenQueue, buffer);
        return new ArrayCommand(new ILexerCommand[]{command1, command2});
    }

    /**
     * Method creating complex command saving fixed text token command, clearing buffer and appending signal symbol to buffer
     *
     * @param signal - signal symbol of which will be appended to buffer
     * @return - complex command saving fixed text token command, clearing buffer and appending signal symbol to buffer
     */
    public ILexerCommand getTextTokenClearAndAppend(final ILexerSignal signal) {
        ILexerCommand command1 = new AddFixedTextTokenCommand(tokenQueue, buffer.getText());
        ILexerCommand command2 = new ClearBufferCommand(buffer);
        ILexerCommand command3 = new AppendTextCommand(signal.getSymbol(), buffer);
        return new ArrayCommand(new ILexerCommand[]{command1, command2, command3});
    }

    /**
     * Method creating command adding one line token to queue
     *
     * @param signal - unused
     * @return command adding one line token to queue
     */
    public ILexerCommand getOneLineCommentToken(final ILexerSignal signal) {
        return new AddOneLineCommentTokenCommand(tokenQueue, buffer);
    }
}
