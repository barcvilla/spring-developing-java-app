package org.packt.spring.chapter6.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.packt.spring.chapter6.hibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * La anotacion @Repository, convierte a la clase en un DAO la cual permite Create-Recuperar-Actualizar-Eliminar
 * registro en una tabla.
 */
@Repository
@Transactional(readOnly = true) //la clase y todos sus metodos son de acceso read-only
public class EmployeeDaoImpl implements EmployeeDao {
	
	/**
	 * Interface de Hibernate que ofrece metodos para encontrar, guardar, eliminar objetos en una base de datos relacional
	 * con @Autowired automaticamente inicializamos el SessioFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Recupera todos los registros de empleados. El metodo obtiene un Session que es la interface entre hibernate y Java
	 * sessionFactory.openSession() 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		/**
		 * Interface Session es utilizado para atender las conexiones fisicas con la BD. session es iniciado cada vez que
		 * se necesita interactuar con la BD. Tambien, la persistencia de objetos son guardados y recuperados a traves
		 * de un objeto Session.
		 */
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

	@Override
	@Transactional(readOnly = false)
	public int updateEmployee() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Employee E set E.firstName = :name WHERE id = :employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("name", "Shashi");
		query.setParameter("employee_id", 3);
		int result = query.executeUpdate();
		return result;
	}

}
