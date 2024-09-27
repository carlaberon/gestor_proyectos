package ar.edu.unrn.seminario.dto;

public class ProyectoDTO {

    private String nombre;
    private String usuarioPropietario; // Puede ser el nombre o el ID del propietario
    private boolean estado; // ACTIVO = false, FINALIZADO = true
    private String prioridad;
    private String descripcion;

    public ProyectoDTO(String nombre, String usuarioPropietario, boolean estado, String prioridad, String descripcion) {
        this.nombre = nombre;
        this.usuarioPropietario = usuarioPropietario;
        this.estado = estado;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioPropietario() {
        return usuarioPropietario;
    }

    public void setUsuarioPropietario(String usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString() para facilitar la visualización del DTO

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

