package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.read.IReader;
import it.sevenbits.formatter.read.StringReader;
import it.sevenbits.formatter.write.IWriter;
import it.sevenbits.formatter.write.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FormatterTest {
    private Formatter formatter;

    @Before
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void FormattingSimple() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("aaa { bbbb; ccc;}");
        IWriter writer = new StringWriter(result);

        formatter.format(reader, writer);
        assertEquals("aaa {\n    bbbb;\n    ccc;\n}", result.toString());
    }

    @Test
    public void FormattingBraces() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("{{{{}}}}");
        IWriter writer = new StringWriter(result);

        formatter.format(reader, writer);
        assertEquals("{\n    {\n        {\n            {\n                \n            }\n        }\n    }\n}", result.toString());
    }

    @Test
    public void FormattingComplex() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("aaa { bbbb; fff { trtryy; hhhh; } else { bsufg; } ccc;}");
        IWriter writer = new StringWriter(result);

        formatter.format(reader, writer);
        assertEquals("aaa {\n    bbbb;\n    fff {\n        trtryy;\n        hhhh;\n    }\n    else {\n        bsufg;\n    }\n    ccc;\n}", result.toString());
    }

    @Test
    public void FormattingHelloWorld() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("public class HelloWorld { public static void main(String[] args) { System.out.println(\\\"Hello, World\\\");}}");
        IWriter writer = new StringWriter(result);

        formatter.format(reader, writer);
        assertEquals("public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\\\"Hello, World\\\");\n    }\n}", result.toString());
    }

    @Test
    public void FormatSpaceSkip() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("  {  a  ; } { {  } }   ");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals(
                "{\n" +
                        "    a;\n" +
                        "}\n" +
                        "{\n" +
                        "    {\n" +
                        "        \n" +
                        "    }\n" +
                        "}", result.toString());
    }

    @Test
    public void IncompleteBracesTest() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("{a;{}");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals(
                "{\n" +
                        "    a;\n" +
                        "    {\n" +
                        "        \n" +
                        "    }", result.toString());
    }

    @Test
    public void OneLineCommentTest() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader(
                "fo na//li\n" +
                        "ve;");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals(
                "fo na\n" +
                        "//li\n" +
                        "ve;", result.toString());
    }

    @Test
    public void CommentSuspicionTest() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("int a = b/c;");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals("int a = b / c;", result.toString());
    }

    @Test
    public void OneLineCommentBetweenBracesTest() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("{//comment_1\n}//comment_2\n;");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals(
                "{\n" +
                        "    //comment_1\n" +
                        "} //comment_2\n" +
                        ";", result.toString());
    }

    @Test
    public void OneLineCommentOnlyTest() throws FormatterException {
        StringBuilder result = new StringBuilder();
        IReader reader = new StringReader("//comment_1");
        IWriter writer = new StringWriter(result);
        formatter.format(reader, writer);
        assertEquals("//comment_1", result.toString());
    }
}
