package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unrn.seminario.dto.EventoDTO;
import ar.edu.unrn.seminario.dto.MiembroDTO;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.TareaDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Evento;
import ar.edu.unrn.seminario.modelo.Miembro;
import ar.edu.unrn.seminario.modelo.Plan;
import ar.edu.unrn.seminario.modelo.Proyecto;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Tarea;
import ar.edu.unrn.seminario.modelo.Usuario;

public class MemoryApi implements IApi {

	private Set<Rol> roles = new HashSet<>();
	private Set<Usuario> usuarios = new HashSet<>();
	private List<Tarea> tareas = new ArrayList<>();
	private Set<Proyecto> proyectos = new HashSet<>();
	private Set<Evento> eventos = new HashSet<>();
	private Set<Plan> planeSet = new HashSet<>();
	

	public MemoryApi() {
		//Set<Proyecto> proyectos
		// datos iniciales
		this.roles.add(new Rol(1, "PROPIETARIO"));
		this.roles.add(new Rol(2, "OBSERVADOR"));
		this.roles.add(new Rol(3, "COLABORADOR"));
		inicializarUsuarios();
		//this.proyectos = proyectos;
	}

	private void inicializarUsuarios() {
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);
		registrarUsuario("ldifabio", "4", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("bjgorosito", "1234", "bjgorosito@unrn.edu.ar", "Bruno", 3);

	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

		Rol role = this.buscarRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		this.usuarios.add(usuario);

	}
	


	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			dtos.add(new UsuarioDTO(u.getUsuario(),u.getContrasena(), u.getNombre(), u.getEmail(), u.getRol(), u.isActivo()));
		}
		return dtos;
	}

	@Override
	public UsuarioDTO obtenerUsuario(String username) {
		UsuarioDTO user;
		for(Usuario u: this.usuarios) {
			if (u.getNombre() == username) {
				
				user = new UsuarioDTO(u.getUsuario(),u.getContrasena(), u.getNombre(), u.getEmail(), u.getRol(), u.isActivo());
				
				return user;
			}
		}
		
		return null;
	}

	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RolDTO> obtenerRoles() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			if (r.isActivo())
				dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		// TODO Auto-generated method stub
		Rol rol = new Rol(codigo, descripcion);
		this.roles.add(rol);
	}

	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);
		user.activar();
	}

	@Override
	public void desactivarUsuario(String usuario) {
		// TODO: desactivar usuario
		Usuario user = this.buscarUsuario(usuario);
		user.desactivar();
	}

	private Rol buscarRol(Integer codigo) {
		for (Rol rol : roles) {
			if (rol.getCodigo().equals(codigo))
				return rol;
		}
		return null;
	}

	private Usuario buscarUsuario(String usuario) {
		for (Usuario user : usuarios) {
			if (user.getUsuario().equals(usuario))
				return user;
		}
		return null;
	}

	@Override
	public void registrarTarea(String name, String project, String priority, String user, boolean estado,
			String descripcion) { //falta inicio y fin: estan en null
	
		Tarea tarea = new Tarea(name, project, priority, user, estado, descripcion, null, null );
		this.tareas.add(tarea);
	}

	@Override
	public List<TareaDTO> obtenerTareas() {
		// TODO Auto-generated method stub
		List<TareaDTO> tareas = new ArrayList<>();
		for (Tarea t : this.tareas) {
			//agregar parametros de la tarea
			tareas.add(new TareaDTO(null, null, null, null, false, null, null, null));
			
		}
		return tareas;
	}
	
	@Override
	public void crearEvento(LocalDateTime fecha, LocalDateTime inicio, LocalDateTime fin, String descripcion) {
		// TODO Auto-generated method stub
		Evento evento = new Evento(fecha, inicio, fin, descripcion);
		this.eventos.add(evento);
	}

	@Override
	public List<EventoDTO> obtenerEventos() {
		List<EventoDTO> eventosDTO = new ArrayList<>();
		for (Evento evento : this.eventos) {
	        // Convertir cada Evento en EventoDTO
	        EventoDTO eventoDTO = new EventoDTO(
	            evento.getFecha(),
	            evento.getInicio(),
	            evento.getFin(),
	            evento.getDescripcion()
	        );
	        
	        eventosDTO.add(eventoDTO); // Agregar a la lista de DTOs
	    }
		return eventosDTO;
	}
	
	// Implementación del método para obtener la lista de proyectos como DTO
    @Override
    public List<ProyectoDTO> obtenerProyectos() {
        List<ProyectoDTO> dtos = new ArrayList<>();
        for (Proyecto p : this.proyectos) {
            dtos.add(new ProyectoDTO(p.getNombre(), p.getUsuarioPropietario().getNombre(), p.getEstado(), p.getPrioridad(), p.getDescripcion()));
        }
        return dtos;
    }
	
    // Implementación del método para asignar prioridad a un proyecto
    @Override
    public void asignarPrioridad(String nombreProyecto, String prioridad) {
        for (Proyecto p : this.proyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                p.setPrioridad(prioridad);
                break;
            }
        }
    }
    @Override
    public int compare(Proyecto p1, Proyecto p2) {
        // Ordenar por prioridad (alta, media, baja)
        List<String> prioridades = Arrays.asList("alta", "media", "baja");
        return Integer.compare(prioridades.indexOf(p1.getPrioridad()), prioridades.indexOf(p2.getPrioridad()));
    }

	@Override
	public void crearPlan(String nombre, Proyecto pertenece) {
		// Verificar si el proyecto ya tiene un plan asignado
		
		// Crear una nueva instancia de Plan con el nombre y el proyecto
	    Plan nuevoPlan = new Plan(nombre, pertenece);
	    pertenece.setPlan(nuevoPlan);
	}

	@Override
	public void crearProyecto(String nombre, Usuario usuarioPropietario, boolean estado, String descripcion) {
		// Crear un nuevo proyecto con los parámetros recibidos
	    Proyecto nuevoProyecto = new Proyecto(nombre, usuarioPropietario, estado, descripcion);
	    
	    // Agregar el proyecto a la colección de proyectos
	    this.proyectos.add(nuevoProyecto);
	}
    
	@Override
	public void eliminarProyecto(String nombreProyecto) {
	    Proyecto proyectoAEliminar = null;

	    // Buscar el proyecto por nombre
	    for (Proyecto proyecto : this.proyectos) {
	        if (proyecto.getNombre().equals(nombreProyecto)) {
	            proyectoAEliminar = proyecto;
	            break;
	        }
	    }

	    // Si se encuentra el proyecto, eliminarlo
	    if (proyectoAEliminar != null) {
	        this.proyectos.remove(proyectoAEliminar);
	        System.out.println("El proyecto '" + nombreProyecto + "' ha sido eliminado.");
	    }	 
	}

	@Override
	public void modificarProyecto(String nombreProyecto, ProyectoDTO proyectoModificado) {
	    // Buscar el proyecto por nombre
	    Proyecto proyectoExistente = buscarProyectoPorNombre(nombreProyecto);
	    
	    // Modificar los campos del proyecto existente con los valores del DTO
	    proyectoExistente.setNombre(proyectoModificado.getNombre());
	    
	    // Buscar el usuario propietario por nombre (asumiendo que el DTO guarda el nombre del usuario)
	    Usuario usuarioPropietario = buscarUsuarioPorNombre(proyectoModificado.getUsuarioPropietario());
	    /*if (usuarioPropietario != null) {
	        proyectoExistente.setUsuarioPropietario(usuarioPropietario);
	    } else {
	        throw new IllegalArgumentException("No se encontró el usuario propietario con nombre: " + proyectoModificado.getUsuarioPropietario());
	    }*/

	    // Modificar otros atributos del proyecto
	    proyectoExistente.setPrioridad(proyectoModificado.getPrioridad());
	    proyectoExistente.setEstado(proyectoModificado.isEstado());
	    proyectoExistente.setDescripcion(proyectoModificado.getDescripcion());
	    
	    // Actualizar miembros utilizando el MiembroDTO
	    Set<Miembro> miembrosActualizados = convertirMiembrosDTOAMiembros(proyectoModificado.getMiembros());
	    proyectoExistente.setMiembros(miembrosActualizados);
	    
	    // Actualizar tareas utilizando el TareaDTO
	    Set<Tarea> tareasActualizadas = convertirTareasDTOATareas(proyectoModificado.getTareas());
	    proyectoExistente.setTareas(tareasActualizadas);
	}
	
	private Set<Miembro> convertirMiembrosDTOAMiembros(Set<MiembroDTO> miembrosDTO) {
	    Set<Miembro> miembros = new HashSet<>();
	    
	    for (MiembroDTO dto : miembrosDTO) {
	        Miembro miembro = new Miembro(dto.getCodigo());
	        miembro.setFechaBaja(dto.getFechaBaja());
	        miembros.add(miembro);
	    }
	    
	    return miembros;
	}
	
	private Set<Tarea> convertirTareasDTOATareas(Set<TareaDTO> tareasDTO) {
	    Set<Tarea> tareas = new HashSet<>();
	    
	    for (TareaDTO dto : tareasDTO) {
	        Tarea tarea = new Tarea(
	            dto.getName(),
	            dto.getProject(),
	            dto.getPriority(),
	            dto.getUser().getNombre(),  // Asume que Usuario tiene un método getNombre()
	            dto.isEstado(),
	            dto.getDescription(),
	            dto.getInicio(),
	            dto.getFin()
	        );
	        tareas.add(tarea);
	    }
	    
	    return tareas;
	}
		
	
    
}
