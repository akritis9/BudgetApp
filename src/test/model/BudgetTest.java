package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {
    private Budget testbudget;

    @BeforeEach
    void runBefore() {
        testbudget = new Budget();
    }

    @Test
    void testBudgetConstructor() {
        assertEquals(0, testbudget.getBudgetAmount());
        assertEquals(0, testbudget.getMoneyToSpend());
        assertTrue(testbudget.getExpenseList().getExpenses().isEmpty());
    }

    @Test
    void testGetBudget() {
        assertEquals(0, testbudget.getBudgetAmount());
    }

    @Test
    void testGetBudgetFromNonZero() {

        testbudget.setBudgetAmount(33.5);
        assertEquals(33.5, testbudget.getBudgetAmount());
    }

    @Test
    void testGetMoneyToSpend() {
        assertEquals(0, testbudget.getMoneyToSpend());
    }

    @Test
    void testMoneyToSpendFromNonZero() {
        testbudget.setBudgetAmount(33.5);
        assertEquals(33.5, testbudget.getMoneyToSpend());
    }

    @Test
    void testGetExpenseList() {
        assertTrue(testbudget.getExpenseList().getExpenses().isEmpty());
    }

    @Test
    void testGetExpenseListNonEmpty() {
        ExpenseList testExpenseList = new ExpenseList();
        Expense testexpense1 = new Expense(10,ExpenseCategory.EATING_OUT);
        Expense testexpense2 = new Expense(15, ExpenseCategory.BILLS);
        testExpenseList.addExpense(testexpense1);
        testExpenseList.addExpense(testexpense2);

        testbudget.setExpenseList(testExpenseList);
        assertEquals(testExpenseList,testbudget.getExpenseList());
    }


    @Test
    void testSetExpenseList() {
        ExpenseList testExpenseList = new ExpenseList();
        Expense testexpense1 = new Expense(10,ExpenseCategory.EATING_OUT);
        Expense testexpense2 = new Expense(10,ExpenseCategory.EATING_OUT);
        testExpenseList.addExpense(testexpense1);
        testExpenseList.addExpense(testexpense2);

        testbudget.setExpenseList(testExpenseList);
        assertEquals(testExpenseList,testbudget.getExpenseList());
    }

    @Test
    void testSetBudgetZeroBudget() {
        testbudget.setBudgetAmount(0.0);
        assertEquals(0.0, testbudget.getBudgetAmount());
        assertEquals(0.0, testbudget.getMoneyToSpend());
    }

    @Test
    void testSetBudgetNonZeroBudget() {
        testbudget.setBudgetAmount(45);
        assertEquals(45, testbudget.getBudgetAmount());
        assertEquals(45, testbudget.getMoneyToSpend());
    }


    @Test
    void testSetBudgetFromNonZeroToZero() {
        testbudget.setBudgetAmount(45);

        assertEquals(45, testbudget.getBudgetAmount());
        assertEquals(45, testbudget.getMoneyToSpend());

        testbudget.setBudgetAmount(0);

        assertEquals(0, testbudget.getBudgetAmount());
        assertEquals(0, testbudget.getMoneyToSpend());
    }


    @Test
    void testSetBudgetNonZeroDecimalBudget() {
        testbudget.setBudgetAmount(100.09);
        assertEquals(100.09, testbudget.getBudgetAmount());
        assertEquals(100.09, testbudget.getMoneyToSpend());
    }


    @Test
    void testAddToBudgetFromZero() {
        testbudget.addToBudget(100.99);
        assertEquals(100.99, testbudget.getBudgetAmount());
        assertEquals(100.99, testbudget.getMoneyToSpend());
    }

    @Test
    void testAddToBudgetFromNonZero() {
        testbudget.setBudgetAmount(2000.4578);
        testbudget.addToBudget(100.99);
        assertEquals(2101.4478, testbudget.getBudgetAmount());
        assertEquals(2101.4478, testbudget.getMoneyToSpend());
    }

    @Test
    void testSetMoneyToSpend() {
        testbudget.setMoneyToSpend(88.9);

        assertEquals(88.9, testbudget.getMoneyToSpend());
    }

    @Test
    void testLogExpenseNoBudgetSetNoExpenses() {
        testbudget.logExpense(25,ExpenseCategory.GROCERIES);
        assertEquals(-25,testbudget.getMoneyToSpend());
        assertEquals(25,testbudget.getExpenseList().getExpenses().get(0).getExpense());
    }

    @Test
    void testLogExpenseBudgetSetButWithExpenses() {
        testbudget.setBudgetAmount(9000);
        testbudget.logExpense(100,ExpenseCategory.TRANSPORTATION);
        testbudget.logExpense(200,ExpenseCategory.ENTERTAINMENT);
        testbudget.logExpense(300,ExpenseCategory.GROCERIES);
        assertEquals(100,testbudget.getExpenseList().getExpenses().get(0).getExpense());
        assertEquals(200,testbudget.getExpenseList().getExpenses().get(1).getExpense());
        assertEquals(300,testbudget.getExpenseList().getExpenses().get(2).getExpense());
        assertEquals(8400,testbudget.getMoneyToSpend());
    }

}