package community.redrover.mercuryit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class MercuryITMongoConfigTest {

    public static final String INPUT_URI = "uri1";
    public static final String INPUT_DATABASE = "database1";

    @Test
    @Order(1)
    public void testMongoConfigDefault() {
        MercuryITMongoConfig globalMongoConfig = MercuryIT.globalConfig(MercuryITMongoConfig.class);
        Assertions.assertNull(globalMongoConfig.getUri());
        Assertions.assertNull(globalMongoConfig.getDatabase());
    }

    @Test
    @Order(2)
    public void testSqlConfigInput(){
        MercuryITMongoConfig mongoConfig = MercuryIT.globalConfig(MercuryITMongoConfig.class)
                .uri(INPUT_URI)
                .database(INPUT_DATABASE)
                .request(MercuryITMongo.class)
                .contextConfig(MercuryITMongoConfig.class);

        Assertions.assertEquals(INPUT_URI, mongoConfig.getUri());
        Assertions.assertEquals(INPUT_DATABASE, mongoConfig.getDatabase());
    }
}
