package edu.iis.powp.command;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.manager.PlotterCommandManager;

public class FigureFactory {
    public static ComplexCommand getSquare(int startX, int startY, int size) {
        ComplexCommand command1 = new ComplexCommand();
        command1.addCommand(new SetPositionCommand(startX, startY));
        command1.addCommand(new DrawToCommand(startX+size, startY));
        command1.addCommand(new SetPositionCommand(startX+size, startY));
        command1.addCommand(new DrawToCommand(startX+size, startY+size));
        command1.addCommand(new SetPositionCommand(startX+size, startY+size));
        command1.addCommand(new DrawToCommand(startX, startY+size));
        command1.addCommand(new SetPositionCommand(startX, startY+size));
        command1.addCommand(new DrawToCommand(startX, startY));
        startX += 80;
        
        ComplexCommand command = new ComplexCommand();
        command.addCommand(new SetPositionCommand(startX, startY));
        command.addCommand(new DrawToCommand(startX+size, startY));
        command.addCommand(new SetPositionCommand(startX+size, startY));
        command.addCommand(new DrawToCommand(startX+size, startY+size));
        command.addCommand(new SetPositionCommand(startX+size, startY+size));
        command.addCommand(new DrawToCommand(startX, startY+size));
        command.addCommand(new SetPositionCommand(startX, startY+size));
        command.addCommand(new DrawToCommand(startX, startY));
        
        command1.addCommand(command);
        
        return command1;
    }
}
