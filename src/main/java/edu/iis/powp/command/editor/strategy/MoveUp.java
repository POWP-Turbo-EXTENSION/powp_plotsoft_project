package edu.iis.powp.command.editor.strategy;

import java.util.ListIterator;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.editor.EditedCommand;

public class MoveUp implements Strategy{

	private final IPlotterCommand lastPlotterCommand;

	private final ICompoundCommand currentCommand;

	public MoveUp(IPlotterCommand lastPlotterCommand, ICompoundCommand currentCommand) {
		super();
		this.lastPlotterCommand = lastPlotterCommand;
		this.currentCommand = currentCommand;
	}

	@Override
	public void execute() {
		moveCommandUp(currentCommand);

	}

	private void moveCommandUp(ICompoundCommand parent) {
		if (lastPlotterCommand != null) {
			for (ListIterator<IPlotterCommand> iterator = parent.iterator(); iterator.hasNext();) {
				IPlotterCommand type;
				type = iterator.next();
				if (iterator.hasPrevious() && type.equals(lastPlotterCommand)) {
					IPlotterCommand temp1;
//					temp1 = /*iterator.next();*/
					iterator.previous();
					temp1 = iterator.previous();
					System.out.println("0: "+type);
					System.out.println("0: "+temp1);
					iterator.set(type);
					iterator.next();
					iterator.next();
					iterator.set(temp1);
					EditedCommand.getInstance().refresh();
					break;
				}	else if (ICompoundCommand.class.isInstance(type)) {
					moveCommandUp((ICompoundCommand) type);
				}

			}
		}
	}

}
