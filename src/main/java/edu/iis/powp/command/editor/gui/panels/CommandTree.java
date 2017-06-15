package edu.iis.powp.command.editor.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edu.iis.powp.command.editor.ITreeConfiguration;

public class CommandTree extends JPanel implements ITreeConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6050208091232026397L;
	private JTree tree = new JTree();
	private final DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Empty");
	private final DefaultTreeModel treeModel = new DefaultTreeModel(treeNode); 
	public CommandTree() {
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
		tree.validate();
		treeModel.reload();
		return treeNode;
	}
	

}
