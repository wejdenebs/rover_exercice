package rover;

public class UnknownOrientationException extends Throwable {
    public UnknownOrientationException(char movemennt) {
        super("Unknown movement '" + movemennt + "'!");
    }

}
