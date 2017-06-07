package edu.iis.powp.command;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements ICompoundCommand, IPlotterCommand, Serializable {

    List<IPlotterCommand> commands = new ArrayList<>();
    private String name;

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
	          return;
	       }catch(ClassNotFoundException c) {
	          System.out.println("Employee class not found");
	          c.printStackTrace();
	          return;
	       }
	}
}
