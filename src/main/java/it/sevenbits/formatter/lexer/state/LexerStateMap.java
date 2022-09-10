package it.sevenbits.formatter.lexer.state;

import it.sevenbits.formatter.collection.Pair;
import it.sevenbits.formatter.lexer.ILexerSignal;

import java.util.HashMap;
import java.util.Map;

/**
 * Class wrapping lexer states map and their change logic
 */
public class LexerStateMap {
    private final Map<Pair<LexerState, String>, LexerState> stateMap;

    /**
     * Default constructor initializing map with default changing values
     */
    public LexerStateMap() {
        stateMap = new HashMap<>();
        LexerState endState = new LexerState("END");
        LexerState startState = new LexerState("START");
        LexerState textState = new LexerState("TEXT");
        LexerState stringLiteralState = new LexerState("STRING-LITERAL");
        LexerState commentSuspicionState = new LexerState("COMMENT-SUSPICION");
        LexerState oneLineCommentState = new LexerState("ONE-LINE-COMMENT");

        stateMap.put(new Pair<>(startState, "SPACE"), startState);
        stateMap.put(new Pair<>(startState, "ENTER"), startState);
        stateMap.put(new Pair<>(startState, "ONE-SYMBOL-TOKEN"), endState);
        stateMap.put(new Pair<>(startState, "TEXT"), textState);
        stateMap.put(new Pair<>(startState, "STRING-LITERAL"), stringLiteralState);
        stateMap.put(new Pair<>(startState, "FORWARD-SLASH"), commentSuspicionState);

        stateMap.put(new Pair<>(textState, "SPACE"), endState);
        stateMap.put(new Pair<>(textState, "ENTER"), endState);
        stateMap.put(new Pair<>(textState, "ONE-SYMBOL-TOKEN"), endState);
        stateMap.put(new Pair<>(textState, "TEXT"), textState);
        stateMap.put(new Pair<>(textState, "STRING-LITERAL"), stringLiteralState);
        stateMap.put(new Pair<>(textState, "FORWARD-SLASH"), commentSuspicionState);

        stateMap.put(new Pair<>(stringLiteralState, "SPACE"), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, "ENTER"), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, "ONE-SYMBOL-TOKEN"), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, "TEXT"), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, "STRING-LITERAL"), endState);
        stateMap.put(new Pair<>(stringLiteralState, "FORWARD-SLASH"), stringLiteralState);

        stateMap.put(new Pair<>(commentSuspicionState, "SPACE"), endState);
        stateMap.put(new Pair<>(commentSuspicionState, "ENTER"), endState);
        stateMap.put(new Pair<>(commentSuspicionState, "ONE-SYMBOL-TOKEN"), endState);
        stateMap.put(new Pair<>(commentSuspicionState, "TEXT"), textState);
        stateMap.put(new Pair<>(commentSuspicionState, "STRING-LITERAL"), endState);
        stateMap.put(new Pair<>(commentSuspicionState, "FORWARD-SLASH"), oneLineCommentState);

        stateMap.put(new Pair<>(oneLineCommentState, "SPACE"), oneLineCommentState);
        stateMap.put(new Pair<>(oneLineCommentState, "ENTER"), endState);
        stateMap.put(new Pair<>(oneLineCommentState, "ONE-SYMBOL-TOKEN"), oneLineCommentState);
        stateMap.put(new Pair<>(oneLineCommentState, "TEXT"), oneLineCommentState);
        stateMap.put(new Pair<>(oneLineCommentState, "STRING-LITERAL"), oneLineCommentState);
        stateMap.put(new Pair<>(oneLineCommentState, "FORWARD-SLASH"), oneLineCommentState);
    }

    /**
     * Method extracting new Lexer state from map based on state and signal
     *
     * @param state  - current lexer state
     * @param signal - last received signal
     * @return new lexer state
     */
    public LexerState getNext(final LexerState state, final ILexerSignal signal) {
        return stateMap.get(new Pair<>(state, signal.getType()));
    }
}
