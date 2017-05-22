package edu.iis.powp.command;


public class FigureFactory {
    public static IPlotterCommand getSquare(int startX, int startY, int size) {
        ComplexCommand command = new ComplexCommand();
        command.addCommand(new CommandLineToPosition(startX+size, startY, startX, startY));
        command.addCommand(new CommandLineToPosition(startX+size, startY+size, startX+size, startY));
        command.addCommand(new CommandLineToPosition(startX, startY+size, startX+size, startY+size));
        command.addCommand(new CommandLineToPosition(startX, startY, startX, startY+size));
        return command;
    }
}
