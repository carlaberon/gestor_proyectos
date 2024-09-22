package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaProgreso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JProgressBar progressBar;

    public VentanaProgreso(/* IApi api */) {
        
        // Configuración básica de la ventana
        setTitle("Ventana Progreso");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        
        // Inicializando contentPane
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // Barra de menú superior
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(138, 102, 204)); // Color morado
        menuBar.setPreferredSize(new Dimension(100, 50));
        menuBar.setLayout(new BorderLayout());
        
        // Menú desplegable "nombreProyecto"
        JMenu menuProyecto = new JMenu("nombreProyecto");
        menuProyecto.setForeground(Color.WHITE);
        menuProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JMenuItem item1 = new JMenuItem("Opción 1");
        JMenuItem item2 = new JMenuItem("Opción 2");
        JMenuItem item3 = new JMenuItem("Opción 3");
        menuProyecto.add(item1);
        menuProyecto.add(item2);
        menuProyecto.add(item3);

        // Menú izquierdo con el nombre del proyecto
        JMenuBar leftMenuBar = new JMenuBar();
        leftMenuBar.setOpaque(false); 
        leftMenuBar.add(menuProyecto); 
        leftMenuBar.setBorder(BorderFactory.createEmptyBorder());
        leftMenuBar.setBackground(new Color(138, 102, 204));

        menuBar.add(leftMenuBar, BorderLayout.WEST);
        
        // Nombre de la aplicación al centro
        JLabel appName = new JLabel("LabProject");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(appName);
        menuBar.add(centerPanel, BorderLayout.CENTER);
        
        // Menú de cuenta a la derecha
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);

        JMenu accountMenu = new JMenu("nombreCuenta");
        accountMenu.setForeground(Color.WHITE);
        accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
        JMenuItem confItem = new JMenuItem("Configurar cuenta");
        accountMenu.add(logoutItem);
        accountMenu.add(confItem);

        rightPanel.add(accountMenu);
        menuBar.add(rightPanel, BorderLayout.EAST);
        
        // Añadir la barra de menú al contentPane
        contentPane.add(menuBar, BorderLayout.NORTH);

        // Panel lateral (Menú)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Espacio entre botones
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(65, 62, 77));

        String[] menuItems = {"Progreso", "Volver"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(83, 82, 90));
            menuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            menuButton.setHorizontalAlignment(SwingConstants.CENTER);
            menuButton.setMargin(new Insets(10, 10, 10, 10)); 
            menuPanel.add(menuButton);
        }

        contentPane.add(menuPanel, BorderLayout.WEST);
        
        // Panel central para la barra de progreso
        JPanel centerPanel1 = new JPanel();
        centerPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Cambio a FlowLayout para ajustar tamaño
        centerPanel1.setBackground(new Color(45, 44, 50));
        centerPanel1.setBorder(new EmptyBorder(20, 20, 20, 20)); // Márgenes
        
        // Barra de progreso con tamaño ajustable
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(400, 30)); // Cambiar tamaño de la barra de progreso
        centerPanel1.add(progressBar);
        
        contentPane.add(centerPanel1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        VentanaProgreso frame = new VentanaProgreso();
        frame.setVisible(true);
    }
}
