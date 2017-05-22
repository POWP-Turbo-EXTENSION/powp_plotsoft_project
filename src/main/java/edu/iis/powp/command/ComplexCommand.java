package edu.iis.powp.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements ICompoundCommand, IPlotterCommand {

    List<IPlotterCommand> commands = new ArrayList<>();

    public ComplexCommand() {
        super();
    }

    @Override
    public void execute(IPlotter plotter) {

    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return null;
    }

}
