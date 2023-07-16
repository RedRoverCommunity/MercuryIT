package community.redrover.mercuryit;

import java.util.function.Function;

/**
 * Contains the configHolder whose configs are applied to all tests in the module.
 */
public class MercuryIT {

    private static final MercuryITConfigHolder configHolder = new MercuryITConfigHolder();

    /**
     * Retrieves a config of the given class; if a matching config does not already exist, also creates then adds such a config to the configHolder.
     * @param clazz class of the config to be retrieved/created
     * @return the config from configHolder that matches the given class
     * @param <Config> the type of the config to be retrieved
     */
    public static <Config extends MercuryITConfig> Config config(Class<Config> clazz) {
        return MercuryIT.configHolder.config(clazz);
    }

    /**
     * Updates the indicated config in the ConfigHolder to contain the provided
     * @param clazz class of the config to be edited
     * @param configFunction
     * @param <Config>
     */
    public static <Config extends MercuryITConfig> void config(Class<Config> clazz, Function<Config, Config> configFunction) {
        MercuryIT.configHolder.set(clazz, configFunction.apply(config(clazz)));
    }

    /**
     *
     * @param clazz
     * @return
     * @param <Request>
     */
    public static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return request(clazz, configHolder.copy());
    }

    /**
     *
     * @param clazz
     * @param configHolder
     * @return
     * @param <Request>
     */
    static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz, MercuryITConfigHolder configHolder) {
        return MercuryITHelper.create(clazz, new Class[]{MercuryITConfigHolder.class}, new Object[]{configHolder});
    }
}

