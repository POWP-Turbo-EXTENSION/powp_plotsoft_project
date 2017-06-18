package edu.iis.powp.command;

import java.io.Serializable;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.visitor.Visitable;

/**
 * PlotterCommand interface.
 */
public interface IPlotterCommand extends Cloneable, Serializable {

    /**
     * Execute command on plotter.
     * 
     * @param plotter plotter.
     */
	public void execute(IPlotter plotter);
}
