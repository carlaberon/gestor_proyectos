package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

public class Inicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inicio frame = new Inicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Inicio() {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 800);

        // Panel principal de contenido
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // =======================
        // Panel que contendrá las barras
        // =======================
        JPanel panelBarras = new JPanel();
        panelBarras.setBackground(new Color(109, 114, 195));
        panelBarras.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Alinear a la derecha con espaciado de 10px

        // Segunda barra con notificaciones (JToolBar)
        JToolBar toolBarNotificaciones = new JToolBar();
        toolBarNotificaciones.setFloatable(false);  // Evitar que se mueva

        // Botón de notificaciones
        JButton btnNotificaciones = new JButton("Notificaciones");
        btnNotificaciones.setBackground(new Color(109, 114, 195));
        toolBarNotificaciones.add(btnNotificaciones);

        // Añadir la barra de herramientas
        panelBarras.add(toolBarNotificaciones);

        // Primera barra de menú (JMenuBar)
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(109, 114, 195));

        // Menú "Usuario"
        JMenu menuUsuario = new JMenu("Usuario");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        JMenuItem menuItemConfiguracion = new JMenuItem("Configuración");
        menuUsuario.add(menuItemSalir);
        menuUsuario.add(menuItemConfiguracion);
        menuBar.add(menuUsuario);

        // Añadir la barra de menú
        panelBarras.add(menuBar);

        // Añadir panelBarras a la parte superior
        contentPane.add(panelBarras, BorderLayout.NORTH);

        // =======================
        // Panel transparente en el lado derecho
        // =======================
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS)); // Layout vertical
        panelDerecho.setOpaque(false); // Hacer el panel transparente
        
        // Establecer tamaño preferido para que sea más ancho
        panelDerecho.setPreferredSize(new Dimension(200, 100));
        
        // Mensajes dentro del panel
        JLabel labelMensaje1 = new JLabel("Proyectos");
        labelMensaje1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panelDerecho.add(labelMensaje1);

        // Añadir el panel transparente al lado derecho
        contentPane.add(panelDerecho, BorderLayout.EAST);
        
                // Botones dentro del panel
                JButton btnUno = new JButton("Botón 1");
                btnUno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                btnUno.setBackground(new Color(89, 65, 169));
                btnUno.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                
                        // Añadir componentes al panel
                        panelDerecho.add(btnUno);
                        JButton btnDos = new JButton("Botón 2");
                        panelDerecho.add(btnDos);
                        JLabel labelMensaje2 = new JLabel("Mensaje 2");
                        panelDerecho.add(labelMensaje2);
    }
}

