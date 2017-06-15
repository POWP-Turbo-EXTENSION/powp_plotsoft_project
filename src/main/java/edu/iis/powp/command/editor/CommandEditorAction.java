package edu.iis.powp.command.editor;

import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorAction implements ICommandEditorAction, Subscriber {
	private final PlotterCommandManager commandManager;
	private ITreeConfiguration treeConfiguration;

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
		treeConfiguration.setTreeNode(commandManager.getCurrentCommand().getClass().getSimpleName());		
	}

}
