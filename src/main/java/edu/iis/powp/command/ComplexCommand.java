package edu.iis.powp.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements ICompoundCommand {

    List<IPlotterCommand> commands = new ArrayList<>();
    

    public ComplexCommand() {
        super();
    }

    @Override
    public void execute(IPlotter plotter) {
    	for (Iterator iterator = commands.iterator(); iterator.hasNext();) {
			IPlotterCommand iPlotterCommand = (IPlotterCommand) iterator.next();
			iPlotterCommand.execute(plotter);
		}
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return null;
    }
    public void addCommand(IPlotterCommand command) {
		commands.add(command);
	}

	public void removeCommand(IPlotterCommand command) {
		commands.remove(command);
	}

	public IPlotterCommand getCommand(int position) {
		return commands.get(position);
	}
}
