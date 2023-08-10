package community.redrover.mercuryit;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MercuryITMongoConfigTest {

    public static final String INPUT_URI = "uri-const";
    public static final String INPUT_DATABASE = "database-const";

    @Test
    @Order(1)
    public void testMongoGlobalConfigFromFile() {
        MercuryITMongoConfig mongoConfig = MercuryIT.globalConfig(MercuryITMongoConfig.class);

        Assertions.assertEquals("uri-file", mongoConfig.getUri());
        Assertions.assertEquals("database-file", mongoConfig.getDatabase());
    }

    @Test
    @Order(2)
    public void testMongoGlobalConfigInput() {
        MercuryITMongoConfig mongoConfig = MercuryIT.globalConfig(MercuryITMongoConfig.class)
                .uri(INPUT_URI)
                .database(INPUT_DATABASE);

        Assertions.assertEquals(INPUT_URI, mongoConfig.getUri());
        Assertions.assertEquals(INPUT_DATABASE, mongoConfig.getDatabase());
    }

    @Test
    @Order(3)
    public void testMongoGlobalConfigGet() {
        MercuryITMongoConfig mongoConfig = MercuryIT.globalConfig(MercuryITMongoConfig.class);

        Assertions.assertEquals(INPUT_URI, mongoConfig.getUri());
        Assertions.assertEquals(INPUT_DATABASE, mongoConfig.getDatabase());
    }

    @Test
    @Order(4)
    public void testMongoGlobalConfigToContext() {
        MercuryITMongoConfig mongoConfig = MercuryIT
                .request(MercuryITMongo.class)
                .contextConfig(MercuryITMongoConfig.class);

        Assertions.assertEquals(INPUT_URI, mongoConfig.getUri());
        Assertions.assertEquals(INPUT_DATABASE, mongoConfig.getDatabase());
    }
}
