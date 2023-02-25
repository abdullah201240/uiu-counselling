package com.example.mylabproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.mylabproject.StudentSearchFacultyController.s;
import static com.example.mylabproject.StudentHomeController.*;

public class StudentFacultyProfileController implements Initializable  {
public static String formattedDate;
    public static String namez;
    public static String idz;

    public static String room;
    public static String dayz;
    @FXML
    private DatePicker DataPicer;
    @FXML
    private TextField counselling_days;

    @FXML
    private Button bookbt;

    @FXML
    private TextField depttf;

    @FXML
    private TextField emailtf;

    @FXML
    private TextField nametf;

    @FXML
    private TextField roomtf;
    @FXML
    void backbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentSearchFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void bookBt(ActionEvent event) throws IOException {
        LocalDate i = DataPicer.getValue();
        java.time.DayOfWeek dayOfWeek = i.getDayOfWeek();

        dayz=dayOfWeek.toString();
        formattedDate = i.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));


        Parent root = FXMLLoader.load(getClass().getResource("BookingPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="Select * from teacher where id = ?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                idz=rs.getString(1);
                namez=rs.getString(2);
                room=rs.getString(5);


                nametf.setText(rs.getString(2));
                emailtf.setText(rs.getString(3));
                roomtf.setText(rs.getString(5));
                depttf.setText(rs.getString(6));
            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }




        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="Select DISTINCT(day), count from time_slot where fid =? ORDER by (count)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();




            while(rs.next()){
                counselling_days.appendText(rs.getString(1));
                counselling_days.appendText(" , ");


            }



            conn.close();
            rs.close();
        }
        catch(Exception e){


        }

    }
}
