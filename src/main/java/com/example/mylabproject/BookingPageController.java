package com.example.mylabproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.mylabproject.StudentFacultyProfileController.dayz;
import static com.example.mylabproject.StudentFacultyProfileController.formattedDate;
import static com.example.mylabproject.StudentFacultyProfileController.namez;
import static com.example.mylabproject.StudentFacultyProfileController.room;
import static com.example.mylabproject.StudentFacultyProfileController.idz;
import static com.example.mylabproject.StudentLoginController.sname;
import static com.example.mylabproject.StudentLoginController.sid1;


public class BookingPageController implements Initializable {
    public static int s;
    public  static String fname;
    public  static String fid;
    public  static String start;
    public  static String end;



    @FXML
    private TableColumn<freetimesolt, String> fday;

    @FXML
    private TableColumn<freetimesolt, String> fend;

    @FXML
    private TableView<freetimesolt> freetimetable;

    @FXML
    private TableColumn<freetimesolt, String> fstart;

    @FXML
    private TextField datetf;

    @FXML
    private TextField nametf;

    @FXML
    private TextField roomtf;
    @FXML
    private TableColumn<freetimesolt, Integer> id;
    @FXML
    private Button addbt;

    @FXML
    void backbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentFacultyProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    ObservableList<freetimesolt> list1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datetf.setText(formattedDate);
        roomtf.setText(room);
        nametf.setText(namez);

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="SELECT * FROM time_slot WHERE fid=? and day=? and  NOT EXISTS(SELECT * from booking WHERE booking.date=? and time_slot.id=booking.id);";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, idz);
            pst.setString(2, dayz);
            pst.setString(3, formattedDate);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                list1.add(new freetimesolt(rs.getString("day"),rs.getString("start"),rs.getString("end"),rs.getInt(1)));
            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }
        fday.setCellValueFactory(new PropertyValueFactory<freetimesolt,String>("fday"));
        fstart.setCellValueFactory(new PropertyValueFactory<freetimesolt,String>("fstart"));
        fend.setCellValueFactory(new PropertyValueFactory<freetimesolt,String>("fend"));
        id.setCellValueFactory(new PropertyValueFactory<freetimesolt,Integer>("id"));


        freetimetable.setItems(list1);

        freetimetable.setOnMouseClicked(e->{


                    events();
                }

        );






    }
    private void events(){
        for(freetimesolt solt  : freetimetable.getSelectionModel().getSelectedItems()){
            for(int i=1;i<=1;i++)
                s= solt.getId();
            start=solt.fstart;
            end=solt.fend;




        }

    }
    @FXML
    void add(ActionEvent event) throws ClassNotFoundException, SQLException {
//        System.out.println(idz);
//        System.out.println(namez);
//        System.out.println(sid1);
//        System.out.println(sname);
//        System.out.println(formattedDate);
//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(dayz);
//        System.out.println("Pending");
//        System.out.println(s);


        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String  sql="INSERT INTO `booking`(`tid`, `tname`, `sid`, `sname`, `date`, `stime`, `endtime`, `day`, `status`, `id`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,idz);
        ps.setString(2,namez);
        ps.setString(3, sid1);
        ps.setString(4, sname);
        ps.setString(5, formattedDate);
        ps.setString(6, start);
        ps.setString(7, end);
        ps.setString(8, dayz);
        ps.setString(9, "Pending");
        ps.setInt(10,s);
        ps.executeUpdate();

        conn.close();
        Window owner = addbt.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!!!", "Added Successfully.");




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
