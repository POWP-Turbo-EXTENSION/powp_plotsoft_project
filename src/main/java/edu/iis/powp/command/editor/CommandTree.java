package edu.iis.powp.command.editor;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.iis.powp.app.Application;
import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.observer.Subscriber;

public class CommandTree implements ICommandTree{
	private Application app;
	private JPanel treePanel;
	private JTree tree;
	private IPlotterCommand currentCommand;
	public CommandTree(Application app) {
		super();
		this.app = app;
		treePanel = new JPanel();
	}
	
	@Override
	public void update() {
		currentCommand = FeaturesManager.getPlotterCommandManager().getCurrentCommand();
		treePanel.removeAll();
		BuildTree(currentCommand);
	}
	
	@Override
	public void EraseTree() {
		tree.removeAll();
		treePanel.removeAll();
		treePanel.revalidate();
		treePanel.repaint();
	}
	
	@Override
	public void BuildTree(IPlotterCommand commandToBuild) {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(commandToBuild.getClass().getSimpleName());
		if(ICompoundCommand.class.isInstance(commandToBuild)) {
			createNodes(top, commandToBuild);
		}
		tree = new JTree(top);
		treePanel.add(tree);
	    this.app.getFreeRightPanel().add(treePanel, BorderLayout.CENTER);
	    treePanel.revalidate();
		treePanel.repaint();
	}
	
	private void createNodes(DefaultMutableTreeNode top, IPlotterCommand nodeCommand){
		if(ICompoundCommand.class.isInstance(nodeCommand)) {
			Iterator<IPlotterCommand> iterator = ((ICompoundCommand)nodeCommand).iterator();
			while (iterator.hasNext()) {
				IPlotterCommand t = iterator.next();
				DefaultMutableTreeNode bot = new DefaultMutableTreeNode(t.getClass().getSimpleName());
				createNodes(bot, t);
				top.add(bot);
			}
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
