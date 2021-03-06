package notpure.antlr4.macro.model.token;

import notpure.antlr4.macro.processor.Lexer;

import java.util.regex.Pattern;

/**
 * Definitions for the {@link Lexer}.
 */
public enum TokenDefinition {
    /* Letters / Digits */
    LETTER("[A-Za-z]", ValueType.REGEX),
    DIGIT("[0-9]", ValueType.REGEX),
    /* Brackets */
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    LEFT_SQUARE_BRACKET("["),
    RIGHT_SQUARE_BRACKET("]"),
    /* Math symbols */
    PLUS("+"),
    HYPHEN("-"),
    ASTERISK("*"),
    EQUALS("="),
    PERCENT("%"),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    /* Punctuation */
    EXCLAMATION_MARK("!"),
    AMPERSAND("&"),
    QUESTION_MARK("?"),
    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    /* Other */
    HASH("#"),
    GRAVE_ACCENT("`"),
    CARET("^"),
    SEMICOLON(";"),
    COLON(":"),
    UNDERSCORE("_"),
    VERTICAL_LINE("|"),
    BACK_SLASH("\\"),
    FORWARD_SLASH("/"),
    COMMERCIAL_AT("@"),
    TILDE("~"),
    /* Currencies */
    DOLLAR("$"),
    POUND("£"),
    EURO("€"),
    /* Escape sequences / white space */
    CARRIAGE_RETURN("\r"),
    NEW_LINE("\n"),
    TAB("\t"),
    SPACE(" "),
    /**
     * Unknown token.
     */
    UNKNOWN,
    /**
     * End of file, unique token.
     */
    EOF;

    private final String value;
    private final ValueType valueType;
    private Pattern pattern;

    TokenDefinition(String value, ValueType valueType) {
        this.value = value;
        this.valueType = valueType;

        if (valueType == ValueType.REGEX)
            this.pattern = Pattern.compile(value);
    }

    TokenDefinition(String value) {
        this(value, ValueType.LITERAL);
    }

    TokenDefinition() {
        this(null, ValueType.SPECIAL);
    }

    public boolean matches(String input) {
        switch (valueType) {
            case LITERAL:
                return input.equals(value);
            case REGEX:
                return pattern.matcher(input).matches();
            case SPECIAL:
            default:
                return false;
        }
    }

    public String getValue() {
        return value;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public static TokenDefinition forName(String name) {
        for (TokenDefinition def : values()) {
            if (def.name().equals(name)) {
                return def;
            }
        }
        return null;
    }

    /**
     * Value type for {@link TokenDefinition}.
     */
    public enum ValueType {

        /**
         * The value is literal.
         */
        LITERAL,
        /**
         * The value is a RegEx.
         */
        REGEX,
        /**
         * The value is special in that {@link TokenDefinition#matches(String)} will always return false for it.
         */
        SPECIAL
    }
}
