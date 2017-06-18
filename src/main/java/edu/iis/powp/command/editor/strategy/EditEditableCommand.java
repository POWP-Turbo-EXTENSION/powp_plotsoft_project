package edu.iis.powp.command.editor.strategy;

import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.visitor.Visitor;

public class EditEditableCommand implements Strategy {

	private final IEditablePlotterCommand cmd;
	private final ICompoundCommand currentCommand;
	
	public EditEditableCommand(IEditablePlotterCommand cmd, ICompoundCommand currentCommand) {
		super();
		this.cmd = cmd;
		this.currentCommand = currentCommand;
	}

	@Override
	public void execute() {
		if (cmd != null) {
			checkChildren(currentCommand);
		}
		
		
	}
	private void checkChildren(ICompoundCommand parent){
		for (Iterator iterator = parent.iterator(); iterator.hasNext();) {
			IPlotterCommand type = (IPlotterCommand) iterator.next();
			if (IEditablePlotterCommand.class.isInstance(type) && type.equals(cmd)) {
				JTextField posX = new JTextField(5);
				JTextField posY = new JTextField(5);
				Object[] message = { "X:", posX, "Y:", posY };
				posX.setText(String.valueOf(((IEditablePlotterCommand)type).getX()));
				posY.setText(String.valueOf(((IEditablePlotterCommand)type).getY()));
				int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == 0) {
					((IEditablePlotterCommand)type).setX(Integer.parseInt(posX.getText()));
					((IEditablePlotterCommand)type).setY(Integer.parseInt(posY.getText()));
				}
				break;
			}  
			else if (ICompoundCommand.class.isInstance(type)) {
				checkChildren((ICompoundCommand) type);
			}
		}
	}
	
	


}
