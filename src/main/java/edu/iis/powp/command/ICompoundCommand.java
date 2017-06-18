package edu.iis.powp.command;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Interface extending IPlotterCommand to execute more than one command.
 */
public interface ICompoundCommand extends IPlotterCommand  {

	public ListIterator<IPlotterCommand> iterator();
	public ICompoundCommand clone() throws CloneNotSupportedException;
}
