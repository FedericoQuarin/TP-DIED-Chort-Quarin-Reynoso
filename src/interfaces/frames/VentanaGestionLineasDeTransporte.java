package interfaces.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import interfaces.paneles.PanelListadoGestionLineas;

public class VentanaGestionLineasDeTransporte extends JFrame{

	GridBagLayout gbLayout;
	GridBagConstraints gbc;
	public VentanaGestionLineasDeTransporte() {
		
		gbLayout = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("Gesti�n de l�neas de transporte");
//		this.add(new PanelListadoGestionLineas());
		this.setLayout(gbLayout);		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		PanelListadoGestionLineas panelListado = new PanelListadoGestionLineas();
		this.add(panelListado, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JPanel panelGrafo = new JPanel();
		panelGrafo.setBorder(BorderFactory.createTitledBorder("L�neas de transporte"));
		this.add(panelGrafo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton botonCancelar = new JButton("Cancelar");
		this.add(botonCancelar, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		JButton botonSiguiente = new JButton("Siguiente");
		this.add(botonSiguiente, gbc);
		
		
		
		
		this.setVisible(true);
	}
}
