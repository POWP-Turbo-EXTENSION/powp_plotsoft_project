package edu.iis.powp.command.editor;

import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.editor.strategy.Strategy;
import edu.iis.powp.command.visitor.Visitor;

/*
 * taki troche eventhander, byłby odpalany za każdym razem 
 * gdy przycisk jest nacisnięty z określoną strategią wykonania
 * 
 * Bo sam visitor pozwala jedynie zidentyfikować jaki obiekt ma być obsługiwany a nie jak ma obsługiwać dany obiekt. 
 * A więc to w jaki sposób będzie to zrealizowane musi być dostarczone z zewnątrz. 
 * 
 * Albo dla każdej akcji osobny wizytor, gdy robimy moveup i gdy robimy movedown 
 * to były by dwa visitory bo visitor aktualizował by się sam poprzez observer
 */
public class CommandVisitor implements Visitor {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void visit(DrawToCommand cmd) {
//        strategy.setCurrentCommand(cmd);
        System.out.println("Dtc");
    }

    @Override
    public void visit(SetPositionCommand cmd) {
//        strategy.setCurrentCommand(cmd);
        System.out.println("Spc");
    }

    @Override
    public void visit(ComplexCommand complexCommand) {
//        strategy.setCurrentCommand(complexCommand);
        System.out.println("Ccc");
    }

//    @Override
//    public void visit(IPlotterCommand cmd) {        
//        cmd.accept(this);
//        System.out.println(cmd);
//    }
//
//    @Override
//    public void visit(IEditablePlotterCommand cmd) {
//        cmd.accept(this);
//        System.out.println(cmd);
//    }

}
