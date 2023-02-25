package com.example.mylabproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class FirstPageController {



    @FXML
    void admin(ActionEvent event) throws IOException {



        System.out.println("Admin button clicked.\n");
        Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void faculty(ActionEvent event) throws IOException{


        System.out.println("Faculty button clicked.\n");
        Parent root = FXMLLoader.load(getClass().getResource("facultyLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void student(ActionEvent event) throws IOException{
        System.out.println("Student button clicked.\n");
        Parent root = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
