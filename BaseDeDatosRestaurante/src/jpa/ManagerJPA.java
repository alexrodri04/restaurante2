package jpa;

import java.util.List;


import pojos.Rol;
import pojos.Usuario;

public interface ManagerJPA {
	void connect();

	void disconnect();
	
	Usuario searchUsuario(String email);
	
	List<Usuario> searchUsuarioList(String email);
	
	List<Rol> getRoles();

	Rol getRolById(int rolId);

	void addUsuario(Usuario usuario);

	void addRol(Rol rol);

	Usuario checkPass(String email, String pass);
	
	void updateUsuario(Usuario user, String nombre);
	
	void deleteUsuario(Usuario user);
	
}

