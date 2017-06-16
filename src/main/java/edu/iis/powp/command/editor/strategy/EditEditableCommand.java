package edu.iis.powp.command.editor.strategy;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.visitor.Visitor;

public class EditEditableCommand implements Strategy {

	private final IEditablePlotterCommand cmd;
	
	public EditEditableCommand(IEditablePlotterCommand cmd) {
		super();
		this.cmd = cmd;
	}

	@Override
	public void execute() {
		JTextField posX = new JTextField(5);
		JTextField posY = new JTextField(5);
		Object[] message = { "X:", posX, "Y:", posY };
		posX.setText(String.valueOf(cmd.getX()));
		posY.setText(String.valueOf(cmd.getY()));
		int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == 0) {
			cmd.setX(Integer.parseInt(posX.getText()));
			cmd.setY(Integer.parseInt(posY.getText()));
		}
		
	}

	
	


}
