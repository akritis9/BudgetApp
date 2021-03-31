package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a budget having a target budget and money left to spend (in dollars)
public class Budget implements Writable {
    protected double budgetAmount;  // budget set by user
    protected double moneyToSpend;   // money available to spend
    protected ExpenseList expl;

    // EFFECTS: constructs a new budget object
    public Budget() {
        budgetAmount = 0;
        moneyToSpend = 0;
        expl = new ExpenseList();
    }

    // REQUIRES: userbudget >= 0
    // MODIFIES: this
    // Effects: budget and moneyToSpend are set to userbudget
    public void setBudgetAmount(double userbudget) {
        budgetAmount = userbudget;
        moneyToSpend = userbudget;
    }


    // REQUIRES: addedbudget >= 0
    // MODIFIES: this
    // Effects: addedbudget is added to budget and moneyToSpend
    public void addToBudget(double addedbudget) {
        budgetAmount = budgetAmount + addedbudget;
        moneyToSpend = moneyToSpend + addedbudget;
    }


    // EFFECTS: returns moneyToSpend
    public double getMoneyToSpend() {
        return moneyToSpend;
    }


    // EFFECTS: returns budget amount
    public double getBudgetAmount() {
        return budgetAmount;
    }

    // EFFECTS: returns expl field
    public ExpenseList getExpenseList() {
        return expl;
    }

    // MODIFIES: this
    // EFFECTS: sets this.expl to expl
    public void setExpenseList(ExpenseList expl) {
        this.expl = expl;
    }

    // MODIFIES: this
    // EFFECTS: sets moneyToSpend field to leftover
    public void setMoneyToSpend(double leftover) {
        moneyToSpend = leftover;
    }

    //MODIFIES: this and expl
    // EFFECTS: creates and adds an expense to an expenseList and updates moneyToSpend
    public void logExpense(double expAmt,ExpenseCategory expensecategory) {
        Expense expense = new Expense(expAmt,expensecategory);
        expl.addExpense(expense);
        double balance = getMoneyToSpend();
        double expenseAmount = expense.getExpense();
        double newBalance = balance - expenseAmount;
        setMoneyToSpend(newBalance);
    }

    // EFFECTS: converts Budget to JSon object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Budget", getBudgetAmount());
        json.put("Balance", getMoneyToSpend());
        json.put("Expenses", expl.expensesToJson());
        return json;
    }


}
