package edu.iis.powp.commandeditor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iis.client.plottermagic.IPlotter;

public class CommandVisualisation extends JPanel implements IPlotter {

    private final JLabel commandName = new JLabel("");
    private final Dimension WINDOW_MIN_SIZE = new Dimension(500, 500);
    private final int CORDS_X;
    private final int CORDS_Y;
    private int currentPos_x = this.getWidth() / 2;
    private int currentPos_y = this.getHeight() / 2;
    private int toPos_x = 0;
    private int toPos_y = 0;

    public CommandVisualisation(String commandName) {
        
        this.commandName.setText(commandName);
        initUI();
        CORDS_X = 250;// this.getWidth() / 2;
        CORDS_Y = 250;//this.getHeight() / 2;
    }

    public CommandVisualisation() {
        
        initUI();
        CORDS_X = 250;//this.getWidth() / 2;
        CORDS_Y = 250;//this.getHeight() / 2;
    }

    public void setCommandName(String text) {
        this.commandName.setText(text);
    }

    private void initUI() {
        this.setMinimumSize(WINDOW_MIN_SIZE);
        this.setPreferredSize(WINDOW_MIN_SIZE);
        this.setLayout(new BorderLayout());
        this.add(commandName, BorderLayout.NORTH);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void drawTo(int arg0, int arg1) {
        this.toPos_x = CORDS_X + arg0;
        this.toPos_y = CORDS_Y + arg1;
        System.out.println(arg0 + " " + arg1 + "|" + toPos_x + " " + toPos_y);
        this.repaint();
        this.invalidate();
    }

    @Override
    public void setPosition(int arg0, int arg1) {
        this.currentPos_x = CORDS_X + arg0;
        this.currentPos_y = CORDS_Y + arg1;
        System.out.println(arg0 + " " + arg1 + "|" + currentPos_x + " " + currentPos_y);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawLine(currentPos_x, currentPos_y, toPos_x, toPos_y);
    }

}
