package edu.iis.powp.events.predefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;

public class LoadTestFigureOptionCommandListener implements ActionListener
{

	private IPlotterCommand command;
	private Application app;
	
    public LoadTestFigureOptionCommandListener(Application app) {
		super();
		this.command = null;
		this.app = app;
	}


	@Override
    public void actionPerformed(ActionEvent e)
    {
		command = new ComplexCommand(true);
		command.execute(app.getDriverManager().getCurrentPlotter());
	    PlotterCommandManager manager = FeaturesManager.getPlotterCommandManager();
	    manager.setCurrentCommand(command);
        
    }
}
