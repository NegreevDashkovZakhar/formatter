package it.sevenbits.formatter.formatter.state;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Class for getting next formatter state
 */
public class FormatterStateTransition {
    private final FormatterStateMap stateMap;

    /**
     * Default constructor initializing state map
     */
    public FormatterStateTransition() {
        stateMap = new FormatterStateMap();
    }

    /**
     * Method for getting next state based on given state and token
     *
     * @param state - current formatter state
     * @param token - last received token
     * @return next state for formatter
     * @throws FormatterStateTransitionException when can not get next state
     */
    public FormatterState getNext(final FormatterState state, final IToken token) throws FormatterStateTransitionException {
        try {
            return stateMap.getNext(state, token);
        } catch (Exception e) {
            throw new FormatterStateTransitionException("Could not get next state", e);
        }
    }
}
