package edu.iis.powp.command.visitor;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

public interface Visitor {
	public void visit(DrawToCommand cmd);
	public void visit(SetPositionCommand cmd);	
	public void visit(ComplexCommand complexCommand);
//	public void visit(IPlotterCommand cmd);
//	public void visit(IEditablePlotterCommand cmd);
}

