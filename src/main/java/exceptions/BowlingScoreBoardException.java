package exceptions;

/**
 * A simple wrapper exception meant to represent exceptions encountered within
 * package calls of {@link BowlingScoreBoard}.
 */
public class BowlingScoreBoardException extends Exception {

    private static final long serialVersionUID = 2923804381920774061L;

    public BowlingScoreBoardException(String errorMessage) {
        super(errorMessage);
    }
}
