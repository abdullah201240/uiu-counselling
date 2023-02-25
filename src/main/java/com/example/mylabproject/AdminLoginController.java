package com.example.mylabproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class AdminLoginController {

    @FXML
    private Button backbt;

    @FXML
    private Button forgetpassbt;

    @FXML
    private Button loginbt;

    @FXML
    private PasswordField passtf;

    @FXML
    private TextField usertf;

    @FXML
    void backBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void forgetpassBt(ActionEvent event) {

    }

    @FXML
    void loginBt(ActionEvent event) throws IOException {


        if(usertf.getText().equals("admin")  && passtf.getText().equals("1234") ) {


            Parent root = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else{
            Window owner = loginbt.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter correct Username and Password.");

        }

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}

