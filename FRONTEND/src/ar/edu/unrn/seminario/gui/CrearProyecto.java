package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

import javax.swing.JTextPane;
import javax.swing.JTree;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class CrearProyecto extends JFrame {

	private JPanel contentPane;
	private JTextField nombreProyectoTextField;
	private JComboBox proyectoComboBox;
	private IApi api;
	private List<ProyectoDTO> proyectos; //crear el proyectoDTO, crear el proyecto
	private Usuario usuarioPropietario;
	private JTextField textField;
	
	/**
	 * Create the frame.
	 */
	public CrearProyecto(IApi api, Usuario usuarioPropietario) {
		this.api = api;
		this.usuarioPropietario = usuarioPropietario;
		this.proyectos = api.obtenerProyectos(); // Se obtienen los proyectos existentes
		
		setTitle("Crear proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		setSize(900,600);
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		contentPane.setBackground(new Color(81, 79, 89));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel nombreProyecto = new JLabel("Nombre Proyecto*:");
		nombreProyecto.setForeground(new Color(240, 240, 240));
		nombreProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nombreProyecto.setBounds(88, 93, 227, 39);
		contentPane.add(nombreProyecto);

		nombreProyectoTextField = new JTextField();
		nombreProyectoTextField.setBounds(325, 105, 390, 25);
		contentPane.add(nombreProyectoTextField);
		nombreProyectoTextField.setColumns(10);

		JButton aceptarButton = new JButton("Guardar");
		aceptarButton.setForeground(new Color(229, 212, 237));
		aceptarButton.setBackground(new Color(89, 65, 169));
		aceptarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		aceptarButton.setBounds(395, 398, 147, 27);
		contentPane.add(aceptarButton);
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProyecto = nombreProyectoTextField.getText();
				
				// Validar que se haya ingresado un nombre
                if (nombreProyecto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre del proyecto es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Crear un nuevo proyecto
                api.crearProyecto(nombreProyecto, usuarioPropietario, false, "Nuevo proyecto creado desde la interfaz.");
                JOptionPane.showMessageDialog(null, "Proyecto registrado con éxito!", "Info", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                dispose();
			}
		});

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setForeground(new Color(29, 17, 40));
		cancelarButton.setBackground(new Color(229, 212, 237));
		cancelarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelarButton.setBounds(568, 398, 147, 27);
		contentPane.add(cancelarButton);



		JLabel subproyectoLabel = new JLabel("Subproyecto de:");
        subproyectoLabel.setForeground(new Color(240, 240, 240));
        subproyectoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subproyectoLabel.setBounds(88, 233, 167, 39);
        contentPane.add(subproyectoLabel);



        proyectoComboBox = new JComboBox<>();
        proyectoComboBox.setForeground(new Color(29, 17, 40));
        proyectoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        proyectoComboBox.setBounds(325, 245, 390, 25);
        contentPane.add(proyectoComboBox);
        
		proyectoComboBox.addItem("");
		// Llenar el ComboBox con los proyectos existentes
        for (ProyectoDTO proyecto : proyectos) {
            proyectoComboBox.addItem(proyecto.getNombre());
        }
		
		JLabel lblNewLabel = new JLabel("Nuevo Proyecto");
		lblNewLabel.setForeground(new Color(29, 17, 40));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lblNewLabel.setBounds(41, 10, 291, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(UIManager.getColor("Button.background"));
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblDescripcion.setBounds(88, 142, 227, 39);
		contentPane.add(lblDescripcion);
		
		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setForeground(UIManager.getColor("Button.background"));
		lblPrioridad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPrioridad.setBounds(88, 191, 227, 39);
		contentPane.add(lblPrioridad);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(325, 157, 390, 25);
		contentPane.add(textField);
		
		JComboBox<Object> proyectoComboBox_1 = new JComboBox<Object>();
		proyectoComboBox_1.setForeground(new Color(29, 17, 40));
		proyectoComboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		proyectoComboBox_1.setBounds(325, 205, 390, 25);
		contentPane.add(proyectoComboBox_1);

		/*
		for (ProyectoDTO proyect : this.proyectos) {
			proyectoComboBox.addItem(proyect.getNombre());
		} */

		
	}
}