package edu.iis.powp.command.editor;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public interface ITreeConfiguration {
	public DefaultMutableTreeNode setRootTreeNode(String rootNodeName);
	public DefaultMutableTreeNode setRootTreeNode(Object object);
	public DefaultMutableTreeNode addNode(Object object);
	public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, Object object);
	public TreeModel getActualTreeModel();
}
