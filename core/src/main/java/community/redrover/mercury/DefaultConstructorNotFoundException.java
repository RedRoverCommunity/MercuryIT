package community.redrover.mercury;

public class DefaultConstructorNotFoundException extends RuntimeException {

    public DefaultConstructorNotFoundException(Throwable cause) {
        super("Default constructor is not found.", cause);
    }
}
