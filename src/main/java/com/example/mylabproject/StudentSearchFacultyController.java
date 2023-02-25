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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static com.example.mylabproject.StudentHomeController.search;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StudentSearchFacultyController  implements Initializable {
    public static String s;

    @FXML
    private TableColumn<studentsearchfaculty, String> depttf;

    @FXML
    private TableColumn<studentsearchfaculty, String> emailtf;

    @FXML
    private TableColumn<studentsearchfaculty, String> fnametf;
    @FXML
    private TableColumn<studentsearchfaculty, String> tid;

    @FXML
    private TableView<studentsearchfaculty> searchresulttable;
    @FXML
    private Button profilebt;

    @FXML
    private Button searchbt;

    @FXML
    private TextField searchtf;
    @FXML
    private Button deletebt;
    @FXML
    void drgdt(MouseEvent event) {


    }
    private void events(){
        for(studentsearchfaculty solt  : searchresulttable.getSelectionModel().getSelectedItems()){
           for(int i=1;i<=1;i++)
               s=  solt.getId();


        }

    }
    @FXML
    void delete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentFacultyProfile.fxml"));
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
    void profileBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentSearchFaculty.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    ObservableList<studentsearchfaculty> list1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="Select * from teacher where name = ?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, search);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                list1.add(new studentsearchfaculty(rs.getString("name"),rs.getString("email"),rs.getString("department"),rs.getString("id")));
            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }

        fnametf.setCellValueFactory(new PropertyValueFactory<studentsearchfaculty,String>("fname"));
        emailtf.setCellValueFactory(new PropertyValueFactory<studentsearchfaculty,String>("email"));
        depttf.setCellValueFactory(new PropertyValueFactory<studentsearchfaculty,String>("dept"));
        tid.setCellValueFactory(new PropertyValueFactory<studentsearchfaculty,String>("id"));

        searchresulttable.setItems(list1);
        searchresulttable.setOnMouseClicked(e->{


                    events();
                }

        );
    }
}
