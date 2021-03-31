package ui;

import model.Budget;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the graphical user interface for the application
public class Gui extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    private JFrame frame;
    private JPanel panel;
    private Budget newBudget;
    private JLabel balanceLabel;
    private JScrollPane expenseLogPane;
    private JTextArea expenseLog;
    private JPanel expenseLogBlock;
    private SetBudgetButton budgetButton;
    private LogExpenseButton expenseButton;
    private ui.ExpenditureByCategoryButton expenditureByCategoryButton;
    private PieChartButton chartButton;
    private SaveButton saveButton;
    private LoadButton loadButton;

    //MODIFIES: this
    //EFFECTS: constructs Gui, displays buttons and panels
    public Gui() throws IOException {
        frame = new JFrame();
        newBudget = new Budget();

        initializeButtons();

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 2));
        panel.add(budgetButton.getSetBudgetButton());
        panel.add(expenseButton.getLogExpenseButton());
        panel.add(expenditureByCategoryButton.getExpenditureByCategoryButton());
        panel.add(saveButton.getSaveButton());
        panel.add(loadButton.getLoadButton());
        panel.add(chartButton.getPieChartButton());
        panel.add(balanceLabel);
        panel.add(expenseLogBlock);


        frame.add(panel);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Smart Budget App");
        frame.pack();
        frame.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: instantiates buttons
    private void initializeButtons() throws IOException {

        budgetButton = new SetBudgetButton(this);
        balanceLabel = new JLabel("Balance: 0");

        expenseButton = new LogExpenseButton(this);

        expenseLogBlock = new JPanel(new GridLayout(2, 1));
        expenseLogBlock.setSize(Gui.WIDTH - (Gui.WIDTH / 5),
                Gui.HEIGHT - (Gui.HEIGHT / 5));
        expenseLogPane = new JScrollPane(new JTextArea(6, 40));
        expenseLog = new JTextArea("", 6, 40);
        expenseLog.setVisible(true);
        expenseLogBlock.add(expenseLogPane);

        expenditureByCategoryButton = new ui.ExpenditureByCategoryButton(this);

        chartButton = new PieChartButton(this);


        saveButton = new SaveButton(this);

        loadButton = new LoadButton(this);

    }

    //EFFECTS: returns the newBudget field
    public Budget getCurrentBudget() {
        return newBudget;
    }

    //EFFECTS: returns the balanceLabel field
    public JLabel getBalanceLabel() {
        return balanceLabel;
    }

    //MODIFIES: this
    //EFFECTS: sets the newBudget to budget
    public void setCurrentBudget(Budget budget) {
        this.newBudget = budget;
    }

    //EFFECTS: returns the expenseLogBlock field
    public JPanel getExpenseBlockLog() {
        return expenseLogBlock;
    }

    //EFFECTS: returns the expenseLog field
    public JTextArea getExpenseLog() {
        return expenseLog;
    }

    //EFFECTS: returns the expenseLogPane field
    public JScrollPane getExpenseLogPane() {
        return expenseLogPane;
    }


    public static void main(String[] args) throws IOException {
        new Gui();
    }

}
