package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Rol;
import javax.swing.JTextArea;

import javax.swing.JSpinner;

public class CrearTarea extends JFrame {

    private JPanel contentPane;
    private JTextField nombreTareaTextField;
    private JComboBox<String> proyectoTareaComboBox; // ComboBox para seleccionar proyecto
    private JComboBox<String> asignarUsuarioComboBox; // ComboBox para seleccionar usuario
    private JTextField prioridadTareaTextField;

    
    private List<ProyectoDTO> proyectos = new ArrayList<>();
    private List<UsuarioDTO> usuarios = new ArrayList<>();

    private VentanaTareas ventanaTareas;
    
    private JSpinner fechaInicioSpinner;
    private JSpinner fechaFinSpinner;
    
    private LocalDateTime fechaInicioDate;
    private LocalDateTime fechaFinDate;
    
    
    public CrearTarea(IApi api, JFrame ventanaTareas) {
    	 
    	this.ventanaTareas = (VentanaTareas) ventanaTareas;
        
<<<<<<< HEAD
    	proyectos.add(new ProyectoDTO("Proyecto1", "UsuarioPropietario",false, "alta", "false"));
        proyectos.add(new ProyectoDTO("Proyecto2", "UsuarioPropietario",false, "alta", "false"));
=======
  
    	proyectos.add(new ProyectoDTO("Proyecto1", "UsuarioPropietario", false, "Alta", "proyecto para la gestion de tareas"));
        proyectos.add(new ProyectoDTO("Proyecto2", "UsuarioPropietario", false, "Baja", "proyecto para la gestion de tareas2"));
>>>>>>> develope

        Rol unRol = new Rol();
        usuarios.add(new UsuarioDTO("usuario1", "password", "nombre", "email", unRol, true));
        usuarios.add(new UsuarioDTO("usuario2", "password", "nombre", "email", unRol, true));

        
        // no hay proyectos cargados
        //this.proyectos = api.obtenerProyectos();
        this.usuarios = api.obtenerUsuarios();

        setTitle("Crear Tarea");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 600, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);


        JLabel nombreTareaLabel = new JLabel("Nombre de Tarea:");
        nombreTareaLabel.setBounds(43, 20, 150, 16);
        contentPane.add(nombreTareaLabel);

        nombreTareaTextField = new JTextField();
        nombreTareaTextField.setBounds(190, 20, 160, 22);
        contentPane.add(nombreTareaTextField);
        nombreTareaTextField.setColumns(10);

        JLabel proyectoTareaLabel = new JLabel("Proyecto:");
        proyectoTareaLabel.setBounds(43, 60, 150, 16);
        contentPane.add(proyectoTareaLabel);

        proyectoTareaComboBox = new JComboBox<>();
        proyectoTareaComboBox.setBounds(190, 60, 160, 22);
        for (ProyectoDTO proyecto : this.proyectos) {
            proyectoTareaComboBox.addItem(proyecto.getNombre());
        }
        contentPane.add(proyectoTareaComboBox);

        JLabel asignarUsuarioLabel = new JLabel("Asignar Usuario:");
        asignarUsuarioLabel.setBounds(43, 100, 150, 16);
        contentPane.add(asignarUsuarioLabel);

       
        asignarUsuarioComboBox = new JComboBox<>();
        asignarUsuarioComboBox.setBounds(190, 100, 160, 22);
        for (UsuarioDTO usuario : this.usuarios) {
            asignarUsuarioComboBox.addItem(usuario.getUsername());
        }
        contentPane.add(asignarUsuarioComboBox);

        JLabel prioridadTareaLabel = new JLabel("Prioridad:");
        prioridadTareaLabel.setBounds(43, 140, 150, 16);
        contentPane.add(prioridadTareaLabel);

        prioridadTareaTextField = new JTextField();
        prioridadTareaTextField.setBounds(190, 138, 160, 22);
        contentPane.add(prioridadTareaTextField);
        prioridadTareaTextField.setColumns(10);

        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setBounds(312, 438, 97, 25);
        contentPane.add(aceptarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(440, 438, 97, 25);
        contentPane.add(cancelarButton);
        
        JLabel lblDescripcin = new JLabel("Descripción:");
        lblDescripcin.setBounds(43, 291, 150, 16);
        contentPane.add(lblDescripcin);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(208, 291, 329, 111);
        contentPane.add(textArea);
        
        JLabel lblFechaInicio = new JLabel("Fecha inicio:");
        lblFechaInicio.setBounds(43, 183, 150, 16);
        contentPane.add(lblFechaInicio);
        
        fechaInicioSpinner = new JSpinner(new SpinnerDateModel());
        fechaInicioSpinner.setBounds(190, 181, 160, 22);
        JSpinner.DateEditor fechaInicioEditor = new JSpinner.DateEditor(fechaInicioSpinner, "yyyy-MM-dd HH:mm");
        fechaInicioSpinner.setEditor(fechaInicioEditor);
        contentPane.add(fechaInicioSpinner);
        
        
        JLabel lblFechaFin = new JLabel("Fecha fin:");
        lblFechaFin.setBounds(43, 232, 150, 16);
        contentPane.add(lblFechaFin);
        
        fechaFinSpinner = new JSpinner(new SpinnerDateModel());
        fechaFinSpinner.setBounds(190, 228, 160, 22);
        JSpinner.DateEditor fechaFinEditor = new JSpinner.DateEditor(fechaFinSpinner, "yyyy-MM-dd HH:mm");
        fechaFinSpinner.setEditor(fechaFinEditor);
        contentPane.add(fechaFinSpinner);

   
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Validaciones de campos
                if (nombreTareaTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la tarea", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (proyectoTareaComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un proyecto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prioridadTareaTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese la prioridad de la tarea", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int selectedUserIndex = asignarUsuarioComboBox.getSelectedIndex();
                if (selectedUserIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UsuarioDTO usuario = usuarios.get(selectedUserIndex);
                String proyectoSeleccionado = (String) proyectoTareaComboBox.getSelectedItem();
                
                //convertir los valores de las fechas ingresadas a localdatetime
                fechaInicioDate = (LocalDateTime) fechaInicioSpinner.getValue();
                fechaFinDate = (LocalDateTime) fechaFinSpinner.getValue();

                // Registrar la tarea
                api.registrarTarea(
                    nombreTareaTextField.getText(),
                    proyectoSeleccionado,
                    prioridadTareaTextField.getText(),
                    usuario.getUsername(), // Pasar el usuario seleccionado
                    false, // Estado inicial
                    "" // Descripción inicial (puedes cambiar esto si necesitas)
                );
                
                ((VentanaTareas) ventanaTareas).actualizarTabla();

                JOptionPane.showMessageDialog(null, "Tarea creada con éxito!", "Info", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                dispose();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
}

