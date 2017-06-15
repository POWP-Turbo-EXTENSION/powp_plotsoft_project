package edu.iis.powp.command.editor.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.iis.powp.command.editor.ICommandEditorAction;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.window.WindowComponent;

public class CommandEditorWindow extends JFrame implements WindowComponent {

	private final JPanel buttonsSouth;
	private final JPanel buttonsNorth;
	private final PlotterCommandManager commandManager;
		
	public CommandEditorWindow(PlotterCommandManager commandManager, ICommandEditorAction buttonsAction) {
		super();
		this.commandManager = commandManager;
		this.buttonsSouth = new CommandEditorSouthButtonsPane(buttonsAction);
		this.buttonsNorth = new CommandEditorNorthButtonsPane(buttonsAction);
		initUI();
	}

	private void initUI() {
		this.setLayout(new BorderLayout());
		this.getContentPane().add(buttonsSouth, BorderLayout.SOUTH);
		this.getContentPane().add(buttonsNorth, BorderLayout.NORTH);
		pack();
	}
	
	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
		
	}
}
