package edu.iis.powp.command.editor;

public interface IOperationsOnTree {
	public void editSelectedCommand();
	public void removeSelectedCommand();
	public void moveUp();
	public void moveDown();
	public void newPosition();
	public void newLine();
}
