package interfaces.valen.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interfaces.valen.frames.VentanaGestionLineasDeTransporte;
import interfaces.valen.otros.ColorPicker;
import interfaces.valen.otros.ElementoListaGestionTransporte;
import interfaces.valen.otros.TextPrompt;

public class PanelListadoGestionLineas extends JPanel implements ItemListener, DocumentListener{
	
	JTextField textoBusqueda;
//	JPanel panelBusqueda;
	JPanel panelCheckboxesColorPicker;
	JCheckBox checkBoxActiva;
	JCheckBox checkBoxNoActiva;
	JLabel labelColor;
	ColorPicker colorPicker;
	PanelGridListaGestionLineas panelGridLista;
	JScrollPane panelScrollLista;
	VentanaGestionLineasDeTransporte framePadre;
	GridBagConstraints gbc;
	
	public PanelListadoGestionLineas(VentanaGestionLineasDeTransporte frame) {
		
		framePadre = frame;
		
		this.setBorder(BorderFactory.createTitledBorder("Listado"));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		
		// Primer componente - JTextField
//		panelBusqueda = new JPanel();
		textoBusqueda = new JTextField();
		TextPrompt tpTextoBusqueda = new TextPrompt("Busca una l�nea por nombre", textoBusqueda);
		textoBusqueda.setPreferredSize(new Dimension(200,20));
//		textoBusqueda.addActionListener(e -> System.out.println(textoBusqueda.getText()));
		textoBusqueda.getDocument().addDocumentListener(this);
		
//		panelBusqueda.add(textoBusqueda);
		this.add(textoBusqueda, gbc);
		
		// Segundo componente - Panel con 2 checkboxes y un colorPicker
		panelCheckboxesColorPicker = new JPanel();
		panelCheckboxesColorPicker.setMinimumSize(new Dimension(300,40));
		
		checkBoxActiva = new JCheckBox("Activa");
		checkBoxActiva.setMnemonic(KeyEvent.VK_A);
		checkBoxActiva.setSelected(true);
		checkBoxActiva.addItemListener(this);
		
		panelCheckboxesColorPicker.add(checkBoxActiva);
		
		checkBoxNoActiva = new JCheckBox("No activa");
		checkBoxNoActiva.setMnemonic(KeyEvent.VK_N);
		checkBoxNoActiva.setSelected(true);
		checkBoxNoActiva.addItemListener(this);
		
		panelCheckboxesColorPicker.add(checkBoxNoActiva);
		
		labelColor = new JLabel("Color:");
		panelCheckboxesColorPicker.add(labelColor);
		colorPicker = new ColorPicker(frame, Color.WHITE);
		panelCheckboxesColorPicker.add(colorPicker);
		this.add(panelCheckboxesColorPicker, gbc);
		
		// Tercer componente - Lista
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelGridLista = new PanelGridListaGestionLineas(frame, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), "");		
		panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(panelScrollLista, gbc);
	}
	
	public void itemStateChanged(ItemEvent e) {
	    
	    Object source = e.getItemSelectable();

	    if (source == checkBoxActiva) {
	    	
	    	this.remove(panelGridLista);
	    	this.remove(panelScrollLista);
	    	
	    	this.revalidate();
	    	this.repaint();
	    	
	    	gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.BOTH;
			panelGridLista = new PanelGridListaGestionLineas(framePadre, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText());		
			panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(panelScrollLista, gbc);
			
	    } else if (source == checkBoxNoActiva) {
	    	
	    	this.remove(panelGridLista);
	    	this.remove(panelScrollLista);
	    	
	    	this.revalidate();
	    	this.repaint();
	    	
	    	gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.BOTH;
			panelGridLista = new PanelGridListaGestionLineas(framePadre, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText());		
			panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.add(panelScrollLista, gbc);
	    } 

	    if (e.getStateChange() == ItemEvent.DESELECTED) {
	    
	    }
	    
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.remove(panelGridLista);
    	this.remove(panelScrollLista);
    	
    	this.revalidate();
    	this.repaint();
    	
    	gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelGridLista = new PanelGridListaGestionLineas(framePadre, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText());		
		panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panelScrollLista, gbc);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.remove(panelGridLista);
    	this.remove(panelScrollLista);
    	
    	this.revalidate();
    	this.repaint();
    	
    	gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelGridLista = new PanelGridListaGestionLineas(framePadre, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText());		
		panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panelScrollLista, gbc);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.remove(panelGridLista);
    	this.remove(panelScrollLista);
    	
    	this.revalidate();
    	this.repaint();
    	
    	gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelGridLista = new PanelGridListaGestionLineas(framePadre, checkBoxActiva.isSelected(), checkBoxNoActiva.isSelected(), textoBusqueda.getText());		
		panelScrollLista = new JScrollPane(panelGridLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(panelScrollLista, gbc);
	}
}
