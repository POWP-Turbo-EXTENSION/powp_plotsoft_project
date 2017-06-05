package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import edu.iis.powp.command.manager.PlotterCommandManager;

public class ComplexCommandEditorWindow extends JFrame {
	private PlotterCommandManager commandManager;
	private JMenuBar menuBar;
	private JMenu menuFile;
	
	public ComplexCommandEditorWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Editor");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		this.commandManager = commandManager;
		InitMenu();
	}
	
	private void InitMenu(){
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		this.setJMenuBar(menuBar);
		this.validate();
		this.repaint();
	}
}
