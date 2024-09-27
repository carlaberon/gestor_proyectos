package ar.edu.unrn.seminario.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unrn.seminario.modelo.Proyecto;
public class Inicio {

    private JFrame frame;

    public Inicio() {
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

        accountMenu.add(confItem);
        accountMenu.add(logoutItem);
        menuBar.add(accountMenu);

        logoutItem.addActionListener(e -> System.exit(0));

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

        // Panel derecho para proyectos
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(30, 30, 30));

        JLabel proyectosLabel = new JLabel("Proyectos");
        proyectosLabel.setForeground(Color.GRAY);
        proyectosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Panel donde se muestran los proyectos
        JPanel proyectosListPanel = new JPanel();
        proyectosListPanel.setLayout(new BoxLayout(proyectosListPanel, BoxLayout.Y_AXIS));
        proyectosListPanel.setBackground(new Color(30, 30, 30));

        // ESTA ES LA PARTE QUE MUEVE LA PRIORIDAD DE LOS PROYECTOS
        List<Proyecto> proyectos = crearProyectos();
        proyectos.sort((p1, p2) -> p1.getPrioridad().compareTo(p2.getPrioridad())); 
        for (Proyecto proyecto : proyectos) {
            JLabel proyectoLabel = new JLabel(proyecto.getNombre());
            proyectoLabel.setForeground(Color.LIGHT_GRAY);
            proyectoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            proyectosListPanel.add(proyectoLabel);
        }

        // Panel inferior con botones
        JPanel proyectosButtonsPanel = new JPanel();
        proyectosButtonsPanel.setBackground(new Color(30, 30, 30));

        JButton btnNuevoProyecto = new JButton("Proyecto +");
        JButton btnVerProyectos = new JButton("Ver todos los proyectos");
        formatButton(btnNuevoProyecto);
        formatButton(btnVerProyectos);

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

    // Método para crear proyectos de ejemplo
    private List<Proyecto> crearProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        proyectos.add(new Proyecto("Gestor proyectos", "Alta"));
        proyectos.add(new Proyecto("Recuentos votos", "cedia"));
        proyectos.add(new Proyecto("Gestionar tareas", "Baja"));
        return proyectos;
    }

    // Método para dar formato a los botones
    private void formatButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(80, 80, 80));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    public static void main(String[] args) {
        new Inicio();
    }

    // Clase Proyecto para almacenar información de proyectos
    class Proyecto {
        private String nombre;
        private String prioridad;

        public Proyecto(String nombre, String prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
        }

        public String getNombre() {
            return nombre;
        }

        public String getPrioridad() {
            return prioridad;
        }
    }
}
