package edu.iis.powp.command.editor;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Subscriber;

public interface ICommandTree extends Subscriber{
	public void BuildTree(IPlotterCommand commandToBuild);
	public void EraseTree();
}
