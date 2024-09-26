package ar.edu.unrn.seminario.dto;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unrn.seminario.modelo.Miembro;
import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Tarea;
import ar.edu.unrn.seminario.modelo.Usuario;

public class ProyectoDTO {

	private String nombreProyecto;
	private String usuarioPropietario;
	private String prioridad;
	private boolean estado; //ACTIVO= false; FINALIZADO = true
	private Set<String> miembros = new HashSet<>();
	private String descripcion; 
	private Set<String> proyectos = new HashSet<>();
	private Set<String> tareas = new HashSet<>();
	
	//falta lista miembros, lista proyectos, lista tareas
	public ProyectoDTO(String nombreProyecto, String usuarioPropietario, String prioridad, boolean estado) {
		this.nombreProyecto = nombreProyecto;
		this.usuarioPropietario = usuarioPropietario;
		this.prioridad = prioridad;
		this.estado = estado;
	}
	
	public String getNombre() {
		return this.nombreProyecto;
	}
}
