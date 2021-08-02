package interfaces.julio.paneles;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import clases.Estacion;
import enums.EstadoEstacion;
import interfaces.julio.frames.EstacionEditar;
import interfaces.julio.frames.EstacionEditarGrafo;
import interfaces.julio.frames.EstacionGestionar;
import interfaces.julio.otros.ModeloTabla;
import interfaces.valen.paneles.PanelGridListaGestionLineas;

public class PanelEstacionTablaResultado extends JPanel implements DocumentListener{

	private JLabel label;
	private JButton botonEditar, botonBorrar, botonEditarGrafo;
	private JTextField field;
	private JPanel panelBusqueda;
	private JTable tabla;
	
	private Vector filaSeleccionada = null;
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private EstacionEditar frameEdicion;
	private EstacionEditarGrafo frameEditarGrafo;
	
	public PanelEstacionTablaResultado(EstacionGestionar frame) {
	
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Resultado"));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		ModeloTabla miModelo = new ModeloTabla();
		cargarModelo(miModelo);
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tama�o de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		prueba(miModelo);
		
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.out.println(miModelo.getDataVector().elementAt(tabla.getSelectedRow()));
				
				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
				nroFilaSeleccionada = tabla.getSelectedRow();
				botonEditar.setEnabled(true);
				botonBorrar.setEnabled(true);
			}
		});
		

		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		//this.add(tableContainer, BorderLayout.CENTER);
		c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 30, 0, 30);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(tableContainer, c);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		
		
		botonEditar = new JButton("Editar Estaci�n");
		botonEditar.setEnabled(false);	//Deshabilitado por defecto
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameEdicion = new EstacionEditar(filaSeleccionada);
			}
		});
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		this.add(botonEditar, c);
		
		botonBorrar = new JButton("Borrar Estaci�n");
		botonBorrar.setEnabled(false); 		//Deshabilitado por defecto
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				Object[] options = {"Cancelar", "Borrar"};
				
				int n = JOptionPane.showOptionDialog(frame,
				"�Realmente desea borrar la estaci�n "+filaSeleccionada.elementAt(1)+" del sistema?",
				"Borrar Estaci�n",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]); //default button title
				
				if(n == 1) {
					System.out.println("Apret� borrar");
					miModelo.removeRow(nroFilaSeleccionada);
					filaSeleccionada = null;
					botonEditar.setEnabled(false);
					botonBorrar.setEnabled(false);
				}
				else {
					System.out.println("Apret� cancelar o cerr� la ventana");
				}
			}
//			}
		});
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
		this.add(botonBorrar, c);
		
		botonEditarGrafo = new JButton("Editar grafo de estaciones");
		botonEditarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameEditarGrafo = new EstacionEditarGrafo();
			}
		});
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 1;
		this.add(botonEditarGrafo, c);
	}
	
	public void actualizarPanelGridLista() {
		this.remove(panelGridLista);
    	this.remove(panelScrollLista);
    	
    	this.revalidate();
    	this.repaint();
    	
    	gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelGridLista = new PanelGridListaGestionLineas(framePadre,this, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText(), colorPicker.getColor());		
		panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panelScrollLista, gbc);

	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.actualizarPanelGridLista();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.actualizarPanelGridLista();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.actualizarPanelGridLista();
	}

	public static void cargarModelo(ModeloTabla miModelo) {
	
	miModelo.addColumn("Id");
	miModelo.addColumn("Nombre");
	miModelo.addColumn("Hora de apertura");
	miModelo.addColumn("Hora de cierre");
	miModelo.addColumn("Estado");
}

	public static void prueba(ModeloTabla miModelo) {
	
	Estacion[] estaciones = new Estacion[8];
	
	estaciones[0] = new Estacion(1, "A", LocalTime.of(8, 05), LocalTime.of(18, 45), EstadoEstacion.OPERATIVA);
	estaciones[1] = new Estacion(2, "B", LocalTime.of(8, 15), LocalTime.of(15, 25), EstadoEstacion.OPERATIVA);
	estaciones[2] = new Estacion(3, "C", LocalTime.of(11, 25), LocalTime.of(23, 45), EstadoEstacion.OPERATIVA);
	estaciones[3] = new Estacion(4, "D", LocalTime.of(10, 35), LocalTime.of(20, 05), EstadoEstacion.OPERATIVA);
	estaciones[4] = new Estacion(5, "E", LocalTime.of(9, 55), LocalTime.of(14, 50), EstadoEstacion.OPERATIVA);
	estaciones[5] = new Estacion(6, "F", LocalTime.of(6, 55), LocalTime.of(10, 10), EstadoEstacion.EN_MANTENIMIENTO);
	estaciones[6] = new Estacion(7, "G", LocalTime.of(7, 30), LocalTime.of(13, 15), EstadoEstacion.EN_MANTENIMIENTO);
	estaciones[7] = new Estacion(8, "H", LocalTime.of(11, 15), LocalTime.of(16, 20), EstadoEstacion.EN_MANTENIMIENTO);
	
	
	for(int i = 0; i < estaciones.length; i++)
	miModelo.addRow(
			new Object[] {estaciones[i].getId(), estaciones[i].getNombre(), estaciones[i].getHorarioApertura(), estaciones[i].getHorarioCierre(), estaciones[i].getEstado()}
			);
}

}