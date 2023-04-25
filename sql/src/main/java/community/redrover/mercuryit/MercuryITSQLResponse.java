package community.redrover.mercuryit;

import lombok.SneakyThrows;

import java.lang.ref.Cleaner;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MercuryITSQLResponse extends MercuryITObject<MercuryITSQLResponse> {

    private static class ResultSetHolder {

        private final ResultSet resultSet;

        public ResultSetHolder(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

        @SneakyThrows
        protected void close() {
            resultSet.close();
        }
    }

    private final ResultSet resultSet;

    MercuryITSQLResponse(ResultSet resultSet) {
        super(null);
        this.resultSet = resultSet;

		Cleaner cleaner = Cleaner.create();
        cleaner.register(this, new ResultSetHolder(resultSet)::close);
    }

    private <T> Constructor<T> getConstructor(Class<T> clazz, int paramCount) {
        Constructor<?> constructor = null;
        for (Constructor<?> item : clazz.getConstructors()) {
            if (item.getParameterCount() == paramCount) {
                if (constructor != null) {
                    throw new RuntimeException("Found multiple constructors with the same number of parameters");
                }
                constructor = item;
            }
        }

        return (Constructor<T>) constructor;
    }

    @SneakyThrows
    public boolean isEmpty() {
        return this.resultSet.isFirst() && this.resultSet.isLast();
    }

    @SneakyThrows
    private Object getField(String name) {
        return resultSet.getObject(name);
    }

    @SneakyThrows
    private <T> T getCurrentRow(Constructor<T> constructor, String... fields) {
        return constructor.newInstance(Arrays.stream(fields).map(this::getField).toArray(Object[]::new));
    }

    @SneakyThrows
    public <T> T getNextRow(Class<T> clazz, String... fields) {
        if (resultSet.next()) {
            return getCurrentRow(getConstructor(clazz, fields.length), fields);
        } else {
            return null;
        }
    }

    @SneakyThrows
    public <T> List<T> getRows(Class<T> clazz, String... fields) {
        List<T> resultList = new ArrayList<>();
        Constructor<T> constructor = getConstructor(clazz, fields.length);

        while (resultSet.next()) {
            resultList.add(getCurrentRow(constructor, fields));
        }

        return resultList;
    }
}
