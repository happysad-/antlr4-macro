package notpure.antlr4.macro;

import notpure.antlr4.macro.model.token.Token;
import notpure.antlr4.macro.model.token.TokenDefinition;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A set of tests for {@link notpure.antlr4.macro.model.token.Token}.
 */
public final class TokenTest {

    @Test(expected = IllegalArgumentException.class)
    public void testTokenConstructorForRegexTokenDefinitionParameter() {
        new Token(TokenDefinition.DIGIT);
    }

    @Test
    public void testTokenConstructorForTokenDefinitionParameters() {
        // Literal type
        assertToken(TokenDefinition.ASTERISK);
        assertToken(TokenDefinition.NEW_LINE);

        // Special type
        assertToken(TokenDefinition.EOF);
    }

    @Test
    public void testArrayContains() {
        Token[] tokens = new Token[] {
                new Token(TokenDefinition.ASTERISK),
                new Token(TokenDefinition.BACK_SLASH),
                new Token(TokenDefinition.AND),
                new Token(TokenDefinition.AT),
                new Token(TokenDefinition.CARET),
                new Token(TokenDefinition.CARRIAGE_RETURN),
                new Token(TokenDefinition.COLON)
        };

        // Test valid values
        for (Token token : tokens) {
            assertTrue(Token.arrayContains(tokens, token));
        }

        // Test invalid values
        assertFalse(Token.arrayContains(tokens, new Token(TokenDefinition.SEMICOLON)));
        assertFalse(Token.arrayContains(tokens, new Token(TokenDefinition.NEW_LINE)));
        assertFalse(Token.arrayContains(tokens, new Token(TokenDefinition.EOF)));
        assertFalse(Token.arrayContains(tokens, new Token(TokenDefinition.LEFT_BRACKET)));
    }

    @Test
    public void testArrayToString() {
        Token[] tokens = new Token[] {
                new Token(TokenDefinition.ASTERISK),
                new Token(TokenDefinition.BACK_SLASH),
                new Token(TokenDefinition.AND),
                new Token(TokenDefinition.AT),
                new Token(TokenDefinition.CARET),
                new Token(TokenDefinition.CARRIAGE_RETURN),
                new Token(TokenDefinition.COLON)
        };

        String expectedValue = "Token[] { Token(ASTERISK='*'), Token(BACK_SLASH='\\'), Token(AND='&'), Token(AT='@'),"
                + " Token(CARET='^'), Token(CARRIAGE_RETURN=''), Token(COLON=':') }";
        assertEquals(expectedValue, Token.toString(tokens));
    }

    private static void assertToken(TokenDefinition def) {
        Token token = new Token(def);
        assertEquals(token.getName(), def.name());
        assertEquals(token.getValue(), def.getValue());
    }
}