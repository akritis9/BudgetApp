package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    private Expense testexpense;

    @BeforeEach
    void runBefore() {
        testexpense = new Expense(99.9, ExpenseCategory.ENTERTAINMENT );
    }

    @Test
    void testExpenseConstructor() {
        assertEquals(99.9, testexpense.getExpense());
        assertEquals(ExpenseCategory.ENTERTAINMENT, testexpense.getExpenseCategory());
    }

}

