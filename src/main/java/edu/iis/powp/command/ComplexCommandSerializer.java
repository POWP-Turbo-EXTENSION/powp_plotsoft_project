package edu.iis.powp.command;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ComplexCommandSerializer {
	public static void save(ComplexCommand command, String name) {
		try {
			command.name = name;
			FileOutputStream fileOut = new FileOutputStream("./src/commands/" + command.name + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(command.commands);
			out.close();
			fileOut.close();
			System.out.printf("Serialized command at " + "/commands/" + name + ".ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void save(ComplexCommand command) {
		if (command.name == null)
			command.name = JOptionPane.showInputDialog("What name do you want to save this ComplexCommand as?");
		if(command.name!=null)
		{
			try {
				FileOutputStream fileOut = new FileOutputStream("./src/commands/" + command.name + ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(command.commands);
				out.close();
				fileOut.close();
				System.out.printf("Serialized command at " + "/commands/" + command.name + ".ser");
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}

	public static ComplexCommand load() {
		try {
			ComplexCommand command = new ComplexCommand();
			JFileChooser fileChooser = new JFileChooser("./src/commands/");
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath());
				command.name = fileChooser.getSelectedFile().getName();
				command.name = command.name.substring(0, command.name.length() - 4);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				command.commands = (List<IPlotterCommand>) in.readObject();
				in.close();
				fileIn.close();
				return command;
			}
		} catch (StreamCorruptedException s) {
			System.out.println("Chosen file is not a command list");
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
		return null;
	}
}
