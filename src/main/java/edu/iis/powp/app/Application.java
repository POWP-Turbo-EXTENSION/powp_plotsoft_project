package edu.iis.powp.app;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.gui.PlotterGUI;
import edu.iis.powp.app.gui.events.SelectDriverMenuOptionListener;
import edu.iis.powp.app.gui.events.SelectJFrameWindowMenuOptionListener;
import edu.iis.powp.app.gui.events.SelectWindowComponentMenuOptionListener;
import edu.iis.powp.observer.Subscriber;
import edu.iis.powp.window.WindowComponent;

/**
 * Application.
 */
public class Application {
	private PlotterGUI gui = new PlotterGUI();

	private DriverManager driverManager = new DriverManager();

	public Application() {
		driverManager.getChangePublisher().addSubscriber(new DriverChangeSubscriber());
	}

	public DriverManager getDriverManager() {
		return driverManager;
	}

	/**
	 * 
	 * @return a right side panel of the application.
	 */
	public JPanel getFreePanel() {
		return gui.getFreePanel();
	}
	public JPanel getFreeRightPanel() {
		return gui.getFreeRightPanel();
	}
	/**
	 * Add driver to context, create button in driver menu.
	 * 
	 * @param name
	 *            Button name.
	 * @param plotter
	 *            IPlotter object.
	 */
	public void addDriver(String name, IPlotter plotter) {
		SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(plotter, driverManager);
		gui.addNewDriverMenuListener(listener, name);
	}

	/**
	 * Add driver to context, create button in driver menu.
	 * 
	 * @param name
	 *            Window name.
	 * @param window
	 *            Window object.
	 */
	public void addJFrameWindow(String name, JFrame window) {
		SelectJFrameWindowMenuOptionListener listener = new SelectJFrameWindowMenuOptionListener(window);
		gui.addNewWindowMenuListener(listener, name);
		window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	/**
	 * Add driver to context, create button in driver menu.
	 * 
	 * @param name
	 *            Window name.
	 * @param window
	 *            Window object.
	 */
	public void addWindowComponent(String name, WindowComponent window) {
		SelectWindowComponentMenuOptionListener listener = new SelectWindowComponentMenuOptionListener(window);
		gui.addNewWindowMenuListener(listener, name);
	}

	/**
	 * Add button in test menu.
	 * 
	 * @param name
	 *            Button name.
	 * @param listener
	 *            Listener that handles button click and run test.
	 */
	public void addTest(String name, ActionListener listener) {
		gui.addNewTestMenuListener(listener, name);
	}

	/**
	 * Add new option in menu.
	 * 
	 * @param compClass
	 *            Component associated with this menu.
	 * @param name
	 *            Menu button name.
	 */
	public void addComponentMenu(Class<?> compClass, String name) {
		gui.addComponentMenu(compClass, name);
	}

	/**
	 * Add new option in menu.
	 * 
	 * @param compClass
	 *            Component associated with this menu.
	 * @param name
	 *            Menu button name.
	 * @param position
	 *            position in menu list.
	 */
	public void addComponentMenu(Class<?> compClass, String name, int position) {
		gui.addComponentMenu(compClass, name, position);
	}

	/**
	 * Add button in menu associated with component.
	 * 
	 * @param compClass
	 *            Component associated with menu.
	 * @param label
	 *            Button label.
	 * @param listener
	 *            Listener that handles button click action.
	 */
	public void addComponentMenuElement(Class<?> compClass, String label, ActionListener listener) {
		gui.addComponentMenuElement(compClass, label, listener);
	}

	/**
	 * Add button with checkbox in menu associated with component.
	 * 
	 * @param compClass
	 *            Component associated with menu.
	 * @param label
	 *            Button label.
	 * @param listener
	 *            Listener that handles button click action.
	 * @param checkBoxState
	 *            If True checkbox will be set.
	 */
	public void addComponentMenuElementWithCheckBox(Class<?> compClass, String label, ActionListener listener,
			boolean checkBoxState) {
		gui.addComponentMenuElementWithCheckBox(compClass, label, listener, checkBoxState);
	}

	/**
	 * Set visibility.
	 * 
	 * @param visible
	 *            visibility.
	 */
	public void setVisibility(boolean visible) {
		gui.setVisible(visible);
	}

	class DriverChangeSubscriber implements Subscriber {

		@Override
		public void update() {
			updateDriverInfo();
		}
	}

	/**
	 * Update driver info.
	 */
	public void updateDriverInfo() {
		gui.updatePlotterInfo(driverManager.getCurrentPlotter().toString());
	}

	public void flushLoggerOutput() {
		gui.flushLoggerTextArea();
	}
}
