package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.read.IReader;
import it.sevenbits.formatter.read.ReaderEndException;
import it.sevenbits.formatter.read.StringReader;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class LexerTest {

    @Test
    public void HappyPathTest() {
        try {
            IReader reader = Mockito.mock(IReader.class);
            Mockito.doReturn(true, true, true, true, true, true, true, true, false).when(reader).hasNext();
            Mockito.doReturn('{', '}', ';', 't', 'e', 'x', 't', ';').when(reader).read();
            Lexer lexer = new Lexer(reader);
            assertEquals("OPEN-CURLY-BRACE", lexer.getNextToken().getType());
            assertEquals("CLOSE-CURLY-BRACE", lexer.getNextToken().getType());
            assertEquals("SEMICOLON", lexer.getNextToken().getType());
            IToken token = lexer.getNextToken();
            assertEquals("TEXT", token.getType());
            assertEquals("text", token.getText());
            assertEquals("SEMICOLON", lexer.getNextToken().getType());
            assertFalse(lexer.hasNext());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ReaderErrorTest() {
        //TODO simplify this test
        IReader reader = Mockito.mock(IReader.class);
        Mockito.doReturn(true, true).when(reader).hasNext();
        try {
            Mockito.doReturn(true).when(reader).hasNext();
            Mockito.doReturn('{').doThrow(new ReaderEndException()).when(reader).read();
        } catch (Exception e) {
            fail();
        }
        Lexer lexer = new Lexer(reader);
        try {
            assertEquals("OPEN-CURLY-BRACE", lexer.getNextToken().getType());
        } catch (LexerException e) {
            fail();
        }
        assertTrue(lexer.hasNext());
        try {
            lexer.getNextToken();
            fail();
        } catch (LexerException ignored) {
        }
    }

    @Test
    public void EmptyReaderTest() {
        IReader reader = Mockito.mock(IReader.class);
        try {
            Mockito.doReturn(false).when(reader).hasNext();
            Mockito.doThrow(new ReaderEndException()).when(reader).read();
        } catch (ReaderEndException e) {
            fail();
        }
        Lexer lexer = new Lexer(reader);
        assertFalse(lexer.hasNext());
        try {
            lexer.getNextToken();
            fail();
        } catch (LexerException ignored) {
        }
    }

    @Test
    public void BigTokenTest() throws LexerException {
        IReader reader = new StringReader("qwertyuiopasdfghjklzxcvbnm");
        Lexer lexer = new Lexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals("TEXT", token.getType());
        assertEquals("qwertyuiopasdfghjklzxcvbnm", token.getText());
        assertFalse(lexer.hasNext());
    }

    @Test
    public void OneLineCommentTest() throws LexerException {
        IReader reader = new StringReader("//asd\nlou");
        Lexer lexer = new Lexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals("ONE-LINE-COMMENT", token.getType());
        assertEquals("//asd", token.getText());
        assertTrue(lexer.hasNext());
    }

    @Test
    public void CommentSuspicionTest() throws LexerException {
        IReader reader = new StringReader("a/b");
        Lexer lexer = new Lexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals("TEXT", token.getType());
        assertEquals("a", token.getText());
        token = lexer.getNextToken();
        assertEquals("TEXT", token.getType());
        assertEquals("/", token.getText());
        token = lexer.getNextToken();
        assertEquals("TEXT", token.getType());
        assertEquals("b", token.getText());
        assertFalse(lexer.hasNext());
    }

    @Test
    public void ManyStringLiteralsTest() throws LexerException {
        IReader reader = new StringReader("z\"1\"\"2\"");
        Lexer lexer = new Lexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals("TEXT", token.getType());
        assertEquals("z", token.getText());
        token = lexer.getNextToken();
        assertEquals("STRING-LITERAL", token.getType());
        assertEquals("\"1\"", token.getText());
        token = lexer.getNextToken();
        assertEquals("STRING-LITERAL", token.getType());
        assertEquals("\"2\"", token.getText());
        assertFalse(lexer.hasNext());
    }
}