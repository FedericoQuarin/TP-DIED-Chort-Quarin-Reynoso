package interfaces.valen.paneles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.valen.frames.VentanaEdicionLineaDeTransporte;
import interfaces.valen.frames.VentanaGestionLineasDeTransporte;

public class PanelBotonesEdicionLinea extends JPanel{

	JButton botonCancelar;
	JButton botonTerminado;
	GridBagConstraints gbc;
	VentanaEdicionLineaDeTransporte frame;
	
	public PanelBotonesEdicionLinea(VentanaEdicionLineaDeTransporte frame, PanelPrincipalEdicionLineaDeTransporte panelPrincipal) {
		
		this.frame = frame;
		
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		gbc.gridx = 0;
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(e -> this.cancelarEdicion());
		this.add(botonCancelar, gbc);
		
		gbc.gridx = 1;
		botonTerminado = new JButton("Confirmar cambios");
		botonTerminado.addActionListener(e -> this.editarDatos(panelPrincipal));
		this.add(botonTerminado, gbc);
		
	}
	
	public void editarDatos(PanelPrincipalEdicionLineaDeTransporte panelPrincipal) {
		//Custom JOptionPane
		Object[] options = {"Cancelar",
		                    "Confirmar"};
		int n = JOptionPane.showOptionDialog(frame,
										     "�Est� seguro que desea confirmar los cambios realizados?",
										     "Confirmar edici�n de l�nea",
										     JOptionPane.YES_NO_OPTION,
										     JOptionPane.QUESTION_MESSAGE,
										     null,
										     options,
										     options[0]);
		
		if(n == JOptionPane.NO_OPTION) {
			frame.dispose();
			panelPrincipal.actualizarDatos();
			new VentanaGestionLineasDeTransporte();
		}
		
	}
	
	public void cancelarEdicion() {
		//Custom JOptionPane
		Object[] options = {"No",
		                    "Si"};
		int n = JOptionPane.showOptionDialog(frame,
										     "�Est� seguro que desea cancelar la edici�n? \n" +
										     "Todos los cambios realizados se perder�n.",
										     "Cancelar edici�n de l�nea",
										     JOptionPane.YES_NO_OPTION,
										     JOptionPane.QUESTION_MESSAGE,
										     null,
										     options,
										     options[0]);
		
		if(n == JOptionPane.NO_OPTION){
			frame.dispose();
			new VentanaGestionLineasDeTransporte();
		}
	}
}
