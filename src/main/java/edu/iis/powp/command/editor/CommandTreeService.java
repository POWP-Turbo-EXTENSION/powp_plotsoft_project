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
		if (lastPlotterCommand != null) {
			if (IEditablePlotterCommand.class.isInstance(lastPlotterCommand))
				editCommand((IEditablePlotterCommand) lastPlotterCommand);
		}

	}

	/* @Override */
	private void editCommand(IEditablePlotterCommand cmd) {

		JTextField posX = new JTextField(5);
		JTextField posY = new JTextField(5);
		Object[] message = { "X:", posX, "Y:", posY };
		posX.setText(String.valueOf(cmd.getX()));
		posY.setText(String.valueOf(cmd.getY()));
		int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == 0) {
			cmd.setX(Integer.parseInt(posX.getText()));
			cmd.setY(Integer.parseInt(posY.getText()));
		}
	}

	@Override
	public void removeSelectedCommand() {
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
