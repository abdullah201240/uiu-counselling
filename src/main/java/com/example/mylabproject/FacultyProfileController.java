package com.example.mylabproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.mylabproject.StudentSearchFacultyController.s;

public class FacultyProfileController extends FacultyLoginController  implements Initializable {

    @FXML
    private TextField emailtf;

    @FXML
    private TextField idtf;

    @FXML
    private Circle imageview;

    @FXML
    private Button img;

    @FXML
    private Button img1;

    @FXML
    private TextField nametf;

    @FXML
    private TextField roomtf;
    public static String imagepath;
    public static Blob z;
    public static int za;


    @FXML
    void changepassbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("facultyConfirmPass.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void cslotsbt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Counselling slots.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void homebt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FacultyHomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void save(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/counselling";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String  sql="UPDATE `teacher` SET `Image`=? WHERE id=?";
        PreparedStatement pst=conn.prepareStatement(sql);
        InputStream stream = new FileInputStream(imagepath);
        pst.setBlob(1,stream);
        pst.setString(2,id);

        pst.executeUpdate();
        pst.close();
        conn.close();
        Window owner = img.getScene().getWindow();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Congratulations!!!", "Image Change  Successfully.");

    }

    @FXML
    void set(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            imagepath = file.getPath();

            Image images = new Image(imagepath);
            imageview.setFill(new ImagePattern(images));


        }

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
            String  sql="Select Image, LENGTH(Image) as len from teacher where id = ?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){
                z=rs.getBlob(1);
                za=rs.getInt(2);



            }
            if(za>0){
                InputStream l = z.getBinaryStream();
                Image images = new Image(l);
                imageview.setFill(new ImagePattern(images));

            }
            else{

            }

            conn.close();
            rs.close();
        }
        catch(Exception e){


        }



        emailtf.setText(email);
        nametf.setText(name);
        idtf.setText(id);
        roomtf.setText(room);

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
