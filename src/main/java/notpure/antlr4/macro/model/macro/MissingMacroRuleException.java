package notpure.antlr4.macro.model.macro;

/**
 * Thrown if no macro rule is defined for the given macro identifier.
 */
public final class MissingMacroRuleException extends Exception {

	/**
	 * Generated serial version unique identifier.
	 */
	private static final long serialVersionUID = -2695052988524723517L;

	public MissingMacroRuleException(String macroIdentifier) {
        super("Unable to locate macro rule for macro identifier `" + macroIdentifier + "`.");
    }
}
