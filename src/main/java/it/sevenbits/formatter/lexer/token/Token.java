package it.sevenbits.formatter.lexer.token;

/**
 * Token class for any type of token
 * Contains info about both text of the token, and it's type
 */
public class Token implements IToken {
    private final String text;
    private final String type;

    /**
     * Constructor setting text to ""
     *
     * @param type - type of creating token
     */
    public Token(final String type) {
        this.text = "";
        this.type = type;
    }

    /**
     * Constructor receiving setting text to given
     *
     * @param text - text of this token
     * @param type - type of this token
     */
    public Token(final String type, final String text) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }
}
