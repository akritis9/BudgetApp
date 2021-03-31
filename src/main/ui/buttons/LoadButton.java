package ui.buttons;

import persistence.JsonReader;
import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// represents the LoadButton on the Gui
public class LoadButton extends Button {

    private static final String JSON_STORE = "./data/savedbudget.json";
    private persistence.JsonReader jsonReader;
    private JButton loadButton;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public LoadButton(Gui controller) throws IOException {
        super(controller);

        jsonReader = new JsonReader(JSON_STORE);
        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");

        loadButton.addActionListener(new ActionListener() {
            //MODIFIES: controller, budget
            // EFFECTS: loads and renders saved data
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Load")) {
                    loadBudget();

                    getController().getBalanceLabel().setText("Balance: "
                            + "$" + getController().getCurrentBudget().getMoneyToSpend());

                    getController().getExpenseLog().setText(getController().getCurrentBudget()
                            .getExpenseList().viewListOfExpenses());
                    getController().getExpenseLogPane().setViewportView(getController().getExpenseLog());
                }
            }
        });

    }

    // MODIFIES: this
    // EFFECTS: loadsBudget from file
    private void loadBudget() {
        try {
            getController().setCurrentBudget(jsonReader.readBudget());
            System.out.println("Loaded " + getController().getCurrentBudget().getBudgetAmount()
                    + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: returns the loadButton
    public JButton getLoadButton() {
        return loadButton;
    }
}
