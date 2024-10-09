package ar.edu.unrn.seminario.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
public class Inicio extends JFrame {
	public static final Map<String, Integer> PRIORIDAD_MAP = new HashMap<>();
    static {
        PRIORIDAD_MAP.put("alta", 1);
        PRIORIDAD_MAP.put("media", 2);
        PRIORIDAD_MAP.put("baja", 3);
    }
    private JFrame frame;
    IApi api;
    public Inicio(IApi api) {
    	this.api = api;

    	frame = new JFrame("LabProject");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(138, 102, 204));
        menuBar.setPreferredSize(new Dimension(100, 50));

        JLabel projectName = new JLabel("LabProject");
        projectName.setForeground(Color.WHITE);
        projectName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(projectName);
        menuBar.add(Box.createHorizontalGlue());

        JMenu accountMenu = new JMenu("nombreCuenta");
        accountMenu.setForeground(Color.WHITE);
        accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
        JMenuItem confItem = new JMenuItem("Configurar Cuenta");
        JMenuItem verTodosProyectosMenuItem = new JMenuItem("Ver todos los proyectos");

        accountMenu.add(confItem);
        accountMenu.add(logoutItem);
        accountMenu.add(verTodosProyectosMenuItem);
        menuBar.add(accountMenu);

        logoutItem.addActionListener(e -> System.exit(0));

        verTodosProyectosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirListaProyectos(); // Abrir la ventana de proyectos desde el menú
                
                
            }
        });

        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(65, 62, 77));

        String[] menuItems = {"Proyectos", "Actividad", "Calendario"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item + " →");
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(83, 82, 90));
            menuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setMargin(new Insets(10, 10, 10, 10));
            menuPanel.add(menuButton);
        }
        frame.add(menuPanel, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(45, 45, 45));
        JLabel welcomeLabel = new JLabel("¡Bienvenido a LabProject!");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPanel.add(welcomeLabel);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(30, 30, 30));

        JLabel proyectosLabel = new JLabel("Proyectos");
        proyectosLabel.setForeground(Color.GRAY);
        proyectosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel proyectosListPanel = new JPanel();
        proyectosListPanel.setLayout(new BoxLayout(proyectosListPanel, BoxLayout.Y_AXIS));
        proyectosListPanel.setBackground(new Color(30, 30, 30));
        //BACK -> DTO -> FRONT
        List<ProyectoDTO> proyectos = api.obtenerProyectos();

<<<<<<< HEAD
        
        //proyectos.sort((p1, p2) -> p1.getPrioridad().compareTo(p2.getPrioridad()));
        
=======
        proyectos.sort(Comparator.comparingInt(p -> PRIORIDAD_MAP.get(p.getPrioridad())));
>>>>>>> Gabriel
        for (ProyectoDTO proyecto : proyectos) {
            JButton proyectoButton = new JButton(proyecto.getNombre());
            proyectoButton.setForeground(Color.DARK_GRAY);
            proyectoButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            proyectoButton.addActionListener( new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					abrirVentanaResumen(proyecto);
				}
            	
            });
            proyectosListPanel.add(proyectoButton);
        }

        JPanel proyectosButtonsPanel = new JPanel();
        proyectosButtonsPanel.setBackground(new Color(30, 30, 30));

        JButton btnNuevoProyecto = new JButton("Proyecto +");
        JButton btnVerProyectos = new JButton("Ver todos los proyectos");
        formatButton(btnNuevoProyecto);
        formatButton(btnVerProyectos);

        btnVerProyectos.addActionListener(e -> abrirListaProyectos()); // Acción del boton
        proyectosButtonsPanel.add(btnNuevoProyecto);
        proyectosButtonsPanel.add(btnVerProyectos);

        rightPanel.add(proyectosLabel, BorderLayout.NORTH);
        rightPanel.add(proyectosListPanel, BorderLayout.CENTER);
        rightPanel.add(proyectosButtonsPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setVisible(true);
    }

<<<<<<< HEAD

=======
>>>>>>> Gabriel
    private void formatButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(80, 80, 80));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void abrirListaProyectos() {
        ListaProyectos listaProyectos = new ListaProyectos(); 
        listaProyectos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cerrar la ventana de la lista
        listaProyectos.setVisible(true); 
        // No cerrar la ventana principal
    }


    private void abrirVentanaResumen(ProyectoDTO proyecto) {
        VentanaResumen ventanaResumen = new VentanaResumen(proyecto); // Crear una instancia de VentanaResumen 
        ventanaResumen.setVisible(true); // Hacer visible la ventana de resumen
    }

    public static void main(String[] args) {
    	IApi api = new MemoryApi();
        new Inicio(api);
    }
}
<<<<<<< HEAD

=======
>>>>>>> Gabriel
