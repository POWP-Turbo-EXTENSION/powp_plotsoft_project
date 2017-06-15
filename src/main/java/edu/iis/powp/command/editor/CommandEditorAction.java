package edu.iis.powp.command.editor;

import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorAction implements ICommandEditorAction, Subscriber {
	private final PlotterCommandManager commandManager;
	private ITreeConfiguration treeConfiguration;
	private IPlotterCommand currentCommand;
	private DefaultMutableTreeNode rootNode; 
	
	public CommandEditorAction(PlotterCommandManager commandManager) {
		super();
		this.commandManager = commandManager;

	}

	public CommandEditorAction(PlotterCommandManager commandManager, ITreeConfiguration treeConfiguration) {
		super();
		this.commandManager = commandManager;
		this.treeConfiguration = treeConfiguration;
	}

	@Override
	public void setTreeConfiguration(ITreeConfiguration treeConfiguration) {
		this.treeConfiguration = treeConfiguration;
	}

	@Override
	public void update() {
		this.currentCommand = commandManager.getCurrentCommand();//treeTestCommand();//
		rootNode = treeConfiguration.setRootTreeNode(this.currentCommand);	
		buildTree(currentCommand, rootNode);
	}

	private void buildTree(IPlotterCommand command, DefaultMutableTreeNode root){
		if(ICompoundCommand.class.isInstance(command)){			
			DefaultMutableTreeNode parent = treeConfiguration.addNode(rootNode, command);
			this.rootNode = parent;
			ICompoundCommand compoundCommand = (ICompoundCommand) command;
			for (Iterator iterator = compoundCommand.iterator(); iterator.hasNext();) {
				IPlotterCommand type = (IPlotterCommand) iterator.next();				
				buildTree(type, parent);
			}
		} else {
			treeConfiguration.addNode(rootNode, command);
		}
	}

	private ICompoundCommand treeTestCommand() { // metoda testowa do drzewa z głębiami różnymi, do użycia podstawić jako parametr funkcji 'BuildTree()' w update
		ComplexCommand complexCommand = new ComplexCommand();
			ComplexCommand complexCommand2 = new ComplexCommand();
				SetPositionCommand spc1 = new SetPositionCommand(10,10);
				DrawToCommand dtc1 = new DrawToCommand(20,20);
				complexCommand2.addCommand(spc1);
				complexCommand2.addCommand(dtc1);
			ComplexCommand complexCommand3 = new ComplexCommand();
				SetPositionCommand spc3 = new SetPositionCommand(10,10);
				complexCommand3.addCommand(spc3);
			DrawToCommand dtcmain = new DrawToCommand(200,50);
			complexCommand.addCommand(dtcmain);
			ComplexCommand complexCommand4 = new ComplexCommand();
				DrawToCommand dtc3 = new DrawToCommand(70,20);
				SetPositionCommand spc4 = new SetPositionCommand(50,10);
				DrawToCommand dtc4 = new DrawToCommand(30,400);
				SetPositionCommand spc5 = new SetPositionCommand(90,120);
				DrawToCommand dtc5 = new DrawToCommand(150,0);
				SetPositionCommand spc6 = new SetPositionCommand(10,10);
				DrawToCommand dtc6 = new DrawToCommand(80,20);
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

}
