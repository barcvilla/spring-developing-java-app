package org.packt.spring.ch03.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.packt.spring.ch03.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int getEmployeeCount() {
		String sql = "select count(*) from persona";
		int count = 0;
		return count = jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into persona(nombre, apellido_paterno, apellido_materno, email, telefono) values(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, employee.getNombre(), employee.getApellidoPaterno(), employee.getApellidoMaterno(), employee.getEmail(), employee.getTelefono());
	}

	@Override
	public int deleteEmployee(int empId) {
		String sql = "delete from persona where id_persona = ?";
		return jdbcTemplate.update(sql, empId);
	}

	@Override
	public Employee getEmployeeById(int empId) {
		String sql = "select * from persona where id_persona = ?";
		return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), empId);
	}

	@Override
	public void EmployeeBatchUpdate(List<Employee> employees) {
		String sql = "insert into persona(nombre, apellido_paterno, apellido_materno, email, telefono) values(?, ?, ?, ?, ?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getNombre());
				ps.setString(2, employee.getApellidoPaterno());
				ps.setString(3, employee.getApellidoMaterno());
				ps.setString(4, employee.getEmail());
				ps.setString(5, employee.getTelefono());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return employees.size();
			}
		});
	}

}
