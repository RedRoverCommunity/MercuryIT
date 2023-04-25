package community.redrover.mercuryit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.Function;


@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MercuryITSQLConfig extends MercuryITConfig<MercuryITSQLConfig.MercuryITSQLConfigBuilder> {

    protected static final String CONFIG_NAME = "sql";

    protected static final String DRIVER_NAME = "driver";
    protected static final String HOST_NAME = "host";
    protected static final String PORT_NAME = "port";
    protected static final String DATABASE_NAME = "dbname";

    @Override
    public MercuryITConfig<MercuryITSQLConfigBuilder> copy() {
        return this.toBuilder().build();
    }
}
