package Controller;

import Db.Database;
import Model.Driver;
import Model.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class AddDriverFormController {
    public AnchorPane AddDriverContext;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtLicenseNo;
    public TextField txtAddress;
    public TextField txtContactNo;
    public Button btnAddDriver;

    public void initilalize(){

    }

    public void AddDriverOnAction(ActionEvent actionEvent) {

        Database.driverTable.add
                (new Driver(txtName.getText(), (String) txtNIC.getText(),(String)txtLicenseNo.getText(),
                        (String) txtContactNo.getText(),(String) txtAddress.getText()));
        clear ();
    }
    public void clear(){
        txtName.clear();
        txtNIC.clear();
        txtLicenseNo.clear();
        txtAddress.clear();
        txtContactNo.clear();

    }


}
