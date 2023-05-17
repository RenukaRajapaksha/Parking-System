package Controller;

import Db.Database;
import Model.Vehicle;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddVehicleFormController {
    public AnchorPane AddVehicleContext;
    public TextField txtVehicleNumber;
    public TextField txtMaximumWeight;
    public TextField txtNoOfPassengers;
    public ComboBox cmbVehicleType;

    public void initialize(){
        cmbVehicleType.getItems().add("Bus");
        cmbVehicleType.getItems().add("Van");
        cmbVehicleType.getItems().add("Cargo Lorry");

    }
    public void SetAddVehicleOnAction(ActionEvent actionEvent) {
        Database.vehicleTable.add
               (new Vehicle(txtVehicleNumber.getText(),(String)cmbVehicleType.getValue(),Double.parseDouble(txtMaximumWeight.getText()),
                       Integer.parseInt(txtNoOfPassengers.getText())));
        clear();
    }
    public void clear(){
        txtVehicleNumber.clear();
        txtMaximumWeight.clear();
        txtNoOfPassengers.clear();
        cmbVehicleType.getSelectionModel().clearSelection();
    }
}
