package edu.iis.powp.command.editor.strategy;

import java.util.ListIterator;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.editor.EditedCommand;

public class MoveDown implements Strategy {

	private final IPlotterCommand lastPlotterCommand;

	private final ICompoundCommand currentCommand;

	public MoveDown(IPlotterCommand lastPlotterCommand, ICompoundCommand currentCommand) {
		super();
		this.lastPlotterCommand = lastPlotterCommand;
		this.currentCommand = currentCommand;
	}

	@Override
	public void execute() {
		moveCommandDown(currentCommand);

	}

	private void moveCommandDown(ICompoundCommand parent) {
		if (lastPlotterCommand != null) {
			for (ListIterator<IPlotterCommand> iterator = parent.iterator(); iterator.hasNext();) {
				IPlotterCommand type;
				type = iterator.next();
				if (iterator.hasPrevious() && type.equals(lastPlotterCommand)) {
					IPlotterCommand temp1;
					temp1 = iterator.next();
					iterator.set(type);
					iterator.previous();
					iterator.previous();
					iterator.set(temp1);
					break;
				}	else if (ICompoundCommand.class.isInstance(type)) {
					moveCommandDown((ICompoundCommand) type);
				}

			}
		}
	}

}
