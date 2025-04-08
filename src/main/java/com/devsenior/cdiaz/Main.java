package com.devsenior.cdiaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.devsenior.cdiaz.dao.EmployeeDao;
import com.devsenior.cdiaz.vo.Employee;

public class Main {

    public static void main(String[] args) {
        var employee = new Employee();
        employee.setFirstName("Cesar");
        employee.setLastName("Diaz");
        employee.setEmail("cesar@diaz.com");
        employee.setPhoneNumber("321123456");
        employee.setDepartmentId(1);
        employee.setManagerId(100);
        employee.setSalary(15000);
        employee.setJobId(1);
        employee.setHireDate(LocalDate.of(2025, 1, 1));

        var employeeDao = new EmployeeDao();
        // prueba de Insert
        // employeeDao.saveEmployee(employee);

        // prueba de Update
        // employee.setLastName("Diaz Arriaga");
        // employee.setPhoneNumber("3101234567");
        // employeeDao.updateEmployee(1, employee);

        // prueba de Delete
        // employeeDao.deleteEmployee(1);

        // prueba de Select
        employeeDao.findAllEmployees().forEach(System.out::println);

    }

    public static void testDataBaseConnection() {
        var employees = new ArrayList<Employee>();

        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "admin123";
        try {
            // Driver manager que nos de una conexion a la base de datos
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión éxitosa");

            // Preparar una sentencia para lanzar una instruccion SQL a la BD
            Statement stmt = conn.createStatement();
            // stmt.execute("DELETE FROM employees WHERE employee_id = 9999")
            ResultSet rset = stmt.executeQuery("SELECT email, salary, employee_id as id, first_name FROM employees");
            while (rset.next()) {
                var employee = new Employee();
                employee.setEmployeeId(rset.getInt("id"));
                employee.setFirstName(rset.getString("first_name"));
                employee.setEmail(rset.getString("email"));
                employee.setSalary(rset.getInt("salary"));

                employees.add(employee);
            }
            // Cerrar conexiones (flujos)
            rset.close();
            stmt.close();
            conn.close();        

            employees.forEach(System.out::println);

            // for (int i = 0; i < ids.size(); i++) {
            //     System.out.printf("El empleado con id %d se llama %s con email %s y gana $ %,d%n",
            //         ids.get(i), names.get(i), emails.get(i), salaries.get(i));
            // }

        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos");
            System.err.println(e.getMessage());
        }
    }
}