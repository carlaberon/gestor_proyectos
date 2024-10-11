package ar.edu.unrn.seminario.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.dto.ProyectoDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListaProyectos extends JFrame {
	private IApi api;
	
    public ListaProyectos(IApi api) {
    	this.api = api;
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
        /*JTable tabla = new JTable(new DefaultTableModel(
                new Object[][]{
                        {"Gestionar evento", "Activo", "La gestion de diferentes", "\u2714", "31/03/2024", "Carla", "A1987", "16/08/2023", "\u2B07", "alta"},
                        {"Recuento de votos", "Finalizado", "Informacion sobre los votos", "\u2718", "28/03/2024", "Gabriel", "A2987", "28/03/2023", "\u2B07", "Media"},
                        {"Gestionar tarea", "Activo", "Tareas necesarias sobre la", "\u2714", "14/04/2024", "Hernan", "A3987", "11/01/2023", "\u2B07", "Baja"},
                        {"Parciales", "Inactivo", "Informacion sobre como completar la informacion de los parciales de la carrera", "\u2718", "31/11/2023", "Tomas", "A4987", "28/03/2023", "\u2B07", "Alta"},
                },
                new String[]{
                        "Nombre del proyecto", "Estado", "Descripcion del estado", "Público", "Fecha de vencimiento", "Propietario del proyecto", "ID del proyecto", "Creado el", "Expandir", "Prioridad"
                }
        ));*/
        JTable tabla = new JTable();
        String[] proyectosTabla = {"Nombre", "Descripcion", "Estado", "Prioridad", "Propietario"};
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, proyectosTabla);
        tabla.setModel(modelo);
        
        List<ProyectoDTO> proyectos = api.obtenerProyectos();
        
        for (ProyectoDTO p : proyectos) {
			modelo.addRow(new Object[] {p.getNombre(), p.getDescripcion(), p.isEstado(), p.getPrioridad(), p.getUsuarioPropietario()});
		}

     // Configurar render de la tabla
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(Color.WHITE);
                return c;
            }
        });

        // Personalizar la columna "Público" con un JCheckBox centrado
        /*tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el checkbox
                checkBox.setSelected("\u2714".equals(value)); // Marca el checkbox si el valor es el caracter ✔
                return checkBox;
            }
        });*/

        // Establecer fuente y color de encabezados
        tabla.setFont(fuente);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tabla.getTableHeader().setBackground(tituloColor); 
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setBackground(fondoColor);
        tabla.setRowHeight(30);

        /*// Ajustar ancho de columnas
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50); // Público
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50); // Expandir
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50); // Prioridad*/

        // Hacer que la columna de descripción permita texto multilínea
        tabla.getColumnModel().getColumn(2).setCellRenderer(new JTextAreaRenderer());

        /*// Crear botones de expandir
        tabla.getColumn("Expandir").setCellRenderer(new ButtonRenderer());
        tabla.getColumn("Expandir").setCellEditor(new ButtonEditor(new JCheckBox(), tabla));*/

     // ComboBox para prioridad
        String[] prioridades = {"Alta", "Media", "Baja"};
        JComboBox<String> comboBox = new JComboBox<>(prioridades);
        TableColumn prioridadColumna = tabla.getColumnModel().getColumn(3);
        prioridadColumna.setCellEditor(new DefaultCellEditor(comboBox));

        // Crear un panel inferior con un color específico
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(109, 114, 195)); // Púrpura para el fondo del panel inferior
        JLabel labelInferior = new JLabel("LabProject - Sistema de Gestión de Proyectos");
        labelInferior.setForeground(Color.WHITE);
        panelInferior.add(labelInferior);

        // Añadir la tabla y el panel inferior al JFrame
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.getViewport().setBackground(fondoColor); // Establecer el fondo del viewport
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH); // Añadir el panel inferior
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
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(new Color(48, 48, 48)); // Fondo oscuro
                setForeground(Color.WHITE); // Texto en blanco
            }
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	IApi api = null;
			try {
				api = new MemoryApi();
			} catch (NotNullException | DataEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	ListaProyectos frame = new ListaProyectos(api);
            frame.setVisible(true);
        });
    }
}
