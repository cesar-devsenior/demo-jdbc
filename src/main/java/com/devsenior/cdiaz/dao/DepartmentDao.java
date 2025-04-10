package com.devsenior.cdiaz.dao;

import java.sql.SQLException;
import java.util.List;

import com.devsenior.cdiaz.vo.Department;

public class DepartmentDao extends AbstractDao<Department, Integer> {

    @Override
    public void save(Department department) {
        try (var conn = getConnection();
                var stmt = conn.prepareStatement("INSERT INTO departments VALUES (1, ?, ?);")) {

            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getLocationId());
            stmt.execute();

        } catch (SQLException e) {
            // TODO: handle exception
        }

    }

    @Override
    public void update(Integer id, Department value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Department> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Department findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
