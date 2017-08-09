package notpure.antlr4.macro.model.parser;

/**
 * A parser exception.
 */
public final class ParserException extends Exception {

	/**
	 * Generated serial version unique identifier.
	 */
	private static final long serialVersionUID = 4862798521905150102L;

	public ParserException(Class<?> clazz, String message) {
        super(clazz.getSimpleName() + ": " + message);
    }
}
