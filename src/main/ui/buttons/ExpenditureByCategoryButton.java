package ui;

import model.ExpenseCategory;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the ExpenditureByCategoryButton on the Gui
public class ExpenditureByCategoryButton extends Button {
    private JButton expenditureByCategoryButton;
    private JLabel expenditureByCategoryLabel;

    //REQUIRES: Gui controller that holds this button
    //EFFECTS: creates a button with an actionListener
    public ExpenditureByCategoryButton(Gui controller) {
        super(controller);

        expenditureByCategoryButton = new JButton("View Expenditure By Category");
        expenditureByCategoryButton.setActionCommand("View Expenditure By Category");

        expenditureByCategoryLabel = new JLabel();

        expenditureByCategoryButton.addActionListener(new ActionListener() {
            //EFFECTS: displays expenditure based on category
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("View Expenditure By Category")) {
                    ExpenseCategory[] expenseCategories = ExpenseCategory.values();
                    ExpenseCategory categoryInput = (ExpenseCategory) JOptionPane.showInputDialog(null,
                            "Please Select a Category", "Log Expense", JOptionPane.QUESTION_MESSAGE,
                            null, expenseCategories, expenseCategories[0]);

                    double expenseAmount = getController().getCurrentBudget().getExpenseList()
                            .viewExpenseByCategory(categoryInput);
                    JOptionPane.showMessageDialog(null, "You have spent $" + expenseAmount
                            + " on " + categoryInput + " so far");
                }
            }
        });
    }

    //EFFECTS: returns the expenditureByCategoryButton
    public JButton getExpenditureByCategoryButton() {
        return expenditureByCategoryButton;
    }
}
