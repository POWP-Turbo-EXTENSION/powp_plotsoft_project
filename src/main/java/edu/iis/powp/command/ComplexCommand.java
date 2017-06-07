package edu.iis.powp.command;

import java.io.FileOutputStream;
import java.io.IOException;
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
          out.writeObject(this);
          out.close();
          fileOut.close();
          System.out.printf("Serialized command at " + "/commands/" + name + ".ser");
       }catch(IOException i) {
          i.printStackTrace();
       }
	}
}
