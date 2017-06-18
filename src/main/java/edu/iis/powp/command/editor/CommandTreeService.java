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
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
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
						(IEditablePlotterCommand) lastPlotterCommand, currentCommand);
				editStrategy.execute();
			}

		}
		reload();
	}

	@Override
	public void removeSelectedCommand() {
		DeleteCommand deleteStrategy = new DeleteCommand(lastPlotterCommand, currentCommand, lastCompoundCommand);
		deleteStrategy.execute();
		reload();
	}
	
	@Override
	public void visit(IEditablePlotterCommand cmd) {

	}

	@Override
	public void update() {
		if (ICompoundCommand.class.isInstance(commandManager.getCurrentCommand())) {
			this.currentCommand = ((ComplexCommand) EditedCommand.getInstance().getEditedCommand());
			plotterCommandsList = new ArrayList<>();
			currentCommand.iterator().forEachRemaining(plotterCommandsList::add);
		}

	}
	
	public void reload(){
		FeaturesManager.drawerController().clearPanel();
		currentCommand.execute(FeaturesManager.getDriverManager().getCurrentPlotter());
		EditedCommand.getInstance().Refresh();
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

	private ICompoundCommand treeTestCommand() { // metoda testowa do drzewa z
		// głębiami różnymi, do
		// użycia podstawić jako
		// parametr funkcji
		// 'BuildTree()' w update
ComplexCommand complexCommand = new ComplexCommand();
ComplexCommand complexCommand2 = new ComplexCommand();
SetPositionCommand spc1 = new SetPositionCommand(10, 10);
DrawToCommand dtc1 = new DrawToCommand(20, 20);
complexCommand2.addCommand(spc1);
complexCommand2.addCommand(dtc1);
ComplexCommand complexCommand3 = new ComplexCommand();
SetPositionCommand spc3 = new SetPositionCommand(10, 10);
complexCommand3.addCommand(spc3);
DrawToCommand dtcmain = new DrawToCommand(200, 50);
DrawToCommand dtcmain2 = new DrawToCommand(300, 50);
DrawToCommand dtcmain3 = new DrawToCommand(400, 50);
complexCommand.addCommand(dtcmain);
complexCommand.addCommand(dtcmain2);
complexCommand.addCommand(dtcmain3);
ComplexCommand complexCommand4 = new ComplexCommand();
DrawToCommand dtc3 = new DrawToCommand(70, 20);
SetPositionCommand spc4 = new SetPositionCommand(50, 10);
DrawToCommand dtc4 = new DrawToCommand(30, 400);
SetPositionCommand spc5 = new SetPositionCommand(90, 120);
DrawToCommand dtc5 = new DrawToCommand(150, 0);
SetPositionCommand spc6 = new SetPositionCommand(10, 10);
DrawToCommand dtc6 = new DrawToCommand(80, 20);
complexCommand4.addCommand(dtc3);
complexCommand4.addCommand(spc4);
complexCommand4.addCommand(dtc4);
ComplexCommand complexCommand5 = new ComplexCommand();
complexCommand5.addCommand(spc5);
complexCommand5.addCommand(dtc5);
complexCommand4.addCommand(complexCommand5);
complexCommand4.addCommand(spc6);
complexCommand4.addCommand(dtc6);
complexCommand.addCommand(complexCommand2);
complexCommand.addCommand(complexCommand3);
complexCommand.addCommand(complexCommand4);
return complexCommand;
}

	@Override
	public void newPosition() {
		JTextField posX = new JTextField(5);
		JTextField posY = new JTextField(5);
		Object[] message = { "X:", posX, "Y:", posY };
		posX.setText("0");
		posY.setText("0");
		int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
		IPlotterCommand newCommand = new SetPositionCommand(Integer.parseInt(posX.getText()),Integer.parseInt(posY.getText()));
		checkChildren(currentCommand, newCommand);
		reload();
	}

	@Override
	public void newLine() {
		JTextField posX = new JTextField(5);
		JTextField posY = new JTextField(5);
		Object[] message = { "X:", posX, "Y:", posY };
		posX.setText("0");
		posY.setText("0");
		int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
		IPlotterCommand newCommand = new DrawToCommand(Integer.parseInt(posX.getText()),Integer.parseInt(posY.getText()));
		checkChildren(currentCommand, newCommand);
		reload();
	}
	
	private void checkChildren(ICompoundCommand parent, IPlotterCommand newCommand){
		for (Iterator iterator = parent.iterator(); iterator.hasNext();) {
			IPlotterCommand type = (IPlotterCommand) iterator.next();
			if (IEditablePlotterCommand.class.isInstance(type) && type.equals(lastPlotterCommand)) {
					parent.iterator().add(newCommand);
					break;
			}  
			else if (ICompoundCommand.class.isInstance(type)) {
				checkChildren((ICompoundCommand) type, newCommand);
			}
		}
	}
}
