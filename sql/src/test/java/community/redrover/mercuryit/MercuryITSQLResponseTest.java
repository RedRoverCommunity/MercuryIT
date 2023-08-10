package community.redrover.mercuryit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.*;
import org.mockito.Mockito;


public class MercuryITSQLResponseTest {

    private static final MercuryITConfigHolder CONFIG_HOLDER = MercuryIT.request(MercuryITContext.class).getConfigHolder();
    private static ResultSet FULL_RESULT_SET;
    private static ResultSet EMPTY_RESULT_SET;

    @BeforeAll
    public void createResultSet() throws SQLException {
        EMPTY_RESULT_SET = Mockito.mock(ResultSet.class);

        Mockito.when(EMPTY_RESULT_SET.getRow()).thenReturn(0);
        Mockito.when(EMPTY_RESULT_SET.isBeforeFirst()).thenReturn(false);

        FULL_RESULT_SET = Mockito.mock(ResultSet.class);

        // todo: add code
    }

    @Test
    public void testIsEmpty() {
        MercuryITSQLResponse mercuryITSQLResponse = new MercuryITSQLResponse(CONFIG_HOLDER, EMPTY_RESULT_SET);

        Assertions.assertTrue(mercuryITSQLResponse.isEmpty());
    }
}
