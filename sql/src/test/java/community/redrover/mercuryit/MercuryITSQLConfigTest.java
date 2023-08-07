package community.redrover.mercuryit;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MercuryITSQLConfigTest {

    public static final String EXPECTED_DRIVER = "org.h2.Driver";
    public static final String INPUT_DRIVER = "driver1";
    public static final String INPUT_CONNECT = "connect1";
    public static final String INPUT_USERNAME = "username1";
    public static final String INPUT_PASSWORD = "password1";
    @Test
    @Order(1)
    public void testSqlConfigDefault() {
        MercuryITSQLConfig globalSqlConfig = MercuryIT.globalConfig(MercuryITSQLConfig.class);
        Assertions.assertEquals(EXPECTED_DRIVER, globalSqlConfig.getDriver());
        Assertions.assertNull(globalSqlConfig.getConnect());
        Assertions.assertNull(globalSqlConfig.getUsername());
        Assertions.assertNull(globalSqlConfig.getPassword());
    }

    @Test
    @Order(2)
    public void testSqlConfigInput(){
        MercuryITSQLConfig sqlConfig = MercuryIT.globalConfig(MercuryITSQLConfig.class)
                .driver(INPUT_DRIVER)
                .connect(INPUT_CONNECT)
                .username(INPUT_USERNAME)
                .password(INPUT_PASSWORD)
                .request(MercuryITSQL.class)
                .contextConfig(MercuryITSQLConfig.class);

        Assertions.assertEquals(INPUT_DRIVER, sqlConfig.getDriver());
        Assertions.assertEquals(INPUT_CONNECT, sqlConfig.getConnect());
        Assertions.assertEquals(INPUT_USERNAME, sqlConfig.getUsername());
        Assertions.assertEquals(INPUT_PASSWORD, sqlConfig.getPassword());
    }

}
