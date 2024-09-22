package ar.edu.unrn.seminario.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaResumen {

    private JFrame frame;

    public VentanaResumen() {
        frame = new JFrame("LabProject - Resumen");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //OCULTA LA VENTANA PERO NO TERMINA EL PROGRAMA
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Barra de navegación superior (JMenuBar)
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(138, 102, 204)); // Color morado
        menuBar.setPreferredSize(new Dimension(100, 50));
        menuBar.setLayout(new BorderLayout());

        // Panel izquierdo de la barra superior (nombre del proyecto como menú desplegable)
        JMenu menuProyecto = new JMenu("nombreProyecto");
        menuProyecto.setForeground(Color.WHITE);
        menuProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        // Items del menú desplegable (todavía no definidos)
        JMenuItem item1 = new JMenuItem("Opción 1");
        JMenuItem item2 = new JMenuItem("Opción 2");
        JMenuItem item3 = new JMenuItem("Opción 3");
        menuProyecto.add(item1);
        menuProyecto.add(item2);
        menuProyecto.add(item3);

        JMenuBar leftMenuBar = new JMenuBar();
        leftMenuBar.setOpaque(false); // Fondo transparente
        leftMenuBar.add(new JLabel(new ImageIcon("menu-icon.png"))); // Ícono del menú (opcional)
        leftMenuBar.add(menuProyecto); // Agregar el menú "nombreProyecto"
        leftMenuBar.setBorder(BorderFactory.createEmptyBorder());
        leftMenuBar.setBackground(new Color(138, 102, 204)); // Fondo morado

        menuBar.add(leftMenuBar, BorderLayout.WEST);

        // Panel central de la barra (nombre de la aplicación)
        JLabel appName = new JLabel("LabProject");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(appName);
        menuBar.add(centerPanel, BorderLayout.CENTER);

        // Botón "nombreCuenta" a la derecha de la barra
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);

        JMenu accountMenu = new JMenu("nombreCuenta");
        accountMenu.setForeground(Color.WHITE);
        accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
        JMenuItem confItem = new JMenuItem("Configurar cuenta");
        accountMenu.add(logoutItem);
        accountMenu.add(confItem);
        /*
        rightPanel.add(new JLabel(new ImageIcon("notification-icon.png"))); // Placeholder para ícono de notificación
        */
        rightPanel.add(accountMenu);
       
        menuBar.add(rightPanel, BorderLayout.EAST);

        frame.setJMenuBar(menuBar);

        // Panel lateral (Menú)-----------------------------------------------------------------------------
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Espacio entre botones
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(65, 62, 77));

        String[] menuItems = {"Resumen", "Progreso", "Plan", "Calendario", "Tareas", "Miembros", "Configuración"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item + " →");
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(83, 82, 90));
            menuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            menuButton.setHorizontalAlignment(SwingConstants.LEFT); // Alineación izquierda
            menuButton.setMargin(new Insets(10, 10, 10, 10)); // Margen interno
            menuPanel.add(menuButton);
        }

        frame.add(menuPanel, BorderLayout.WEST);

        // Panel principal (Centro)
        JPanel centerPanel1 = new JPanel();
        centerPanel1.setLayout(new GridLayout(3, 2, 10, 10));
        centerPanel1.setBackground(new Color(45, 44, 50));
        centerPanel1.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor del contenido
        
        // Descripción del proyecto
        JPanel descPanel = createPanel("Descripción del proyecto", "Estado no definido");
        centerPanel1.add(descPanel);

        // Estado del proyecto
        JPanel estadoPanel = createPanel("Estado del proyecto", "No definido");
        centerPanel1.add(estadoPanel);

        // Detalles del plan
        JPanel planPanel = createPanel("Detalles del plan", null);
        JButton btnPlan = createButton("Plan +", new Color(138, 102, 204));
        JButton btnVerPlan = createButton("Ver plan", new Color(83, 82, 90));
        planPanel.add(btnPlan);
        planPanel.add(btnVerPlan);
        centerPanel1.add(planPanel);

        // Miembros del proyecto
        JPanel miembrosPanel = createPanel("Miembros del proyecto", null);
        JButton btnMiembro = createButton("Miembro +", new Color(138, 102, 204));
        JButton btnVerMiembros = createButton("Ver miembros", new Color(83, 82, 90));
        miembrosPanel.add(btnMiembro);
        miembrosPanel.add(btnVerMiembros);
        centerPanel1.add(miembrosPanel);

        // Tareas
        JPanel tareasPanel = createPanel("Tareas", null);
        JButton btnTarea = createButton("Tarea +", new Color(138, 102, 204));
        JButton btnVerTareas = createButton("Ver detalles", new Color(83, 82, 90));
        tareasPanel.add(btnTarea);
        tareasPanel.add(btnVerTareas);
        centerPanel1.add(tareasPanel);

        frame.add(centerPanel1, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método auxiliar para crear paneles con título y diseño consistente
    private JPanel createPanel(String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(53, 52, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        JLabel label = new JLabel(title);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);

        if (subtitle != null) {
            JLabel subLabel = new JLabel(subtitle);
            subLabel.setForeground(Color.GRAY);
            subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            panel.add(subLabel);
        }

        return panel;
    }

    // Método para crear botones con estilo
    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaResumen());
    }
}
