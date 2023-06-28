package community.redrover.mercuryit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.MapConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.TreeMap;

/**
 * Contains properties relating to a particular aspect of test setup.
 */
public abstract class MercuryITConfig extends MercuryITObject<MercuryITConfig> {

    private static Configuration configuration;

    /**
     * Creates a Configuration object if none exists, based on the first matching file in the directory "test/resources", in the following list of priorities:
     * <ol>
     *      <li>.yaml file </li>
     *      <li>.properties file</li>
     *      <li> New, empty configuration object with no file source</li>
     * </ol>
     * @return the existing configuration, or a new configuration if none is found
     */
    protected static Configuration configuration() {
        if (configuration == null) {
            Configurations configurations = new Configurations();
            try {
                configuration = configurations.fileBased(YAMLConfiguration.class, APP_NAME + ".yaml");
            } catch (ConfigurationException yamlException) {
                try {
                    configuration = configurations.fileBased(PropertiesConfiguration.class, APP_NAME + ".properties");
                } catch (ConfigurationException propertiesException) {
                    configuration = new MapConfiguration(new TreeMap<>());
                }
            }
        }

        return configuration;
    }

    public static final String APP_NAME = "mercuryit";

    /**
     * Constructs a property name to be retrieved from the config's map; intended to be used by the Configuration's getString() method.
     * @param names components that are combined to generate the property name
     * @return a property name, formatted in the style of a config key
     */
    public static String name(String... names) {
        StringBuilder fullName = new StringBuilder();
        for (String name : names) {
            fullName.append(name).append('.');
        }

        if (fullName.length() > 0) {
            fullName.deleteCharAt(fullName.length() - 1);
        }

        return fullName.toString();
    }

    protected MercuryITConfig(MercuryITConfigHolder configHolder) {
        super(configHolder);
    }

    /**
     * Generates a copy of the config, stores a reference to the given ConfigHolder inside of it, and adds the config into the specified ConfigHolder's map.
     * @param configHolder the ConfigHolder the copy of this object will belong to
     * @return the generated copy of this config
     */
    protected abstract MercuryITConfig copy(MercuryITConfigHolder configHolder);

    /**
     * Used to generate an API request
     * @param clazz type of the API request to be generated
     * @return a generated API request
     * @param <Request> type of the API request to be generated
     */
    @Override
    public <Request extends MercuryITRequest<Request>> Request request(Class<Request> clazz) {
        return super.request(clazz);
    }
}
