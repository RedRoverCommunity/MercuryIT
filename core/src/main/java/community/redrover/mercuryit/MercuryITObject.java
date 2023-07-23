package community.redrover.mercuryit;

import java.util.function.Function;


@SuppressWarnings("unchecked")
public abstract class MercuryITObject<Self extends MercuryITObject<Self>> {

    /* field is protected because lombok bug */
    protected final MercuryITConfigHolder configHolder;

    protected MercuryITObject(MercuryITConfigHolder configHolder) {
        this.configHolder = configHolder;
    }

    protected MercuryITConfigHolder getConfigHolder() {
        return configHolder;
    }

    /**
     * Creates a request of the desired type using the configs specific to this context.
     * @param clazz Class of the request to be generated
     * @return A new instance of the desired request
     * @param <Request> Class of the request to be generated
     */
    protected <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return MercuryIT.request(clazz, getConfigHolder());
    }

    /**
     * Retrieves a config of the given class; if a matching config does not already exist, also creates then adds such a config to the context's configHolder.
     * @param clazz class of the config to be retrieved/created
     * @return the config in the context's configHolder that matches the given class
     * @param <Config> the type of the config to be retrieved
     */
    public <Config extends MercuryITConfig> Config config(Class<Config> clazz) {
        return getConfigHolder().config(clazz);
    }

    /**
     * Updates the indicated config in the context's configHolder according to the provided function.
     * @param clazz class of the config to be edited
     * @param configFunction Function that takes in a config, makes changes to it, and returns the updated version
     * @param <Config> class of the config to be edited
     */
    public <Config extends MercuryITConfig> Self config(Class<Config> clazz, Function<Config, Config> configFunction) {
        this.getConfigHolder().set(clazz, configFunction.apply(config(clazz)));
        return (Self) this;
    }
}
