package it.sevenbits.formatter.lexer;

/**
 * Interface wrapping signal lexer received
 */
public interface ILexerSignal {
    /**
     * Method returning string describing type og signal
     *
     * @return - type of the signal
     */
    String getType();

    /**
     * Method returning char saved in signal
     *
     * @return - saved char in signal
     */
    char getSymbol();
}
