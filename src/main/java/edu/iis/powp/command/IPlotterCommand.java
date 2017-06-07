package edu.iis.powp.command;

import java.io.Serializable;

import edu.iis.client.plottermagic.IPlotter;

/**
 * PlotterCommand interface.
 */
public interface IPlotterCommand extends Serializable {

	public void execute(IPlotter plotter);
}
