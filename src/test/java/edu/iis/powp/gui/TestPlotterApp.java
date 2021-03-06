package edu.iis.powp.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.iis.client.plottermagic.ClientPlotter;
import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LineAdapterPlotterDriver;
import edu.iis.powp.app.Application;
import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.FigureFactory;
import edu.iis.powp.command.editor.CommandEditorAction;
import edu.iis.powp.command.editor.CommandTreeService;
import edu.iis.powp.command.editor.EditedCommand;
import edu.iis.powp.command.editor.gui.CommandEditorWindow;
import edu.iis.powp.command.gui.CommandManagerWindow;
import edu.iis.powp.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.iis.powp.events.SelectLoadSecretCommandOptionListener;
import edu.iis.powp.events.SelectRunCurrentCommandOptionListener;
import edu.iis.powp.events.SelectTestFigure2OptionListener;
import edu.iis.powp.events.predefine.LoadTestFigureOptionCommandListener;
import edu.iis.powp.events.predefine.SelectTestFigureOptionCommandListener;
import edu.iis.powp.events.predefine.SelectTestFigureOptionListener;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.LineFactory;

public class TestPlotterApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener();
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener();
		SelectTestFigureOptionCommandListener selectTestFigureOptionCommandListener = new SelectTestFigureOptionCommandListener(
				FigureFactory.getSquare(10, 10, 40), application);
		LoadTestFigureOptionCommandListener loadTestFigureOptionCommandListener = new LoadTestFigureOptionCommandListener(
				application);
		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure KWADRAT", selectTestFigureOptionCommandListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);	
		application.addTest("Load from directory", loadTestFigureOptionCommandListener);		
	}

	/**
	 * Setup test using plotter commands in context.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupCommandTests(Application application) {
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener());

	}

	/**
	 * Setup driver manager, and set default IPlotter for application.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupDrivers(Application application) {
		IPlotter clientPlotter = new ClientPlotter();
		application.addDriver("Client Plotter", clientPlotter);

		DrawPanelController drawerController = FeaturesManager.drawerController();
		IPlotter plotter = new LineAdapterPlotterDriver(drawerController, LineFactory.getBasicLine(), "basic");
		application.addDriver("Line Simulator", plotter);
		FeaturesManager.getDriverManager().setCurrentPlotter(plotter);

		plotter = new LineAdapterPlotterDriver(drawerController, LineFactory.getSpecialLine(), "special");
		application.addDriver("Special line Simulator", plotter);
		application.updateDriverInfo();
	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(FeaturesManager.getPlotterCommandManager());
		
		CommandEditorAction commandEditorAction = new CommandEditorAction(FeaturesManager.getPlotterCommandManager());		
		CommandTreeService treeService = new CommandTreeService(FeaturesManager.getPlotterCommandManager());
		CommandEditorWindow commandEditorUI = new CommandEditorWindow(commandEditorAction,treeService,treeService);
		
		application.addWindowComponent("Command Manager", commandManager);
		application.addWindowComponent("Command Editor", commandEditorUI);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		EditedCommand.getInstance().getPublisher().addSubscriber(treeService);
		EditedCommand.getInstance().getPublisher().addSubscriber(commandEditorAction);
		//FeaturesManager.getPlotterCommandManager().getChangePublisher().addSubscriber(treeService);
		//FeaturesManager.getPlotterCommandManager().getChangePublisher().addSubscriber(commandEditorAction);
		FeaturesManager.getPlotterCommandManager().getChangePublisher().addSubscriber(windowObserver);
		

	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application();
				FeaturesManager.expandApplication(app);

				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);

				app.setVisibility(true);
			}
		});
	}

}
