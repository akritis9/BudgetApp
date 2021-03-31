package Persistence;

import Persistence.JsonTest;
import model.Budget;

import model.ExpenseCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//CODE CREDIT: All code in this class is based on the JsonSerialization app
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/noSuchFile.json");
        try {
            Budget budg = reader.readBudget();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoBudget() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/testWriterEmptyExpenseListNoBudget.json");
        try {
            Budget budg = reader.readBudget();
            assertEquals(0, budg.getBudgetAmount());
            assertEquals(0, budg.getMoneyToSpend());
            assertTrue(budg.getExpenseList().getExpenses().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBudget() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/testWriterGeneralExpenseList.json");
        try {
            Budget budg = reader.readBudget();
            assertEquals(800, budg.getBudgetAmount());
            assertEquals(740, budg.getMoneyToSpend());
            assertEquals(3,budg.getExpenseList().getExpListSize());
            checkExpense(ExpenseCategory.ENTERTAINMENT,10, budg.getExpenseList().getExpenses().get(0));
            checkExpense(ExpenseCategory.TRANSPORTATION,20, budg.getExpenseList().getExpenses().get(1));
            checkExpense(ExpenseCategory.EATING_OUT,30, budg.getExpenseList().getExpenses().get(2));
            checkBudget(800,740,budg);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}