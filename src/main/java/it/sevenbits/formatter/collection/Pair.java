package it.sevenbits.formatter.collection;

import java.util.Objects;

/**
 * Collection class for saving two values of any type
 *
 * @param <V1> - type of the first saved value
 * @param <V2> - type of the second saved value
 */
public class Pair<V1, V2> {
    private final V1 first;
    private final V2 second;

    /**
     * Default constructor saving given first and second value to their place in pair respectively
     *
     * @param first  - first value to be saved
     * @param second - second value to be saved
     */
    public Pair(final V1 first, final V2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * First value getter
     *
     * @return first value
     */
    public V1 getFirst() {
        return first;
    }

    /**
     * Second value getter
     *
     * @return second value
     */
    public V2 getSecond() {
        return second;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
