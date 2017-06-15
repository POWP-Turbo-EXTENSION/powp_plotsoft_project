package edu.iis.powp.command.editor;

import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.editor.gui.panels.DrawToPane;
import edu.iis.powp.command.visitor.Visitor;

public class CommandTreeService implements ITreeBehaviour, ITreeOperations, Visitor {

	IPlotterCommand lastPlotterCommand;
	ICompoundCommand lastCompoundCommand;

	@Override
	public void leafIsSelected(Object obj) {
		lastCompoundCommand = null;
		lastPlotterCommand = null;
		if (IPlotterCommand.class.isInstance(obj)) {
			IPlotterCommand cmd = (IPlotterCommand) obj;
			lastPlotterCommand = cmd;
		}
	}

	@Override
	public void dirIsSelected(Object obj) {
		lastCompoundCommand = null;
		lastPlotterCommand = null;
		if (ICompoundCommand.class.isInstance(obj)) {
			ICompoundCommand cmd = (ICompoundCommand) obj;
			lastCompoundCommand = cmd;
		}

	}

	@Override
	public void testAction() {
		if (lastPlotterCommand != null) {

			System.out.println("asdasd234");
			System.out.println(lastPlotterCommand);
			if(IEditablePlotterCommand.class.isInstance(lastPlotterCommand))
				visit((IEditablePlotterCommand) lastPlotterCommand);
		}

	}
	//
	// @Override
	// public void visit(DrawToCommand cmd) {
	// JTextField posX = new JTextField(5);
	// JTextField posY = new JTextField(5);
	// Object[] message = {
	// "X:", posX,
	// "Y:", posY
	// };
	// posX.setText(String.valueOf(cmd.posX));
	// posY.setText(String.valueOf(cmd.posY));
	// int result = JOptionPane.showConfirmDialog(null, message,
	// "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	// cmd.setXY(Integer.parseInt(posX.getText()),
	// Integer.parseInt(posY.getText()));
	// }
	//
	// @Override
	// public void visit(SetPositionCommand cmd) {
	//
	// }
	//
	// @Override
	// public void visit(ComplexCommand complexCommand) {
	//
	// }

	@Override
	public void visit(IEditablePlotterCommand cmd) {
		JTextField posX = new JTextField(5);
		JTextField posY = new JTextField(5);
		Object[] message = { "X:", posX, "Y:", posY };
		posX.setText(String.valueOf(cmd.getX()));
		posY.setText(String.valueOf(cmd.getY()));
		int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
		cmd.setX(Integer.parseInt(posX.getText()));
		cmd.setY(Integer.parseInt(posY.getText()));

	}

}
