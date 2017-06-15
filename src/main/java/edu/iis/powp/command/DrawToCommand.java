package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

/**
 * Implementation of IPlotterCommand for drawTo command functionality.
 */
public class DrawToCommand implements IPlotterCommand {

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
	public String ke() {
		// TODO Auto-generated method stub
		return "GSD";
	}
}
