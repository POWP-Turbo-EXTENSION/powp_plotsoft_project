package edu.iis.powp.command.editor;

import java.util.Iterator;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

public class CommandTreeService implements ITreeBehaviour, ITreeOperations {

	IPlotterCommand lastPlotterCommand;
	ICompoundCommand lastCompoundCommand;
	
	@Override
	public void leafIsSelected(Object obj) {
		if (IPlotterCommand.class.isInstance(obj)) {
			IPlotterCommand cmd = (IPlotterCommand) obj;
			lastPlotterCommand = cmd;
			System.out.println(cmd.ke());
		}
	}

	@Override
	public void dirIsSelected(Object obj) {
		if (ICompoundCommand.class.isInstance(obj)) {
			ICompoundCommand cmd = (ICompoundCommand) obj;
			lastCompoundCommand = cmd;
			System.out.println(cmd.ke());
		}
		
	}

	@Override
	public void testAction() {
		System.out.println("TETEJ");
		for (Iterator iterator = lastCompoundCommand.iterator(); iterator.hasNext();) {
			IPlotterCommand type = (IPlotterCommand) iterator.next();
			System.out.println(type);
		}

		
	}

}
