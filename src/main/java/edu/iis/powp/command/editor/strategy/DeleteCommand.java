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
			// TODO: jesli trzeba skasowac glowna galaz to tutaj przed wejsciem
			// do iteratora
			for (Iterator iterator = currentCommand.iterator(); iterator.hasNext();) {
				IPlotterCommand type = (IPlotterCommand) iterator.next();

				if (type.equals(lastPlotterCommand)) {
					System.out.println("DUPA");
					int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?", "REMOVE",
							JOptionPane.OK_CANCEL_OPTION);
					if (decision == 0) {
						iterator.remove();
					}
				} else if (type.equals(lastCompoundCommand)) {
					System.out.println("DUPAinna");
					int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?", "REMOVE",
							JOptionPane.OK_CANCEL_OPTION);
					if (decision == 0) {
						iterator.remove();
					}
				}
			}
		} else {
			System.out.println("TUnie");
		}
		
	}

}
