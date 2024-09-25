package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

public class Tarea {

    private String nombre;
    private Proyecto proyecto;
    private String prioridad;
    private Usuario usuario;
    private boolean estado; // FINALIZADO: TRUE, NOFINALIZADO: FALSE
    private String descripcion;
    private LocalDateTime inicio; 
    private LocalDateTime fin;

    public Tarea(String nombretarea, Proyecto proyecto, String prioridad, Usuario usuario, boolean estado, String descripcion, LocalDateTime inicio, LocalDateTime fin)
    {
        this.nombre = nombretarea;
        this.proyecto = proyecto;
        this.prioridad = prioridad;
        this.usuario = usuario;
        this.estado = estado;
        this.descripcion = descripcion; 
        this.inicio = inicio;
        this.fin = fin; 
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isEstado() {
        return estado; // Devuelve si la tarea está realizada
    }
   

	public String obtenerEstado() {
		return isEstado() ? "REALIZADA" : "ENCURSO";
	}

	public void finalizarTarea() {
		if (!isEstado())
			this.estado = true;
	}

	public void tareaEnCurso() {
		if (isEstado())
			this.estado = false;
	}

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (!(obj instanceof Tarea)) return false; 
        Tarea other = (Tarea) obj; 
        return nombre.equals(other.nombre) && usuario.equals(other.usuario); 
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
        int result = 1;
        result = prime * result + (nombre == null ? 0 : nombre.hashCode());
        result = prime * result + (usuario == null ? 0 : usuario.hashCode()); 
        return result; 
    }


}

