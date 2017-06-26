package edu.iis.powp.command.editor.strategy;

import java.util.Iterator;

import javax.swing.JOptionPane;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

public class DeleteCommand implements Strategy{
	
	//TODO: DO KOMPLETNEGO NAPRAWIENIA, JAK TO ZROBIĆ?!
	private final IPlotterCommand lastPlotterCommand;
	private final ICompoundCommand currentCommand;
	private final ICompoundCommand lastCompoundCommand;
	
	public DeleteCommand(IPlotterCommand lastPlotterCommand, ICompoundCommand currentCommand,
			ICompoundCommand lastCompoundCommand) {
		super();
		this.lastPlotterCommand = lastPlotterCommand;
		this.currentCommand = currentCommand;
		this.lastCompoundCommand = lastCompoundCommand;
	}

	@Override
	public void execute() {
		if (lastPlotterCommand != null || lastCompoundCommand != null) {
			checkChildren(currentCommand);
		}
	}
	private void checkChildren(ICompoundCommand parent){
		for (Iterator iterator = parent.iterator(); iterator.hasNext();) {
			IPlotterCommand type = (IPlotterCommand) iterator.next();

			if (type.equals(lastPlotterCommand)) {
				int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?", "REMOVE",
						JOptionPane.OK_CANCEL_OPTION);
				if (decision == 0) {
					iterator.remove();
					break;
				}
			} else if (type.equals(lastCompoundCommand)) {
				int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?", "REMOVE",
						JOptionPane.OK_CANCEL_OPTION);
				if (decision == 0) {
					iterator.remove();
					break;
				}
				
			} else if (ICompoundCommand.class.isInstance(type)) {
				checkChildren((ICompoundCommand) type);
			}
		}
	}

    @Override
    public void setCommandHead(ICompoundCommand head) {
        
    }

    @Override
    public void setCurrentCommand(IPlotterCommand currentCommand) {
        
    }
}
