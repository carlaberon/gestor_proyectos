package ar.edu.unrn.seminario.dto;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unrn.seminario.modelo.Miembro;
import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Tarea;
import ar.edu.unrn.seminario.modelo.Usuario;

public class ProyectoDTO {

    private String nombre;
    private String id;
    private Usuario usuarioPropietario;
    private String prioridad;
    private boolean estado; //ACTIVO= false; FINALIZADO = true
    private Set<String> miembros = new HashSet<>();
    private String descripcion; 
    private Set<String> proyectos = new HashSet<>();
    private Set<String> tareas = new HashSet<>();
    
    public ProyectoDTO(String nombreProyecto, Usuario usuarioPropietario, String id, boolean estado, String prioridad, String descripcion) {
        this.nombre = nombreProyecto;
        this.usuarioPropietario = usuarioPropietario;
        this.prioridad = prioridad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.id = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public Usuario getUsuarioPropietario() {
        return usuarioPropietario;
    }

    public void setUsuarioPropietario(Usuario usuarioPropietario) {
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
    
    public String getId() {
    	return id;
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
}
