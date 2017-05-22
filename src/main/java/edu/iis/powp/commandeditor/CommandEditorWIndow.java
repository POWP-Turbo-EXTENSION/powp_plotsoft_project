package edu.iis.powp.commandeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.commandeditor.gui.CommandVisualisation;
import edu.iis.powp.window.WindowComponent;

public class CommandEditorWIndow extends JFrame implements WindowComponent {

    private static final long serialVersionUID = 1L;
    private final PlotterCommandManager commandManager;
    private final CommandVisualisation visualisation;
    private final Dimension WINDOW_MIN_SIZE = new Dimension(800, 600);
    public CommandEditorWIndow(PlotterCommandManager commandManager) throws HeadlessException {
        super();
        this.commandManager = commandManager;
        this.visualisation = new CommandVisualisation(commandManager.getCurrentCommandString());
        FiguresJoe.figureScript1(visualisation);
        initUI();
    }

    private void initUI() {
        this.setMinimumSize(WINDOW_MIN_SIZE);
        this.setPreferredSize(WINDOW_MIN_SIZE);
        this.setLayout(new BorderLayout());
        this.add(visualisation, BorderLayout.CENTER);
        this.pack();
//        this.setVisible(true);
    }
    
    public void updateEditor(){
        visualisation.setCommandName(commandManager.getCurrentCommandString());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

}
