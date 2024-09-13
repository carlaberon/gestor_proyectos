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
import ar.edu.unrn.seminario.dto.RolDTO;
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

	/*
	private List<ProyectoDTO> proyectos = new ArrayList<>(); //crear el proyectoDTO, crear el proyecto
	*/
	/**
	 * Create the frame.
	 */
	public CrearProyecto(/* IApi api */) {
		/*
		// Obtengo los proyecto 
		this.proyectos = api.obtenerProyectos(); 
	*/
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
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
		nombreProyecto.setBounds(88, 145, 227, 39);
		contentPane.add(nombreProyecto);

		

		nombreProyectoTextField = new JTextField();
		nombreProyectoTextField.setBounds(325, 157, 390, 25);
		contentPane.add(nombreProyectoTextField);
		nombreProyectoTextField.setColumns(10);


		JButton aceptarButton = new JButton("Guardar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		aceptarButton.setForeground(new Color(229, 212, 237));
		aceptarButton.setBackground(new Color(89, 65, 169));
		aceptarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		/*
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Registrar un proyecto
				ProyectoDTO proyecto = proyectos.get(proyectoComboBox.getSelectedIndex()); 
			
					api.registrarProyecto(nombreProyectoTextField .getText(), proyecto.getCodigo());
					JOptionPane.showMessageDialog(null, "Proyecto registrado con exito!", "Info", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				
			}
		}); 
		*/
		aceptarButton.setBounds(395, 398, 147, 27);
		contentPane.add(aceptarButton);

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



		JLabel rolLabel = new JLabel("Subproyecto de:");
		rolLabel.setForeground(new Color(240, 240, 240));
		rolLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		rolLabel.setBounds(88, 233, 167, 39);
		contentPane.add(rolLabel);



		proyectoComboBox = new JComboBox();
		proyectoComboBox.setForeground(new Color(29, 17, 40));
		proyectoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		proyectoComboBox.setBounds(325, 245, 390, 25);
		contentPane.add(proyectoComboBox);
		
		JLabel lblNewLabel = new JLabel("Nuevo Proyecto");
		lblNewLabel.setForeground(new Color(29, 17, 40));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lblNewLabel.setBounds(35, 47, 291, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblConfiguracinAvanzada = new JLabel("Configuración avanzada:");
		lblConfiguracinAvanzada.setForeground(UIManager.getColor("Button.background"));
		lblConfiguracinAvanzada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblConfiguracinAvanzada.setBounds(509, 316, 167, 39);
		contentPane.add(lblConfiguracinAvanzada);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(new Color(81, 79, 89));
		chckbxNewCheckBox.setBounds(694, 323, 21, 32);
		contentPane.add(chckbxNewCheckBox);

		/*
		for (ProyectoDTO proyect : this.proyectos) {
			proyectoComboBox.addItem(proyect.getNombre());
		} */

		
	}
	   public static void main(String[] args) {
	 
	        CrearProyecto frame = new CrearProyecto();
	        frame.setVisible(true);
	    }
}