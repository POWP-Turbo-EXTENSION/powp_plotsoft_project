package edu.iis.powp.commandeditor.gui;

import edu.iis.powp.commandeditor.CommandEditorWIndow;
import edu.iis.powp.observer.Subscriber;

public class CommandEditorWindowObserver implements Subscriber {

    private CommandEditorWIndow window;
    
    public CommandEditorWindowObserver(CommandEditorWIndow window) {
        super();
        this.window = window;
    }

    @Override
    public void update() {
        window.updateEditor();        
    }

}
