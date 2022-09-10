package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.command.ILexerCommand;

/**
 * Class concatenating given commands array to one command
 */
public class ArrayCommand implements ILexerCommand {
    private final ILexerCommand[] commands;

    /**
     * Default constructor saving given commands array to execute
     * The first command in array will be executed first
     *
     * @param commands - commands that will be executed when array command will be executed
     */
    public ArrayCommand(final ILexerCommand[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (ILexerCommand command : commands) {
            command.execute();
        }
    }
}
