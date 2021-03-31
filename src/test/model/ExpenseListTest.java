package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseListTest {
    private ExpenseList testexpenselist;
    private Expense testexpense;


    @BeforeEach
    void runBefore() {

        testexpenselist = new ExpenseList();
    }


    @Test
    void testExpenseListConstructor() {
        assertEquals(0, testexpenselist.getExpListSize());
    }

    @Test
    void testGetExpListSizeFromZeroToOne() {
        testexpense = new Expense(5.8, ExpenseCategory.BILLS );

        assertEquals(0, testexpenselist.getExpListSize());

        testexpenselist.addExpense(testexpense);

        assertEquals(1, testexpenselist.getExpListSize());
    }


    @Test
    void testGetExpListSizeFromAZeroToMany() {

        assertEquals(0, testexpenselist.getExpListSize());

        testexpense = new Expense(5.5, ExpenseCategory.ENTERTAINMENT);
        testexpenselist.addExpense(testexpense);
        assertEquals(1, testexpenselist.getExpListSize());

        Expense testexpense2 = new Expense(10,ExpenseCategory.BILLS );
        testexpenselist.addExpense(testexpense2);
        assertEquals(2, testexpenselist.getExpListSize());

        Expense testexpense3 = new Expense(10,ExpenseCategory.ENTERTAINMENT );
        testexpenselist.addExpense(testexpense3);
        assertEquals(3, testexpenselist.getExpListSize());

        assertEquals(15.5,testexpenselist.viewExpenseByCategory(ExpenseCategory.ENTERTAINMENT));

    }


    @Test
    void testAddExpenseOneExpense() {
        Expense testexpense = new Expense(5.8, ExpenseCategory.BILLS );
        testexpenselist.addExpense(testexpense);

        ArrayList<Expense> newList = new ArrayList<Expense>();
        newList.add(testexpense);

        assertEquals(1, testexpenselist.getExpListSize());
        //assertEquals(newList,testexpenselist);
    }

    @Test
    void testViewExpensesEmpty() {
        assertEquals(0, testexpenselist.getExpListSize());
        assertEquals(0, testexpenselist.viewExpenses());
    }


    @Test
    void testViewExpensesOneExpense() {
        testexpense = new Expense(5.8, ExpenseCategory.ENTERTAINMENT );
        testexpenselist.addExpense(testexpense);
        assertEquals(1, testexpenselist.getExpListSize());
        assertEquals(5.8, testexpenselist.viewExpenses());
    }

    @Test
    void testViewExpensesMultipleExpenses() {
        Expense testexpense1 = new Expense(5.8, ExpenseCategory.BILLS);
        Expense testexpense2 = new Expense(20, ExpenseCategory.ENTERTAINMENT );
        Expense testexpense3 = new Expense(112.33, ExpenseCategory.EATING_OUT );
        Expense testexpense4 = new Expense(66.4, ExpenseCategory.TRANSPORTATION );

        testexpenselist.addExpense(testexpense1);
        testexpenselist.addExpense(testexpense2);
        testexpenselist.addExpense(testexpense3);
        testexpenselist.addExpense(testexpense4);
        assertEquals(4, testexpenselist.getExpListSize());
        assertEquals(204.53, testexpenselist.viewExpenses());
    }

    @Test
    void testGetExpenses() {
        assertEquals(0, testexpenselist.getExpenses().size());
        assertTrue(testexpenselist.getExpenses().isEmpty());
    }

    @Test
    void testContains() {
        Expense testexpense1 = new Expense(77.8,ExpenseCategory.EATING_OUT );
        Expense testexpense2 = new Expense(83,ExpenseCategory.ENTERTAINMENT );
        Expense testexpense3 = new Expense(102.99,ExpenseCategory.TRANSPORTATION );

        testexpenselist.addExpense(testexpense1);
        testexpenselist.addExpense(testexpense2);
        testexpenselist.addExpense(testexpense3);

        assertTrue(testexpenselist.contains(testexpense1));
        assertTrue(testexpenselist.contains(testexpense2));
        assertTrue(testexpenselist.contains(testexpense3));
        assertEquals(77.8,testexpenselist.viewExpenseByCategory(ExpenseCategory.EATING_OUT));

    }

    @Test
    void testViewListOfExpenses() {
        Expense testexpense1 = new Expense(77.8,ExpenseCategory.EATING_OUT );
        Expense testexpense2 = new Expense(83,ExpenseCategory.ENTERTAINMENT );
        Expense testexpense3 = new Expense(102.99,ExpenseCategory.TRANSPORTATION );

        testexpenselist.addExpense(testexpense1);
        testexpenselist.addExpense(testexpense2);
        testexpenselist.addExpense(testexpense3);

        assertEquals("\n" +
                "EATING_OUT:$77.8\n" +
                "ENTERTAINMENT:$83.0\n" +
                "TRANSPORTATION:$102.99",testexpenselist.viewListOfExpenses());

    }

    @Test
    void testViewListOfExpensesEmpty() {

        assertEquals("",testexpenselist.viewListOfExpenses());

    }
}
