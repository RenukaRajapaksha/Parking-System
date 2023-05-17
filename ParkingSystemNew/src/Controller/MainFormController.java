package Controller;


import Db.Database;
import Model.*;
import View.tm.DriverTM;
import View.tm.VehicleTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.security.mscapi.CPublicKey;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;


public class MainFormController {


    public AnchorPane ParkingSystemContext;
    public Text txtSelectVehicle;
   // public Text txtVehicleType;
    public Text txtDriver;
    public ComboBox cmbSelectVehicle;
    public ComboBox cmbDriver;
    public Text txtSlot;
    public Text txtSlotNumber;
    public Button btnParkVehicle;
    public Button btnOnDeliveryShift;
    public Button btnManagementLogin;
    public TextField txtType;
    public Label lblDateTime;
    public Vehicle selectVehicle;
    public Label lblSlot;
    boolean b= false;
    boolean d= false;


    public void initialize() {
        setDateTime();
        loadData();
        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           getVehicle(newValue);
            txtType.setText(selectVehicle.getVehicleType());
            getSlot();

        });

    }

    private void viewSlot(String vehicleType) {
        for (int i=0; i<Database.slotTable.size(); i++){
            for (int j=0; j<Database.slotTable.size(); j++){
                if (vehicleType.equals(Database.slotTable.get(i).getVehicleType()) && Database.slotTable.get(i).getStatus().equals("notUse")) {
                    lblSlot.setText(Database.slotTable.get(i).getSlotNo());
                    return;
                }
            }
        }

    }

    private void loadData() {
        ObservableList<VehicleTM> observableList = FXCollections.observableArrayList();
        for (Vehicle v: Database.vehicleTable) {
            VehicleTM tm = new VehicleTM(v.getVehicleNo());
            observableList.add(tm);
        }

        ObservableList<DriverTM> observableList2 = FXCollections.observableArrayList();
        for (Driver d: Database.driverTable) {
            DriverTM tm = new DriverTM(d.getName());
            observableList2.add(tm);
        }
        cmbSelectVehicle.setItems(observableList);
        cmbDriver.setItems(observableList2);
    }

    private void setDateTime() {
        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            lblDateTime.setText(currentDate.getYear()+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth()+"    "+
                    currentTime.getHour() + ":" + currentTime.getMinute() + ":"+ currentTime.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void getSlot() {
        switch (selectVehicle.getVehicleType()){
            case "Van" : {
                viewSlot("Van");
            }break;
            case "Cargo Lorry" : {
                viewSlot("Cargo Lorry");
            }break;
            case "Bus" : {
                viewSlot("Bus");
            }break;
        }
    }

    private void getVehicle(Object newValue) {
        int i=-1;

        for (Vehicle v: Database.vehicleTable
        ) {
            i++;
            if(v.getVehicleNo().equals(String.valueOf(newValue) )){
                selectVehicle = Database.vehicleTable.get(i);
            }

        }
    }

    public void ParkVehicleOnAction(ActionEvent actionEvent) {

        for(int k=0;k<Database.slotTable.size();k++){
            if( lblSlot.getText().equals(Database.slotTable.get(k).getSlotNo())){
                Database.slotTable.get(k).setStatus("Use");
            }
        }
        Parking();


    }

    private void Parking() {
        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            btnParkVehicle.setDisable(false);
            String temp=String.valueOf(newValue);
            for (int i=0;i<Database.parkInTable.size();i++){
                b= Database.parkInTable.get(i).getVehicleNo().contains(temp);
                if(b==true){
                    btnParkVehicle.setDisable(true);

                }
            }
        });
        if(b==false){
            if(cmbSelectVehicle.getValue()!=null){
                String data= (String.valueOf(cmbSelectVehicle.getValue())) ;
                Park p=new Park(String.valueOf(cmbSelectVehicle.getValue()),txtType.getText(),lblSlot.getText(),lblDateTime.getText());
                Database.parkInTable.add(p);

                for(int i=0;i<Database.deliverTable.size();i++){
                    if(Database.deliverTable.get(i).getVehicleNo().contains(data)){
                        Database.deliverTable.remove(i);
                    }
                }
            }
        }
        clear();
    }

    private void clear() {

        txtType.clear();
        cmbSelectVehicle.getSelectionModel().clearSelection();
        cmbDriver.getSelectionModel().clearSelection();
    }


    public void OnDeliveryShiftOnAction(ActionEvent actionEvent) {


        try{
            DeliveryVehicle();
            for(int k=0;k<Database.deliverTable.size();k++){
                if(cmbSelectVehicle.getValue().equals(Database.parkInTable.get(k).getVehicleNo())){
                    setnotuse(String.valueOf(Database.parkInTable.get(k).getParkingSlot()));
                }
            }

        }catch(Throwable e){
            System.out.println(e);
        }

    }


    private void DeliveryVehicle() {
        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String temp2=String.valueOf(newValue);
            btnOnDeliveryShift.setDisable(false);

            for (int i=0;i<Database.deliverTable.size();i++){
                d= Database.deliverTable.get(i).getVehicleNo().contains(temp2);

                if(d==false){
                    btnOnDeliveryShift.setDisable(true);

                }
            }
        });

        if(d==false){

            if(cmbSelectVehicle.getValue()!=null && cmbSelectVehicle.getValue()!=null){
                String data= (String.valueOf(cmbSelectVehicle.getValue())) ;
                for(int i=0;i<Database.parkInTable.size();i++){
                    if(Database.parkInTable.get(i).getVehicleNo().contains(data)){
                        Database.parkInTable.remove(i);
                    }
                }
                Deliver d=new Deliver(String.valueOf(cmbSelectVehicle.getValue()),txtType.getText(),String.valueOf(cmbSelectVehicle.getValue()),lblDateTime.getText());
                Database.deliverTable.add(d);
            }
        }

    }

    private void setnotuse(String slotnmbr) {
        for (int i=0; i<Database.slotTable.size(); i++){
            if (Database.slotTable.get(i).getSlotNo().equals(slotnmbr)){
                Database.slotTable.get(i).setStatus("notUse");
            }
        }
    }


    public void MLoginOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
