package it.sevenbits.formatter.lexer.token;

/**
 * Interface for classes providing sample of info for lexer
 */
public interface IToken {
    /**
     * Getter method for text included in token
     *
     * @return text of token
     */
    String getText();

    /**
     * Getter method for type of the token
     *
     * @return type of the token
     */
    String getType();
}
