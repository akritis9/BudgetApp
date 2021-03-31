package model;

import org.json.JSONArray;

import java.util.*;

// Represents a list of expenses incurred by user (in dollars)
public class ExpenseList {
    private ArrayList<Expense> expenses;


    // EFFECTS: constructs an ExpenseList object
    public ExpenseList() {
        expenses = new ArrayList<Expense>();
    }


    // MODIFIES: this
    // EFFECTS: adds an expense to the list of expenses
    public void addExpense(Expense newexpense) {
        expenses.add(newexpense);
    }

    //EFFECTS: accesses list of expenses
    public List<Expense> getExpenses() {
        return expenses;
    }


    // EFFECTS: return size of expenses
    public int getExpListSize() {
        return expenses.size();
    }

    //EFFECTS: returns true if e is contained in expenses else return false
    public boolean contains(Expense e) {
        return expenses.contains(e);
    }


    // EFFECTS: returns the sum of expenses in the expenseList object
    public double viewExpenses() {
        double totalExpenses = 0.0;
        for (Expense e : expenses) {
            totalExpenses = totalExpenses + e.getExpense();
        }
        return totalExpenses;
    }

    // EFFECTS: returns the sum of expenses in the expenseList object
    public double viewExpenseByCategory(ExpenseCategory expensecategory) {
        double totalCategoryExpenditure = 0.0;
        for (Expense e : expenses) {
            if (e.getExpenseCategory() == expensecategory) {
                totalCategoryExpenditure = totalCategoryExpenditure + e.getExpense();
            }
        }
        return totalCategoryExpenditure;
    }

    // EFFECTS: returns the list of expenses as a string for Gui
    public String viewListOfExpenses() {
        StringBuilder expenseLog = new StringBuilder("");
        for (Expense e : expenses) {
            expenseLog.append("\n").append(e.getExpenseCategory()).append(":").append("$").append(e.getExpense());
        }
        return expenseLog.toString();
    }

    // EFFECTS: returns things in this expenselist as a JSON array
    public JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

//    // EFFECTS: returns things in this expenselist as a JSON array
//    private double budgetToJson(Budget budget) {
//        return budget.getBudgetAmount();
//    }
//
//    // EFFECTS: returns things in this expenselist as a JSON array
//    private double moneyToJson(Budget budget) {
//        return budget.getMoneyToSpend();
//    }

}
