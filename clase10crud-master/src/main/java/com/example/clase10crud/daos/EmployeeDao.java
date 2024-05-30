package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employee;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;

public class EmployeeDao {
    private static final String username = "root";
    private static final String password = "1234";

    public ArrayList<Employee> list(int limit,int offset){
        ArrayList<Employee> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/employees";
        String sql = "select * from employees limit ? offset ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,limit);
            pstmt.setInt(2,offset);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.(rs.getString(2));
                    employee.setFirstName(rs.getString(3));
                    employee.setLastName(rs.getString(4));
                    employee.setEmail(rs.getString(5));
                    employee.setHireDate(rs.getString(6));
                    employee.setManagerId(rs.getString(7));
                    lista.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void create(Employee employee){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "insert into employees (employee_id, first_name, last_name, email, hire_date, manager_id) values(?,?,?,?,?,?)";

        try(Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,employee.getEmployeeId());
            pstmt.setString(2,employee.getFirstName());
            pstmt.setString(3,employee.getLastName());
            pstmt.setString(4,employee.getEmail());
            pstmt.setString(5,employee.getHireDate());
            pstmt.setString(6,employee.getManagerId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee buscarPorId(String id){

        Employee employee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "select * from employees where employee_id = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpNo(rs.getInt(1));
                    employee.setBirthDate(rs.getString(2));
                    employee.setFirstName(rs.getString(3));
                    employee.setLastName(rs.getString(4));
                    employee.setGender(rs.getString(5));
                    employee.setHireDate(rs.getString(6));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public void actualizar(Employee employee){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "update employees set birth_date=?, first_name=?, last_name=?, gender=?, hire_date=? where emp_no=?";

        try(Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,employee.getBirthDate());
            pstmt.setString(2,employee.getFirstName());
            pstmt.setString(3,employee.getLastName());
            pstmt.setString(4,employee.getGender());
            pstmt.setString(5,employee.getHireDate());
            pstmt.setInt(6,employee.getEmpNo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String employeeNo) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "delete from employees where emp_no = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,employeeNo);
            pstmt.executeUpdate();

        }
    }
    public ArrayList<Employee> searchByName(String name,int limit,int offset) {
        ArrayList<Employee>listaEmpleados=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/employees";
        String sql = "select * from employees where lower(first_name) like ? or lower(last_name) like ? limit ? offset ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,"%"+name+"%");
            pstmt.setString(2,"%"+name+"%");
            pstmt.setInt(3,limit);
            pstmt.setInt(4,offset);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {

                    e.setEmployeeId(rs.getInt(1));
                    e.setFirstName(rs.getString(2));
                    e.setLastName(rs.getString(3));
                    e.setEmail(rs.getString(4));
                    e.setHireDate(rs.getString(5));
                    e.setManagerId(rs.getString(6));

                    listaEmpleados.add(e);
                }return listaEmpleados;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int searchLastId() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "select emp_no from employees order by emp_no desc limit 1";

        try(Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
