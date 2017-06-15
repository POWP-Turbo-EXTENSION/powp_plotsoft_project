package edu.iis.powp.command.visitor;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

public interface Visitable {
	public void accept(Visitor visitor);

}
