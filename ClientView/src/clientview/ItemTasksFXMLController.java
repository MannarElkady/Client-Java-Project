package clientview;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class ItemTasksFXMLController implements Initializable, ChangeListener<Boolean> { 
    
    @FXML
    private ProgressBar prograssbar;
    @FXML
    private JFXTextField notesTextField;
    @FXML
    private JFXButton addNotes;
    @FXML
    private JFXListView notesListView;
    @FXML
    private JFXTextField tasksTextField;
    @FXML
    private JFXButton addTasks;
    @FXML
    private VBox tasksVBox;
    
    static int id =0;
    ArrayList<String> notesList;
    ArrayList<CheckBox> tasksList;
    
    int checkedCheckBoxesCounter=0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notesList = new ArrayList<>();
        tasksList = new ArrayList<>();
    }    

    private void updateProgressBar(){
        for(int i=0;i<tasksList.size();i++){
            if(tasksList.get(i).isSelected()){
                checkedCheckBoxesCounter++;
            }
        }
        prograssbar.setProgress(checkedCheckBoxesCounter*1.0/tasksList.size());
        checkedCheckBoxesCounter=0;
    }
    
    @FXML
    private void addNotesAction(ActionEvent event) {
        String data = notesTextField.getText();
        if(!data.isEmpty()){
            data += "  ,  added by "+ClientView.currentUser.getUsername();
            Label noteLabel = new Label(data);
            noteLabel.setWrapText(true);
            notesListView.getItems().add(noteLabel);
            notesList.add(data);
            notesTextField.clear();            
        }
    }

    @FXML
    private void addTasksAction(ActionEvent event) {
        String data = tasksTextField.getText();
        if(!data.isEmpty()){
            CheckBox taskCheckBox = new CheckBox(data);
            taskCheckBox.setId(""+(id++));
            taskCheckBox.selectedProperty().addListener(this);
            tasksVBox.getChildren().add(taskCheckBox);
            tasksList.add(taskCheckBox);
            tasksTextField.clear();
            updateProgressBar();
        }
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        updateProgressBar();
    }
    
}
