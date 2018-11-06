package CheckoutSimulator.controller;

import CheckoutSimulator.models.SharedResource;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    Label totalDuration;
    @FXML
    Label noOfCheckouts;
    @FXML
    Label noOfProductsProcessed;
    @FXML
    Label avgCustWaitTime;
    @FXML
    Label avgCheckoutUtilisation;
    @FXML
    Label avgProductsPerTrolley;
    @FXML
    Label noOfLostCust;

    public void initialize(URL location, ResourceBundle resources) {
        totalDuration.setText(""+SharedResource.simulationTime);
        noOfCheckouts.setText(String.valueOf(SharedResource.totalCheckouts));
        noOfProductsProcessed.setText(String.valueOf(SharedResource.totalProductsProcessed));
        avgCustWaitTime.setText(String.valueOf(SharedResource.simulationTime/(SharedResource.totalCustomersProcessed==0?1:SharedResource.totalCustomersProcessed)));
        avgCheckoutUtilisation.setText("00");
        avgProductsPerTrolley.setText(String.valueOf(SharedResource.totalProducts/(SharedResource.totalCustomers==0?1:SharedResource.totalCustomers)));
        noOfLostCust.setText(String.valueOf(SharedResource.leftCustomers));

    }
}
