package community.redrover.mercuryit;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Contains a set of testing configurations in an internal map; only one ConfigHolder exists for a given test.
 */
@SuppressWarnings("unchecked")
public class MercuryITConfigHolder {

    private final Map<Class<? extends MercuryITConfig>, MercuryITConfig> configMap;

    MercuryITConfigHolder() {
        this.configMap = new ConcurrentHashMap<>();
    }

    /**
     * Uses the Map interface's put method to add or update to this ConfigHolder's map
     * @param clazz class of the provided configuration; used as key for the map
     * @param config configuration to be added or updated; used as value for the map
     * @param <Config> The type of the configuration to be added.
     */
    <Config extends MercuryITConfig> void set(Class<Config> clazz, Config config) {
        configMap.put(clazz, config);
    }

    /**
     * Retrieves a config of the given class; if a matching config does not already exist, also creates then adds such a config to the map.
     * @param clazz class of the config to be retrieved or generated
     * @return a configuration of the requested type
     * @param <Config> The type of the configuration to be retrieved.
     */
    public <Config extends MercuryITConfig> Config config(Class<Config> clazz) {
        return (Config) configMap.computeIfAbsent(clazz, configClass -> MercuryITHelper.create(configClass, new Class[]{MercuryITConfigHolder.class}, new Object[]{this}));
    }

    /**
     * Creates a copy of this ConfigHolder, containing all entries in its map. This is used for giving a new Request all existing configuration data.
     * @return an identical copy of this ConfigHolder
     */
    MercuryITConfigHolder copy() {
        MercuryITConfigHolder mercuryITConfigHolder = new MercuryITConfigHolder();
        mercuryITConfigHolder.configMap.putAll(
                configMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().copy(mercuryITConfigHolder))));

        return mercuryITConfigHolder;
    }
}
