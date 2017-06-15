package edu.iis.powp.command.editor.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.editor.ITreeAction;
import edu.iis.powp.command.editor.ITreeBehaviour;
import edu.iis.powp.command.editor.ITreeConfiguration;

public class CommandTree extends JPanel implements ITreeConfiguration, TreeSelectionListener, ITreeAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6050208091232026397L;
	private JTree tree = new JTree();
	private final DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Empty");
	private final DefaultTreeModel treeModel = new DefaultTreeModel(treeNode);
	private ITreeBehaviour treeBehaviour;
	private Object lastSelectedObject;
	public CommandTree() {
		super();
		initUI();
	}

	public CommandTree(ITreeBehaviour treeBehaviour) {
		super();
		this.treeBehaviour = treeBehaviour;
		initUI();
	}

	public ITreeBehaviour getTreeBehaviour() {
		return treeBehaviour;
	}

	public void setTreeBehaviour(ITreeBehaviour treeBehaviour) {
		this.treeBehaviour = treeBehaviour;
	}

	private void initUI() {
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setModel(treeModel);
		this.setLayout(new BorderLayout());
		tree.addTreeSelectionListener(this);
		this.add(getTree(), BorderLayout.CENTER);
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	@Override
	public DefaultMutableTreeNode setRootTreeNode(String rootNodeName) {
		treeNode.removeAllChildren();
		treeNode.setUserObject(rootNodeName);
		tree.validate();
		treeModel.reload();
		return treeNode;
	}

	@Override
	public DefaultMutableTreeNode addNode(Object object) {
		DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(object);
		treeNode.add(newChild);
		tree.validate();
		treeModel.reload();
		return newChild;
	}

	@Override
	public DefaultMutableTreeNode setRootTreeNode(Object object) {
		treeNode.removeAllChildren();
		treeNode.setUserObject(object);
		tree.validate();
		treeModel.reload();
		return treeNode;
	}

	@Override
	public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, Object object) {
		DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(object);
		parent.add(newChild);
		tree.validate();
		treeModel.reload();
		return newChild;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node == null)
			return;
		Object nodeInfo = node.getUserObject();
		
		if (node.isLeaf()) {
			if (treeBehaviour != null)
				treeBehaviour.leafIsSelected(nodeInfo);
		} else {
			if (treeBehaviour != null)
				treeBehaviour.dirIsSelected(nodeInfo);
		}
	}

	@Override
	public Object getSelectedObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
