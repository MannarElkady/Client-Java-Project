/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.dao.implementation.ComponentDBOperations;
import Model.entities.ComponentEntity;
import Model.entities.ItemEntity;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class TodoStatisticsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label ItemNumber;
    @FXML
    private Label CollaboratorNumber;
    @FXML
    private Label TasksNumber;
    @FXML
    private VBox statistcs;
    public static ArrayList<Object> componentList;
    ItemEntity e;
    int counter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CollaboratorNumber.setText("" + TodoFormXMLController.todoCollaborators.size());
        ItemNumber.setText("" + TodoFormXMLController.itemList.size());
        TasksNumber.setText(""+componentList.size());
        createChart();

    }

    public static void setComponentList(ArrayList<Object> components) {
        System.out.println("ccccccccccc" + components.size());
        componentList = new ArrayList();
        componentList = components;

    }

    public void createChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("ITEMS");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");

        // Create a StackedBarChart
        StackedBarChart<String, Number> barChart = new StackedBarChart<String, Number>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("Tasks");
        for (int i = 0; i < TodoFormXMLController.itemList.size(); i++) {
            counter = 0;
            e = new ItemEntity();
            e = (ItemEntity) TodoFormXMLController.itemList.get(i);
            System.out.println("itemname" + e.getTitle());
            System.out.println("ddd" + componentList);
            for (int j = 0; j < componentList.size(); j++) {
                ComponentEntity c = new ComponentEntity();
                c = (ComponentEntity) componentList.get(j);
                if (c.getItemId() == e.getItemID()) {
                    counter++;
                }
            }
            System.out.println("count" + counter);
            dataSeries2.getData().add(new XYChart.Data<String, Number>(e.getTitle(), counter));

            // Add Series to StackedBarChart.
        }
        barChart.getData().add(dataSeries2);
        statistcs.getChildren().add(barChart);

    }
}
