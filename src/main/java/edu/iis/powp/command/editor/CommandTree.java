package edu.iis.powp.command.editor;

import java.awt.BorderLayout;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.iis.powp.app.Application;

public class CommandTree {
	private Application app;
	private JTree tree;
	public CommandTree(Application app) {
		super();
		this.app = app;
	}
	
	public void setTreeDemo() {
	    
	    DefaultMutableTreeNode top =
	        new DefaultMutableTreeNode("The Java Series");
//	    createNodes(top);
	    tree = new JTree(top);
	    this.app.getFreeRightPanel().add(tree, BorderLayout.CENTER);
	    
	}
}
