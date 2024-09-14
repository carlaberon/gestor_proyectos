package ar.edu.unrn.seminario.gui;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


//Se extiende el DefaultTableModel
public class ModeloReservas extends DefaultTableModel {
	
//Se define un constructor que recibe como parámetros los títulos y datos
	
	public ModeloReservas(final Object[][] datos, final String[] titulos) {
		
		super(datos,titulos);
		
	}
//Este método se debe sobreescribir para indicar,
	//de qué tipo es cada columna.
	//de lo contrario siempre se muestran como String
	public Class getColumnClass(final int column) {
		
		if (column == 1) {
			return Boolean.class;
		}
		
		return this.getValueAt(0,column).getClass();
	}
}

