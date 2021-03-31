package ui.buttons;

import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// represents the saveButton on the Gui
public class SaveButton extends Button {

    private JButton saveButton;
    private static final String JSON_STORE = "./data/savedbudget.json";
    private persistence.JsonWriter jsonWriter;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public SaveButton(Gui controller) throws FileNotFoundException {
        super(controller);

        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");

        jsonWriter = new persistence.JsonWriter(JSON_STORE);

        saveButton.addActionListener(new ActionListener() {
            // EFFECTS: saves data to file
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Save")) {
                    saveBudget();
                }

            }
        });
    }

    // EFFECTS: saves the Budget to file
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(getController().getCurrentBudget());
            jsonWriter.close();
            System.out.println("Saved " + getController().getCurrentBudget().getExpenseList()
                    .getExpenses() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: returns the saveButton
    public JButton getSaveButton() {
        return saveButton;
    }
}

