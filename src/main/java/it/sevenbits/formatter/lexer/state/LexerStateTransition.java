package it.sevenbits.formatter.lexer.state;

import it.sevenbits.formatter.lexer.LexerSignal;

/**
 * Class responsible for getting next lexer state
 */
public class LexerStateTransition {
    private final LexerStateMap stateMap;

    /**
     * Default constructor for StateTransition
     */
    public LexerStateTransition() {
        stateMap = new LexerStateMap();
    }

    /**
     * Method for getting next lexer state
     *
     * @param state  - current lexer state
     * @param signal - last lexer read input
     * @return new lexer state
     */
    public LexerState getNextState(final LexerState state, final char signal) {
        return stateMap.getNext(state, new LexerSignal(signal));
    }
}
