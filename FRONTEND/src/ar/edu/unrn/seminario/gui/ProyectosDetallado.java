package ar.edu.unrn.seminario.gui;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProyectosDetallado extends JFrame {

    public ProyectosDetallado() {
        // Configuración básica de la ventana
        setTitle("Proyectos Activos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        getContentPane().setLayout(new BorderLayout());

        // Configurar colores y fuente
        Color fondoColor = new Color(48, 48, 48); // Color de fondo oscuro
        Color tituloColor = new Color(109, 114, 195); // Púrpura para los títulos
        Color nombreProyectoColor = new Color(109, 114, 195); // Púrpura para nombres de proyecto
        Font fuente = new Font("Segoe UI", Font.PLAIN, 11);

        getContentPane().setBackground(fondoColor);

        // Crear la tabla
        JTable tabla = new JTable(new DefaultTableModel(
                new Object[][]{
                        {"Gestionar evento", "Activo", "La gestion de diferentes", "\u2714", "31/03/2024", "Carla", "A1987", "16/08/2023", "\u2B07"},
                        {"Recuento de votos", "Finalizado", "Informacion sobre los votos", "\u2718", "28/03/2024", "Gabriel", "A2987", "28/03/2023", "\u2B07"},
                        {"Gestionar tarea", "Activo", "Tareas necesarias sobre la", "\u2714", "14/04/2024", "Hernan", "A3987", "11/01/2023", "\u2B07"},
                        {"Parciales", "Inactivo", "Informacion sobre como completar la informacion de los parciales de la carrera", "\u2718", "31/11/2023", "Tomas", "A4987", "28/03/2023", "\u2B07"},
                },
                new String[]{
                        "Nombre del proyecto", "Estado", "Descripcion del estado", "Público", "Fecha de vencimiento", "Propietario del proyecto", "ID del proyecto", "Creado el", "Expandir"
                }
        ));

        // Cambiar el color de la columna "Nombre del proyecto"
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 0) { // Nombres de proyectos
                    c.setForeground(nombreProyectoColor);
                } else {
                    c.setForeground(Color.WHITE); // Color blanco para otras columnas
                }
                setBackground(new Color(48, 48, 48)); // Color de fondo de la tabla
                return c;
            }
        });

        // Personalizar la columna "Público" con un JCheckBox centrado
        tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el checkbox
                checkBox.setSelected("\u2714".equals(value)); // Marca el checkbox si el valor es el caracter ✔
                return checkBox;
            }
        });

        // Establecer fuente y color de encabezados
        tabla.setFont(fuente);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tabla.getTableHeader().setBackground(new Color(109, 114, 195)); // Color púrpura para el encabezado
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setBackground(fondoColor);
        tabla.setRowHeight(30);

        // Ajustar ancho de columnas
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); // Público
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50); // Expandir

        // Hacer que la columna de descripción permita texto multilínea
        tabla.getColumnModel().getColumn(2).setCellRenderer(new JTextAreaRenderer());

        // Crear botones de expandir
        tabla.getColumn("Expandir").setCellRenderer(new ButtonRenderer());
        tabla.getColumn("Expandir").setCellEditor(new ButtonEditor(new JCheckBox(), tabla));

        // Crear un panel inferior con un color específico
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(109, 114, 195)); // Púrpura para el fondo del panel inferior
        JLabel labelInferior = new JLabel("LabProject - Sistema de Gestión de Proyectos");
        labelInferior.setForeground(Color.WHITE);
        panelInferior.add(labelInferior);

        // Añadir la tabla y el panel inferior al JFrame
        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH); // Añadir el panel inferior
        
        //quisas quito esto
    }

    // Renderer personalizado para celdas con JTextArea (que permita texto multilínea)
    class JTextAreaRenderer extends JTextArea implements TableCellRenderer {
        public JTextAreaRenderer() {
            setLineWrap(true); // Permite que el texto salte a la siguiente línea
            setWrapStyleWord(true); // Ajusta a palabras completas
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
                
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
                setForeground(Color.WHITE); // Texto en blanco
                setBackground(new Color(48, 48, 48));
            }
            return this;
        }
    }

    // Editor para manejar la acción del botón "Expandir"
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    
                    // Obtener el tamaño preferido de la celda de la columna de descripción
                    JTextArea textArea = (JTextArea) table.getCellRenderer(row, 2)
                        .getTableCellRendererComponent(table, table.getValueAt(row, 2), false, false, row, 2);
                    int preferredHeight = textArea.getPreferredSize().height;

                    // Establecer la altura de la fila en función del tamaño preferido
                    table.setRowHeight(row, preferredHeight > 30 ? preferredHeight : 30);
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Expandir" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("↓");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER); // Botón centrado
            return this;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProyectosDetallado frame = new ProyectosDetallado();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
