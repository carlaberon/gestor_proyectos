package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Usuario;

public class TareaDTO {

    private String name;
    private Proyecto project;
    private String priority;
    private Usuario user;
    private boolean estado; // FINALIZADO: TRUE, NOFINALIZADO: FALSE
    private String description;
    private LocalDateTime inicio; 
    private LocalDateTime fin;
    
    public TareaDTO(String name, Proyecto project, String priority, Usuario user, boolean estado, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
        super();
        this.name = name;
        this.project = project;
        this.priority = priority;
        this.user = user;
        this.estado = estado;
        this.description = descripcion;
        this.inicio = inicio;
        this.fin = fin;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proyecto getProject() {
        return project;
    }

    public void setProject(Proyecto project) {
        this.project = project;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }
}

