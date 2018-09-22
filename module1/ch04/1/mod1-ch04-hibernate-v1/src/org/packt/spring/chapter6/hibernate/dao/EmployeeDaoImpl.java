package org.packt.spring.chapter6.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.packt.spring.chapter6.hibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //la clase es un DAO
@Transactional(readOnly = true) //la clase y todos sus metodos son de acceso read-only
public class EmployeeDaoImpl implements EmployeeDao {
	
	//automaticamente inicializamos el SessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.openSession();
		String hql = "from Employee";
		Query query = session.createQuery(hql);
		List<Employee> emList = query.list();
		//Spring cierra la session por nosotros
		return emList;
	}

	@Override
	@Transactional(readOnly = false) //esta anotacion nos permite realizar la operacion INSERT
	public void insertEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		session.save(employee);
	}

}
