package community.redrover.mercuryit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Used to create new class instances as desired.
 */
public class MercuryITHelper {

    public static class CannotCreateInstanceException extends RuntimeException {

        public CannotCreateInstanceException(Throwable cause, String className) {
            super(String.format("Cannot create instance of \"%s\".", className), cause);
        }
    }

    public static class DefaultConstructorNotFoundException extends RuntimeException {

        public DefaultConstructorNotFoundException(Throwable cause) {
            super("Default constructor is not found.", cause);
        }
    }

    /**
     * Calls the constructor of the given class; throws an exception if the method was unable to create an instance of the class.
     * @param clazz Desired class to be created
     * @param classes One-element array containing only the class to be created
     * @param objects array containing all arguments to be passed to the constructor of the desired class
     * @return A newly-created instance of the desired class.
     * @param <Result> Desired class to be created
     */
    static <Result> Result create(Class<Result> clazz, Class<?>[] classes, Object[] objects) {
        Constructor<Result> defaultConstructor;
        try {
            defaultConstructor = clazz.getDeclaredConstructor(classes);
        } catch (NoSuchMethodException e) {
            throw new DefaultConstructorNotFoundException(e);
        }

        try {
            return defaultConstructor.newInstance(objects);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new CannotCreateInstanceException(e, clazz.getSimpleName());
        }
    }
}
