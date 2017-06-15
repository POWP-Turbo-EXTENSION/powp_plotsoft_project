package edu.iis.powp.command.editor.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edu.iis.powp.command.editor.ITreeConfiguration;

public class CommandEditorCentralPane extends JPanel implements ITreeConfiguration {

	private JTree tree = new JTree();
	private final DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Empty");
	private final DefaultTreeModel treeModel = new DefaultTreeModel(treeNode); 
	public CommandEditorCentralPane() {
		super();
		initUI();
	}

	private void initUI() {
		tree.setModel(treeModel);
		this.setLayout(new BorderLayout());
		this.add(getTree(), BorderLayout.CENTER);
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	@Override
	public DefaultMutableTreeNode setTreeNode(String rootNodeName) {
		treeNode.setUserObject(rootNodeName);
//		treeNode = new DefaultMutableTreeNode(rootNodeName);
//		treeModel = new DefaultTreeModel( treeNode ); 
//		tree.setModel(treeModel);
		tree.validate();
		treeModel.reload();
		return treeNode;
	}
	

}
