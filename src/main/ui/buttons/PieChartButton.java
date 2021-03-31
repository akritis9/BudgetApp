package ui.buttons;

import ui.Gui;
import ui.PieChart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the PieChartButton on the Gui
public class PieChartButton extends Button {

    private JButton pieChartButton;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public PieChartButton(Gui controller) {
        super(controller);

        pieChartButton = new JButton("Pie Chart");
        pieChartButton.setActionCommand("Pie Chart");

        pieChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // MODIFIES: controller
                // EFFECTS: renders a pie chart showing breakdown of expenditure by category
                if (e.getActionCommand().equals("Pie Chart")) {

                    PieChart chart = new PieChart(getController());
                    chart.setSize(800, 400);
                    chart.setLocationRelativeTo(null);
                    chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    chart.setVisible(true);
                }
            }
        });

    }

    //EFFECTS: returns the pieChartButton
    public JButton getPieChartButton() {
        return pieChartButton;
    }
}
