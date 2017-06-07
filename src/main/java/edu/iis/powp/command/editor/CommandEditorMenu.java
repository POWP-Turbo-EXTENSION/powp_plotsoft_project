package edu.iis.powp.command.editor;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.iis.powp.app.Application;

public class CommandEditorMenu {
	private Application app;
	private JPanel pane;
	private JPanel buttons = new JPanel();
	private ITurboButtonsAction turbo;
	private JButton newComplexCommand = new JButton("New"); 
	private JButton saveComplexCommand = new JButton("Save");
	private JButton newPosition = new JButton("Add position");
	private JButton newLine = new JButton("Add line");
	public CommandEditorMenu(Application app) {
		super();
		this.app = app;
		this.pane = this.app.getFreeRightPanel();
		buildMenu();
	}

	private void buildMenu() {
		buttons.setLayout(new GridLayout(4, 4));
		this.pane.add(buttons, BorderLayout.SOUTH);
		this.buttons.add(newComplexCommand);
		this.buttons.add(saveComplexCommand);
		this.buttons.add(newPosition);
		this.buttons.add(newLine);
	}
}
