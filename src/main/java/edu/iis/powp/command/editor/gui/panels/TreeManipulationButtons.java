package edu.iis.powp.command.editor.gui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.iis.powp.command.editor.ICommandEditorAction;
import edu.iis.powp.command.editor.IOperationsOnTree;

public class TreeManipulationButtons extends JPanel implements ActionListener {
	
	private final JButton moveUp = new JButton("↑Up");
	private final JButton moveDown = new JButton("↓Down");
	private final JButton deleteIt = new JButton("×Delete");
	private final JButton editIt = new JButton("→Edit");
	private final IOperationsOnTree buttonsAction;

	public TreeManipulationButtons(IOperationsOnTree buttonsAction) {
		super();
		this.buttonsAction = buttonsAction;
		initUI();
	}

	private void initUI() {
		this.setLayout(new GridLayout(2, 2));
		this.add(moveUp);
		this.add(moveDown);
		this.add(deleteIt);
		this.add(editIt);
		this.editIt.addActionListener(this);
		this.deleteIt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(editIt)){
			buttonsAction.editSelectedCommand();
		}
		if(e.getSource().equals(deleteIt)){
			buttonsAction.removeSelectedCommand();
		}
	}
}
