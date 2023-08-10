package community.redrover.mercuryit;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MercuryITSQLConfigTest {

    public static final String INPUT_DRIVER = "driver-const";
    public static final String INPUT_CONNECT = "connect-const";
    public static final String INPUT_USERNAME = "username-const";
    public static final String INPUT_PASSWORD = "password-const";

    @Test
    @Order(1)
    public void testSqlConfigFromFile() {
        MercuryITSQLConfig sqlConfig = MercuryIT.globalConfig(MercuryITSQLConfig.class);

        Assertions.assertEquals("driver-file", sqlConfig.getDriver());
        Assertions.assertEquals("connect-file", sqlConfig.getConnect());
        Assertions.assertEquals("username-file", sqlConfig.getUsername());
        Assertions.assertEquals("password-file", sqlConfig.getPassword());
    }

    @Test
    @Order(2)
    public void testSqlGlobalConfigInput() {
        MercuryITSQLConfig sqlConfig = MercuryIT.globalConfig(MercuryITSQLConfig.class)
                .driver(INPUT_DRIVER)
                .connect(INPUT_CONNECT)
                .username(INPUT_USERNAME)
                .password(INPUT_PASSWORD);

        Assertions.assertEquals(INPUT_DRIVER, sqlConfig.getDriver());
        Assertions.assertEquals(INPUT_CONNECT, sqlConfig.getConnect());
        Assertions.assertEquals(INPUT_USERNAME, sqlConfig.getUsername());
        Assertions.assertEquals(INPUT_PASSWORD, sqlConfig.getPassword());
    }

    @Test
    @Order(3)
    public void testSqlGlobalConfigGet() {
        MercuryITSQLConfig sqlConfig = MercuryIT.globalConfig(MercuryITSQLConfig.class);

        Assertions.assertEquals(INPUT_DRIVER, sqlConfig.getDriver());
        Assertions.assertEquals(INPUT_CONNECT, sqlConfig.getConnect());
        Assertions.assertEquals(INPUT_USERNAME, sqlConfig.getUsername());
        Assertions.assertEquals(INPUT_PASSWORD, sqlConfig.getPassword());
    }

    @Test
    @Order(4)
    public void testSqlGlobalConfigToContext() {
        MercuryITSQLConfig sqlConfig = MercuryIT
                .request(MercuryITSQL.class)
                .contextConfig(MercuryITSQLConfig.class);

        Assertions.assertEquals(INPUT_DRIVER, sqlConfig.getDriver());
        Assertions.assertEquals(INPUT_CONNECT, sqlConfig.getConnect());
        Assertions.assertEquals(INPUT_USERNAME, sqlConfig.getUsername());
        Assertions.assertEquals(INPUT_PASSWORD, sqlConfig.getPassword());
    }
}
