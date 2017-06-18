package edu.iis.powp.command.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.iis.powp.command.editor.CommandTreeService;
import edu.iis.powp.command.editor.ICommandEditorAction;
import edu.iis.powp.command.editor.IOperationsOnTree;
import edu.iis.powp.command.editor.ITreeBehaviour;
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
	private final static Dimension MINIMAL_WINDOW_SIZE = new Dimension(200, 600);
//	private final CommandTreeService service;

	public CommandEditorWindow(ICommandEditorAction buttonsAction, IOperationsOnTree onTree, ITreeBehaviour behaviour) {
		super();		
						
		this.centralPane = new CommandTree();
		this.buttonsSouth = new ManageCommandButtons(buttonsAction, onTree);
		this.buttonsNorth = new TreeManipulationButtons(onTree);
		centralPane.setTreeBehaviour(behaviour);
		buttonsAction.setTreeConfiguration(centralPane);
		
		initUI();
	}

	private void initUI() {
		this.setLayout(new BorderLayout());
		this.setMinimumSize(MINIMAL_WINDOW_SIZE);
		this.setPreferredSize(MINIMAL_WINDOW_SIZE);
		this.getContentPane().add(buttonsSouth, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(centralPane), BorderLayout.CENTER);
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
