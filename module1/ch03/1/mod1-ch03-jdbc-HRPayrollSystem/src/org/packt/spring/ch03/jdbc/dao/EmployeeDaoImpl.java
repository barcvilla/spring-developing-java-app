package org.packt.spring.ch03.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.packt.spring.ch03.jdbc.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/recursos_humanos?useSSL=false";
	private static final String user = "root";
	private static final String password = "admin";
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private void registerDriver()
	{
		try
		{
			Class.forName(JDBC_DRIVER).newInstance();
		}
		catch(InstantiationException e) {}
		catch(IllegalAccessException e) {}
		catch(ClassNotFoundException e) {}
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		try
		{
			//1. Registramos el driver
			registerDriver();
			
			//2. Abrimos la conexion
			conn = DriverManager.getConnection(DB_URL, user, password);
			
			//3. Objeto PreparedStatement para enviar una consulta sql parametrizada
			ps = conn.prepareStatement("select * from persona where id_persona = ?");
			
			//4. Establecemos los parametros para el sql definido
			ps.setInt(1, id);
			
			//5. Ejecutamos el sql y retornamos un objeto Resultset
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				employee =  new Employee();
				employee.setId(rs.getInt("id_persona"));
				employee.setNombre(rs.getString("nombre"));
				employee.setApellidoPaterno(rs.getString("apellido_paterno"));
				employee.setApellidoMaterno(rs.getString("apellido_materno"));
				employee.setEmail(rs.getString("email"));
				employee.setTelefono(rs.getString("telefono"));
			}
			rs.close();
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
				catch(SQLException e) {}
			}
		}
		return employee;
	}

}
