package edu.iis.powp.command.editor.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.iis.powp.command.editor.CommandTreeService;
import edu.iis.powp.command.editor.ICommandEditorAction;
import edu.iis.powp.command.editor.gui.panels.CommandTree;
import edu.iis.powp.command.editor.gui.panels.ManageCommandButtons;
import edu.iis.powp.command.editor.gui.panels.TreeManipulationButtons;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.window.WindowComponent;

public class CommandEditorWindow extends JFrame implements WindowComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391923043891870668L;
	private final ManageCommandButtons buttonsSouth;
	private final CommandTree centralPane;
	private final TreeManipulationButtons buttonsNorth;
	private final CommandTreeService service = new CommandTreeService();

	public CommandEditorWindow(ICommandEditorAction buttonsAction) {
		super();		
						
		this.centralPane = new CommandTree();
		this.buttonsSouth = new ManageCommandButtons(buttonsAction);
		this.buttonsNorth = new TreeManipulationButtons(service);
		centralPane.setTreeBehaviour(service);
		buttonsAction.setTreeConfiguration(centralPane);
		
		initUI();
	}

	private void initUI() {
		this.setLayout(new BorderLayout());
		this.getContentPane().add(buttonsSouth, BorderLayout.SOUTH);
		this.getContentPane().add(centralPane, BorderLayout.CENTER);
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
//		centralPane.setTreeNode(System.currentTimeMillis()+"_NODE");
	}
}
