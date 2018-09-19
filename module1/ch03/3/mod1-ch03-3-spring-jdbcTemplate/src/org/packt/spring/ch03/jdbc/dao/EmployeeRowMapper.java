package org.packt.spring.ch03.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.packt.spring.ch03.jdbc.model.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee obj = new Employee();
		obj.setId(rs.getInt("id_persona"));
		obj.setNombre(rs.getString("nombre"));
		obj.setApellidoPaterno(rs.getString("apellido_paterno"));
		obj.setApellidoMaterno(rs.getString("apellido_materno"));
		obj.setEmail(rs.getString("email"));
		obj.setTelefono(rs.getString("telefono"));
		
		return obj;
	}

}
