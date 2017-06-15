package edu.iis.powp.command;

import edu.iis.powp.command.visitor.Visitable;

public interface IEditablePlotterCommand extends IPlotterCommand ,Visitable {
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
}
