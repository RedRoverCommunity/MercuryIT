package community.redrover.mercuryit;

import java.util.function.Function;


public class MercuryIT {

    private static final MercuryITConfigHolder globalConfigHolder = new MercuryITConfigHolder();

    public static <Config extends MercuryITConfig> Config globalConfig(Class<Config> clazz) {
        return MercuryIT.globalConfigHolder.config(clazz);
    }

    public static <Config extends MercuryITConfig> void globalConfig(Class<Config> clazz, Function<Config, Config> configFunction) {
        MercuryIT.globalConfigHolder.set(clazz, configFunction.apply(globalConfig(clazz)));
    }

    public static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return request(clazz, globalConfigHolder.copy());
    }

    static <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz, MercuryITConfigHolder configHolder) {
        return MercuryITHelper.create(clazz, new Class[]{MercuryITConfigHolder.class}, new Object[]{configHolder});
    }
}
