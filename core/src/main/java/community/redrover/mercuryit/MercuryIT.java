package community.redrover.mercuryit;

import java.util.function.Function;

/**
 * Contains the configHolder whose configs are applied to all tests in the module.
 */
public class MercuryIT {

    private static final MercuryITConfigHolder configHolder = new MercuryITConfigHolder();

    /**
     * Retrieves a config of the given class; if a matching config does not already exist, also creates then adds such a config to the global configHolder.
     * @param clazz class of the config to be retrieved/created
     * @return the config from configHolder that matches the given class
     * @param <Config> the type of the config to be retrieved
     */
    public static <Config extends MercuryITConfig> Config config(Class<Config> clazz) {
        return MercuryIT.configHolder.config(clazz);
    }

    /**
     * Updates the indicated config in the ConfigHolder to contain the provided elements.
     * @param clazz class of the config to be edited
     * @param configFunction Function that takes in the current config, makes changes to it, and returns the updated version
     * @param <Config> class of the config to be edited
     */
    public static <Config extends MercuryITConfig> void config(Class<Config> clazz, Function<Config, Config> configFunction) {
        MercuryIT.configHolder.set(clazz, configFunction.apply(config(clazz)));
    }

    /**
     * The public-access version of the overloaded 'request' method; passes in a copy of the global configHolder to the
     * other version of the 'request' method.
     * @param clazz class of the request to be created
     * @return The request generated by the inner-use version of this method
     * @param <Request> class of the request to be created
     */
    public static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return request(clazz, configHolder.copy());
    }

    /**
     * The inner-use version of the overloaded 'request' method; creates an instance of the specified request, passing it the given configHolder.
     * @param clazz Class of the request to be created
     * @param configHolder ConfigHolder to be passed to the request's constructor
     * @return A new instance of the given request
     * @param <Request> Class of the request to be created
     */
    static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz, MercuryITConfigHolder configHolder) {
        return MercuryITHelper.create(clazz, new Class[]{MercuryITConfigHolder.class}, new Object[]{configHolder});
    }
}

