package it.sevenbits.formatter.formatter.state;

import it.sevenbits.formatter.lexer.token.IToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Class containing mapped values for state transition
 */
public class FormatterStateMap {
    private final Map<String, FormatterState> stateMap;

    /**
     * Default constructor initializing default map
     */
    public FormatterStateMap() {
        stateMap = new HashMap<>();
        FormatterState semicolonState = new FormatterState("SEMICOLON");
        FormatterState openCurlyBraceState = new FormatterState("OPEN-CURLY-BRACE");
        FormatterState closeCurlyBraceState = new FormatterState("CLOSE-CURLY-BRACE");
        FormatterState stringLiteralState = new FormatterState("STRING-LITERAL");
        FormatterState textState = new FormatterState("TEXT");
        FormatterState oneLineCommentState = new FormatterState("ONE-LINE-COMMENT");

        stateMap.put("SEMICOLON", semicolonState);
        stateMap.put("OPEN-CURLY-BRACE", openCurlyBraceState);
        stateMap.put("CLOSE-CURLY-BRACE", closeCurlyBraceState);
        stateMap.put("STRING-LITERAL", stringLiteralState);
        stateMap.put("TEXT", textState);
        stateMap.put("ONE-LINE-COMMENT", oneLineCommentState);
    }

    /**
     * Method for getting next state based on given state and token
     *
     * @param state - current formatter state
     * @param token - last received token
     * @return new formatter state
     * @throws FormatterStateTransitionException when do not have mapped value for given arguments
     */
    public FormatterState getNext(final FormatterState state, final IToken token) throws FormatterStateTransitionException {
        if (stateMap.containsKey(token.getType())) {
            return stateMap.get(token.getType());
        }
        throw new FormatterStateTransitionException("Do not have state for given arguments");
    }
}
