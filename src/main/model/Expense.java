package model;

import com.sun.corba.se.spi.ior.Writeable;
import org.json.JSONObject;
import persistence.Writable;

// Represents an expense incurred by user (in dollars)
public class Expense implements Writable {
    private double cost;
    private ExpenseCategory category;

    // REQUIRES: moneyspent > 0
    //EFFECTS: creates an expense object
    public Expense(double moneyspent, ExpenseCategory category) {
        cost = moneyspent;
        this.category = category;
    }


    // EFFECTS: returns the cost associated with an expense
    public double getExpense() {
        return cost;
    }

    // EFFECTS: returns the cost associated with an expense
    public ExpenseCategory getExpenseCategory() {
        return category;
    }

    // Converts Expense to Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Expense Amount", cost);
        json.put("Category", category);
        return json;
    }

}
