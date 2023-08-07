package community.redrover.mercuryit;

import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MercuryITSQLTest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TestPerson {

        private int id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestPerson person = (TestPerson) o;
            return id == person.id && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
    @SneakyThrows
    @Test
    public void testResponseDeserialization(){
        final TestPerson[] EXPECTED_PERSONS = {
                new TestPerson(1, "Alex"),
                new TestPerson(2, "Bob"),
                new TestPerson(3, "Carl"),
                new TestPerson(4, "Dylan")};
        final String TEST_FIELD_1 = "id";
        final String TEST_FIELD_2 = "name";

        MercuryITConfigHolder configHolder = MercuryIT.request(MercuryITContext.class).getConfigHolder();
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        MercuryITSQLResponse spyResponse = Mockito.spy(new MercuryITSQLResponse(configHolder,mockResultSet));

        Mockito.when(spyResponse.getNextRow())
                .thenReturn(Map.of(TEST_FIELD_1, EXPECTED_PERSONS[0].getId(), TEST_FIELD_2, EXPECTED_PERSONS[0].getName()))
                .thenReturn(Map.of(TEST_FIELD_1, EXPECTED_PERSONS[1].getId(), TEST_FIELD_2, EXPECTED_PERSONS[1].getName()))
                .thenReturn(Map.of(TEST_FIELD_1, EXPECTED_PERSONS[2].getId(), TEST_FIELD_2, EXPECTED_PERSONS[2].getName()))
                .thenReturn(Map.of(TEST_FIELD_1, EXPECTED_PERSONS[3].getId(), TEST_FIELD_2, EXPECTED_PERSONS[3].getName()))
                .thenReturn(null);

        TestPerson actualPerson1 = spyResponse.getNextRow(TestPerson.class);
        List<TestPerson> actualPersons2Plus = spyResponse.getRows(TestPerson.class);

        Assertions.assertEquals(EXPECTED_PERSONS[0], actualPerson1);
        Assertions.assertEquals(EXPECTED_PERSONS[1], actualPersons2Plus.get(0));
        Assertions.assertEquals(EXPECTED_PERSONS[2], actualPersons2Plus.get(1));
        Assertions.assertEquals(EXPECTED_PERSONS[3], actualPersons2Plus.get(2));
        Assertions.assertNull(spyResponse.getNextRow());
    }


}
