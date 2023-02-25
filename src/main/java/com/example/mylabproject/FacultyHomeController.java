package com.example.mylabproject;

import com.almasb.fxgl.net.Server;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.mylabproject.StudentFacultyProfileController.*;
import static com.example.mylabproject.FacultyLoginController.id;


public class FacultyHomeController implements Initializable {
    public static int s;



    @FXML
    private Button acceptbt;

    @FXML
    private Button cancelbt;
    @FXML
    private TableColumn<fbookingtable, String> date;
    @FXML
    private TableView<fbookingtable> fbookingtable;
    @FXML
    private TableColumn<fbookingtable, String> sid;

    @FXML
    private TableColumn<fbookingtable, String> sname;

    @FXML
    private TableColumn<fbookingtable, String> status;

    @FXML
    private TableColumn<fbookingtable, String> stime;
    @FXML
    private TableColumn<fbookingtable, String> etime;
    @FXML
    private TableColumn<fbookingtable, Integer> idbook;

    @FXML
    private Pane PnlStatus;

    @FXML
    private Label lblStatus;

    @FXML
    private Button profilebt;

    @FXML
    void profileBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FacultyProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    ObservableList<fbookingtable> list1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String sql = "SELECT * FROM booking WHERE tid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();


            while (rs.next()) {
                if(rs.getString(9).equals("Cancel")){

                }
                else{
                    list1.add(new fbookingtable(rs.getString("sid"), rs.getString("sname"),rs.getString("stime"),  rs.getString("endtime"), rs.getString("status"), rs.getInt(10), rs.getString("date")));
                }

            }

            conn.close();
            rs.close();
        } catch (Exception e) {


        }
        sid.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("sid"));
        sname.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("sname"));
        stime.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("stime"));
        etime.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("etime"));
        status.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("status"));
        idbook.setCellValueFactory(new PropertyValueFactory<fbookingtable,Integer>("idbook"));
        date.setCellValueFactory(new PropertyValueFactory<fbookingtable, String>("date"));


        fbookingtable.setItems(list1);
        fbookingtable.setOnMouseClicked(e->{


                    events();
                }

        );

    }
    private void events(){
        for(fbookingtable solt  : fbookingtable.getSelectionModel().getSelectedItems()){
            for(int i=1;i<=1;i++)
                s=solt.getIdbook();





        }

    }

    @FXML
    void acceptBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
  String za="Accept";
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, password);
        String spl = "update booking set status= ? where id= ?";
        PreparedStatement ps = conn.prepareStatement(spl);
        ps.setString(1, za);
        ps.setInt(2, s);
        ps.executeUpdate();
        ps.close();
        conn.close();

//        String sa = "UPDATE `booking` SET `status`=?  WHERE id=?";
//        PreparedStatement pst = conn.prepareStatement(sa);
//        System.out.println(za);
//        System.out.println(s);
//        pst.setString(1,za);
//        pst.setInt(2, s);
//        pst.executeQuery();


        Parent root = FXMLLoader.load(getClass().getResource("FacultyHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void cancelBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String za="Cancel";
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, password);
        String spl = "update booking set status= ? where id= ?";
        PreparedStatement ps = conn.prepareStatement(spl);
        ps.setString(1, za);
        ps.setInt(2, s);
        ps.executeUpdate();
        ps.close();
        conn.close();
        Parent root = FXMLLoader.load(getClass().getResource("FacultyHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void logoutBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("facultyLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}

