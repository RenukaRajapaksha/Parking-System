package View.tm;

public class VehicleTM {
    private String vehicleNo;
    private String vehicleType;
    private double maxWeight;
    private int passengers;


    public VehicleTM(String vehicleNo) {
        this.vehicleNo=vehicleNo;

    }

    public VehicleTM(String vehicleNo, String vehicleType, double maxWeight, int passengers) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.passengers = passengers;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return vehicleNo;


    }
}
