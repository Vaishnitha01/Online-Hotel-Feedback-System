package Jdbc;

import java.sql.SQLException;

public abstract class AbstractDAO<T> {
    public abstract void save(T item) throws SQLException;
}
