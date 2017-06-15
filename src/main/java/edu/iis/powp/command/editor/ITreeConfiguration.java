package edu.iis.powp.command.editor;

import javax.swing.tree.DefaultMutableTreeNode;

public interface ITreeConfiguration {
	public DefaultMutableTreeNode setRootTreeNode(String rootNodeName);
	public DefaultMutableTreeNode setRootTreeNode(Object object);
	public DefaultMutableTreeNode addNode(Object object);
	public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, Object object);
	
}
