package org.packt.spring.ch03.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.packt.spring.ch03.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//mediante la anotacion @Repository Spring automaticamente la regista como un Spring Bean
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private DataSource dataSource;
	
	private static Connection conn = null;
	private static Statement stm = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	@Override
	public Employee getEmployeeById(int id) 
	{
		Employee employee = null;
		try
		{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select * from persona where id_persona = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setNombre(rs.getString(2));
				employee.setApellidoPaterno(rs.getString(3));
				employee.setApellidoMaterno(rs.getString(4));
				employee.setEmail(rs.getString(5));
				employee.setTelefono(rs.getString(6));
			}
			rs.close();
			ps.close();
		}
		catch(SQLException e)
		{
			throw new RuntimeException();
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					
				}
			}
		}
		return employee;
	}

	@Override
	public void addEmployee(Employee employee) {
		try
		{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("insert into persona (nombre, apellido_paterno, apellido_materno, email, telefono) values(?, ?, ?, ?, ?)");
			ps.setString(1, employee.getNombre());
			ps.setString(2, employee.getApellidoPaterno());
			ps.setString(3, employee.getApellidoMaterno());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getTelefono());
			ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					
				}
			}
		}
		
	}
}
