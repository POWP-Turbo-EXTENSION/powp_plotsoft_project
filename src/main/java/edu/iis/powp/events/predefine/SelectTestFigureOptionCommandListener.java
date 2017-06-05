package edu.iis.powp.events.predefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.IPlotterCommand;

public class SelectTestFigureOptionCommandListener implements ActionListener
{

	private IPlotterCommand command;
	private Application app;
	
    public SelectTestFigureOptionCommandListener(IPlotterCommand command,Application app) {
		super();
		this.command = command;
		this.app = app;
	}


	@Override
    public void actionPerformed(ActionEvent e)
    {
		command.execute(app.getDriverManager().getCurrentPlotter());
        
    }
}
