package edu.iis.powp.command.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Publisher;

/**
 * Driver Manager.
 */
public class PlotterCommandManager
{
    private IPlotterCommand currentCommand = null;
    
    private Publisher changePublisher = new Publisher(); 

	/**
     * Set current command.
     * 
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(IPlotterCommand commandList)
    {
    	this.currentCommand = commandList;
    	changePublisher.notifyObservers();
    }
    
    /**
     * Set current command.
     * 
     * @param commandList list of commands representing a compound command.
     * @param name name of the command.
     */
    public synchronized void setCurrentCommand(List<IPlotterCommand> commandList, String name)
    {
    	setCurrentCommand(new ICompoundCommand() {
			
    		List<IPlotterCommand> plotterCommands = commandList;
    		
			@Override
			public void execute(IPlotter plotter) {
				plotterCommands.forEach((c) -> c.execute(plotter));
			}
			
			@Override
			public Iterator<IPlotterCommand> iterator() {
				return plotterCommands.iterator();
			}
			
			@Override
			public String toString() {
				return name;
			}
		});

    }
    
    public synchronized void setCurrentCommand(IPlotterCommand singleCommand, String name) {
    	List<IPlotterCommand> commandInAList = new ArrayList<IPlotterCommand>();
    	commandInAList.add(singleCommand);
    	setCurrentCommand(new ICompoundCommand() {
			
    		List<IPlotterCommand> plotterCommands = commandInAList;
    		
			@Override
			public void execute(IPlotter plotter) {
				plotterCommands.forEach((c) -> c.execute(plotter));
			}
			
			@Override
			public Iterator<IPlotterCommand> iterator() {
				return plotterCommands.iterator();
			}
			
			@Override
			public String toString() {
				return name;
			}
		});
    }
    /**
     * Return current command.
     * 
     * @return Current command.
     */
    public synchronized IPlotterCommand getCurrentCommand()
    {
        return currentCommand;
    }
    
    public synchronized void clearCurrentCommand()
    {
        currentCommand = null;
    }
    
    public synchronized String getCurrentCommandString() {
		if(getCurrentCommand() == null) {
			return "No command loaded";
		} else return getCurrentCommand().toString();
    }

	public Publisher getChangePublisher() {
		return changePublisher;
	}
}
