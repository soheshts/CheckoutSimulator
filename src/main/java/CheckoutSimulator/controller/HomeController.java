package CheckoutSimulator.controller;

import CheckoutSimulator.models.SharedResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Spinner totalNoOfCheckout;
    @FXML
    private Spinner totalNoOfExpressCheckout;
    @FXML
    private Spinner simulationTime;
    @FXML
    private Button button;


    public void initialize(URL location, ResourceBundle resources) {
//        SpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8,1);
        totalNoOfCheckout.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8,1));
        totalNoOfExpressCheckout.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8,1));
        ObservableList<Integer> simulationTimes = FXCollections.observableArrayList(30,60,120,180,240,300);
        simulationTime.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory(simulationTimes));
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SharedResource.totalCheckouts = Integer.parseInt(totalNoOfCheckout.getValue().toString());
                SharedResource.totalExpressCheckouts= Integer.parseInt(totalNoOfExpressCheckout.getValue().toString());
                SharedResource.simulationTime = Integer.parseInt(simulationTime.getValue().toString());
                new CheckOutCounterController().start();
            }
        });
    }
}
