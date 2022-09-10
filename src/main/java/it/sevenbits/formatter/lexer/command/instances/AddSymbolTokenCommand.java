package it.sevenbits.formatter.lexer.command.instances;

import it.sevenbits.formatter.lexer.command.CommandException;
import it.sevenbits.formatter.lexer.command.ILexerCommand;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * ICommand realisation adding one symbol token
 */
public class AddSymbolTokenCommand implements ILexerCommand {
    private final Queue<IToken> tokenQueue;
    private final IToken inputToken;

    /**
     * Default constructor
     *
     * @param tokenQueue - to which token will be added
     * @param signal     - symbol defining which token will be added
     * @throws CommandException when given unknown signal
     */
    public AddSymbolTokenCommand(final Queue<IToken> tokenQueue, final char signal) throws CommandException {
        this.tokenQueue = tokenQueue;
        Map<Character, IToken> tokenMap = new HashMap<>();
        tokenMap.put(';', new Token("SEMICOLON", ";"));
        tokenMap.put('{', new Token("OPEN-CURLY-BRACE", "{"));
        tokenMap.put('}', new Token("CLOSE-CURLY-BRACE", "}"));

        if (tokenMap.containsKey(signal)) {
            inputToken = tokenMap.get(signal);
        } else {
            throw new CommandException("Unknown one symbol signal");
        }
    }

    @Override
    public void execute() {
        tokenQueue.add(inputToken);
    }
}
