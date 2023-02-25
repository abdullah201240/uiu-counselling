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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminViewAllStudentsController implements Initializable {

    @FXML
    private Button AddFacultyButt;

    @FXML
    private Button AddStudentButt;
    @FXML
    private Button homebt;

    @FXML
    private Pane PnlStatus;

    @FXML
    private Button ViewFacultyButt;

    @FXML
    private Button ViewStudentButt;

    @FXML
    private Button addCoursebt;

    @FXML
    private Button allCoursesbt;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;

    @FXML
    private Button searchbt;

    @FXML
    private TableColumn<adminViewAllStudentTable, String> depttf;

    @FXML
    private TableColumn<adminViewAllStudentTable, String> emailtf;

    @FXML
    private TableColumn<adminViewAllStudentTable, String> idtf;
    @FXML
    private TableColumn<adminViewAllStudentTable, String> nametf;


    @FXML
    private TableView<adminViewAllStudentTable> viewStudentTable;

    @FXML
    void homebt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void AddCourseBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddCourse.fxml"));
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
    void allCoursesbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllCourse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchBt(ActionEvent event) {

    }
    ObservableList<adminViewAllStudentTable> list1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            ResultSet rs =conn.createStatement().executeQuery("SELECT * FROM student");

            while(rs.next()){
                list1.add(new adminViewAllStudentTable(rs.getString("id"),rs.getString("name"),rs.getString("email"),rs.getString("department")));
            }
            conn.close();
            rs.close();
        }
        catch(Exception e){


        }



        idtf.setCellValueFactory(new PropertyValueFactory<adminViewAllStudentTable,String>("id"));
        nametf.setCellValueFactory(new PropertyValueFactory<adminViewAllStudentTable,String>("name"));
        emailtf.setCellValueFactory(new PropertyValueFactory<adminViewAllStudentTable,String>("email"));
        depttf.setCellValueFactory(new PropertyValueFactory<adminViewAllStudentTable,String>("dept"));
//        course.setCellValueFactory(new PropertyValueFactory<adminallfacultytable,String>("course"));
        viewStudentTable.setItems(list1);

    }

}
