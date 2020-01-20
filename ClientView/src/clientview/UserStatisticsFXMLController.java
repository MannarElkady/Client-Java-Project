/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientview;

import Model.entities.TodoEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class UserStatisticsFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vBoxChart;
    @FXML
    private Label numberOfUsers;
    int createdTodoNumber, assignedTodoNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numberOfUsers.setText("" + MainXMLController.test2.size());
        initializeChart();

    }

    public void initializeChart() {
        if (MainXMLController.data !=null) {
            createdTodoNumber = 0;
            assignedTodoNumber = 0;
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
            bc.setTitle("Todo Statistics");
            System.out.println("ddddddddddddddddddddddddddddddddddddd");
            for (int i = 0; i < MainXMLController.data.size(); i++) {
                TodoEntity todo = null;
                todo = (TodoEntity) MainXMLController.data.get(i);
                if (todo.getCreatorId() == ClientView.currentUser.getId()) {
                    createdTodoNumber++;
                } else {
                    assignedTodoNumber++;
                }

            }
            XYChart.Series allTodO = new XYChart.Series();
            allTodO.setName("AllTodos");
            allTodO.getData().add(new XYChart.Data("  ", MainXMLController.data.size()));

            XYChart.Series createdTodo = new XYChart.Series();
            createdTodo.setName("Created Todo");
            createdTodo.getData().add(new XYChart.Data("  ", createdTodoNumber));

            XYChart.Series assignedTodo = new XYChart.Series();
            assignedTodo.setName("Assign Todo");
            assignedTodo.getData().add(new XYChart.Data("  ", assignedTodoNumber));
            bc.getData().addAll(allTodO, createdTodo, assignedTodo);
            vBoxChart.getChildren().add(bc);
        }

    }
}
