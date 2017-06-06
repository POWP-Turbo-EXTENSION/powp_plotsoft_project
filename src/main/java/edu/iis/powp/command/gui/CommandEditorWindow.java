package edu.iis.powp.command.gui;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;
import edu.iis.powp.window.WindowComponent;

public class CommandEditorWindow extends JFrame implements WindowComponent {
	private PlotterCommandManager commandManager;
	private JTextArea currentCommandField;
	private String observerListString;
	private JTextArea observerListField;
	private JMenuBar menuBar;
	private JMenu file;
	
	private static final long serialVersionUID = 9204679248304669948L;
	
	public CommandEditorWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Editor");
		this.setSize(840, 520);
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);
		Container content = this.getContentPane();
		GridBagLayout mainGridBagLayout = new GridBagLayout();
		mainGridBagLayout.columnWidths = new int[] { 0, 540 };
		mainGridBagLayout.rowHeights = new int[] { 0, 0 };
		mainGridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		mainGridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		content.setLayout(mainGridBagLayout);
		this.commandManager = commandManager;
		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		updateObserverListField();


	}
	
	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}
	
	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}
	
	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}
	
	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";
		observerListField.setText(observerListString);
	}
	
	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}