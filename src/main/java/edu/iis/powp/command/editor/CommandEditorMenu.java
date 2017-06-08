package edu.iis.powp.command.editor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import edu.iis.powp.app.Application;

public class CommandEditorMenu implements ActionListener {
	private Application app;
	private JPanel pane;
	private JPanel buttonsBottom = new JPanel();
	private JPanel buttonsTop = new JPanel();
	private ITurboButtonsAction turbo;

	private JButton newComplexCommand = new JButton("New");
	private JButton saveComplexCommand = new JButton("Save");
	private JButton newPosition = new JButton("Add position");
	private JButton newLine = new JButton("Add line");

	private JButton moveUp = new JButton("↑Up");
	private JButton moveDown = new JButton("↓Down");
	private JButton deleteIt = new JButton("×Delete");
	private JButton editIt= new JButton("→Edit");
//	private JButton moveUp = new JButton("Delete");
	
	public CommandEditorMenu(Application app) {
		super();
		this.app = app;
		this.pane = this.app.getFreeRightPanel();
		buildMenu();
	}

	public CommandEditorMenu(Application app, ITurboButtonsAction turbo) {
		super();
		this.app = app;
		this.pane = this.app.getFreeRightPanel();
		buildMenu();
		this.turbo = turbo;
	}

	private void buildMenu() {
		buttonsBottom.setLayout(new GridLayout(4, 4));
		buttonsTop.setLayout(new GridLayout(2, 2));
		buttonsTop.add(moveDown);
		buttonsTop.add(moveUp);
		buttonsTop.add(deleteIt);
		buttonsTop.add(editIt);
		this.pane.add(buttonsBottom, BorderLayout.SOUTH);
		this.pane.add(buttonsTop, BorderLayout.NORTH);
		this.buttonsBottom.add(newComplexCommand);
		this.buttonsBottom.add(saveComplexCommand);
		this.buttonsBottom.add(newPosition);
		this.buttonsBottom.add(newLine);
		newComplexCommand.addActionListener(this);
		saveComplexCommand.addActionListener(this);
		newPosition.addActionListener(this);
		newLine.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newComplexCommand)) {

		}
		if (e.getSource().equals(saveComplexCommand)) {

		}
		if (e.getSource().equals(newPosition)) {
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    // If you want the value to be committed on each keystroke instead of focus lost
		    formatter.setCommitsOnValidEdit(true);
		    JFormattedTextField xField = new JFormattedTextField(formatter);
		    JFormattedTextField yField = new JFormattedTextField(formatter);
		    xField.setColumns(5);
		    yField.setColumns(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("x:"));
			myPanel.add(xField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("y:"));
			myPanel.add(yField);

			int result = JOptionPane.showConfirmDialog(this.app.getFreePanel(), myPanel, "Please Enter X and Y Values",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				System.out.println("x value: " + xField.getText());
				System.out.println("y value: " + yField.getText());
				turbo.addPosition(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
			}

		}
		if (e.getSource().equals(newLine)) {
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    // If you want the value to be committed on each keystroke instead of focus lost
		    formatter.setCommitsOnValidEdit(true);
		    JFormattedTextField xField = new JFormattedTextField(formatter);
		    JFormattedTextField yField = new JFormattedTextField(formatter);
		    xField.setColumns(5);
		    yField.setColumns(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("x:"));
			myPanel.add(xField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("y:"));
			myPanel.add(yField);

			int result = JOptionPane.showConfirmDialog(this.app.getFreePanel(), myPanel, "Please Enter X and Y Values",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				System.out.println("x value: " + xField.getText());
				System.out.println("y value: " + yField.getText());
				turbo.addLineTo(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
			}
		}
	}
}
