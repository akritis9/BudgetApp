
package Persistence;

import model.Budget;
import model.Expense;
import model.ExpenseCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//CODE CREDIT: All code in this class is based on the JsonSerialization app
public class JsonTest {
    protected void checkExpense(ExpenseCategory category, double money, Expense expense) {
        assertEquals(money, expense.getExpense());
        assertEquals(category, expense.getExpenseCategory());
    }

    protected void checkBudget(double money, double balance, Budget budget) {
        assertEquals(money, budget.getBudgetAmount());
        assertEquals(balance, budget.getMoneyToSpend());
    }
}

