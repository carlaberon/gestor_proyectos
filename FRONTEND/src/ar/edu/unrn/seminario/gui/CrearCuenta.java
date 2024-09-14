package ar.edu.unrn.seminario.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class CrearCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_1_3;
	private JTextField textField_4;
	private JButton aceptar;
	private JButton cancelar;
	private JLabel lblNewLabel_1_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_1_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IApi api = new MemoryApi(); //polimorfismo
					CrearCuenta frame = new CrearCuenta(api);
					frame.setLocationRelativeTo(null);
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
	public CrearCuenta(IApi api) {
		setTitle("Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(81, 79, 89));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Crear Cuenta");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(29, 17, 40));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		lblNewLabel.setBounds(276, 30, 234, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario*:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(241, 105, 93, 44);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(314, 116, 172, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre*:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(138, 150, 93, 44);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(219, 162, 172, 26);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido*:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(413, 149, 93, 44);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(491, 161, 172, 26);
		contentPane.add(textField_2);
		
		lblNewLabel_1_2 = new JLabel("Email*:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(138, 206, 66, 44);
		contentPane.add(lblNewLabel_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(219, 217, 370, 26);
		contentPane.add(textField_3);
		
		lblNewLabel_1_3 = new JLabel("Domicilio:");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(138, 410, 93, 44);
		contentPane.add(lblNewLabel_1_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(219, 422, 370, 26);
		contentPane.add(textField_4);
		
		aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		aceptar.setForeground(new Color(255, 255, 255));
		aceptar.setBackground(new Color(89, 65, 169));
		aceptar.setBounds(534, 574, 111, 26);
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Usuario registrado con exito!", "Info", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(aceptar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		cancelar.setForeground(new Color(255, 255, 255));
		cancelar.setBackground(new Color(89, 65, 169));
		cancelar.setBounds(663, 574, 111, 26);
		contentPane.add(cancelar);
		
		lblNewLabel_1_4 = new JLabel("Teléfono:");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(138, 465, 87, 44);
		contentPane.add(lblNewLabel_1_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(219, 477, 172, 26);
		contentPane.add(textField_5);
		
		lblNewLabel_1_5 = new JLabel("Contraseña*:");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(138, 261, 120, 44);
		contentPane.add(lblNewLabel_1_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(237, 272, 210, 26);
		contentPane.add(textField_6);
		
		lblNewLabel_2 = new JLabel("(opcional)");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_2.setBounds(597, 428, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("(opcional)");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_3.setBounds(400, 483, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_1_7 = new JLabel("Fecha de Nacimiento*");
		lblNewLabel_1_7.setForeground(Color.WHITE);
		lblNewLabel_1_7.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_7.setBounds(138, 307, 210, 44);
		contentPane.add(lblNewLabel_1_7);
		
		JComboBox comboBoxDia = new JComboBox();
		comboBoxDia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxDia.setBounds(138, 354, 93, 26);
		contentPane.add(comboBoxDia);
		comboBoxDia.addItem("Día");
		
		JComboBox comboBoxMes = new JComboBox();
		comboBoxMes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxMes.setBounds(241, 354, 93, 26);
		contentPane.add(comboBoxMes);
		comboBoxMes.addItem("Mes");
		
		JComboBox comboBoxAño = new JComboBox();
		comboBoxAño.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBoxAño.setBounds(344, 354, 93, 26);
		contentPane.add(comboBoxAño);
		comboBoxAño.addItem("Año");
		
		lblNewLabel_1_6 = new JLabel("Género*");
		lblNewLabel_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_6.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(473, 307, 93, 44);
		contentPane.add(lblNewLabel_1_6);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		rdbtnNewRadioButton.setBackground(new Color(81, 79, 89));
		rdbtnNewRadioButton.setBounds(467, 355, 87, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setForeground(Color.WHITE);
		rdbtnFemenino.setFont(new Font("Segoe UI", Font.BOLD, 11));
		rdbtnFemenino.setBackground(new Color(81, 79, 89));
		rdbtnFemenino.setBounds(556, 355, 97, 23);
		contentPane.add(rdbtnFemenino);
	}
}
