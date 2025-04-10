package com.devsenior.cdiaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devsenior.cdiaz.vo.Employee;

// DRY - Don't Repeat Yourself

// DAO - Data Access Object
public class EmployeeDao extends AbstractDao<Employee, Integer> {

    @Override
    public void save(Employee employee) {
        
        try {
            var conn = getConnection();
            var stmt = conn.createStatement();

            var format = """
                    INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id)
                    VALUES(nextval('employees_employee_id_seq'::regclass), '%s', '%s', '%s', '%s', '%s', %d, %d, %d, %d);
                    """;
            var sql = String.format(format, employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getPhoneNumber(), "2025-01-01", // employee.getHireDate(),
                    employee.getJobId(), employee.getSalary(), employee.getManagerId(),
                    employee.getDepartmentId());
            System.out.println(sql);
            stmt.execute(sql);

            stmt.close();
            conn.close();
            System.out.println("Empleado guardado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void update(Integer id, Employee employee) {
        try (var conn = getConnection();
                var stmt = conn.createStatement()) {

            var format = """
                    update employees
                    set first_name = '%s',
                        last_name = '%s',
                        email = '%s',
                        phone_number = '%s',
                        job_id = %d,
                        salary = %d,
                        manager_id = %d,
                        department_id = %d
                    where employee_id = %d
                    """;
            var sql = String.format(format, employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getPhoneNumber(), // employee.getHireDate(),
                    employee.getJobId(), employee.getSalary(), employee.getManagerId(),
                    employee.getDepartmentId(), id);
            System.out.println(sql);
            stmt.execute(sql);

            System.out.println("Empleado actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (var conn = getConnection();
                var stmt = conn.prepareStatement("DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?")) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Empleado eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        var employees = new ArrayList<Employee>();
        try (var conn = getConnection();
                var stmt = conn.prepareStatement("SELECT * from EMPLOYEES");
                var rset = stmt.executeQuery()) {

            while (rset.next()) {
                var employee = new Employee();
                employee.setEmployeeId(rset.getInt("employee_id"));
                employee.setFirstName(rset.getString("first_name"));
                employee.setLastName(rset.getString("last_name"));
                employee.setEmail(rset.getString("email"));
                employee.setPhoneNumber(rset.getString("phone_number"));
                employee.setDepartmentId(rset.getInt("department_id"));
                employee.setManagerId(rset.getInt("manager_id"));
                employee.setSalary(rset.getInt("salary"));
                employee.setJobId(rset.getInt("job_id"));
                // employee.setHireDate(LocalDate.of(2025, 1, 1));

                employees.add(employee);
            }
            System.out.println("Empleados listados exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al listar los usuarios: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }
}
