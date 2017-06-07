package edu.iis.powp.appext;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.editor.CommandEditorMenu;
import edu.iis.powp.command.editor.CommandTree;
import edu.iis.powp.command.manager.LoggerCommandChangeObserver;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.events.predefine.SelectClearPanelOptionListener;
import edu.kis.powp.drawer.panel.DrawPanelController;

public class FeaturesManager {

	private static boolean areFeaturesAdded = false;

	private static PlotterCommandManager commandManager;
	private static DriverManager driverManager;
	private static DrawPanelController drawerController;
	private static CommandTree commandTree;
	/**
	 * Startup configuration.
	 */
	public synchronized static void expandApplication(Application application) {
		if (!areFeaturesAdded) {
			areFeaturesAdded = true;

			driverManager = application.getDriverManager();
			setupTree(application);
			setupCommandManager();
			setupDrawerPlugin(application);
		}
	}

	private static void setupTree(Application application) {
		// TODO Auto-generated method stub
		JPanel test= new JPanel();
		test.setLayout(new BorderLayout());
		application.getFreeRightPanel().setLayout(new BorderLayout());

		application.getFreeRightPanel().add(test, BorderLayout.CENTER);

		commandTree = new CommandTree(application);
		//ct.setTreeDemo();
		CommandEditorMenu cmdMenu = new CommandEditorMenu(application);
		
	}

	private static void setupCommandManager() {
		commandManager = new PlotterCommandManager();
		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
		commandManager.getChangePublisher().addSubscriber(commandTree);
	}

	/**
	 * Setup Drawer Plugin and add to context.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupDrawerPlugin(Application application) {
		SelectClearPanelOptionListener selectClearPanelOptionListener = new SelectClearPanelOptionListener();

		drawerController = new DrawPanelController();
		application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
		application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);

		drawerController.initialize(application.getFreePanel());
	}

	/**
	 * Get controller of application drawing panel.
	 * 
	 * @return drawPanelController.
	 */
	public static DrawPanelController drawerController() {
		return drawerController;
	}

	/**
	 * Get manager of application driver.
	 * 
	 * @return driverManager.
	 */
	public static DriverManager getDriverManager() {
		return driverManager;
	}

	/**
	 * Get manager of application plotter command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static PlotterCommandManager getPlotterCommandManager() {
		return commandManager;
	}
	
	public static CommandTree getCommandTree() {
		return commandTree;
	}
}
