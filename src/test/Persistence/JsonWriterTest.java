
package Persistence;

import model.Budget;
import model.Expense;
import model.ExpenseCategory;
import model.ExpenseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//CODE CREDIT: All code in this class is based on the JsonSerialization app
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            persistence.JsonWriter writer = new persistence.JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExpenseListNoBudget() {
        try {
            Budget budg = new Budget();
            persistence.JsonWriter writer = new persistence.JsonWriter("./data/testWriterEmptyExpenseListNoBudget.json");
            writer.open();
            writer.write(budg);
            writer.close();

            persistence.JsonReader reader = new persistence.JsonReader("./data/testWriterEmptyExpenseListNoBudget.json");
             budg = reader.readBudget();
            assertEquals(0, budg.getExpenseList().getExpListSize());
            assertEquals(0, budg.getMoneyToSpend());
            assertEquals(0, budg.getBudgetAmount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkList() {
        try {
            Budget budg = new Budget();
            budg.setBudgetAmount(800);

            /*ExpenseList el = new ExpenseList();
            Expense e1 = new Expense(10, ExpenseCategory.ENTERTAINMENT );
            Expense e2 = new Expense(20, ExpenseCategory.TRANSPORTATION );
            Expense e3 = new Expense(30, ExpenseCategory.EATING_OUT);*/
            budg.logExpense(10,ExpenseCategory.ENTERTAINMENT);
            budg.logExpense(20,ExpenseCategory.TRANSPORTATION);
            budg.logExpense(30,ExpenseCategory.EATING_OUT);

            persistence.JsonWriter writer = new persistence.JsonWriter("./data/testWriterGeneralExpenseList.json");
            writer.open();
            writer.write(budg);
            writer.close();

            persistence.JsonReader reader = new persistence.JsonReader("./data/testWriterGeneralExpenseList.json");
            budg = reader.readBudget();
            assertEquals(3, budg.getExpenseList().getExpListSize());
            checkExpense(ExpenseCategory.ENTERTAINMENT,10, budg.getExpenseList().getExpenses().get(0));
            checkExpense(ExpenseCategory.TRANSPORTATION,20, budg.getExpenseList().getExpenses().get(1));
            checkExpense(ExpenseCategory.EATING_OUT,30, budg.getExpenseList().getExpenses().get(2));
            checkBudget(800,740,budg);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
