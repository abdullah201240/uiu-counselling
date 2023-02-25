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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewAllCoursesController  implements Initializable {


    @FXML
    private Button AddFacultyButt;
    @FXML
    private Button homebt;

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
    private Button allCoursesbt;



    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;



    @FXML
    private Button searchbt;
    @FXML
    private TableView<adminviewallcourses> allcourse;

    @FXML
    private TableColumn<adminviewallcourses, String> caname;

    @FXML
    private TableColumn<adminviewallcourses, String> cid;

    @FXML
    private TableColumn<adminviewallcourses, String> dept;

    @FXML
    private TableColumn<adminviewallcourses, String> fname;
    @FXML
    private TableColumn<adminviewallcourses, String> section;
    ObservableList<adminviewallcourses> list1 = FXCollections.observableArrayList();



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
    void ViewStudentBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminViewAllStudent.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void searchBt(ActionEvent event) {

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
            ResultSet rs =conn.createStatement().executeQuery("select * from course");
            while(rs.next()){
                list1.add(new adminviewallcourses(rs.getString("cid"),rs.getString("cname"),rs.getString("tname"),rs.getString("department"),rs.getString("section")));
            }
            conn.close();
            rs.close();
        }
        catch(Exception e){


        }


        caname.setCellValueFactory(new PropertyValueFactory<adminviewallcourses,String>("cname"));
        cid.setCellValueFactory(new PropertyValueFactory<adminviewallcourses,String>("cid"));
        fname.setCellValueFactory(new PropertyValueFactory<adminviewallcourses,String>("fname"));
        dept.setCellValueFactory(new PropertyValueFactory<adminviewallcourses,String>("dept"));
        section.setCellValueFactory(new PropertyValueFactory<adminviewallcourses,String>("section"));

        allcourse.setItems(list1);
    }
}
