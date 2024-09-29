package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.dto.TareaDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

public class VentanaTareas extends JFrame {

    private JPanel contentPane;
    private JTable table;
	DefaultTableModel modelo;
	IApi api;
	JButton botonModificar;
	JButton botonEliminar;

    public VentanaTareas(IApi api) {

    	this.api = api; 
    	
    	setTitle("");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // Configuración del menú superior
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(138, 102, 204));
        menuBar.setPreferredSize(new Dimension(100, 50));

        JMenu menuProyecto = new JMenu("nombreProyecto");
        menuProyecto.setForeground(Color.WHITE);
        menuProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JMenuItem item1 = new JMenuItem("Opción 1");
        JMenuItem item2 = new JMenuItem("Opción 2");
        JMenuItem item3 = new JMenuItem("Opción 3");
        menuProyecto.add(item1);
        menuProyecto.add(item2);
        menuProyecto.add(item3);

        menuBar.add(menuProyecto);

        JLabel appName = new JLabel("LabProject");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(appName);
        menuBar.add(centerPanel);

        JMenu accountMenu = new JMenu("nombreCuenta");
        accountMenu.setForeground(Color.WHITE);
        accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JMenuItem logoutItem = new JMenuItem("Cerrar sesión");
        JMenuItem confItem = new JMenuItem("Configurar cuenta");
        accountMenu.add(confItem);
        accountMenu.add(logoutItem);
        
        logoutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });

        menuBar.add(accountMenu);
        this.setJMenuBar(menuBar);
        
        // Panel lateral (Menú)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(65, 62, 77));

        String[] menuItems = {"Tareas", "Volver"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(83, 82, 90));
            menuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menuButton.setBorderPainted(false);
            menuButton.setFocusPainted(false);
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setMargin(new Insets(10, 10, 10, 10));
            menuPanel.add(menuButton);
        }
    
        contentPane.add(menuPanel, BorderLayout.WEST);
        
        JPanel centerPanel1 = new JPanel();
        centerPanel1.setLayout(new GridLayout(1, 1, 10, 10));
        centerPanel1.setBackground(new Color(45, 44, 50));
        centerPanel1.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(centerPanel1, BorderLayout.CENTER);
    
        JPanel descPanel = createPanel("","");
        descPanel.setLayout(new BorderLayout());
        
        JScrollPane scrollPane = new JScrollPane();
        descPanel.add(scrollPane, BorderLayout.CENTER);

		// Personalización de la tabla
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        // Personalización del encabezado de la tabla
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(83, 82, 90)); // Color de fondo del encabezado
        table.getTableHeader().setForeground(Color.WHITE); // Color del texto del encabezado
        table.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Altura del encabezado

        // Personalización de la tabla
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(40); // Altura de las filas
        table.setBackground(new Color(45, 44, 50)); // Fondo de la tabla
        table.setForeground(Color.WHITE); // Color de texto de las celdas
        table.setSelectionBackground(new Color(138, 102, 204)); // Fondo de la selección
        table.setSelectionForeground(Color.WHITE); // Texto de la selección

        // Mostrar las líneas de cuadrícula
        table.setGridColor(new Color(83, 82, 90)); // Color de la cuadrícula
        table.setShowGrid(true);

        // Modelo de la tabla
		String[] titulos = { "NOMBRE", "PROYECTO", "ESTADO", "ASIGNADO", "PRIORIDAD" };
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		// Obtiene la lista de tareas a mostrar
		List<TareaDTO> tareas = (List<TareaDTO>) api.obtenerTareas();
		// Agrega las tareas en el model
		for (TareaDTO t : tareas) {
			modelo.addRow(new Object[] { t.getName(), t.getProject(),t.isEstado(), t.getUser(), t.getPriority() });
		}

		table.setModel(modelo);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(new Color(45, 44, 50)); // Fondo del scrollPane
      	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		
		// Configuración del botón "Tarea +" en la esquina superior derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton btnTarea = createButton("Tarea +", new Color(138, 102, 204));
        btnTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearTarea crearTarea = new CrearTarea(api, VentanaTareas.this);
				crearTarea.setLocationRelativeTo(null);
				crearTarea.setVisible(true);
				actualizarTabla();
			}
		});

        buttonPanel.add(btnTarea);
        descPanel.add(buttonPanel, BorderLayout.NORTH); // Coloca el botón en el norte (arriba)
        centerPanel1.add(descPanel);
        
       //Configuración de los botones "Modificar" y "Eliminar" tarea
      JPanel botones = new JPanel(new FlowLayout());
      botones.setOpaque(false);
      
      botonModificar = createButton("Modificar", new Color(138, 102, 204));
      botonEliminar = createButton("Eliminar", new Color(138, 102, 204));
      botones.add(botonModificar);
      botones.add(botonEliminar);
      descPanel.add(botones, BorderLayout.SOUTH);
      
      habilitarBotones(false);
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
    
	private void habilitarBotones(boolean b) {
		botonModificar.setEnabled(b);
		botonEliminar.setEnabled(b);

	}
	void actualizarTabla() {
		// Obtiene el model del table
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// Obtiene la lista de usuarios a mostrar
		List<TareaDTO> tareas = api.obtenerTareas();
		// Resetea el model
		modelo.setRowCount(0);

		// Agrega los usuarios en el model
		for (TareaDTO t : tareas) {
			modelo.addRow(new Object[] { t.getName(), t.getProject(),t.isEstado(), t.getUser(), t.getPriority() });
		}

	}

    public static void main(String[] args) {
    	IApi api = new MemoryApi();
    	VentanaTareas ventanatareas = new VentanaTareas(api);
    	ventanatareas.setVisible(true);
    }
}
