package edu.iis.powp.command.editor;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

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
	private ICompoundCommand currentCommand;
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
		if(ICompoundCommand.class.isInstance(commandManager.getCurrentCommand())){
			this.currentCommand = ((ComplexCommand) EditedCommand.getInstance().getEditedCommand());
		}
		rootNode = treeConfiguration.setRootTreeNode(this.currentCommand);
		buildTree(currentCommand, rootNode);
	}

	private void buildTree(IPlotterCommand command, DefaultMutableTreeNode root) {
		if (ICompoundCommand.class.isInstance(command)) {
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

	public static void print(DefaultMutableTreeNode aNode) {
		Object name = aNode.getUserObject();
		int level = aNode.getLevel();
		String placement = "";
		while (level > 0) {
			placement += ">";
			level--;
		}
		if (aNode.isLeaf()) {
			System.out.println(placement + name);
			return;
		}
		System.out.println(placement + "--- " + name + " ---");
		for (int i = 0; i < aNode.getChildCount(); i++) {
			print((DefaultMutableTreeNode) aNode.getChildAt(i));
		}
		System.out.println(placement + "+++ " + name + " +++");
	}

}
