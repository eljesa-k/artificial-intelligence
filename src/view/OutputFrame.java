package view;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class OutputFrame extends JFrame{
    private JPanel panel;
    private JLabel timerLabel;
    JTextArea outputArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(outputArea);
    Date start_time;
    public OutputFrame(){
                super("Algoritmi Gjenetik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        timerLabel = new JLabel();
        panel.add(timerLabel, BorderLayout.NORTH);

        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);
        setSize(400, 400);
        setVisible(true);

        Timer timer = new Timer(1000, e -> {
            long elapsedTime = (new Date().getTime() - start_time.getTime()) / 1000;
            timerLabel.setText("Koha e kaluar: " + elapsedTime + " sekonda");
        });
        timer.start();
    }
    public void appendNewLine(int i, double score){
        outputArea.append("Iterimi " + i + ": " + score + "\n");
    }
    public void displaySequenceTable(boolean[][] seq) {
        // Create the table model with sequence data
        String[] columnNames = new String[seq[0].length + 1];
        columnNames[0] = ""; // Empty space for row labels
        for (int i = 0; i < seq[0].length; i++) {
            columnNames[i + 1] = "frame-" + (i + 1);
        }

        String[][] rowData = new String[seq.length][seq[0].length + 1];
        for (int i = 0; i < seq.length; i++) {
            rowData[i][0] = "S-" + (i + 1);
            for (int j = 0; j < seq[i].length; j++) {
                rowData[i][j + 1] = seq[i][j] ? "true" : "false";
            }
        }

        // Create the JTable
        JTable table = new JTable(rowData, columnNames);
        table.setRowHeight(25);

        // Set the preferred size of the table based on its contents
        int tableWidth = table.getPreferredSize().width;
        int tableHeight = table.getPreferredSize().height;
        table.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.removeAll();
        panel.add(scrollPane, BorderLayout.CENTER);

        // Refresh the frame to display the table
        pack();
        repaint();
    }
    public void setStartTime(Date time){
        this.start_time = time;
    }
}
