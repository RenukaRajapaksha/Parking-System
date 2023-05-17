package Controller;

import Db.Database;
import Model.Deliver;
import Model.Park;
import View.tm.DeliverTM;
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

public class OnDeliveryFormController {
    public AnchorPane OnDeliveryContext;
    public Button cmbAddVehicle;
    public ComboBox cmbOnDelivery;
    public Button cmbAddDriver;
    public TableView tblOnDelivery;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;

    public void initialize(){

        loadData();

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));

        cmbOnDelivery.getItems().add("InParking");
        cmbOnDelivery.getItems().add("OnDelivery");

        cmbOnDelivery.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            String text = String.valueOf((newValue));
            if(text.equals("InParking")){
                Stage stage1= (Stage) OnDeliveryContext.getScene().getWindow();

                try{
                    stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InParkingForm.fxml"))));
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void loadData() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Deliver d: Database.deliverTable) {
            DeliverTM tm= new DeliverTM(d.getVehicleNo(),d.getVehicleType(), d.getDriverName(),d.getLeftTime());
            obList.add(tm);
        }
        tblOnDelivery.setItems(obList);
    }

    public void AddVehicleOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/AddVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void AddDriverOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/AddDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
