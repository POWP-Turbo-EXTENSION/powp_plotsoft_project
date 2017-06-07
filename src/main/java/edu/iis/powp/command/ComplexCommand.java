package edu.iis.powp.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;

public class ComplexCommand implements ICompoundCommand, IPlotterCommand, Serializable {

    List<IPlotterCommand> commands = new ArrayList<>();
    private String name = null;

    public ComplexCommand() {
        super();
    }
    
    public ComplexCommand(String name){
    	super();
    	Load(name);
    }

    @Override
    public void execute(IPlotter plotter) {
    	for (Iterator iterator = commands.iterator(); iterator.hasNext();) {
			IPlotterCommand iPlotterCommand = (IPlotterCommand) iterator.next();
			iPlotterCommand.execute(plotter);
		}
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return null;
    }
    public void addCommand(IPlotterCommand command) {
		commands.add(command);
	}

	public void removeCommand(IPlotterCommand command) {
		commands.remove(command);
	}

	public IPlotterCommand getCommand(int position) {
		return commands.get(position);
	}
	public void Save(String name){
      try {
          FileOutputStream fileOut = new FileOutputStream("./src/commands/" + name + ".ser");
          ObjectOutputStream out = new ObjectOutputStream(fileOut);
          out.writeObject(commands);
          out.close();
          fileOut.close();
          System.out.printf("Serialized command at " + "/commands/" + name + ".ser");
       }catch(IOException i) {
          i.printStackTrace();
       }
	}
	public void Save(){
	    if(name!=null){
			try {
		        FileOutputStream fileOut = new FileOutputStream("./src/commands/" + name + ".ser");
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        out.writeObject(commands);
		        out.close();
		        fileOut.close();
		        System.out.printf("Serialized command at " + "/commands/" + name + ".ser");
			}catch(IOException i) {
				i.printStackTrace();
		    }
	    }
	}
	public void Load(String name){
	      try {
	    	  this.name = name;
	          FileInputStream fileIn = new FileInputStream("./src/commands/" + name + ".ser");
	          ObjectInputStream in = new ObjectInputStream(fileIn);
	          commands = (List<IPlotterCommand>) in.readObject();
	          in.close();
	          fileIn.close();
	       }catch(IOException i) {
	          i.printStackTrace();
	       }catch(ClassNotFoundException c) {
	          System.out.println("Employee class not found");
	          c.printStackTrace();
	       }
	}
	public void Load(){
	      try {
	    	  JFileChooser fileChooser = new JFileChooser();
	    	//  fileChooser.setCurrentDirectory(new File(System.getProperty("./src/commands/")));
	    	  int result = fileChooser.showOpenDialog(null);
	    	  if (result == JFileChooser.APPROVE_OPTION) {
		          FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath());
		          name = fileChooser.getSelectedFile().getName();
		          name = name.substring(0, name.length()-4);
		          ObjectInputStream in = new ObjectInputStream(fileIn);
		          commands = (List<IPlotterCommand>) in.readObject();
		          in.close();
		          fileIn.close();
	    	  }
	       }catch(StreamCorruptedException s){
	    	   System.out.println("Chosen file is not a command list");
	       }catch(IOException i) {
	          i.printStackTrace();
	       }catch(ClassNotFoundException c) {
	          System.out.println("Class not found");
	          c.printStackTrace();
	       }
	}
	public static List<ComplexCommand> findComplexCommands(){
		List<ComplexCommand> list = new ArrayList<ComplexCommand>();
		
		File folder = new File("./src/commands/");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        list.add(new ComplexCommand(file.getName().substring(0, file.getName().length()-4)));
		    }
		}
		return list;
	}
}