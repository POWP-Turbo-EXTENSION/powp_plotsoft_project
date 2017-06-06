package edu.iis.powp.command.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
	private JSplitPane window;
	
	private static final long serialVersionUID = 9204679248304669948L;
	
	public CommandEditorWindow(PlotterCommandManager commandManager) {
		this.setTitle("Command Editor");
		this.setSize(840, 520);
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		file = new JMenu("File");
		file.add(new JMenuItem("New"));
		file.add(new JMenuItem("Open"));
		file.add(new JMenuItem("Save"));
		menuBar.add(file);
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		this.commandManager = commandManager;
		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		updateObserverListField();
		JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane bar = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, bottom);
        JPanel drawSpace = new JPanel();
        window = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bar, drawSpace);
        content.add(window, BorderLayout.CENTER);
		

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