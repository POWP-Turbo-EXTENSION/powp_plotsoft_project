package edu.iis.powp.command.editor.gui.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawToPane extends JPanel {
	 private final JTextField xField = new JTextField(5);
	 private final JTextField yField = new JTextField(5);

	 
     public DrawToPane() {
		super();
		this.setLayout(new GridLayout(1, 4));
	    this.add(new JLabel("x:"));
	    this.add(xField);
	    
	    this.add(new JLabel("y:"));
	     this.add(yField);

	    
	}

     public int getX(){
    	 return Integer.parseInt(xField.getText());
     }
     public int getY(){
    	 return Integer.parseInt(yField.getText());
     }
}
