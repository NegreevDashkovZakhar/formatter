package it.sevenbits.formatter.lexer;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic class providing signal functionality
 * Used to simplify State machine logic
 */
public class LexerSignal implements ILexerSignal {
    private final String type;
    private final char symbol;

    /**
     * Constructor initializing type from given type, not from given symbol
     *
     * @param type   - type of signal
     * @param symbol - symbol saved in signal
     */
    public LexerSignal(final String type, final char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    /**
     * Default constructor saving symbol and getting type according to given symbol
     *
     * @param symbol - signal content
     */
    public LexerSignal(final char symbol) {
        Map<Character, String> typeSymbolMap = new HashMap<>();

        String defaultType = "TEXT";
        typeSymbolMap.put(' ', "SPACE");
        typeSymbolMap.put('\n', "ENTER");
        typeSymbolMap.put('\t', "SPACE");
        typeSymbolMap.put(';', "ONE-SYMBOL-TOKEN");
        typeSymbolMap.put('{', "ONE-SYMBOL-TOKEN");
        typeSymbolMap.put('}', "ONE-SYMBOL-TOKEN");
        typeSymbolMap.put('"', "STRING-LITERAL");
        typeSymbolMap.put('/', "FORWARD-SLASH");

        this.type = typeSymbolMap.getOrDefault(symbol, defaultType);
        this.symbol = symbol;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
