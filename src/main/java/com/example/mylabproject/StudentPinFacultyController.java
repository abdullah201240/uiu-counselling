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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.mylabproject.StudentHomeController.search;
import static com.example.mylabproject.StudentHomeController.z;
import static com.example.mylabproject.StudentLoginController.sid1;

public class StudentPinFacultyController implements Initializable {
    public static  String tname;
    public static  String section;
    public static  String coursename;


    @FXML
    private TableColumn<facaltypintable, String> coursrtf;

    @FXML
    private Button deletebt;

    @FXML
    private TableColumn<facaltypintable, String> fnametf;

    @FXML
    private Button profilebt;

    @FXML
    private Button searchbt;

    @FXML
    private TableView<facaltypintable> searchresulttable;

    @FXML
    private TextField searchtf;

    @FXML
    private TableColumn<facaltypintable, String> sectiontf;

    @FXML
    void delete(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String sql="INSERT INTO `pin`(`tname`, `coursename`, `section`, `sid`) VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,tname);
        ps.setString(2,coursename);
        ps.setString(3, section);
        ps.setString(4, sid1);
        ps.executeUpdate();

        conn.close();
        Parent root = FXMLLoader.load(getClass().getResource("StudentHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void drgdt(MouseEvent event) {

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
    void searchBt(ActionEvent event) {

    }
    ObservableList<facaltypintable> list1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String  sql="Select * from course where tname = ?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, z);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                list1.add(new facaltypintable(rs.getString("tname"),rs.getString("cname"),rs.getString("section")));
            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }

        fnametf.setCellValueFactory(new PropertyValueFactory<facaltypintable,String>("tname"));
        coursrtf.setCellValueFactory(new PropertyValueFactory<facaltypintable,String>("coursename"));
        sectiontf.setCellValueFactory(new PropertyValueFactory<facaltypintable,String>("section"));


        searchresulttable.setItems(list1);
        searchresulttable.setOnMouseClicked(e->{


                    events();
                }

        );

    }
    private void events(){
        for(facaltypintable solt  : searchresulttable.getSelectionModel().getSelectedItems()){
            for(int i=1;i<=1;i++)
                 tname =solt.getTname();
            coursename=solt.getCoursename();
            section=solt.getSection();


        }

    }


}
