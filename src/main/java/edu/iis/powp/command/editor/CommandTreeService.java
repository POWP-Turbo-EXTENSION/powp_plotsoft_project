package edu.iis.powp.command.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.editor.strategy.DeleteCommand;
import edu.iis.powp.command.editor.strategy.EditEditableCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.visitor.Visitor;
import edu.iis.powp.observer.Subscriber;

public class CommandTreeService implements ITreeBehaviour, IOperationsOnTree, Visitor, Subscriber {

	IPlotterCommand lastPlotterCommand;
	ICompoundCommand lastCompoundCommand;
	private ICompoundCommand currentCommand;
	PlotterCommandManager commandManager;
	List<IPlotterCommand> plotterCommandsList;

	public CommandTreeService(ICompoundCommand currentCommand) {
		super();
		this.currentCommand = currentCommand;
	}

	public CommandTreeService(PlotterCommandManager commandManager) {
		super();
		this.commandManager = commandManager;
	}

	@Override
	public void leafIsSelected(Object obj) {
		lastCompoundCommand = null;
		lastPlotterCommand = null;
		if (IPlotterCommand.class.isInstance(obj)) {
			IPlotterCommand cmd = (IPlotterCommand) obj;
			lastPlotterCommand = cmd;
		}
	}

	@Override
	public void dirIsSelected(Object obj) {
		lastCompoundCommand = null;
		lastPlotterCommand = null;
		if (ICompoundCommand.class.isInstance(obj)) {
			ICompoundCommand cmd = (ICompoundCommand) obj;
			lastCompoundCommand = cmd;
		}

	}

	@Override
	public void editSelectedCommand() {
		//TODO: mozna przeniesc sprawdzenie tymi ifami do strategii
		if (lastPlotterCommand != null) {
			if (IEditablePlotterCommand.class.isInstance(lastPlotterCommand)) {
				EditEditableCommand editStrategy = new EditEditableCommand(
						(IEditablePlotterCommand) lastPlotterCommand);
				editStrategy.execute();
			}

		}

	}

	@Override
	public void removeSelectedCommand() {
		DeleteCommand deleteStrategy = new DeleteCommand(lastPlotterCommand, currentCommand, lastCompoundCommand);
		deleteStrategy.execute();

	}

	@Override
	public void visit(IEditablePlotterCommand cmd) {

	}

	@Override
	public void update() {
		if (ICompoundCommand.class.isInstance(commandManager.getCurrentCommand())) {
			System.out.println("TU");
			this.currentCommand = (ICompoundCommand) commandManager.getCurrentCommand();
			plotterCommandsList = new ArrayList<>();
			currentCommand.iterator().forEachRemaining(plotterCommandsList::add);
		}

	}

	@Override
	public void moveUp() {
		/*
		 * TODO: NAPRAWIC ITERATOR Ten SHIT troche nie działa tak jak chciałem
		 * trzeba to zrobić tak żeby ten iterator zamienial pozycje ale sie nie
		 * zapętlił
		 * 
		 */

		if (lastPlotterCommand != null) {
			for (ListIterator<IPlotterCommand> iterator = currentCommand.iterator(); iterator.hasNext();) {
				IPlotterCommand type = (IPlotterCommand) iterator.next();
				IPlotterCommand tmp = null;
				if (iterator.hasPrevious() && type.equals(lastPlotterCommand))
					tmp = (IPlotterCommand) iterator.previous();
				if (type.equals(lastPlotterCommand) && tmp != null) {
					System.out.println("next: " + type + "\nprev:" + tmp);
					// iterator.set(type);
					// iterator.set(tmp);
				}
				type = (IPlotterCommand) iterator.next();
			}
		}
		FeaturesManager.drawerController().clearPanel();
	}

	@Override
	public void moveDown() {
		if (lastPlotterCommand != null) {

		}
	}

}