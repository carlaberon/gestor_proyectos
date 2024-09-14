package ar.edu.unrn.seminario.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VerMiembros {

    private JFrame f = new JFrame();
    
    private JTable tabla = new JTable();
    
    private JButton activar = new JButton("Activar");
    private JButton desactivar = new JButton("Desactivar");
    
    private JPanel panelBotones = new JPanel();
    private JPanel panelTitulo = new JPanel();
    
    private String[] titulos = {
            "NOMBRE", 
            "ESTADO", 
            "PROYECTOS"
        };
    
    private Object[][] datos = { 
            {"usuario_A", true},
            {"usuario_B", false},
            {"usuario_C", false},
    };
    
    private final JLabel lblNewLabel = new JLabel("Miembros");

    public VerMiembros() {
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        f.setBackground(new Color(81, 79, 89));
        f.setBounds(500, 100, 1000, 1000);
        contentPane.setBounds(500, 100, 1000, 1000);
        
        tabla.setBackground(new Color(81, 79, 89));  // Color de fondo de la tabla
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        
        tabla.setModel(new DefaultTableModel(datos, titulos));

        // Aplicamos el nuevo renderer
        tabla.setDefaultRenderer(Object.class, new CustomCellRenderer());
        
     // Cambiar la fuente de los títulos de las columnas
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Establecer la nueva fuente para el encabezado
        
        // ScrollPane con la tabla dentro
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        // Cambiamos el color de fondo del espacio en blanco
        scrollPane.getViewport().setBackground(new Color(81, 79, 89)); // Color para el espacio vacío
        
        panelBotones.setBackground(new Color(29, 17, 40));
        panelTitulo.setBackground(new Color(81, 79, 89));
        activar.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        activar.setBackground(new Color(229, 212, 237));
    
        panelBotones.add(activar);
        desactivar.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        desactivar.setBackground(new Color(229, 212, 237));
        panelBotones.add(desactivar);
        
        activar.addActionListener(new ActivarRol());
        desactivar.addActionListener(new DesactivarRol());
        
        contentPane.add(panelBotones, BorderLayout.SOUTH);
        contentPane.add(panelTitulo, BorderLayout.NORTH);
        lblNewLabel.setForeground(new Color(29, 17, 40));
        lblNewLabel.setBackground(new Color(29, 17, 40));
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        
        panelTitulo.add(lblNewLabel);
        
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        f.pack();
        f.setVisible(true);
    }
    
    public static void main (String[] args) {
        VerMiembros vent = new VerMiembros();
    }

    // Clase interna para manejar la acción de activar Rol
    private class ActivarRol implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(f, "¿Estás seguro de que deseas Activar Rol?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) tabla.getModel()).setValueAt(true, selectedRow, 1);
                }
            } else {
                JOptionPane.showMessageDialog(f, "Por favor, selecciona un rol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Clase interna para manejar la acción de desactivar Rol
    private class DesactivarRol implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(f, "¿Estás seguro de que deseas Desactivar Rol?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) tabla.getModel()).setValueAt(false, selectedRow, 1);
                }
            } else {
                JOptionPane.showMessageDialog(f, "Por favor, selecciona un rol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Clase Custom para renderizar las celdas de la tabla con color de fondo y color de texto
    class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            // Alterna colores de las filas
            if (row % 2 == 0) {
                c.setBackground(new Color( 81, 79, 89)); // Color para filas pares
            } else {
                c.setBackground(new Color( 81, 79, 89)); // Color para filas impares
            }
            
            // Cambia el color de fondo si la fila está seleccionada
            if (isSelected) {
                c.setBackground(new Color(229, 212, 237)); // Color para fila seleccionada
            }

            // Cambia el color del texto
            if (column == 1 && (boolean)value) { 
                c.setForeground(new Color(240, 240, 240)); 
            } else if (column == 1 && !(boolean)value) {
                c.setForeground(new Color(240, 240, 240)); 
            } else {
                c.setForeground(new Color(29, 17, 40)); 
            }

            return c;
        }
    }
 // Clase para renderizar los encabezados de la tabla con color de fondo
    class HeaderRenderer extends DefaultTableCellRenderer {
        public HeaderRenderer(JTable table) {
            setFont(table.getTableHeader().getFont()); // Usa la fuente del encabezado
            setHorizontalAlignment(CENTER); // Centra el texto
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(new Color(29, 17, 40)); // Color de fondo de los encabezados
            c.setForeground(new Color(29, 17, 40)); // Color del texto del encabezado
            return c;
        }
    }
}
