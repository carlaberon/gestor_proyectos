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
import ar.edu.unrn.seminario.dto.ProyectoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Rol;

public class CrearTarea extends JFrame {

    private JPanel contentPane;
    private JTextField nombreTareaTextField;
    private JTextField prioridadTareaTextField;
    private JTextField proyectoNombreTextField; // New text field for project name

    private JComboBox<String> proyectoTareaComboBox;
    private JComboBox<String> asignarUsuarioComboBox;

    private List<ProyectoDTO> proyectos = new ArrayList<>();
    private List<UsuarioDTO> usuarios = new ArrayList<>();

    public CrearTarea(IApi api) {
        // agregar proyectos y usuarios a las listas
    	
        proyectos.add(new ProyectoDTO("Proyecto1", "UsuarioPropietario", "alta", false));
        proyectos.add(new ProyectoDTO("Proyecto2","UsuarioPropietario", "alta", false));
        
        Rol unRol = new Rol();
   
        usuarios.add(new UsuarioDTO("usuario1", "password","nombre","email",unRol, true));
        usuarios.add(new UsuarioDTO("usuario2", "password","nombre","email",unRol, true));
        // Uncomment to use actual API calls
        // this.proyectos = api.obtenerProyectos();
        // this.usuarios = api.obtenerUsuarios();

        setTitle("Crear Tarea");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel nombreTareaLabel = new JLabel("Nombre:");
        nombreTareaLabel.setBounds(43, 20, 93, 16);
        contentPane.add(nombreTareaLabel);

        nombreTareaTextField = new JTextField();
        nombreTareaTextField.setBounds(148, 20, 160, 22);
        contentPane.add(nombreTareaTextField);
        nombreTareaTextField.setColumns(10);

        JLabel proyectoTareaLabel = new JLabel("Proyecto:");
        proyectoTareaLabel.setBounds(43, 60, 93, 16);
        contentPane.add(proyectoTareaLabel);

        proyectoTareaComboBox = new JComboBox<>();
        proyectoTareaComboBox.setBounds(148, 60, 160, 22);
        for (ProyectoDTO proyecto : this.proyectos) {
            proyectoTareaComboBox.addItem(proyecto.getNombre());
        }
        contentPane.add(proyectoTareaComboBox);

        // New text field to show selected project name
        proyectoNombreTextField = new JTextField();
        proyectoNombreTextField.setBounds(320, 60, 100, 22);
        proyectoNombreTextField.setEditable(false); // Make it read-only
        contentPane.add(proyectoNombreTextField);

        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
                ProyectoDTO proyecto = proyectos.get(proyectoTareaComboBox.getSelectedIndex());
                UsuarioDTO usuario = usuarios.get(asignarUsuarioComboBox.getSelectedIndex());

        
                api.registrarTarea(nombreTareaTextField.getText(), proyecto.getNombre(), 
                    prioridadTareaTextField.getText(), usuario.getUsername(), false, "");

                JOptionPane.showMessageDialog(null, "Tarea creada con éxito!", "Info", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                dispose();
            }
        });
        aceptarButton.setBounds(218, 215, 97, 25);
        contentPane.add(aceptarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        cancelarButton.setBounds(323, 215, 97, 25);
        contentPane.add(cancelarButton);

        JLabel prioridadTareaLabel = new JLabel("Prioridad:");
        prioridadTareaLabel.setBounds(43, 100, 93, 16);
        contentPane.add(prioridadTareaLabel);

        prioridadTareaTextField = new JTextField();
        prioridadTareaTextField.setBounds(148, 100, 160, 22);
        contentPane.add(prioridadTareaTextField);
        prioridadTareaTextField.setColumns(10);

        JLabel asignarUsuarioLabel = new JLabel("Asignar usuario:");
        asignarUsuarioLabel.setBounds(43, 140, 93, 16);
        contentPane.add(asignarUsuarioLabel);

        asignarUsuarioComboBox = new JComboBox<>();
        asignarUsuarioComboBox.setBounds(148, 140, 160, 22);
        contentPane.add(asignarUsuarioComboBox);
        for (UsuarioDTO usuario :  this.usuarios) {
        	asignarUsuarioComboBox.addItem(usuario.getUsername());
        }
     
        for (ProyectoDTO proyecto : this.proyectos) {
            proyectoTareaComboBox.addItem(proyecto.getNombre());
        }
        
        asignarUsuarioComboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectUser = (String) asignarUsuarioComboBox.getSelectedItem();
        		
        	}
        });
        
        // Update project name field when the selected project changes
        proyectoTareaComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProject = (String) proyectoTareaComboBox.getSelectedItem();
                proyectoNombreTextField.setText(selectedProject);
            }
        });

        // Initially set the project name field
        if (!proyectos.isEmpty()) {
            proyectoNombreTextField.setText(proyectos.get(0).getNombre());
        }
    }
}
