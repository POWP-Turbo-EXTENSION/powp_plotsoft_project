package edu.iis.powp.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.visitor.Visitor;

public class ComplexCommand implements ICompoundCommand{

	List<IPlotterCommand> commands = new ArrayList<>();
	public String name = null;

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
	public ListIterator<IPlotterCommand> iterator() {
		return commands.listIterator();
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

	@Override
	public String toString() {
		return getClass().getSimpleName();

	}
	
	public IPlotterCommand clone() throws CloneNotSupportedException{
		ComplexCommand tmp = new ComplexCommand();
		tmp.commands = new ArrayList<IPlotterCommand>();
		for(IPlotterCommand ipc : this.commands){
			tmp.commands.add(ipc.clone());
		}
		return tmp;
	}

	
}
