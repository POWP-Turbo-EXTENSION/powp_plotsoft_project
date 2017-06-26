package edu.iis.powp.command.editor.strategy;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

public interface Strategy {
    public void setCommandHead(ICompoundCommand head);
    public void setCurrentCommand(IPlotterCommand currentCommand);
	public void execute();
}
