package ui;

import javax.swing.*;
import model.ExpenseCategory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.text.DecimalFormat;

public class PieChart extends JFrame {

    private Gui controller;

    public PieChart(Gui controller) {
        this.controller = controller;

        PieDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createPieChart("Expenditure by Category",dataset,
                true,true,false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }


    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Groceries", this.controller.getCurrentBudget().getExpenseList()
                .viewExpenseByCategory(ExpenseCategory.GROCERIES));
        dataset.setValue("Transportation", this.controller.getCurrentBudget().getExpenseList()
                .viewExpenseByCategory(ExpenseCategory.TRANSPORTATION));
        dataset.setValue("Eating Out", this.controller.getCurrentBudget().getExpenseList()
                .viewExpenseByCategory(ExpenseCategory.EATING_OUT));
        dataset.setValue("Entertainment", this.controller.getCurrentBudget().getExpenseList()
                .viewExpenseByCategory(ExpenseCategory.ENTERTAINMENT));
        dataset.setValue("Bills",this.controller.getCurrentBudget().getExpenseList()
                .viewExpenseByCategory(ExpenseCategory.BILLS));
        return dataset;
    }

}
