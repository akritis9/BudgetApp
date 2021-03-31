package ui.buttons;

import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the SetBudgetButton on the Gui
public class SetBudgetButton extends Button {
    private JButton setBudgetButton;
    //private JLabel budgetLabel;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public SetBudgetButton(Gui controller) {
        super(controller);


        setBudgetButton = new JButton("Set Budget");
        setBudgetButton.setActionCommand("Set Budget");

        //budgetLabel = new JLabel("Budget: 0");
        //setBudgetButton.setPreferredSize(new Dimension(5, 5));

        setBudgetButton.addActionListener(new ActionListener() {
            // MODIFIES: controller, budget
            // EFFECTS: sets a new budget
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Set Budget")) {
                    String budgetset = JOptionPane.showInputDialog("Please Enter Your Budget Amount:");
                    //budgetLabel.setText("Budget: " + budgetset);
                    //balanceLabel.setText("Balance: " + budgetset);

                    // Now store the data
                    double budgetdouble = Double.parseDouble(budgetset);
                    getController().getCurrentBudget().setBudgetAmount(budgetdouble);

                    //budgetLabel.setText("Budget: " + "$" + getController().getCurrentBudget().getBudgetAmount());
                    getController().getBalanceLabel().setText("Balance: " + "$"
                            + getController().getCurrentBudget().getMoneyToSpend());

                }
            }
        });
    }

    //EFFECTS: returns the setBudgetButton
    public JButton getSetBudgetButton() {
        return setBudgetButton;
    }

}