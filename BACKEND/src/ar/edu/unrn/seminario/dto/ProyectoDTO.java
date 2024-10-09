package ar.edu.unrn.seminario.dto;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unrn.seminario.modelo.Miembro;
import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Tarea;
import ar.edu.unrn.seminario.modelo.Usuario;

public class ProyectoDTO {

    private String nombre;
    private String usuarioPropietario;
    private String prioridad;
    private boolean estado; //ACTIVO= false; FINALIZADO = true
    private Set<String> miembros = new HashSet<>();
    private String descripcion; 
    private Set<String> proyectos = new HashSet<>();
    private Set<String> tareas = new HashSet<>();
    
    public ProyectoDTO(String nombreProyecto, String usuarioPropietario, boolean estado, String prioridad, String descripcion) {
        this.nombre = nombreProyecto;
        this.usuarioPropietario = usuarioPropietario;
        this.prioridad = prioridad;
        this.estado = estado;
        this.descripcion = descripcion;
    }
    
    public ProyectoDTO(String nombre2, String descripcion2) {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
        return this.nombre;
    }
    
    public String getUsuarioPropietario() {
        return usuarioPropietario;
    }

    public void setUsuarioPropietario(String usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Set<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(Set<String> miembros) {
        this.miembros = miembros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<String> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<String> proyectos) {
        this.proyectos = proyectos;
    }

    public Set<String> getTareas() {
        return tareas;
    }

    public void setTareas(Set<String> tareas) {
        this.tareas = tareas;
    }
    
    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "nombre='" + nombre + '\'' +
                ", usuarioPropietario='" + usuarioPropietario + '\'' +
                ", estado=" + (estado ? "FINALIZADO" : "ACTIVO") +
                ", prioridad='" + prioridad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

	public void agregarTarea(TareaDTO nuevaTarea) {
		// TODO Auto-generated method stub
		
	}
}
