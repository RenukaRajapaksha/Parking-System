package Controller;

import Db.Database;
import Model.Park;
import View.tm.ParkTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class InParkingFormController {
    public AnchorPane InParkingContext;
    public ComboBox cmbInParking;
    public Button btnAddVehicle;
    public Button btnLogOut;
    public Button btnAddDriver;
    public TableView tblInParking;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;


    public void initialize(){

        loadData();

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("parkedTime"));

        cmbInParking.getItems().add("InParking");
        cmbInParking.getItems().add("OnDelivery");

        cmbInParking.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            String text = String.valueOf((newValue));
            if(text.equals("OnDelivery")){
                Stage stage1= (Stage) InParkingContext.getScene().getWindow();

                try{
                    stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OnDeliveryForm.fxml"))));
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    private void loadData() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Park p: Database.parkInTable) {
            ParkTM tm= new ParkTM(p.getVehicleNo(),p.getVehicleType(), p.getParkingSlot(),p.getParkedTime());
            obList.add(tm);
        }
        tblInParking.setItems(obList);
    }

    public void SetAddVehicleOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/AddVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage1= (Stage) InParkingContext.getScene().getWindow();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/MainForm.fxml"))));

    }

    public void SetAddDriverOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/AddDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
