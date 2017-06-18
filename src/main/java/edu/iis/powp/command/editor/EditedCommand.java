package edu.iis.powp.command.editor;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Subscriber;

public class EditedCommand implements Subscriber {
	private static EditedCommand instance = null;
	private IPlotterCommand editedCommand;
	   protected EditedCommand() {
	      // Exists only to defeat instantiation.
	   }
	   public static EditedCommand getInstance() {
	      if(instance == null) {
	         instance = new EditedCommand();
	      }
	      return instance;
	   }
	@Override
	public void update() {
		try {
			editedCommand = ((ICompoundCommand)FeaturesManager.getPlotterCommandManager().getCurrentCommand()).clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IPlotterCommand getEditedCommand(){
		return editedCommand;
	}
}
