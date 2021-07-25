package interfaces.valen.paneles;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelTablaAltaLinea extends JPanel{

	JScrollPane panelScroll;
	JTable tabla;
	String[] nombreColumnas = {	"Estaci�n origen", "Estaci�n destino", "Distancia",
								"Duraci�n de viaje", "Cantidad m�xima de pasajeros",
								"Estado ruta", "Costo"};
	Object[][] data = {{"A", "B", 100, 50, 80, "Activo", 35}};
	public PanelTablaAltaLinea() {
		
		tabla = new JTable(data, nombreColumnas);
		
		panelScroll = new JScrollPane(tabla);
		
		this.add(panelScroll);
	}
}
