package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
public interface IApi {
	List<ProyectoDTO> obtenerProyectos();
	// Método para asignar la prioridad de un proyecto
    void asignarPrioridad(String nombreProyecto, String prioridad);
    public int compare(Proyecto p1, Proyecto p2);
    
	void registrarUsuario(String username, String password, String email, String nombre, Integer rol);

	UsuarioDTO obtenerUsuario(String username);

	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles();

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripcion, boolean estado); // crear el objeto de dominio �Rol�

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.
	
	void registrarTarea(String name, Proyecto project, String priority, Usuario user, boolean estado, String descripcion, LocalDateTime inicio, LocalDateTime fin);
}
