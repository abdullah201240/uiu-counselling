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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminAddCourseController implements Initializable {
    public static String dept;

    @FXML
    private Button AddFacultyButt;

    @FXML
    private Button AddStudentButt;

    @FXML
    private Pane PnlStatus;

    @FXML
    private Button ViewFacultyButt;

    @FXML
    private Button ViewStudentButt;

    @FXML
    private Button addCoursebt;

    @FXML
    private Button addbt;

    @FXML
    private Button allCoursesbt;

    @FXML
    private TextField cidtf;

    @FXML
    private TextField cnametf;

    @FXML
    private ComboBox deptcmb;

    @FXML
    private PasswordField fidtf;

    @FXML
    private TextField fnametf;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;
    @FXML
    private TextField sectiontf;
    @FXML
    private Button homebt;

    @FXML
    void homebt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void AddFacultyBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void AddStudentBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddStudents.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ViewFacultyBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ViewStudentBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllStudent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void addBt(ActionEvent event) throws ClassNotFoundException, SQLException {
        String cid=cidtf.getText();
        String cname=cnametf.getText();
        String tname=fnametf.getText();
        String tid=fidtf.getText();
        String section=sectiontf.getText();


        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);

        String s="SELECT * FROM `course` WHERE tid=? and section =? AND cid=?";
        PreparedStatement psz = conn.prepareStatement(s);
        psz.setString(1, tid);
        psz.setString(2, section);
        psz.setString(3, cid);
        ResultSet rs = psz.executeQuery();
        if(rs.next()){
            Window owner = addbt.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "Error", " This section id already Added.");


        }
        else{
            String sql="INSERT INTO `course`( `cid`, `cname`, `tname`,tid,  `department`,section) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,cid);
            ps.setString(2, cname);
            ps.setString(3, tname);
            ps.setString(4, tid);
            ps.setString(5, dept);
            ps.setString(6, section);

            ps.executeUpdate();

            conn.close();
            cnametf.setText("");
            cidtf.setText("");
            fidtf.setText("");
            fnametf.setText("");
            sectiontf.setText("");

            Window owner = addbt.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!!!", "Added Successfully.");



        }





    }

    @FXML
    void allCoursesbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllCourse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deptBt(ActionEvent event) {
        dept=deptcmb.getSelectionModel().getSelectedItem().toString();


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list1 = FXCollections.observableArrayList("CSE","EEE","BBA");
        deptcmb.setItems(list1);
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
