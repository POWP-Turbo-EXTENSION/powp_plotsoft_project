package edu.iis.powp.command.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.editor.strategy.DeleteCommand;
import edu.iis.powp.command.editor.strategy.EditEditableCommand;
import edu.iis.powp.command.editor.strategy.MoveDown;
import edu.iis.powp.command.editor.strategy.MoveUp;
import edu.iis.powp.command.editor.strategy.Strategy;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.visitor.Visitor;
import edu.iis.powp.observer.Subscriber;
/*
 * Pomyśleć nad template method do zapisu i odczytu z pliku! 
 * http://stg-tud.github.io/eise/WS15-SE-18-Factory_Method_and_Abstract_Factory_Design_Pattern.pdf
 * 
 */
public class CommandTreeService implements ITreeBehaviour, IOperationsOnTree, Subscriber {

    IPlotterCommand lastPlotterCommand;
    ICompoundCommand lastCompoundCommand;
    private ICompoundCommand currentCommand;
    PlotterCommandManager commandManager;
    List<IPlotterCommand> plotterCommandsList;

    public CommandTreeService(ICompoundCommand currentCommand) {
        super();
        this.currentCommand = currentCommand;
    }

    public CommandTreeService(PlotterCommandManager commandManager) {
        super();
        this.commandManager = commandManager;
    }

    @Override
    public void leafIsSelected(Object obj) {
        lastCompoundCommand = null;
        lastPlotterCommand = null;
        if (IPlotterCommand.class.isInstance(obj)) {
            IPlotterCommand cmd = (IPlotterCommand) obj;
            cmd.accept(new CommandVisitor());
            lastPlotterCommand = cmd;
        }
    }

    @Override
    public void dirIsSelected(Object obj) {
        lastCompoundCommand = null;
        lastPlotterCommand = null;
        if (ICompoundCommand.class.isInstance(obj)) {
            ICompoundCommand cmd = (ICompoundCommand) obj;
            cmd.accept(new CommandVisitor());
            lastCompoundCommand = cmd;
        }

    }

    @Override
    public void editSelectedCommand() {
        // TODO: mozna przeniesc sprawdzenie tymi ifami do strategii
        if (lastPlotterCommand != null) {
            if (IEditablePlotterCommand.class.isInstance(lastPlotterCommand)) {
                EditEditableCommand editStrategy = new EditEditableCommand((IEditablePlotterCommand) lastPlotterCommand,
                        currentCommand);
                editStrategy.execute();
            }

        }
        reload();

    }

    @Override
    public void removeSelectedCommand() {
        DeleteCommand deleteStrategy = new DeleteCommand(lastPlotterCommand, currentCommand, lastCompoundCommand);
        deleteStrategy.execute();
        reload();
    }

    @Override
    public void update() {
        if (ICompoundCommand.class.isInstance(commandManager.getCurrentCommand())) {
            this.currentCommand = ((ComplexCommand) EditedCommand.getInstance().getEditedCommand());
            plotterCommandsList = new ArrayList<>();
            currentCommand.iterator().forEachRemaining(plotterCommandsList::add);
        }

    }

    public void reload() {
        FeaturesManager.drawerController().clearPanel();
        currentCommand.execute(FeaturesManager.getDriverManager().getCurrentPlotter());
        EditedCommand.getInstance().refresh();
    }

    @Override
    public void moveUp() {
        Strategy moveUp = new MoveUp(lastPlotterCommand, currentCommand);
        moveUp.execute();
        reload();

    }

    @Override
    public void moveDown() {
        Strategy moveDown = new MoveDown(lastPlotterCommand, currentCommand);
        moveDown.execute();
        reload();
    }

    @Override
    public void newPosition() {
        JTextField posX = new JTextField(5);
        JTextField posY = new JTextField(5);
        Object[] message = {"X:", posX, "Y:", posY};
        posX.setText("0");
        posY.setText("0");
        int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
                JOptionPane.OK_CANCEL_OPTION);
        IPlotterCommand newCommand = new SetPositionCommand(Integer.parseInt(posX.getText()),
                Integer.parseInt(posY.getText()));
        checkChildren(currentCommand, newCommand);
        reload();
    }

    @Override
    public void newLine() {
        JTextField posX = new JTextField(5);
        JTextField posY = new JTextField(5);
        Object[] message = {"X:", posX, "Y:", posY};
        posX.setText("0");
        posY.setText("0");
        int result = JOptionPane.showConfirmDialog(null, message, "Please Enter X and Y Values",
                JOptionPane.OK_CANCEL_OPTION);
        IPlotterCommand newCommand = new DrawToCommand(Integer.parseInt(posX.getText()),
                Integer.parseInt(posY.getText()));
        checkChildren(currentCommand, newCommand);
        reload();
    }

    private void checkChildren(ICompoundCommand parent, IPlotterCommand newCommand) {
        for (Iterator iterator = parent.iterator(); iterator.hasNext();) {
            IPlotterCommand type = (IPlotterCommand) iterator.next();
            if (IEditablePlotterCommand.class.isInstance(type) && type.equals(lastPlotterCommand)) {
                parent.iterator().add(newCommand);
                break;
            } else if (ICompoundCommand.class.isInstance(type)) {
                checkChildren((ICompoundCommand) type, newCommand);
            }
        }
    }

}
