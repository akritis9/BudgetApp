package ui;

import model.Budget;
import model.Expense;
import model.ExpenseCategory;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import java.util.Scanner;

// Smart Budget application
public class BudgetApp {
    private static final String JSON_STORE = "./data/savedbudget.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner input;
    private Budget newBudget;

    // EFFECTS: runs the budget application
    // CODE CREDIT: TellerApp
    public BudgetApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBudget();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    // CODE CREDIT: TellerApp
    private void runBudget() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // CODE CREDIT: TellerApp
    private void processCommand(String command) {
        if (command.equals("b")) {
            doSetBudget();
        } else if (command.equals("e")) {
            doLogExpense();
        } else if (command.equals("v")) {
            doViewListOfExpenses();
        } else if (command.equals("x")) {
            doViewExpenditure();
        } else if (command.equals("c")) {
            doViewExpenditureByCategory();
        } else if (command.equals("m")) {
            doViewMoneyToSpend();
        } else if (command.equals("s")) {
            saveBudget();
        } else if (command.equals("l")) {
            loadBudget();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    // // CODE CREDIT: TellerApp
    private void displayMenu() {
        System.out.println("\nWelcome to your personal budgeting app!");
        System.out.println("\nStart budgeting by selecting from the following options");
        System.out.println("\tEnter b -> To set a new budget");
        System.out.println("\tEnter e -> To log an expense");
        System.out.println("\tEnter v -> To view all your expenses");
        System.out.println("\tEnter x -> To view your total expenditure");
        System.out.println("\tEnter c -> To view total expenditure for all categories");
        System.out.println("\tEnter m -> To view how much money you have left to spend");
        System.out.println("\tEnter s -> To save Budget to file");
        System.out.println("\tEnter l -> load Budget from file");
        System.out.println("\tEnter q -> To quit the application");
    }

    // MODIFIES: this
    // EFFECTS: initializes all objects
    // CODE CREDIT: TellerApp
    private void init() {
        newBudget = new Budget();
        input = new Scanner(System.in);
    }


    // MODIFIES: this
    // EFFECTS:allows user to set a budget and sets budget
    // CODE CREDIT: TellerApp
    public void doSetBudget() {
        init();
        System.out.println("Please enter a budget amount: ");
        double amount = input.nextDouble();
        newBudget.setBudgetAmount(amount);
        System.out.println("You now have $" + amount + " to spend");
    }


//    // MODIFIES: this and ExpenseList
//    // EFFECTS:allows user to log expense and adds expense to expenseList
//    // CODE CREDIT: TellerApp
//    public void doLogExpense() {
//        System.out.println("Please enter your expense amount: ");
//        Expense expAmount = new Expense(input.nextDouble());
//        expenseList.addExpense(expAmount);
//        double newMoneyToSpend = newBudget.getMoneyToSpend() - expAmount.getExpense();
//        newBudget.setMoneyToSpend(newMoneyToSpend);
//        System.out.println("You now have $" + newBudget.getMoneyToSpend() + " left to spend");
//    }

    // MODIFIES: this and ExpenseList
    // EFFECTS:allows user to log expense and adds expense to expenseList
    // CODE CREDIT: TellerApp
    /*public void doLogExpense() {
        System.out.println("Please select an expense category: ");
        System.out.println("\tEnter g -> For Groceries");
        System.out.println("\tEnter t -> For Transportation");
        System.out.println("\tEnter f -> For Eating Out");
        System.out.println("\tEnter c -> For Entertainment");
        System.out.println("\tEnter l -> For Bills");

            System.out.println("Please enter your expense amount: ");
            newBudget.logExpense(input.nextDouble(),expensecategory);
            System.out.println("You now have $" + newBudget.getMoneyToSpend() + " left to spend");}
    }*/

    private void doLogExpense() {
        ExpenseCategory category = readCategory();
        System.out.println("Please enter your expense amount: ");
        double expamount = input.nextDouble();
        newBudget.logExpense(expamount, category);
        System.out.println("You now have $" + newBudget.getMoneyToSpend() + " left to spend");
    }

    // EFFECTS: prompts user to select category and returns it
    private ExpenseCategory readCategory() {
        System.out.println("Please select a category for your expense");

        int menuLabel = 1;
        for (ExpenseCategory c : ExpenseCategory.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        return ExpenseCategory.values()[menuSelection - 1];
    }

    // EFFECTS: prints all the expenses to the console
    public void doViewListOfExpenses() {
        System.out.println("All your expenses are listed below: ");
        List<Expense> listOfExpense = new ArrayList<Expense>();
        listOfExpense = newBudget.getExpenseList().getExpenses();
        for (Expense e : listOfExpense) {
            System.out.println(e.getExpenseCategory() + ": " + "$" + e.getExpense());
        }
    }

    // EFFECTS: prints all the expenses to the console
    public void doViewExpenditureByCategory() {
        ExpenseList listOfExpense = newBudget.getExpenseList();
        for (ExpenseCategory c : ExpenseCategory.values()) {
            System.out.println(c + ": " + listOfExpense.viewExpenseByCategory(c));
        }
    }


    // EFFECTS: allows user to view total expenditure
    // CODE CREDIT: TellerApp
    public void doViewExpenditure() {
        System.out.println("You have spent a total of $" + newBudget.getExpenseList().viewExpenses() + " so far");
    }

    // EFFECTS: allows user to view total money available to spend
    // CODE CREDIT: TellerApp
    public void doViewMoneyToSpend() {
        System.out.println("You have $" + newBudget.getMoneyToSpend() + " left to spend");
    }

    // EFFECTS: saves the Budget to file
    private void saveBudget() {
        try {
            jsonWriter.open();
            jsonWriter.write(newBudget);
            jsonWriter.close();
            System.out.println("Saved " + newBudget.getExpenseList().getExpenses() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loadsBudget from file
    private void loadBudget() {
        try {
            newBudget = jsonReader.readBudget();
            System.out.println("Loaded " + newBudget.getBudgetAmount() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    public Budget getNewBudget() {
//        return newBudget;
//    }
}
