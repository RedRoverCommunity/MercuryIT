package community.redrover.mercuryit;

import java.util.function.Function;


@SuppressWarnings("unchecked")
public abstract class MercuryITObject<Self extends MercuryITObject<Self>> {

    /* field is protected because lombok bug */
    protected final MercuryITConfigHolder localConfigHolder;

    protected MercuryITObject(MercuryITConfigHolder configHolder) {
        this.localConfigHolder = configHolder;
    }

    protected MercuryITConfigHolder getLocalConfigHolder() {
        return localConfigHolder;
    }

    protected <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return MercuryIT.request(clazz, getLocalConfigHolder());
    }

    public <Config extends MercuryITConfig> Config localConfig(Class<Config> clazz) {
        return getLocalConfigHolder().config(clazz);
    }

    public <Config extends MercuryITConfig> Self localConfig(Class<Config> clazz, Function<Config, Config> configFunction) {
        this.getLocalConfigHolder().set(clazz, configFunction.apply(localConfig(clazz)));
        return (Self) this;
    }
}
