package clientview;

import Model.dao.implementation.ComponentDBOperations;
import Model.entities.ComponentEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class ItemTasksFXMLController implements Initializable, ChangeListener<Boolean>{ 
    
    @FXML
    private BorderPane rootBorderPane;
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
    private JFXListView tasksListView;
    @FXML
    private JFXButton finishBtn;
    @FXML
    private JFXButton cancelBtn;
    
    private final String COMPONENT_TYPE_NOTE = "noteComponent";
    private final String COMPONENT_TYPE_CHECKBOX = "checkboxComponent";
    
    private double xOffset;
    private double yOffset;
    ArrayList<String> notesList;
    ArrayList<CheckBox> tasksList;
    ArrayList<CheckBox> newTasksList;
    private static ArrayList<Object> componentsList;
    private ArrayList<ComponentEntity> modifiedList;
    private boolean isChanged = false;
    private int newComponentsCounter=-1;
    int checkedCheckBoxesCounter=0;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notesList = new ArrayList<>();
        tasksList = new ArrayList<>();
        newTasksList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        showComponents(); 
    }    
    
    private void updateProgressBar(){
        for(int i=0;i<tasksList.size();i++){
            if(tasksList.get(i).isSelected()){
                checkedCheckBoxesCounter++;
            }
        }
        for(int i=0;i<newTasksList.size();i++){
            if(newTasksList.get(i).isSelected()){
                checkedCheckBoxesCounter++;
            }
        }
        prograssbar.setProgress(checkedCheckBoxesCounter*1.0/(tasksList.size()+newTasksList.size()));
        checkedCheckBoxesCounter=0;
    }
    
    @FXML
    private void addNotesAction() {
        String data = notesTextField.getText();
        if(!data.isEmpty()){
            isChanged = true;
            data += "  ,  added by "+ClientView.currentUser.getUsername();
            ComponentEntity noteComponent = new ComponentEntity(TodoFormXMLController.itemSelected.getItemID(),COMPONENT_TYPE_NOTE, data);
            Label noteLabel = new Label(data);
            noteLabel.setWrapText(true);
            notesListView.getItems().add(noteLabel);
            notesList.add(data);
            componentsList.add(noteComponent);
            notesTextField.clear();            
        }
    }

    @FXML
    private void addTasksAction() {
        String data = tasksTextField.getText();
        if(!data.isEmpty()){
            isChanged = true;
            CheckBox taskCheckBox = new CheckBox(data);
            taskCheckBox.setId("" + newComponentsCounter--);
            taskCheckBox.selectedProperty().addListener(this);
            tasksListView.getItems().add(taskCheckBox);
            newTasksList.add(taskCheckBox);
            tasksTextField.clear();
            updateProgressBar();
        }
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        isChanged = true;
        updateProgressBar();
    }

    @FXML
    private void finishBtnAction() {
        pushToDatabase();
        updateDatabase();
        ((Stage) finishBtn.getScene().getWindow()).close();
    }

    @FXML
    private void cancelBtnAction() {
        if(isChanged){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setContentText("Do you want to save changes?");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No");
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonYes) {
                pushToDatabase();
                updateDatabase();
                ((Stage) cancelBtn.getScene().getWindow()).close();
            }else if(result.get() == buttonNo){
                ((Stage) cancelBtn.getScene().getWindow()).close();
            }
        }else{
            ((Stage) cancelBtn.getScene().getWindow()).close();
        }
    }
    
    private void pushToDatabase(){
        for(int i = 0 ; i < newTasksList.size() ;i++){
            ComponentEntity component = new ComponentEntity(TodoFormXMLController.itemSelected.getItemID(), COMPONENT_TYPE_CHECKBOX, newTasksList.get(i).getText(), newTasksList.get(i).isSelected()? 1 : 0);
            componentsList.add(component);
        }
        ComponentDBOperations.addComponent(componentsList);
        componentsList.clear();
//        ((Stage) finishBtn.getScene().getWindow()).close();
    }
    
    public static void setComponentList(ArrayList<Object> components){
        componentsList = components;
    }
    
    @FXML
    private void handleDragEvent(MouseEvent event){
        //((Stage) rootBorderPane.getScene().getWindow()).setX(event.getScreenX() + xOffset);
        //((Stage) rootBorderPane.getScene().getWindow()).setX(event.getScreenY() + yOffset);
    }
    
    @FXML 
    private void handleMousePressedEvent(MouseEvent event){
        //xOffset = event.getSceneX();
        //yOffset = event.getSceneY();
    }

    private void showComponents() {
        if(componentsList!=null){
            for(int i = 0 ;i<componentsList.size();i++){
                ComponentEntity component = (ComponentEntity) componentsList.get(i);
                if(component.getComponentType().equals(COMPONENT_TYPE_NOTE)){
                    Label noteLabel = new Label(component.getComponentText());
                    noteLabel.setWrapText(true);
                    notesListView.getItems().add(noteLabel);
                }else{
                    CheckBox taskCheckBox = new CheckBox(component.getComponentText());
                    taskCheckBox.setId(""+component.getComponentId());
                    taskCheckBox.setSelected((component.getFinishedFlag() != 0));
                    taskCheckBox.selectedProperty().addListener(this);
                    tasksListView.getItems().add(taskCheckBox);
                    tasksList.add(taskCheckBox);
                    updateProgressBar();
                }
            }
            componentsList.clear();
        }else componentsList = new ArrayList<>();
    }

    private void updateDatabase() {
        for(int i = 0 ; i < tasksList.size() ;i++){
            ComponentEntity component = new ComponentEntity(Integer.parseInt(tasksList.get(i).getId()), TodoFormXMLController.itemSelected.getItemID(),COMPONENT_TYPE_CHECKBOX, tasksList.get(i).getText(), tasksList.get(i).isSelected()? 1 : 0); 
            modifiedList.add(component);
        }
        ComponentDBOperations.updateComponent(modifiedList);
    }
}
