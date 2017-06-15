package edu.iis.powp.command.editor.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.iis.powp.command.editor.ICommandEditorAction;

public class ManageCommandButtons extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1824361936304879319L;
	
	private final ICommandEditorAction buttonsAction;
	private final JButton newComplexCommand = new JButton("New");
	private final JButton saveComplexCommand = new JButton("Save");
	private final JButton newPosition = new JButton("Add position");
	private final JButton newLine = new JButton("Add line");

	
	public ManageCommandButtons(ICommandEditorAction buttonsAction) {
		super();
		this.buttonsAction = buttonsAction;
		initUI();
	}

	private void initUI() {
		this.setLayout(new GridLayout(4, 1));
		this.add(newComplexCommand);
		this.add(saveComplexCommand);
		this.add(newPosition);
		this.add(newLine);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
