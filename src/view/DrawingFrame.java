package view;

import javax.swing.*;
import java.awt.*;

public class DrawingFrame extends OutputFrame{
    @Override
    public void displaySequenceTable(boolean[][] seq) {
        this.stillRunning = false;
        new DrawingTable(seq);
    }
}
class DrawingTable extends JPanel{
    boolean[][] sequence;
    DrawingTable(boolean[][] seq){
        this.sequence = seq;

        JFrame tableFrame = new JFrame("Solution");
        tableFrame.getContentPane().add(this);
        tableFrame.setSize(780, 460);
        tableFrame.setResizable(false);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void paintComponent(Graphics pen){
        int cellSize = 22;
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, 1000, 1000);

        for (int i = 0; i < sequence[0].length; i++) {
            for (int j = 0; j < sequence.length; j++) {
                pen.setColor(sequence[j][i] ? Color.GREEN : Color.RED);
                pen.fillRect(i * cellSize + 60, j * cellSize + 40, cellSize, cellSize);
                pen.setColor(Color.BLACK);
                pen.drawRect(i * cellSize + 60, j * cellSize + 40, cellSize, cellSize);
            }
        }

        for (int i = 0; i < sequence.length; i++) {
            pen.setColor(Color.BLACK);
            pen.drawString( "S-" + (i + 1), 20, i * cellSize + 55);
        }
    }
}
