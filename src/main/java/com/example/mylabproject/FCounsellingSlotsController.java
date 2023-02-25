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
import java.util.Random;
import java.util.ResourceBundle;

public class FCounsellingSlotsController extends FacultyLoginController implements Initializable {

    String day, shour, smin, ehour, emin;
    public static String da;
    public static String st;
    public static int bid;
    public static int temp;


    @FXML
    private TableView<facultysoltshow> allshow;
    @FXML
    private TableColumn<facultysoltshow, String> dayss;



    @FXML
    private TableColumn<facultysoltshow, String> end;



    @FXML
    private TableColumn<facultysoltshow, String> start;
@FXML
    private Button addbt;

    @FXML
    private ComboBox cmb;

    @FXML
    private ComboBox daycmb;

    @FXML
    private ComboBox endhour;

    @FXML
    private ComboBox endmin;

    @FXML
    private ComboBox starthour;

    @FXML
    private ComboBox startmin;
    @FXML
    private Button timeadd;

    ObservableList<facultysoltshow> list1 = FXCollections.observableArrayList();

    @FXML
    void addBt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {


        if(day.equals("SATURDAY"))
            temp=0;
        if(day.equals("SUNDAY"))
            temp=1;
        if(day.equals("MONDAY"))
            temp=2;
        if(day.equals("TUESDAY"))
            temp=3;
        if(day.equals("WEDNESDAY"))
            temp=4;



        String stime=shour+":"+smin;
        String etime=ehour+":"+emin;
        Random random = new Random();
        int x = random.nextInt(5000);

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String sql="INSERT INTO `time_slot`(id,`fname`, `fid`, `day`,  `start`,end,count) VALUES (?, ?, ?, ?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,x);
        ps.setString(2,name);
        ps.setString(3,id);
        ps.setString(4,day);
        ps.setString(5, stime);
        ps.setString(6, etime);
        ps.setInt(7, temp);

        ps.executeUpdate();

        conn.close();
        Window owner = addbt.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!",
                "Data Added Succesfully");
        Parent root = FXMLLoader.load(getClass().getResource("Counselling slots.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void backBt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FacultyProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void dayCMB(ActionEvent event) {
        day = daycmb.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void endHour(ActionEvent event) {
        ehour = endhour.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void endMin(ActionEvent event) {
        emin = endmin.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void startHour(ActionEvent event) {
        shour = starthour.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void startMin(ActionEvent event) {
        smin = startmin.getSelectionModel().getSelectedItem().toString();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> days = FXCollections.observableArrayList("SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY");
        ObservableList<String> starthours = FXCollections.observableArrayList("08","09","10","11","12","13",
                "14","15","16","17");
        ObservableList<String> startmins = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33",
                "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        ObservableList<String> endhours = FXCollections.observableArrayList("08","09","10","11","12","13",
                "14","15","16","17");
        ObservableList<String> endmins = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33",
                "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        daycmb.setItems(days);
        starthour.setItems(starthours);
        startmin.setItems(startmins);
        endhour.setItems(endhours);
        endmin.setItems(endmins);

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);

            String query = "SELECT * FROM `time_slot` WHERE fid=?";
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list1.add(new facultysoltshow(rs.getString("day"),rs.getString("start"),rs.getString("end")));
            }
            conn.close();
            rs.close();
        }
        catch(Exception e){


        }
        dayss.setCellValueFactory(new PropertyValueFactory<facultysoltshow,String>("day"));
        start.setCellValueFactory(new PropertyValueFactory<facultysoltshow,String>("stime"));
        end.setCellValueFactory(new PropertyValueFactory<facultysoltshow,String>("etime"));


        allshow.setItems(list1);
        allshow.setOnMouseClicked(e->{


                events();
    }

                );





    }
    private void events(){
        for(facultysoltshow solt  : allshow.getSelectionModel().getSelectedItems()){
            for(int i=1;i<=1;i++)
            da=  solt.getDay();
            st=  solt.getStime();

        }

    }
    @FXML
    void deletebt(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
//        System.out.println(id);
//        System.out.println(da);
//        System.out.println(st);
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);

        String q = "SELECT * FROM `time_slot` WHERE fid=? and  day=? and start=?";
        PreparedStatement ps=conn.prepareStatement(q);
        ps.setString(1, id);
        ps.setString(2, da);
        ps.setString(3, st);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            bid=rs.getInt(1);

        }



        String query = "DELETE FROM `time_slot` WHERE id=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, bid);


        pst.executeUpdate();
  conn.close();
        Window owner = timeadd.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!",
                "Data Delete Succesfully");
        Parent root = FXMLLoader.load(getClass().getResource("Counselling slots.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



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
