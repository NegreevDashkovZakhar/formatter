package it.sevenbits.formatter.formatter.state;

import java.util.Objects;

/**
 * Class containing info about current formatter state
 */
public class FormatterState {
    private final String context;

    /**
     * Default constructor
     *
     * @param context - current state info
     */
    public FormatterState(final String context) {
        this.context = context;
    }

    /**
     * Method for getting state info
     *
     * @return state info
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
        FormatterState state = (FormatterState) o;
        return Objects.equals(context, state.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context);
    }
}
