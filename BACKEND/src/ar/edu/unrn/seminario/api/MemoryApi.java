package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
	//private Set<Plan> planeSet = new HashSet<>();
	private List<TareaDTO> tareasDTO;
	

	private Map<String, List<TareaDTO>> tareasPorProyecto = new HashMap<>();


	public MemoryApi() {
		//Set<Proyecto> proyectos
		// datos iniciales
		this.roles.add(new Rol(1, "PROPIETARIO"));
		this.roles.add(new Rol(2, "OBSERVADOR"));
		this.roles.add(new Rol(3, "COLABORADOR"));
		inicializarUsuarios();
		inicializarProyecto();

		
	}
	
	private void inicializarProyecto() {
	    // Crear algunos usuarios para asignar a los proyectos
	    Usuario user1 = new Usuario("HernanPro", "12", "eze@gmail.com", "Hernan", this.buscarRol(2)); // Observador
	    Usuario user2 = new Usuario("bjgorosito", "1234", "bjgorosito@unrn.edu.ar", "Bruno", this.buscarRol(3)); // Colaborador
	    Usuario user3 = new Usuario("Tomas", "12345", "admin@unrn.edu.ar", "Admin", this.buscarRol(1)); // Propietario

	    // Crear proyectos con diferentes prioridades, usuarios asignados * y una lista de tareas
	
	    crearProyecto("Sistema de Gestión de Tareas", user1, true,"media", "Sistema para gestionar tareas en equipo.");
	    LocalDateTime inicio = LocalDateTime.now();
	    Tarea unaTarea = new Tarea("tarea1","Sistema de Gestión de Tareas","alta", user1.getNombre(), false, "descripcion", inicio, inicio); 
	    añadirTareaAProyecto("Sistema de Gestión de Tareas", unaTarea);
	    
	  
	    //CREAR LISTA DE TAREAS
	    crearProyecto("Aplicación de votos", user2, false,"alta", "Aplicación para contar los votos de la municipalidad");
	  //CREAR LISTA DE TAREAS
	    crearProyecto("La gestion de eventos", user3, true,"baja", "Proyecto para desarrollar gestion de los eventos de ");
	  //CREAR LISTA DE TAREAS
	    crearProyecto("Parciales", user1, false,"media", "Informacion sobre como completar la informacion de los parciales de la carrera");
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
		for(Usuario u: this.usuarios) {
			if (u.getUsuario().equals(username)) {
				
				UsuarioDTO user = new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(), u.getEmail(), u.getRol(), u.isActivo());
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

	public void registrarTarea(String name, String project, String priority, Usuario user, boolean estado, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
	    Tarea tarea = new Tarea(name, project, priority, user.getNombre(), estado, descripcion, inicio, fin);
	    this.tareas.add(tarea); // Agrega la tarea a la lista de tareas
	}
	
	public List<TareaDTO> obtenerTareas() {
	    List<TareaDTO> tareasDTO = new ArrayList<>();
	    for (Tarea t : this.tareas) {  // Asegúrate de que `this.tareas` tiene elementos
	        tareasDTO.add(new TareaDTO(t.getNombre(), t.getProyecto(), t.getPrioridad(), t.getUsuario(), t.isEstado(), t.getDescripcion(), null, null));
	    }
	    return tareasDTO;
	}
	
	public void añadirTareaAProyecto(String proyecto, Tarea unaTarea) {

		TareaDTO tarea = new TareaDTO(
				unaTarea.getNombre(), 
				unaTarea.getProyecto(), 
				unaTarea.getPrioridad(), 
				unaTarea.getUsuario(), 
				unaTarea.isEstado(), 
				unaTarea.getDescripcion(), 
				unaTarea.getInicio(), 
				unaTarea.getFin());

		tareasPorProyecto.putIfAbsent(proyecto, new ArrayList<>());
		tareasPorProyecto.get(proyecto).add(tarea);
	}
	
	public List<TareaDTO> obtenerTareasPorProyecto(String nombreProyecto) throws RuntimeException {

		return tareasPorProyecto.getOrDefault(nombreProyecto, new ArrayList<>());
    }
    
	/*public List<TareaDTO> obtenerTareasPorProyecto(String nombreProyecto) {
		
	    return tareas.stream()
	        .filter(t -> t.getProyecto() != null && t.getProyecto().equals(nombreProyecto))
	        .collect(Collectors.toList());
	}*/
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
            dtos.add(new ProyectoDTO(p.getNombre(), p.getUsuarioPropietario().getNombre(), p.getEstado(), p.getPrioridad1(), p.getDescripcion()));
        }
        return dtos;
    }
	
    // Implementación del método para asignar prioridad a un proyecto
    @Override
    public void asignarPrioridad(String nombreProyecto, String prioridad) {
        for (Proyecto p : this.proyectos) {
            if (p.getNombre().equals(nombreProyecto)) {
                p.setPrioridad1(prioridad);
                break;
            }
        }
    }
    @Override
    public int compare(Proyecto p1, Proyecto p2) {
        // Ordenar por prioridad (alta, media, baja)
        List<String> prioridades = Arrays.asList("alta", "media", "baja");
        return Integer.compare(prioridades.indexOf(p1.getPrioridad1()), prioridades.indexOf(p2.getPrioridad1()));
    }

	@Override
	public void crearPlan(String nombre, Proyecto pertenece) {
		// Verificar si el proyecto ya tiene un plan asignado
		
		// Crear una nueva instancia de Plan con el nombre y el proyecto
	    Plan nuevoPlan = new Plan(nombre, pertenece);
	    pertenece.setPlan(nuevoPlan);
	}

	@Override
	public void crearProyecto(String nombre, Usuario usuarioPropietario, boolean estado,String prioridad, String descripcion) {
		
		// Crear un nuevo proyecto con los parámetros recibidos
	    Proyecto nuevoProyecto = new Proyecto(nombre, usuarioPropietario, estado, prioridad, descripcion);
	    
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
	    Proyecto proyectoExistente = buscarProyectoPorNombre(nombreProyecto);
	    
	    // Modificar los campos del proyecto existente
	    proyectoExistente.setNombre(proyectoModificado.getNombre());
	    Usuario usuarioPropietario = buscarUsuarioPorNombre(proyectoModificado.getUsuarioPropietario());
	    if (usuarioPropietario != null) {
	        proyectoExistente.setUsuarioPropietario(usuarioPropietario);
	    } else {
	        throw new IllegalArgumentException("No se encontró el usuario propietario con nombre: " + proyectoModificado.getUsuarioPropietario());
	    }
	   
	    proyectoExistente.setPrioridad1(proyectoModificado.getPrioridad());
	    //proyectoExistente.setEstado(proyectoModificado.isEstado()); //ver para sacarlo
	    proyectoExistente.setDescripcion(proyectoModificado.getDescripcion());

	}
	
	private Proyecto buscarProyectoPorNombre(String nombreProyecto) {
	    for (Proyecto proyecto : this.proyectos) {
	        if (proyecto.getNombre().equals(nombreProyecto)) {
	            return proyecto; // Retorna el proyecto si coincide el nombre
	        }
	    }
	    return null; // Si no se encuentra, retorna null
	}
	
	private Usuario buscarUsuarioPorNombre(String nombreUsuario) {
	    for (Usuario usuario : this.usuarios) {
	        if (usuario.getNombre().equals(nombreUsuario)) {
	            return usuario; // Retorna el usuario si coincide el nombre
	        }
	    }
	    return null; // Si no se encuentra, retorna null
	}

	@Override
	public void crearProyecto(String nombreProyecto, Usuario usuarioPropietario, boolean b, String string) {
		// TODO Auto-generated method stub
		
	}
    
}

