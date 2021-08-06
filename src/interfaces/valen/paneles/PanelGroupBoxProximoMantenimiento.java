package interfaces.valen.paneles;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import clases.Estacion;

public class PanelGroupBoxProximoMantenimiento extends JPanel{

	JLabel labelNombreLinea;
	JLabel labelDetalles;
	GridBagConstraints gbc;
	
	public PanelGroupBoxProximoMantenimiento() {
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Pr�ximo mantenimiento"));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = 0;
		
		labelDetalles = new JLabel("No hay ninguna estaci�n cargada en el sistema.");
		
		this.add(labelDetalles, gbc);
	}
	
	public PanelGroupBoxProximoMantenimiento(Estacion e) {
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Pr�ximo mantenimiento"));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		labelNombreLinea = new JLabel("El siguiente mantenimiento se deber�a realizar en la estaci�n: " + e.getNombre());
		String detalle = "Detalle: ";
		Optional<LocalDate> fechaFin = e.getFechaUltimoMantenimientoRealizado();
		if (fechaFin.isPresent()) {
			LocalDate hoy = LocalDate.now();
			labelDetalles = new JLabel(detalle + String.valueOf(Duration.between(fechaFin.get().atStartOfDay(), hoy.atStartOfDay()).toDays()) + " d�as desde el �ltimo mantenimiento.");
		} else {
			if (e.getMantenimientos().isEmpty()) {
				labelDetalles = new JLabel(detalle + "Nunca se le realiz� un mantenimiento a la estaci�n.");
			} else {
				labelDetalles = new JLabel(detalle + "Todas las estaciones est�n en mantenimiento");
			}
		}
		
		this.add(labelNombreLinea, gbc);
		
		this.add(labelDetalles, gbc);
	}
}
