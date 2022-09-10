package it.sevenbits.formatter.lexer.command;

import it.sevenbits.formatter.collection.Pair;
import it.sevenbits.formatter.lexer.ILexerBuffer;
import it.sevenbits.formatter.lexer.ILexerSignal;
import it.sevenbits.formatter.lexer.state.LexerState;
import it.sevenbits.formatter.lexer.token.IToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Class delegating command creation to specific method in {@link LexerCommandCreator}
 */
public class LexerCommandMap {
    private final LexerCommandCreator commandCreator;
    private final Map<Pair<LexerState, String>, Method> commandMap;

    /**
     * Default constructor delegating arguments to command creator
     *
     * @param tokenQueue - queue to which new tokens will be added
     * @param buffer     - buffer for token text
     * @throws NoSuchMethodException never
     */
    public LexerCommandMap(final Queue<IToken> tokenQueue, final ILexerBuffer buffer) throws NoSuchMethodException {
        commandCreator = new LexerCommandCreator(tokenQueue, buffer);
        commandMap = new HashMap<>();
        LexerState startState = new LexerState("START");
        LexerState textState = new LexerState("TEXT");
        LexerState stringLiteralState = new LexerState("STRING-LITERAL");
        LexerState commentSuspicionState = new LexerState("COMMENT-SUSPICION");
        LexerState oneLineCommentState = new LexerState("ONE-LINE-COMMENT");

        Class<? extends LexerCommandCreator> commandCreatorClass = commandCreator.getClass();

        Method doNothingCommandMethod = commandCreatorClass.getMethod("getDoNothingCommand", ILexerSignal.class);
        Method appendTextCommandMethod = commandCreatorClass.getMethod("getAppendTextCommand", ILexerSignal.class);
        Method symbolTokenCommandMethod = commandCreatorClass.getMethod("getSymbolTokenCommand", ILexerSignal.class);
        Method textTokenCommandMethod = commandCreatorClass.getMethod("getTextTokenCommand", ILexerSignal.class);
        Method textSymbolTokensCommandMethod = commandCreatorClass.getMethod("getTextSymbolTokensCommand", ILexerSignal.class);
        Method textTokenAndAppendCommandMethod = commandCreatorClass.getMethod("getTextTokenAndAppendCommand", ILexerSignal.class);
        Method stringLiteralCommandMethod = commandCreatorClass.getMethod("getStringLiteralCommand", ILexerSignal.class);
        Method textTokenClearAndAppendMethod = commandCreatorClass.getMethod("getTextTokenClearAndAppend", ILexerSignal.class);
        Method oneLineCommentMethod = commandCreatorClass.getMethod("getOneLineCommentToken", ILexerSignal.class);

        commandMap.put(new Pair<>(startState, "SPACE"), doNothingCommandMethod);
        commandMap.put(new Pair<>(startState, "ENTER"), doNothingCommandMethod);
        commandMap.put(new Pair<>(startState, "ONE-SYMBOL-TOKEN"), symbolTokenCommandMethod);
        commandMap.put(new Pair<>(startState, "TEXT"), appendTextCommandMethod);
        commandMap.put(new Pair<>(startState, "STRING-LITERAL"), appendTextCommandMethod);
        commandMap.put(new Pair<>(startState, "FORWARD-SLASH"), appendTextCommandMethod);

        commandMap.put(new Pair<>(textState, "SPACE"), textTokenCommandMethod);
        commandMap.put(new Pair<>(textState, "ENTER"), textTokenCommandMethod);
        commandMap.put(new Pair<>(textState, "ONE-SYMBOL-TOKEN"), textSymbolTokensCommandMethod);
        commandMap.put(new Pair<>(textState, "TEXT"), appendTextCommandMethod);
        commandMap.put(new Pair<>(textState, "STRING-LITERAL"), textTokenAndAppendCommandMethod);
        commandMap.put(new Pair<>(textState, "FORWARD-SLASH"), textTokenClearAndAppendMethod);
        commandMap.put(new Pair<>(textState, "TERMINATION"), textTokenCommandMethod);

        commandMap.put(new Pair<>(stringLiteralState, "SPACE"), appendTextCommandMethod);
        commandMap.put(new Pair<>(stringLiteralState, "ENTER"), doNothingCommandMethod);
        commandMap.put(new Pair<>(stringLiteralState, "ONE-SYMBOL-TOKEN"), appendTextCommandMethod);
        commandMap.put(new Pair<>(stringLiteralState, "TEXT"), appendTextCommandMethod);
        commandMap.put(new Pair<>(stringLiteralState, "STRING-LITERAL"), stringLiteralCommandMethod);
        commandMap.put(new Pair<>(stringLiteralState, "FORWARD-SLASH"), appendTextCommandMethod);

        commandMap.put(new Pair<>(commentSuspicionState, "SPACE"), appendTextCommandMethod);
        commandMap.put(new Pair<>(commentSuspicionState, "ENTER"), doNothingCommandMethod);
        commandMap.put(new Pair<>(commentSuspicionState, "ONE-SYMBOL-TOKEN"), appendTextCommandMethod);
        commandMap.put(new Pair<>(commentSuspicionState, "TEXT"), textTokenClearAndAppendMethod);
        commandMap.put(new Pair<>(commentSuspicionState, "STRING-LITERAL"), stringLiteralCommandMethod);
        commandMap.put(new Pair<>(commentSuspicionState, "FORWARD-SLASH"), appendTextCommandMethod);

        commandMap.put(new Pair<>(oneLineCommentState, "SPACE"), appendTextCommandMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "ENTER"), oneLineCommentMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "ONE-SYMBOL-TOKEN"), appendTextCommandMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "TEXT"), appendTextCommandMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "STRING-LITERAL"), appendTextCommandMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "FORWARD-SLASH"), appendTextCommandMethod);
        commandMap.put(new Pair<>(oneLineCommentState, "TERMINATION"), oneLineCommentMethod);
    }

    /**
     * Method creating command based on given state and signal
     *
     * @param state  - current lexer state
     * @param signal - last received signal
     * @return created command
     * @throws InvocationTargetException when command can not be created
     * @throws IllegalAccessException    never
     */
    public ILexerCommand getCommand(
            final LexerState state,
            final ILexerSignal signal) throws InvocationTargetException, IllegalAccessException {
        return (ILexerCommand) commandMap.get(new Pair<>(state, signal.getType())).invoke(commandCreator, signal);
    }
}
