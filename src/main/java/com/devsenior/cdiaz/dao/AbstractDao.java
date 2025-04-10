package com.devsenior.cdiaz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<VO,ID> {
    protected Connection getConnection() throws SQLException {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "admin123";
        return DriverManager.getConnection(url, user, pass);
    }

    public abstract void save(VO value);

    public abstract void update(ID id, VO value);

    public abstract void delete(ID id);

    public abstract List<VO> findAll();

    public abstract VO findById(ID id);

}
