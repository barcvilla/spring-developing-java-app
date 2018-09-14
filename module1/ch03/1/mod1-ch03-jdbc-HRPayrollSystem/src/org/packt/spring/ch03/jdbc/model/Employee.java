package org.packt.spring.ch03.jdbc.model;

public class Employee {
	
	private int id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String telefono;
	
	public Employee() {}
	
	public Employee(int id) {setId(id);}
	
	public Employee(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono)
	{
		setNombre(nombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
		setEmail(email);
		setTelefono(telefono);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "Employee id: " + id + " Nombre: " + nombre + " Ape. Paterno: " + apellidoPaterno + " Ape.Materno: " + apellidoMaterno + " Email: " + email + " Telefono: " + telefono ;
	}
}
