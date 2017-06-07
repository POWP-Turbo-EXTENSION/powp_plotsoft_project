package edu.iis.powp.command;


public class FigureFactory {
    public static IPlotterCommand getSquare(int startX, int startY, int size) {
        ComplexCommand command = new ComplexCommand();
        command.addCommand(new SetPositionCommand(startX, startY));
        command.addCommand(new DrawToCommand(startX+size, startY));
        command.addCommand(new SetPositionCommand(startX+size, startY));
        command.addCommand(new DrawToCommand(startX+size, startY+size));
        command.addCommand(new SetPositionCommand(startX+size, startY+size));
        command.addCommand(new DrawToCommand(startX, startY+size));
        command.addCommand(new SetPositionCommand(startX, startY+size));
        command.addCommand(new DrawToCommand(startX, startY));
        command.Save("kwadrat");
        return command;
    }
}
