package interfaces.valen.paneles;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.valen.frames.VentanaSiguienteAltaLineaDeTransporte;
import interfaces.valen.otros.ColorPicker;

public class PanelResumenAltaLinea extends JPanel{

	JLabel labelNombre, nombreLinea, labelEstado, estado, labelColor;
	JPanel color;
	GridBagConstraints gbc;
	
	public PanelResumenAltaLinea(VentanaSiguienteAltaLineaDeTransporte frame) {
		
		this.setBorder(BorderFactory.createTitledBorder("Resumen - Alta de l�nea"));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		// Componentes
		labelNombre = new JLabel("Nombre:");
		nombreLinea = new JLabel("<<Nombre l�nea de transporte>>");
		labelEstado = new JLabel("Estado:");
		estado = new JLabel("<<Estado>>");
		labelColor = new JLabel("Color:");
		color = new JPanel();
		color.setBackground(Color.blue);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridy = 0;
		
		gbc.gridx = 0;
		this.add(labelNombre, gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		this.add(nombreLinea, gbc);
		gbc.weightx = 0.0;
		
		gbc.gridx = 2;
		this.add(labelEstado, gbc);
		
		gbc.gridx = 3;
		this.add(estado, gbc);
		
		gbc.gridx = 4;
		this.add(labelColor, gbc);
		
		gbc.gridx = 5;
		this.add(color, gbc);
				
	}
	
}
