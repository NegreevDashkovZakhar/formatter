package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Interface for lexer
 * Lexer makes tokens from code
 */
public interface ILexer {
    /**
     * Method checks whether lexer can return any more tokens
     *
     * @return true if lexer will return new token when called getNextToken
     * otherwise return false
     */
    boolean hasNext();

    /**
     * Method building new token
     *
     * @return next token form given IReader
     * @throws LexerException when lexer can't get next token
     */
    IToken getNextToken() throws LexerException;
}
