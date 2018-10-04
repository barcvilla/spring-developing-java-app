package org.packt.spring.ch06.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.packt.spring.ch06.hibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Employee e";
		
		// query get software engineer using parameter 1st type
		String jobTitle = "Software Engineer";
		String hql1 = "from Employee e where e.jobTitle = '" + jobTitle + "'";
		
		// query get Author using parameter 2nd type
		String hql2 = "from Employee e where e.jobTitle = :jobTitle";
		
		//query order by salary desc
		String hql3 = "from Employee e order by e.salary desc";
		
		Query<Employee> query = session.createQuery(hql);
		//query.setParameter("jobTitle", jobTitle);
		List<Employee> employees = query.list();
		return employees;
	}
	
	@Transactional(readOnly = false)
	public void insertEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}
	
	@Transactional(readOnly = false)
	public int updateEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "UPDATE Employee e SET e.firstName = :firstName, e.lastName = :lastName, e.jobTitle = :jobTitle, e.department = :department, e.salary = :salary WHERE e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("firstName", employee.getFirstName());
		query.setParameter("lastName", employee.getLastName());
		query.setParameter("jobTitle", employee.getJobTitle());
		query.setParameter("department", employee.getDepartment());
		query.setParameter("salary", employee.getSalary());
		query.setParameter("id", employee.getId());
		int result = query.executeUpdate();
		return result;
	}

	@Transactional(readOnly = false)
	public int deleteEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from Employee e where e.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", employee.getId());
		int result = query.executeUpdate();
		return result;
	}
	
	
	public Employee getEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Employee e where e.id = :id";
		Query<Employee> query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	

}
