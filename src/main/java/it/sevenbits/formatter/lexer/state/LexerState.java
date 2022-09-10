package it.sevenbits.formatter.lexer.state;

import java.util.Objects;

/**
 * State class for lexer
 * contains info about current lexer state
 */
public class LexerState {
    private final String context;

    /**
     * Default constructor for state
     *
     * @param context - info about state
     */
    public LexerState(final String context) {
        this.context = context;
    }

    /**
     * Getter fot context of state
     *
     * @return string info about state
     */
    public String getContext() {
        return context;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LexerState state = (LexerState) o;
        return Objects.equals(context, state.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context);
    }
}
