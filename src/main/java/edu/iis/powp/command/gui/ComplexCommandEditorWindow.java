package edu.iis.powp.command.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import edu.iis.powp.command.manager.PlotterCommandManager;

public class ComplexCommandEditorWindow extends JFrame {
	private PlotterCommandManager commandManager;

	public ComplexCommandEditorWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Editor");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		this.commandManager = commandManager;
	}
}
