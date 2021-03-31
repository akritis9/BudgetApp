package persistence;

import model.Budget;
import model.Expense;
import model.ExpenseCategory;
import model.ExpenseList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads ExpenseList and Budget from JSON data stored in file
//CODE CREDIT: All code in this class is based on the JsonSerialization app
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads Budget from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Budget readBudget() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudget(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ExpenseList from JSON object and returns it
    private ExpenseList parseExpenseList(JSONObject jsonObject) {
        ExpenseList expl = new ExpenseList();
        addExpenses(expl, jsonObject);
        return expl;
    }

    // EFFECTS: parses Budget from JSON object and returns it
    private Budget parseBudget(JSONObject jsonObject) {
        Budget budg = new Budget();
        budg.setBudgetAmount(jsonObject.getDouble("Budget"));
        budg.setMoneyToSpend(jsonObject.getDouble("Balance"));
        ExpenseList readExpenseList = parseExpenseList(jsonObject);
        budg.setExpenseList(readExpenseList);
        return budg;
    }

    // MODIFIES: expenselist
    // EFFECTS: parses expenses from JSON object and adds them to expenselist
    private void addExpenses(ExpenseList exp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(exp, nextExpense);
        }
    }

    // MODIFIES: expenselist
    // EFFECTS: parses expense from JSON object and adds it to expenselist
    private void addExpense(ExpenseList expl, JSONObject jsonObject) {
        double expcost = jsonObject.getDouble("Expense Amount");
        ExpenseCategory expcategory = ExpenseCategory.valueOf(jsonObject.getString("Category"));
        Expense exp = new Expense(expcost,expcategory);
        expl.addExpense(exp);
    }
}

