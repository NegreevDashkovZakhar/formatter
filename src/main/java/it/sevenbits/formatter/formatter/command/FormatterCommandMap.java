package it.sevenbits.formatter.formatter.command;

import it.sevenbits.formatter.collection.Pair;
import it.sevenbits.formatter.formatter.IFormatWriter;
import it.sevenbits.formatter.formatter.state.FormatterState;
import it.sevenbits.formatter.lexer.token.IToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Class delegating commands creation to specific method of command creator based on given state and token
 */
public class FormatterCommandMap {
    private final Map<Pair<FormatterState, String>, ICreateCommand> methodMap;
    private final FormatterCommandCreatorRepository commandCreatorRepository;

    /**
     * Default constructor initializing command map
     *
     * @param formatWriter - writer delegated to commands
     */
    public FormatterCommandMap(final IFormatWriter formatWriter) {
        this.methodMap = new HashMap<>();
        commandCreatorRepository = new FormatterCommandCreatorRepository(formatWriter);

        Class<FormatterCommandCreatorRepository> commandCreatorClass = FormatterCommandCreatorRepository.class;

        ICreateCommand writeTextCommandCreator = commandCreatorRepository.getWriteTextCommandCreator();
        ICreateCommand decreaseParagraphCommandCreator = commandCreatorRepository.getDecreaseParagraphCommandCreator();
        ICreateCommand writeSpaceTextCommandCreator = commandCreatorRepository.getSpaceTextCommandCreator();
        ICreateCommand writeParagraphTextCommandCreator = commandCreatorRepository.getWriteParagraphTextCommandCreator();
        ICreateCommand deeperEmptyLineTextCommandCreator = commandCreatorRepository.getDeeperEmptyLineTextCommandCreator();
        ICreateCommand increaseParagraphTextCommandCreator = commandCreatorRepository.getIncreaseParagraphTextCommandCreator();

        FormatterState semicolonState = new FormatterState("SEMICOLON");
        FormatterState openCurlyBraceState = new FormatterState("OPEN-CURLY-BRACE");
        FormatterState closeCurlyBraceState = new FormatterState("CLOSE-CURLY-BRACE");
        FormatterState stringLiteralState = new FormatterState("STRING-LITERAL");
        FormatterState textState = new FormatterState("TEXT");
        FormatterState startState = new FormatterState("START");
        FormatterState oneLineCommentState = new FormatterState("ONE-LINE-COMMENT");

        methodMap.put(new Pair<>(startState, "SEMICOLON"), writeTextCommandCreator);
        methodMap.put(new Pair<>(startState, "OPEN-CURLY-BRACE"), writeTextCommandCreator);
        methodMap.put(new Pair<>(startState, "CLOSE-CURLY-BRACE"), writeTextCommandCreator);
        methodMap.put(new Pair<>(startState, "STRING-LITERAL"), writeTextCommandCreator);
        methodMap.put(new Pair<>(startState, "TEXT"), writeTextCommandCreator);
        methodMap.put(new Pair<>(startState, "ONE-LINE-COMMENT"), writeTextCommandCreator);

        methodMap.put(new Pair<>(textState, "SEMICOLON"), writeTextCommandCreator);
        methodMap.put(new Pair<>(textState, "OPEN-CURLY-BRACE"), writeSpaceTextCommandCreator);
        methodMap.put(new Pair<>(textState, "CLOSE-CURLY-BRACE"), writeSpaceTextCommandCreator);
        methodMap.put(new Pair<>(textState, "STRING-LITERAL"), writeTextCommandCreator);
        methodMap.put(new Pair<>(textState, "TEXT"), writeSpaceTextCommandCreator);
        methodMap.put(new Pair<>(textState, "ONE-LINE-COMMENT"), writeParagraphTextCommandCreator);

        methodMap.put(new Pair<>(stringLiteralState, "SEMICOLON"), writeTextCommandCreator);
        methodMap.put(new Pair<>(stringLiteralState, "OPEN-CURLY-BRACE"), writeTextCommandCreator);
        methodMap.put(new Pair<>(stringLiteralState, "CLOSE-CURLY-BRACE"), writeTextCommandCreator);
        methodMap.put(new Pair<>(stringLiteralState, "STRING-LITERAL"), writeTextCommandCreator);
        methodMap.put(new Pair<>(stringLiteralState, "TEXT"), writeTextCommandCreator);
        methodMap.put(new Pair<>(stringLiteralState, "ONE-LINE-COMMENT"), writeParagraphTextCommandCreator);

        methodMap.put(new Pair<>(semicolonState, "SEMICOLON"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(semicolonState, "OPEN-CURLY-BRACE"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(semicolonState, "CLOSE-CURLY-BRACE"), decreaseParagraphCommandCreator);
        methodMap.put(new Pair<>(semicolonState, "STRING-LITERAL"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(semicolonState, "TEXT"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(semicolonState, "ONE-LINE-COMMENT"), writeParagraphTextCommandCreator);

        methodMap.put(new Pair<>(openCurlyBraceState, "SEMICOLON"), increaseParagraphTextCommandCreator);
        methodMap.put(new Pair<>(openCurlyBraceState, "OPEN-CURLY-BRACE"), increaseParagraphTextCommandCreator);
        methodMap.put(new Pair<>(openCurlyBraceState, "CLOSE-CURLY-BRACE"), deeperEmptyLineTextCommandCreator);
        methodMap.put(new Pair<>(openCurlyBraceState, "STRING-LITERAL"), increaseParagraphTextCommandCreator);
        methodMap.put(new Pair<>(openCurlyBraceState, "TEXT"), increaseParagraphTextCommandCreator);
        methodMap.put(new Pair<>(openCurlyBraceState, "ONE-LINE-COMMENT"), increaseParagraphTextCommandCreator);

        methodMap.put(new Pair<>(closeCurlyBraceState, "SEMICOLON"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(closeCurlyBraceState, "OPEN-CURLY-BRACE"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(closeCurlyBraceState, "CLOSE-CURLY-BRACE"), decreaseParagraphCommandCreator);
        methodMap.put(new Pair<>(closeCurlyBraceState, "STRING-LITERAL"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(closeCurlyBraceState, "TEXT"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(closeCurlyBraceState, "ONE-LINE-COMMENT"), writeSpaceTextCommandCreator);

        methodMap.put(new Pair<>(oneLineCommentState, "SEMICOLON"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(oneLineCommentState, "OPEN-CURLY-BRACE"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(oneLineCommentState, "CLOSE-CURLY-BRACE"), decreaseParagraphCommandCreator);
        methodMap.put(new Pair<>(oneLineCommentState, "STRING-LITERAL"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(oneLineCommentState, "TEXT"), writeParagraphTextCommandCreator);
        methodMap.put(new Pair<>(oneLineCommentState, "ONE-LINE-COMMENT"), writeParagraphTextCommandCreator);
    }

    /**
     * Method for getting command based on given state and token
     *
     * @param state - current formatter state
     * @param token - last received token
     * @return new command for formatter
     */
    public IFormatterCommand getCommand(final FormatterState state, final IToken token) {
        return methodMap.get(new Pair<>(state, token.getType())).run(token);
    }

}
