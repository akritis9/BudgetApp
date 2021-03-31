package ui.buttons;

import model.ExpenseCategory;
import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the LogExpenseButton on the Gui
public class LogExpenseButton extends Button {

    private JButton logExpenseButton;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public LogExpenseButton(Gui controller) {
        super(controller);

        logExpenseButton = new JButton("Log Expense");
        logExpenseButton.setActionCommand("Log Expense");


        logExpenseButton.addActionListener(new ActionListener() {
            //MODIFIES: controller, budget
            // EFFECTS: updates and renders the expense log
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Log Expense")) {
                    ExpenseCategory[] expenseCategories = ExpenseCategory.values();
                    ExpenseCategory categoryInput = (ExpenseCategory) JOptionPane.showInputDialog(null,
                            "Please Select a Category", "Log Expense", JOptionPane.QUESTION_MESSAGE,
                            null, expenseCategories, expenseCategories[0]);

                    if (!(categoryInput == null)) {
                        String expenseAmount = JOptionPane.showInputDialog("Please Enter Your Expense Amount:");
                        double expenseDouble = Double.parseDouble(expenseAmount);

                        updateLog(categoryInput, expenseDouble);

                    }
                }
            }
        });
    }

    //MODIFIES: budget
    // EFFECTS: updates the expense log
    private void updateLog(ExpenseCategory expensecategory, double expenseamount) {

        getController().getCurrentBudget().logExpense(expenseamount, expensecategory);

        getController().getBalanceLabel().setText("Balance: "
                + "$" + getController().getCurrentBudget().getMoneyToSpend());

        getController().getExpenseLog().setText(getController().getCurrentBudget()
                .getExpenseList().viewListOfExpenses());
        getController().getExpenseLogPane().setViewportView(getController().getExpenseLog());
    }

    //EFFECTS: returns the logExpenseButton
    public JButton getLogExpenseButton() {
        return logExpenseButton;
    }

}
