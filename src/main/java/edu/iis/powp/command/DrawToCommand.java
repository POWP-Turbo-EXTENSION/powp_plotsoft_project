package edu.iis.powp.command;

import java.util.ArrayList;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.visitor.Visitor;

/**
 * Implementation of IPlotterCommand for drawTo command functionality.
 */
public class DrawToCommand implements IEditablePlotterCommand {

	private int posX, posY;

	public DrawToCommand(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void execute(IPlotter plotter) {
		plotter.drawTo(posX, posY);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public int getX() {

		return posX;
	}

	@Override
	public int getY() {

		return posY;
	}

	@Override
	public void setX(int x) {
		this.posX = x;
		
	}

	@Override
	public void setY(int y) {
		this.posY = y;
		
	}

	public IPlotterCommand clone() throws CloneNotSupportedException{
		return new DrawToCommand(getX(),getY());
	}
}
