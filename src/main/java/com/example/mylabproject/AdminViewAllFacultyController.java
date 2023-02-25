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


public class AdminViewAllFacultyController implements Initializable  {

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
    private TableView<adminallfacultytable> alfaculty;

    @FXML
    private Button allCoursesbt;
    @FXML
    private Button homebt;


    @FXML
    private TableColumn<adminallfacultytable, String> dept;


    @FXML
    private TableColumn<adminallfacultytable, String> email;
    @FXML
    private TableColumn<adminallfacultytable, String> id;


    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;

    @FXML
    private TableColumn<adminallfacultytable, String> name;
    ObservableList<adminallfacultytable> list1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            ResultSet rs =conn.createStatement().executeQuery("SELECT * FROM teacher order by pid");

            while(rs.next()){
                list1.add(new adminallfacultytable(rs.getString("id"),rs.getString("name"),rs.getString("email"),rs.getString("department")));
            }
            conn.close();
            rs.close();
        }
        catch(Exception e){


        }



        id.setCellValueFactory(new PropertyValueFactory<adminallfacultytable,String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<adminallfacultytable,String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<adminallfacultytable,String>("email"));
        dept.setCellValueFactory(new PropertyValueFactory<adminallfacultytable,String>("dept"));

        alfaculty.setItems(list1);
    }

    @FXML
    private Button searchbt;

    @FXML
    void AddCourseBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminAddCourse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
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
    void ViewStudentBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllStudent.fxml"));
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

}
