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

import static com.example.mylabproject.StudentLoginController.sid1;


public class StudentHomeController implements Initializable {

    public static String c_name, sec;

    public static String search;
    public static String z;

    @FXML
    private Button Profilebt;

    @FXML
    private Button searchbt;

    @FXML
    private TextField searchtf;
    @FXML
    private TableColumn<studenthomeshow, String> date;
    @FXML
    private TableColumn<studenthomeshow, String> endtime;

    @FXML
    private TableColumn<studenthomeshow, String> faname;
    @FXML
    private TableColumn<studenthomeshow, String> status;

    @FXML
    private TableColumn<studenthomeshow, String> stime;

    @FXML
    private TableView<studenthomeshow> studenthometable;
    @FXML
    private TableColumn<homepinfaculty, String> coursename;
    @FXML
    private TableView<homepinfaculty> pinfaculty;
    @FXML
    private TableColumn<homepinfaculty, String> section;
    @FXML
    private TableColumn<homepinfaculty, String> tname;
    @FXML
    private Button searchbt1;
    @FXML
    private TextField searchtf1;
    @FXML
    private Button removebt;




    @FXML
    void searchBt1(ActionEvent event) throws IOException {
         z=searchtf1.getText();


        Parent root = FXMLLoader.load(getClass().getResource("StudentPinFaulty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();




    }

    @FXML
    void ProfileBt(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("StudentProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void homeBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        search = searchtf.getText();
        Parent root = FXMLLoader.load(getClass().getResource("studentSearchFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    ObservableList<studenthomeshow> list2 = FXCollections.observableArrayList();
    ObservableList<homepinfaculty> list3 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String sql = "SELECT * FROM booking WHERE sid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sid1);

            ResultSet rsz = pst.executeQuery();


            while (rsz.next()) {
                list2.add(new studenthomeshow(rsz.getString("tname"), rsz.getString("stime"),rsz.getString("endtime"), rsz.getString("status"), rsz.getString("date")));
            }

            conn.close();
            rsz.close();
        } catch (Exception e) {
        }
        faname.setCellValueFactory(new PropertyValueFactory<studenthomeshow, String>("fname"));
        stime.setCellValueFactory(new PropertyValueFactory<studenthomeshow, String>("start"));
        endtime.setCellValueFactory(new PropertyValueFactory<studenthomeshow, String>("end"));
        status.setCellValueFactory(new PropertyValueFactory<studenthomeshow, String>("status"));
        date.setCellValueFactory(new PropertyValueFactory<studenthomeshow, String>("date"));


        studenthometable.setItems(list2);


        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String sql = "SELECT * FROM pin WHERE sid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sid1);

            ResultSet rsz = pst.executeQuery();


            while (rsz.next()) {
                list3.add(new homepinfaculty(rsz.getString("tname"), rsz.getString("coursename"),rsz.getString("section")));
            }

            conn.close();
            rsz.close();
        } catch (Exception e) {
        }


        coursename.setCellValueFactory(new PropertyValueFactory<homepinfaculty, String>("coursename"));
        section.setCellValueFactory(new PropertyValueFactory<homepinfaculty, String>("section"));
        tname.setCellValueFactory(new PropertyValueFactory<homepinfaculty, String>("tname"));



        pinfaculty.setItems(list3);
        pinfaculty.setOnMouseClicked(e->{


                    events();
                }

        );










        }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    private void events(){
        for(homepinfaculty solt  : pinfaculty.getSelectionModel().getSelectedItems()){
            for(int i=1;i<=1;i++) {
                search = solt.getTname();
                sec = solt.getSection();
                c_name = solt.getCoursename();

            }

        }

    }
    @FXML
    void removeBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        System.out.println(search+ sec + c_name+sid1);

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, password);
//        String q = "DELETE FROM `pin` WHERE tname=?  and sid=?";
//        PreparedStatement p=conn.prepareStatement(q);
//
//        p.setString(1, search);
//        p.setString(2, sid1);
////        p.setString(3, sec);
////        p.setString(4, sid1);
//
//        p.executeQuery();
//        p.close();
//        conn.close();


        String query = "DELETE FROM pin WHERE  sid=? AND tname=? AND coursename=? AND section=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, sid1);
        pst.setString(2, search);
        pst.setString(3, c_name);
        pst.setString(4, sec);


        pst.executeUpdate();
        conn.close();

        Window owner = removebt.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!!!", "Pinned Faculty removed  Successfully.");
        Parent root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void add(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("studentSearchFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void chatBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chatRoom.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void logoutBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
